import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Transaction} from '../../../model/transaction.model';
import {Customer} from '../../../model/customer.model';
import {ApiService} from '../../../service/api.service';
import {config} from '../../../service/config.service';
import {UtilityService} from '../../../service/utility.service';
import {User} from '../../../model/user.model';
import {NgbActiveModal, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ProductCreationFormComponent} from '../../inventory/product-creation-form/product-creation-form.component';
import {AlertDialogComponent} from '../../../partial/alert-dialog/alert-dialog.component';
import {CustomerSearchComponent} from '../customer-search/customer-search.component';
import {AuthenticationService} from '../../../service/authentication.service';

@Component({
  selector: 'app-complete-transaction',
  templateUrl: './complete-transaction.component.html',
  styleUrls: ['./complete-transaction.component.scss']
})
export class CompleteTransactionComponent implements OnInit {

  // https://cuppalabs.github.io/components/datepicker/#Settings

  @Input()  transaction  : Transaction;
  @Output() eventEmitter : EventEmitter<any> = new EventEmitter();

  pickupLater : boolean = false;
  pickupDate  : Date    = new Date();
  customer    : Customer;

  dateSetting = {
    timePicker: true,
    format: 'MMM, dd yyyy hh:mm a'
  };

  constructor(
    private api : ApiService,
    private modalService: NgbModal) { }

  ngOnInit() {
  }

  setCustomer() {

    let modalRef = this.modalService.open(CustomerSearchComponent);

    modalRef.componentInstance.eventEmitter.subscribe( (customer : Customer) => {

      if (customer)
        this.customer = customer;

      modalRef.close();
    });

  }

  removeCustomer() {

    this.customer = null;
  }

  finalizeTransaction() {

    if (this.pickupLater)
      this.transaction.pickupTime = this.pickupDate;

    if (this.customer) {

      let customer = new Customer();

      customer.id = this.customer.id;

      this.transaction.customer       = customer;
      this.transaction.notifyCustomer = true;
    }

    let details = AuthenticationService.getCurrentUserDetails();
    let user    = new User();

    user.id = details.id;

    this.transaction.user = user;

    this.api.httpPost(config.api.endpoint.transaction, this.transaction)
      .then((response) => this.finalizeTransactionCallback(response, this.modalService))
      .catch(UtilityService.logError)
  }

  finalizeTransactionCallback(response : any, modalService : NgbModal) {

    let modalRef = modalService.open(AlertDialogComponent);

    modalRef.componentInstance.headingText     = "Transaction Complete!";
    modalRef.componentInstance.messageText     = "Your transaction is being processed";

    modalRef.componentInstance.eventEmitter.subscribe(result => {

      modalRef.close();

      this.eventEmitter.emit("complete");
    })
  }

  cancel() {

    this.eventEmitter.emit(null);
  }
}
