const api = {
  protocol : 'http',
  host     : '192.168.1.57',
  port     : '8080'

};

export const environment = {
  production : false,
  apiBaseUrl : `${api.protocol}://${api.host}:${api.port}`
};
