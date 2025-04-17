import '../support/commands'
/// <reference types="cypress" />


describe('Logout spec', () => {
    beforeEach(() => {
        cy.login("yoga@studio.com", "test!1234")
    })

    it('Should logout user', () => {
        cy.get('.mat-toolbar > .ng-star-inserted > :nth-child(3)').click()
        cy.url().should('include', '/')
        cy.get('[routerlink="login"]').contains('Login')
        cy.get('[routerlink="register"]').contains('Register')
    })
})