﻿import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

import {config} from "./config.service";

@Injectable()
export class AuthenticationService {

    constructor(private http: HttpClient) { }

    static getCurrentUserDetails() {

      let json = localStorage.getItem("currentUser");
      let obj  = JSON.parse(json);

      return obj.userDetails;
    }

    login(username: string, password: string) {

        return this.http.post<any>(`${ config.api.endpoint.auth } `, { username: username, password: password })
            .pipe(map(user => {

                // login successful if there's authorities jwt token in the response
                if (user && user.token) {

                    // store customer details and jwt token in local storage to keep customer logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }

                return user;
            }));
    }

    logout() {

      // TODO - Implement other log out things

      localStorage.clear();
    }
}
