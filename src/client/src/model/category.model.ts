
export class Category {
  private id  : number;
  private name: string;

  public getId() {

    return this.id;
  }

  public getName() {

    return this.name;
  }

  public setId(id){

    this.id = id;
  }

  public setName(name) {

    this.name = name;
  }

}
