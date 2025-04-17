/// <reference types="cypress" />

describe('Register spec', () => {
  it('Register successfull', () => {
    cy.visit('/register')

    cy.intercept('POST', '/api/auth/register', {
      body: { message: "User registered successfully!" }
    })

    cy.get('input[formControlName=firstName]').type("firstName")
    cy.get('input[formControlName=lastName]').type("lastName")
    cy.get('input[formControlName=email]').type("yoga@studio.com")
    cy.get('input[formControlName=password]').type(`${"test!1234"}{enter}{enter}`)

    cy.url().should('include', '/login')
  })

  it('Register unsuccessfull because password contains only string characters', () => {
    cy.visit('/register')

    cy.intercept('POST', '/api/auth/register', {
      statusCode: 400,
      body: {
        error: 'Invalid credentials',
      },
    })

    cy.get('input[formControlName=firstName]').type("firstName")
    cy.get('input[formControlName=lastName]').type("lastName")
    cy.get('input[formControlName=email]').type("yoga@studio.com")
    cy.get('input[formControlName=password]').type(`${"test"}{enter}{enter}`)

    cy.get('.error').contains('An error occurred')
  })
  
  it('User cannot submit because fields are empty', () => {
    cy.visit('/register')

    cy.intercept('POST', '/api/auth/register', {
      statusCode: 400,
      body: {
        error: 'Invalid credentials',
      },
    })

    cy.get('input[formControlName=password]').type(`{enter}{enter}`)

    cy.get('button[type=submit]').should('be.disabled')
  })

  it('User cannot submit because password contains only number characters', () => {
    cy.visit('/register')

    cy.intercept('POST', '/api/auth/register', {
      statusCode: 400,
      body: {
        error: 'Invalid credentials',
      },
    })

    cy.get('input[formControlName=firstName]').type("firstName")
    cy.get('input[formControlName=lastName]').type("lastName")
    cy.get('input[formControlName=email]').type("yoga@studio.com")
    cy.get('input[formControlName=password]').type(`${"1234"}{enter}{enter}`)

    cy.get('button[type=submit]').should('be.disabled')
  })
});