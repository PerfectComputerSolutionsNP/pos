import {Authority} from "./authority.model";

// let bcrypt: { genSaltSync; genSalt; hashSync; hash; compareSync; compare; getRounds };
//
// let hash = bcrypt.hash(this.password);
//
// bcrypt.genSaltSync(10, function (err, salt) {
//   bcrypt.hash(this.password,salt,function(err, hash){});


export interface User {

  id          : number;
  username    : string;
  password    : string;
  firstname   : string;
  lastname    : string;
  email       : string;
  enabled     : boolean;
  lastPasswordResetDate;


  authorities: Array<Authority>
}
