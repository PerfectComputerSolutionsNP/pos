import {Category} from "./category.model";
import {ModelEntity} from './entity.model';

export class Product extends ModelEntity<Product> {

  constructor() {
    super()
  }

  public name        : string;
  public taxed       : boolean;
  public weighted    : boolean;
  public cents       : number;
  public category    : Category;
  public description : string;
  public costString  : string;

  clone(product : Product): Product {

    let p : Product = new Product();

    p.id          = product.id;
    p.name        = product.name;
    p.cents       = product.cents;
    p.weighted    = product.weighted;
    p.taxed       = product.taxed;
    p.category    = product.category;
    p.description = product.description;
    p.costString  = product.costString;

    return p;
  }

}

