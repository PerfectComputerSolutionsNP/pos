import {Authority} from "./authority.model";
import {ModelEntity} from './entity.model';

export class User extends ModelEntity<User> {

  public id          : number;
  public  username    : string;
  public password    : string;
  public firstname   : string;
  public lastname    : string;
  public email       : string;
  public enabled     : boolean;
  public lastPasswordResetDate : Date;

  public authorities: Array<Authority>;

  clone(entity: User): User {

    let user = new User();

    user.id                    = entity.id;
    user.username              = entity.username;
    user.password              = entity.password;
    user.firstname             = entity.firstname;
    user.lastname              = entity.lastname;
    user.email                 = entity.email;
    user.enabled               = entity.enabled;
    user.lastPasswordResetDate = entity.lastPasswordResetDate;
    user.authorities           = entity.authorities;

    return user;
  }
}
