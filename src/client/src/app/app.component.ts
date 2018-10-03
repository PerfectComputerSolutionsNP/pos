import { Component } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { APIService } from "./api.service";
import { User } from '../model/user.model';
import {Authority} from "../model/authority.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'client';

  posts: any;
  newUser: any;
  // // headers= new HttpHeaders().set('Authorization', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUzOTA2NTg5MiwiaWF0IjoxNTM4NDYxMDkyfQ.LdK2UHQ6rNXWbqee8GLFPZJ5F3pnVxQMN6A_ZL4xzMnQ-NMJtkbUezGqVQGyd36N36XaeRgKtoXgyXtWk35g8A');
  constructor(private svc:APIService, private http: HttpClient) {
  }

  getUsers() {
    let headers= new HttpHeaders().set('Authorization', 'Bearer ' + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUzOTA2NTg5MiwiaWF0IjoxNTM4NDYxMDkyfQ.LdK2UHQ6rNXWbqee8GLFPZJ5F3pnVxQMN6A_ZL4xzMnQ-NMJtkbUezGqVQGyd36N36XaeRgKtoXgyXtWk35g8A");
    let obs = this.posts = this.http.get('http://api.pos.jabaridash.com:8080/users', {headers:headers} );
    obs.subscribe((response) => console.log(response));
    console.log("We get here tho");

  }

  createUser(data : User) {


    let headers = new HttpHeaders().set('Authorization', 'Bearer ' + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUzOTA2NTg5MiwiaWF0IjoxNTM4NDYxMDkyfQ.LdK2UHQ6rNXWbqee8GLFPZJ5F3pnVxQMN6A_ZL4xzMnQ-NMJtkbUezGqVQGyd36N36XaeRgKtoXgyXtWk35g8A");
    let obs = this.newUser = this.http.post('http://api.pos.jabaridash.com:8080/users', data, {headers});

    obs.subscribe( (response) => console.log(response));
  }
  //
  //-------------- Trying something new ----------------//

    model : any = {};

    onSubmit() {

      const data : User = {
        "id" : null,
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

}

