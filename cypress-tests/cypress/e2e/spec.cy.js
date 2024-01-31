describe('should render main page', () => {
  beforeEach(() => {
    cy.visit('http://localhost:8080/')
  })

  it('can view Home Goods Catalog', () => {
    cy.contains('button', 'Home Goods');
    cy.document().contains('Coffee Mug');
    cy.document().contains('Umbrella');
    cy.document().contains('Toothpicks');

  });


  it('can view Sporting Goods Catalog', () => {
    cy.contains('button', 'Sporting Goods').click();
    cy.document().contains('Balaclava');
    cy.document().contains('Basketball');
  });

  it('can view Clothing', () => {
    cy.contains('button', 'Clothing').click();
    cy.document().contains('Jorts');
  });
  it('can view Electronics', () => {
    cy.contains('button', 'Electronics').click();
    cy.document().contains('Apple iPad');
    cy.document().contains('Dell Laptop');
  });


})