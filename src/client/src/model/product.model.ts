import {Category} from "./category.model";

class Product {

  private id       : number;
  private name     : string;
  private category : Category;
  private cost     : number;

  // Getters and setters for product below this line.

  public setId(id) {

    this.id = id;
  }

  public setName(name) {

    this.name = name;

  }

  public setCost(cost) {
    this.cost = cost;
  }

  public getId(){
    return this.id;
  }

  public getName() {
    return this.name;
  }

  public getCost() {
    return this.cost;
  }

}

