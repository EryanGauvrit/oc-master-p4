import '../support/commands'
/// <reference types="cypress" />


describe('Account spec', () => {
    
    it('Should show admin page', () => {
        cy.login("yoga@studio.com", "test!1234")
        cy.intercept('GET', '/api/user/1', {
            body: {
                id: 1,
                email: "yoga@studio.com",
                username: 'john.doe',
                firstName: 'john',
                lastName: 'doe',
                admin: true,
                password: 'test!1234',
                createdAt: new Date(),
                updatedAt: new Date(),
            },
        })
        cy.get('span[routerLink="me"]').click()
        cy.url().should('include', '/me')
        cy.get('h1').contains('User information')
        cy.get('.mat-card-content > div.ng-star-inserted > :nth-child(2)').contains('yoga@studio.com')
        cy.get('.my2').contains('You are admin')
    })

    it('Should show standard page', () => {
        cy.login("yoga@studio.com", "test!1234", false)
        cy.intercept('GET', '/api/user/1', {
            body: {
                id: 1,
                email: "yoga@studio.com",
                username: 'john.doe',
                firstName: 'john',
                lastName: 'doe',
                admin: false,
                password: 'test!1234',
                createdAt: new Date(),
                updatedAt: new Date(),
            },
        })
        cy.get('span[routerLink="me"]').click()
        cy.url().should('include', '/me')
        cy.get('h1').contains('User information')
        cy.get('.mat-card-content > div.ng-star-inserted > :nth-child(2)').contains('yoga@studio.com')
        cy.get('.my2 > p').contains('Delete my account')
    })

    it('Should delete account', () => {
        cy.login("yoga@studio.com", "test!1234", false)
        cy.intercept('GET', '/api/user/1', {
            body: {
                id: 1,
                email: "yoga@studio.com",
                username: 'john.doe',
                firstName: 'john',
                lastName: 'doe',
                admin: false,
                password: 'test!1234',
                createdAt: new Date(),
                updatedAt: new Date(),
            },
        })
        cy.intercept('DELETE', '/api/user/1', {
            statusCode: 200,
            body: {
                message: 'User deleted',
            },
        })

        cy.get('span[routerLink="me"]').click()
        cy.url().should('include', '/me')
        cy.get('.my2 > .mat-focus-indicator').click()
        cy.url().should('include', '/')
        cy.get('[routerlink="login"]').contains('Login')
        cy.get('[routerlink="register"]').contains('Register')

    })
})