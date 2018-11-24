import {ModelEntity} from './entity.model';

export class Category extends ModelEntity<Category> {

   public name : string;

   constructor() {
     super()
   }

  clone(category: Category): Category {

    let c = new Category();

    c.id   = category.id;
    c.name = category.name;

    return c;
  }

}
