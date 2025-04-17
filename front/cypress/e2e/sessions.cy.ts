import '../support/commands'
/// <reference types="cypress" />


describe('Session spec admin view', () => {
    beforeEach(() => {
        cy.login("yoga@studio.com", "test!1234")   
    })

    it('Should show session page', () => {
        cy.get('[fxlayout="row"] > .mat-card-header-text > .mat-card-title').contains('Rentals available')
        cy.get('.items > :nth-child(1) > .mat-card-header > .mat-card-header-text > .mat-card-title').contains('Yoga class for beginners')
        cy.get('.items > :nth-child(2) > .mat-card-header > .mat-card-header-text > .mat-card-title').contains('Yoga class for advanced')
    })

    it('Should show session detail page', () => {
        cy.intercept('GET', '/api/session/1', {
            body: {
                id: 1,
                name: 'Yoga class for beginners',
                description: 'Yoga class for beginners',
                date: new Date(),
                teacher_id: 1,
                users: [1],
                createdAt: new Date(),
                updatedAt: new Date(),
            },
        })

        cy.intercept('GET', '/api/teacher/1', {
            body: {
                id: 1,
                firstName: 'John',
                lastName: 'Doe',
                createdAt: new Date(),
                updatedAt: new Date(),
            }
        })


        cy.get(':nth-child(1) > .mat-card-actions > :nth-child(1)').click()
        cy.get('.ml3 > .ml1').contains('John DOE')
        cy.url().should('include', '/sessions/detail/1')
        cy.get('h1').contains('Yoga Class For Beginners')
    })

    it('Should delete session', () => {
        cy.intercept('GET', '/api/session/1', {
            body: {
                id: 1,
                name: 'Yoga class for beginners',
                description: 'Yoga class for beginners',
                date: new Date(),
                teacher_id: 1,
                users: [1],
                createdAt: new Date(),
                updatedAt: new Date(),
            },
        })

        cy.intercept('GET', '/api/teacher/1', {
            body: {
                id: 1,
                firstName: 'John',
                lastName: 'Doe',
                createdAt: new Date(),
                updatedAt: new Date(),
            }
        })

        cy.intercept('DELETE', '/api/session/1', {
            statusCode: 200,
            body: {
                message: 'Session deleted',
            },
        })


        cy.get(':nth-child(1) > .mat-card-actions > :nth-child(1)').click()
        cy.get(':nth-child(2) > .mat-focus-indicator').click()
        cy.url().should('include', '/sessions')
    })

    it('Should create session', () => {
        cy.intercept('POST', '/api/session', {
            body: {
                id: 1,
                name: 'Yoga class for beginners',
                description: 'Yoga class for beginners',
                date: new Date(),
                teacher_id: 1,
                users: [1],
                createdAt: new Date(),
                updatedAt: new Date(),
            },
        })

        cy.intercept('GET', '/api/teacher', {
            body: [
                {
                    id: 1,
                    firstName: 'John',
                    lastName: 'Doe',
                    createdAt: new Date(),
                    updatedAt: new Date(),
                },
                {
                    id: 2,
                    firstName: 'Jane',
                    lastName: 'Doe',
                    createdAt: new Date(),
                    updatedAt: new Date(),
                },
            ]
        })

        cy.get('[fxlayout="row"] > .mat-focus-indicator').click()
        cy.get('input[formControlName=name]').type("Yoga class for beginners")
        cy.get('#mat-input-3').type(`2025-04-10`)
        cy.get('.mat-select-placeholder').click()
        cy.get('#mat-option-1 > .mat-option-text').click()
        cy.get('#mat-input-4').type("Yoga class for beginners{enter}{enter}")
        cy.get('.mt2 > [fxlayout="row"] > .mat-focus-indicator').click()
        cy.url().should('include', '/sessions')
    })

    it('Should update session', () => {
        cy.intercept('PUT', '/api/session/1', {
            body: {
                id: 1,
                name: 'Yoga class for beginners',
                description: 'For beginners only and no experience required',
                date: new Date(),
                teacher_id: 1,
                users: [1],
                createdAt: new Date(),
                updatedAt: new Date(),
            },
        })

        cy.intercept('GET', '/api/session/1', {
            body: {
                id: 1,
                name: 'Yoga class for beginners',
                description: 'Yoga class for beginners',
                date: new Date(),
                teacher_id: 1,
                users: [1],
                createdAt: new Date(),
                updatedAt: new Date(),
            },
        })

        cy.intercept('GET', '/api/teacher', {
            body: [
                {
                    id: 1,
                    firstName: 'John',
                    lastName: 'Doe',
                    createdAt: new Date(),
                    updatedAt: new Date(),
                },
                {
                    id: 2,
                    firstName: 'Jane',
                    lastName: 'Doe',
                    createdAt: new Date(),
                    updatedAt: new Date(),
                },
            ]
        })

        cy.get(':nth-child(1) > .mat-card-actions > .ng-star-inserted').click()
        cy.get('input[formControlName=name]').type("Yoga class for beginners only")
        cy.get('#mat-input-4').type("For beginners only and no experience required{enter}{enter}")
        cy.get('.mt2 > [fxlayout="row"] > .mat-focus-indicator').click()
        cy.url().should('include', '/sessions')
    })

})

