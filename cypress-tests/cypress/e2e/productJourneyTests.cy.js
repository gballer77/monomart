
describe('should render main page', () => {
    beforeEach(() => {
        cy.visit('http://localhost:8080/')
    })

    it('can view Home Goods Products', () => {
        cy.contains('button', 'Home Goods');
        cy.document().contains('Coffee Mug');
        cy.findAllByText('$10.99').should('exist').should('have.length', 3);
        cy.findAllByText(/qty: 10/i).should('exist').should('have.length', 3);
        cy.document().contains('Umbrella');
        cy.document().contains('Toothpicks');

    });

})

describe('validate products are listed correctly in the Home Goods catalog', () => {
    ["Coffee Mug",
    "Toothpicks",
    "Umbrella"].forEach((nameOfProduct) => {
        beforeEach(() => {
            cy.visit('http://localhost:8080/')
        })
        it(`should display the correct description for ${nameOfProduct}`, () => {
            cy.findByText(`This is a description of ${nameOfProduct}`).should('be.visible');
        });
    })
})
