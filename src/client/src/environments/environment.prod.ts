const api = {
  protocol : "http",
  host     : "api.pos.jabaridash.com",
  port     : "8080"

};

const apiBaseUrl = `${api.protocol}://${api.host}:${api.port}`;

export const environment = {
  production  : true,

  api : {

    endpoint :{
      user      : `${apiBaseUrl}/user`,
      users     : `${apiBaseUrl}/users`,
      product   : `${apiBaseUrl}/product`,
      category  : `${apiBaseUrl}/category`,
      auth      : `${apiBaseUrl}/auth`,
    }
  }

};
