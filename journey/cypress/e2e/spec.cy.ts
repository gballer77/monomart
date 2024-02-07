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

  it('shows the selected product in the cart', () => {
    cy.get('span').click()
    cy.get('.cart-item-list').within(()=>{
      cy.findByRole('listItem', {name: /coffee mug/i})
    })

  })
})