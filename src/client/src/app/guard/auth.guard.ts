import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import {AuthenticationService} from '../service/authentication.service';

// https://medium.com/@ryanchenkie_40935/angular-authentication-using-route-guards-bf7a4ca13ae3

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router) { }

    loggedIn() {

      return !!localStorage.getItem('currentUser');
    }

    hasRole(role : String) {

      if (!this.loggedIn())
        return false;

      let userDetails = AuthenticationService.getCurrentUserDetails();
      let authorities = userDetails.authorities;

      for (let authority of authorities) {

        if (authority.authority === role)
          return true;
      }

      return false;
    }

    isAdmin() {

      return this.hasRole("ROLE_ADMIN");
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

        if (localStorage.getItem('currentUser'))
          return true;

        this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});

        return false;
    }
}
