describe('monomart app', () => {
  beforeEach(() => {
    cy.visit('http://localhost:8080')
  })

  it('puts product into cart', () => {
    cy.findAllByRole('button', {name: /add to cart/i}).first().click()
    cy.get('.item-count').within(() => {
      cy.findByText('1')
    })
  })

  it('should show the selected product in the cart', () => {
    cy.get('span').click()
    cy.findByRole('heading', {name: /cart/i})
    cy.get('ul > :nth-child(1)').within(() => {
      cy.findByText('Coffee Mug (10.99)')
    })
  })

  it('should checkout the product from the shopping cart', () => {
    cy.get('span').click()
    cy.findByRole('button',{name: 'Checkout (10.99)'}).click()
    cy.wait(5000)
    cy.findByRole('button',{name: 'Checkout (0.00)'})
    cy.get(':nth-child(1) > main > .quantity').within(() => {
      cy.findByText('Qty: 10') // need to decrease Qty in implementation
    })
  })
})