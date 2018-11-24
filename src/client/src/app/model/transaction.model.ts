import {ModelEntity} from './entity.model';
import {Item} from './item.model';
import {User} from './user.model';
import {Customer} from './customer.model';

export class Transaction extends ModelEntity<Transaction> {

  public subtotalString : string      = null;
  public items          : Array<Item> = null;
  public cost           : number      = null;
  public customer       : Customer    = null;
  public customerId     : number      = null;
  public userId         : number      = null;
  public totalString    : string      = null;
  public pickupTime     : Date        = null;
  public taxRate        : number      = null;
  public user           : User        = null;
  public total          : number      = null;
  public subTotal       : number      = null;
  public notifyCustomer : boolean     = null;

  constructor() {
    super()
  }

  clone(transaction: Transaction): Transaction {

    let t = new Transaction();

    // TODO - Implement

    return t;
  }

  getDollars() : number {

    return this.items
      .map(item => item.getDollars())
      .reduce((total, sum) => total + sum);
  }

  getSubtotal() : number {

    return this.getDollars();
  }

  getTotal() {

    return (1 + (this.taxRate / 100)) * this.getDollars();
  }
}
