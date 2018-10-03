import { Component } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { APIService } from "./api.service";
import { User } from '../model/user.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'client';

  posts: any;
  newUser: any;
  // headers= new HttpHeaders().set('Authorization', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUzOTA2NTg5MiwiaWF0IjoxNTM4NDYxMDkyfQ.LdK2UHQ6rNXWbqee8GLFPZJ5F3pnVxQMN6A_ZL4xzMnQ-NMJtkbUezGqVQGyd36N36XaeRgKtoXgyXtWk35g8A');
  constructor(private svc:APIService, private http: HttpClient) {
  }

  getUsers() {
    let headers= new HttpHeaders().set('Authorization', 'Bearer ' + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUzOTA2NTg5MiwiaWF0IjoxNTM4NDYxMDkyfQ.LdK2UHQ6rNXWbqee8GLFPZJ5F3pnVxQMN6A_ZL4xzMnQ-NMJtkbUezGqVQGyd36N36XaeRgKtoXgyXtWk35g8A");
    let obs = this.posts = this.http.get('http://api.pos.jabaridash.com:8080/users', {headers:headers} );
    obs.subscribe((response) => console.log(response));
    console.log("We get here tho");

  }

  createUser(data : User) {
    //   const data : User = {
    //     "id" : "",
    //     "firstname" : "Kev3",
    //     "lastname" : "admin",
    //     "enabled" : true,
    //     "email" : "jabarikevyn@gmail.com",
    //     "lastPasswordResetDate": "2016-01-01T00:00:00.000+0000",
    //     "username" : "adminkev",
    //     "password" : "admindyurr"
    // };

    let headers = new HttpHeaders().set('Authorization', 'Bearer ' + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUzOTA2NTg5MiwiaWF0IjoxNTM4NDYxMDkyfQ.LdK2UHQ6rNXWbqee8GLFPZJ5F3pnVxQMN6A_ZL4xzMnQ-NMJtkbUezGqVQGyd36N36XaeRgKtoXgyXtWk35g8A");
    let obs = this.newUser = this.http.post('http://137.140.146.1:8080/users', data, {headers});

    obs.subscribe( (response) => console.log(response));
  }

}

