import {ModelEntity} from './entity.model';

export class Customer extends ModelEntity<Customer> {

  public firstname : string;
  public lastname  : string;
  public email     : string;

  clone(entity: Customer): Customer {

    let customer : Customer = new Customer();

    customer.id        = entity.id;
    customer.firstname = entity.firstname;
    customer.lastname  = entity.lastname;
    customer.email     = entity.email;

    return customer;
  }


}
