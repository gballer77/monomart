const { defineConfig } = require('cypress');
import { baseUrl } from './constants';
module.exports = defineConfig({
  chromeWebSecurity: false, // https://docs.cypress.io/guides/references/error-messages#Page-Load-Errors
  env: {
    auth_base_url: 'http://localhost:10000',
    auth_realm: 'tesseract',
    auth_client_id: 'tesseract',
    'user-name': 'user',
    password: 'password',
  },
  e2e: {
    baseUrl,
  },
  video: false,
  screenshotOnRunFailure: false,
});
