import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Customer} from '../../../model/customer.model';
import {ApiService} from '../../../service/api.service';
import {UtilityService} from '../../../service/utility.service';
import {config} from '../../../service/config.service';

@Component({
  selector: 'app-customer-creation-form',
  templateUrl: './customer-creation-form.component.html',
  styleUrls: ['./customer-creation-form.component.scss']
})
export class CustomerCreationFormComponent extends Customer implements OnInit {

  @Input()  customer : Customer;
  @Output() eventEmitter: EventEmitter<any> = new EventEmitter();

  constructor(private api : ApiService) {
    super();
  }

  ngOnInit() {

    if (this.customer) {
      this.id        = this.customer.id;
      this.firstname = this.customer.firstname;
      this.lastname  = this.customer.lastname;
      this.email     = this.customer.email;
    }
  }

  save() {

    let customer = this.clone(this);

    let success = (result) => this.close(result.entity);
    let error   = UtilityService.logError;
    let always  = this.close;

    if (!customer.id) {

      this.api.httpPost(config.api.endpoint.customer, customer)
        .then(success)
        .catch(error)

    } else {

      this.api.httpPut(config.api.endpoint.customer, customer.id, customer)
        .then(success)
        .catch(error)
    }

  }

  deleteCustomer(id : number) {

    this.api.httpDelete(config.api.endpoint.customer, id)
      .then((result) => this.close(null))
      .catch(UtilityService.logError);
  }

  close(customer : Customer) {

    this.eventEmitter.emit(customer);
  }

}
