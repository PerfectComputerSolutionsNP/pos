import {Component, Input, OnInit} from '@angular/core';
import { User }                    from "../../../model/user.model";
import { ApiService }              from "../../../service/api.service";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { config }                  from "../../../service/config.service";
import {Authority}                 from '../../../model/authority.model';
import {UtilityService} from '../../../service/utility.service';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.scss']
})
export class UserRegistrationComponent extends User implements OnInit {

  @Input() user : User;

  newUser: any;
  authorities : Array<Authority>;

  getAuthority() {

    let auths : Array<Authority> = [
      {
        id : 1,
        name : 'ROLE_ADMIN'
      },
      {

        id : 2,
        name : 'ROLE_USER'
      }
    ];

    this.authorities = auths;
  }

  // // headers= new HttpHeaders().set('Authorization', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUzOTA2NTg5MiwiaWF0IjoxNTM4NDYxMDkyfQ.LdK2UHQ6rNXWbqee8GLFPZJ5F3pnVxQMN6A_ZL4xzMnQ-NMJtkbUezGqVQGyd36N36XaeRgKtoXgyXtWk35g8A');
  constructor(private api: ApiService, private http: HttpClient) {
    super();
  }

  onSubmit() {

    let user : User = this.clone(this);

    this.api.httpPost(config.api.endpoint.users, user)
      .then(response => console.log(response))
      .catch(UtilityService.logError)
  }

  ngOnInit() {

    if (this.user) {
      this.id                    = this.user.id;
      this.username              = this.user.username;
      this.firstname             = this.user.firstname;
      this.lastname              = this.user.lastname;
      this.password              = this.user.password;
      this.email                 = this.user.email;
      this.enabled               = this.user.enabled;
      this.lastPasswordResetDate = this.user.lastPasswordResetDate;
      this.authorities           = this.user.authorities;
    }

    this.getAuthority();
  }

}
