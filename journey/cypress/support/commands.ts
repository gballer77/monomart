// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })

import "@testing-library/cypress/add-commands";
import { baseUrl } from "../../constants";

const createAuthenticatedSession = () => {
  const authBaseUrl = Cypress.env("auth_base_url");
  const realm = Cypress.env("auth_realm");
  const client_id = Cypress.env("auth_client_id");
  const username = Cypress.env("user-name");
  const password = Cypress.env("password");

  cy.request({
    url: `${authBaseUrl}/realms/${realm}/protocol/openid-connect/auth`,

    followRedirect: false,
    qs: {
      scope: "openid",
      response_type: "code",
      approval_prompt: "auto",

      redirect_uri: `${baseUrl}login/oauth2/code/sso`,
      client_id,
    },
  }).then((response) => {
    const html = document.createElement("html");
    html.innerHTML = response.body;

    const form = html.getElementsByTagName("form")[0];
    const url = form.action;
    return cy.request({
      method: "POST",
      url,
      followRedirect: false,
      form: true,
      body: {
        username,
        password,
      },
    });
  });
};

Cypress.Commands.add("acceptUserAgreement", () => {
  cy.findAllByRole('button', {name: /log in/i}).first().click();
  cy.findByRole("link", { name: /accept and login/i }).click();
});

Cypress.Commands.add("createAuthenticatedSession", (): Cypress.cy => {
  createAuthenticatedSession();
  return cy;
});

Cypress.Commands.add("login", () => {
  cy.visit('/');
  cy.findAllByRole('button', {name: /log in/i}).first().click();
  cy.findByRole('link', { name: /accept and log in/i }).click();
});
