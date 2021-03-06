import {ModelEntity} from './entity.model';
import {Product} from './product.model';

export class Item extends ModelEntity<Item> {

  quantity   : number;
  product    : Product;
  weight     : number;
  cost       : number;
  costString : string;

  clone(entity: Item): Item {

    // TODO - Implement

    return undefined;
  }

  getCost() : number {

    return this.product.weighted ?
      this.weight   * this.product.cents :
      this.quantity * this.product.cents;
  }

  getUntaxedDollars() : number {

    return this.getCost() / 100;
  }

  getTaxedDollars(taxRate : number) {

    let untaxed : number = this.getUntaxedDollars();

    return this.product.taxed ?
      (1 + (taxRate / 100)) * untaxed :
      untaxed;
  }

  getTax(taxRate : number) {

    return this.getTaxedDollars(taxRate) - this.getUntaxedDollars();
  }
}