describe('Session spec standard view', () => {
    beforeEach(() => {
        cy.login("yoga@studio.com", "test!1234", false)   
    })

    it('Should show session page', () => {
        cy.get('[fxlayout="row"] > .mat-card-header-text > .mat-card-title').contains('Rentals available')
        cy.get('.items > :nth-child(1) > .mat-card-header > .mat-card-header-text > .mat-card-title').contains('Yoga class for beginners')
        cy.get('.items > :nth-child(2) > .mat-card-header > .mat-card-header-text > .mat-card-title').contains('Yoga class for advanced')
    })

    it('Should join session', () => {
        cy.intercept('GET', '/api/session/1', {
            body: {
                id: 1,
                name: 'Yoga class for beginners',
                description: 'Yoga class for beginners',
                date: new Date(),
                teacher_id: 1,
                users: [2],
                createdAt: new Date(),
                updatedAt: new Date(),
            },
        })

        cy.intercept('GET', '/api/teacher/1', {
            body: {
                id: 1,
                firstName: 'John',
                lastName: 'Doe',
                createdAt: new Date(),
                updatedAt: new Date(),
            }
        })

        cy.intercept('POST', '/api/session/1/participate/1', {
            statusCode: 200,
            body: {
                message: 'Session joined',
            },
        })

        cy.get(':nth-child(1) > .mat-card-actions > :nth-child(1)').click()
        cy.get('.mat-button-wrapper > .ml1').contains('Participate')
        cy.intercept('GET', '/api/session/1', {
            body: {
                id: 1,
                name: 'Yoga class for beginners',
                description: 'Yoga class for beginners',
                date: new Date(),
                teacher_id: 1,
                users: [2, 1],
                createdAt: new Date(),
                updatedAt: new Date(),
            },
        })
        cy.get('div.ng-star-inserted > .mat-focus-indicator').click()
        cy.get('.mat-button-wrapper > .ml1').contains('Do not participate')
        cy.url().should('include', '/sessions')
    })

    it('Should leave session', () => {
        cy.intercept('GET', '/api/session/1', {
            body: {
                id: 1,
                name: 'Yoga class for beginners',
                description: 'Yoga class for beginners',
                date: new Date(),
                teacher_id: 1,
                users: [1, 4, 5],
                createdAt: new Date(),
                updatedAt: new Date(),
            },
        })

        cy.intercept('GET', '/api/teacher/1', {
            body: {
                id: 1,
                firstName: 'John',
                lastName: 'Doe',
                createdAt: new Date(),
                updatedAt: new Date(),
            }
        })

        cy.intercept('DELETE', '/api/session/1/participate/1', {
            statusCode: 200,
            body: {
                message: 'Session left',
            },
        })

        cy.get(':nth-child(1) > .mat-card-actions > :nth-child(1)').click()
        cy.get('.mat-button-wrapper > .ml1').contains('Do not participate')
        cy.intercept('GET', '/api/session/1', {
            body: {
                id: 1,
                name: 'Yoga class for beginners',
                description: 'Yoga class for beginners',
                date: new Date(),
                teacher_id: 1,
                users: [4, 5],
                createdAt: new Date(),
                updatedAt: new Date(),
            },
        })
        cy.get('div.ng-star-inserted > .mat-focus-indicator').click()
        cy.get('.mat-button-wrapper > .ml1').contains('Participate')
        cy.url().should('include', '/sessions')
    })
})