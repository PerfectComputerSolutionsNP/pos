const api = {
  protocol : 'http',
  host     : 'api.pos.jabaridash.com',
  port     : '8080'

};

export const environment = {
  production : false,
  apiBaseUrl : `${api.protocol}://${api.host}:${api.port}`
};
