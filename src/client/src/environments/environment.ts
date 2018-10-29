const api = {
  protocol : 'http',
  host     : 'localhost',
  port     : '8080'

};

export const environment = {
  production : false,
  apiBaseUrl : `${api.protocol}://${api.host}:${api.port}`
};
