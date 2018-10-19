import {environment} from '../../environments/environment';


export const config = {

  api  : {

    endpoint : {
      user      : `${environment.apiBaseUrl}/user`,
      users     : `${environment.apiBaseUrl}/users`,
      product   : `${environment.apiBaseUrl}/product`,
      category  : `${environment.apiBaseUrl}/category`,
      auth      : `${environment.apiBaseUrl}/auth`,
    }

  }


};
