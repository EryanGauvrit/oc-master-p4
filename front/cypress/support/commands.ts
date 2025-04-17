/// <reference types="cypress" />

// ***********************************************
// This example namespace declaration will help
// with Intellisense and code completion in your
// IDE or Text Editor.
// ***********************************************
declare namespace Cypress {
  interface Chainable<Subject = any> {
    login(email: string, password: string, isAdmin?: boolean): Chainable<Element>
  }
}
//
// function customCommand(param: any): void {
//   console.warn(param);
// }
//
// NOTE: You can use it like so:
// Cypress.Commands.add('customCommand', customCommand);
//
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
// Cypress.Commands.add("login", (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add("drag", { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })

Cypress.Commands.add("login", (email: string, password: string, isAdmin = true) => {
    cy.visit('/login')

    cy.intercept('POST', '/api/auth/login', {
        body: {
            id: 1,
            username: 'john.doe',
            firstName: 'john',
            lastName: 'doe',
            admin: isAdmin
        },
    })

    cy.intercept('GET', '/api/session', {
            body: [
                {
                    id: 1,
                    name: 'Yoga class for beginners',
                    description: 'Yoga class for beginners',
                    date: new Date(),
                    teacher_id: 1,
                    users: [1],
                    createdAt: new Date(),
                    updatedAt: new Date(),
                },
                {
                    id: 2,
                    name: 'Yoga class for advanced',
                    description: 'Yoga class for beginners',
                    date: new Date(),
                    teacher_id: 1,
                    users: [1],
                    createdAt: new Date(),
                    updatedAt: new Date(),
                },
            ],
        }).as('session')

    cy.get('input[formControlName=email]').type(email)
    cy.get('input[formControlName=password]').type(`${password}{enter}{enter}`)

    cy.url().should('include', '/sessions')
})
