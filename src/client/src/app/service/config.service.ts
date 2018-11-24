import {environment} from '../../environments/environment';

export const config = {

  api  : {

    endpoint : {
      user        : `${environment.apiBaseUrl}/user`,
      users       : `${environment.apiBaseUrl}/users`,
      customer    : `${environment.apiBaseUrl}/customer`,
      product     : `${environment.apiBaseUrl}/product`,
      category    : `${environment.apiBaseUrl}/category`,
      auth        : `${environment.apiBaseUrl}/auth`,
      role        : `${environment.apiBaseUrl}/role`,
      transaction : `${environment.apiBaseUrl}/transaction`,
    }

  }

};
