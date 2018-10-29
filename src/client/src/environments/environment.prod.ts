const api = {
  protocol : 'http',
  host     : 'pos-api',
  port     : '8080'

};

export const environment = {
  production : false,
  apiBaseUrl : `${api.protocol}://${api.host}:${api.port}`
};
