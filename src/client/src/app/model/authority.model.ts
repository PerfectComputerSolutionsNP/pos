import {User} from "./user.model";

export interface Authority {

  id    : number
  name? : string
  users?: Set<User>
}

