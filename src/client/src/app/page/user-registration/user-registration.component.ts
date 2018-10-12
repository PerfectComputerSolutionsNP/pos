import { Component, OnInit }       from '@angular/core';
import { User }                    from "../../model/user.model";
import { APIService }              from "../../api.service";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { config }                  from "../../service/config.service";

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {

  newUser: any;
  // // headers= new HttpHeaders().set('Authorization', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUzOTA2NTg5MiwiaWF0IjoxNTM4NDYxMDkyfQ.LdK2UHQ6rNXWbqee8GLFPZJ5F3pnVxQMN6A_ZL4xzMnQ-NMJtkbUezGqVQGyd36N36XaeRgKtoXgyXtWk35g8A');
  constructor(private svc:APIService, private http: HttpClient) {}

  createUser(data : User) {

    let obs = this.newUser = this.http.post( config.api.endpoint.users, data, {});

    obs.subscribe( (response) => {

      console.log("We here boys");
      console.log(response);
      alert("success");
      },

      (error) => {

      console.log(error);
      alert("error")
    });

  }


  model : any = {};

  onSubmit() {

    const data    : User = {
      "id"        : null,
      "username"  : this.model.userName,
      "firstname" : this.model.firstName,
      "lastname"  : this.model.lastName,
      "password"  : this.model.password,
      "email"     : this.model.email,
      "enabled"   : true,
      "lastPasswordResetDate": "2016-01-01T00:00:00.000+0000",
      "authorities" : [
        {
          "id": 1
        }
      ]
    };

    this.createUser(data);
  }

  ngOnInit() {
  }

}
