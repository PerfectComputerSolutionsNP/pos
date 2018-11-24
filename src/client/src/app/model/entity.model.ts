export abstract class ModelEntity<T> {

  public id : number;

  abstract clone(entity : T) : T;
}
