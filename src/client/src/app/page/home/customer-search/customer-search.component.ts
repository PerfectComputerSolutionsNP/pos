import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {Customer} from '../../../model/customer.model';
import {config} from '../../../service/config.service';
import {SearchBarComponent} from '../../../partial/search-bar/search-bar.component';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {CustomerCreationFormComponent} from '../../customers/customer-creation-form/customer-creation-form.component';

@Component({
  selector: 'app-customer-search',
  templateUrl: './customer-search.component.html',
  styleUrls: ['./customer-search.component.scss']
})
export class CustomerSearchComponent implements OnInit {

  @ViewChild('searchBar')
  searchBar : SearchBarComponent<Customer>;

  @Output()
  eventEmitter : EventEmitter<Customer> = new EventEmitter<Customer>();

  url         : string = `${config.api.endpoint.customer}/search`;
  paramName   : string = "param";
  placeholder : string = "Search for customers";
  customers   : Array<Customer>;

  constructor(private modalService: NgbModal) { }

  ngOnInit() {
  }

  setCurrentCustomer(customer : Customer) {

    this.eventEmitter.emit(customer);
  }

  setCustomerList(customers : Array<Customer>) {

    this.customers = customers;
  }

  close() {

    this.eventEmitter.emit(null);
  }

  showCreateCustomerForm() {

    let modalRef = this.modalService.open(CustomerCreationFormComponent);

    modalRef.componentInstance.eventEmitter.subscribe(customer => {

      this.eventEmitter.emit(customer);

      modalRef.close();
    })
  }

}
