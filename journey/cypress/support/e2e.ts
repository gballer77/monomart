// ***********************************************************
// This example support/e2e.js is processed and
// loaded automatically before your test files.
//
// This is a great place to put global configuration and
// behavior that modifies Cypress.
//
// You can change the location of this file or turn off
// automatically serving support files with the
// 'supportFile' configuration option.
//
// You can read more here:
// https://on.cypress.io/configuration
// ***********************************************************

// Import commands.ts using ES2015 syntax:
import "./commands";

declare global {
  namespace Cypress {
    interface Chainable {
      /**
       * creates an authenticated session on the backend
       */
      createAuthenticatedSession(): void;

      /**
       * create an authenticated session and accept the user agreement
       */
      login(): void;

      /**
       * accept the user agreement
       */
      acceptUserAgreement(): void;

      authenticatedLoginToLeadsPage(): void;
    }
  }
}

// Alternatively you can use CommonJS syntax:
// require('./commands')
