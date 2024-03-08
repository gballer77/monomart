describe('given the website is rendered', () => {
    it('displays the homepage', () => {
        cy.visit('localhost:8080');
        cy.contains('Monomart');
    });

    it('displays the proper filter buttons', () => {
        cy.visit('localhost:8080');
        cy.contains('Home Goods');
        cy.contains('Sporting Goods');
        cy.contains('Electronics');
        cy.contains('Clothing');
    });

    it('when Home Goods is selected by default, then home goods are displayed', function () {
        cy.visit('localhost:8080');
        cy.contains('Home Goods').should('be.disabled');
        cy.contains('Coffee Mug');
        cy.contains('Toothpicks');
        cy.contains('Umbrella');
        cy.get('[aria-label="Coffee Mug"]').within(() => {
            cy.get('.price').contains('$10.99');
            cy.get('.quantity').contains('10');
            cy.contains('Add to cart').should('be.enabled');
        });
    });

    it('when Sporting Goods is selected, then sporting goods are displayed', function () {
        cy.visit('localhost:8080');
        cy.contains('Sporting Goods').should('be.visible').click();
        cy.contains('Sporting Goods').should('be.disabled');
        cy.contains('Basketball');
        cy.contains('Balaclava');
        cy.get('[aria-label="Balaclava"]').within(() => {
            cy.get('.price').contains('$10.99');
            cy.get('.quantity').contains('10');
            cy.contains('Add to cart').should('be.enabled');
        });
    });

    it('when Electronics is selected, then electronics are displayed', function () {
        cy.visit('localhost:8080');
        cy.contains('Electronics').should('be.visible').click();
        cy.contains('Electronics').should('be.disabled');
        cy.contains('Apple iPad');
        cy.contains('Dell Laptop');
        cy.get('[aria-label="Dell Laptop"]').within(() => {
            cy.get('.price').contains('$10.99');
            cy.get('.quantity').contains('10');
            cy.contains('Add to cart').should('be.enabled');
        });
    });

    it('when Clothing is selected, then clothing are displayed', function () {
        cy.visit('localhost:8080');
        cy.contains('Clothing').should('be.visible').click();
        cy.contains('Clothing').should('be.disabled');
        cy.contains('Jorts');
        cy.get('[aria-label="Jorts"]').within(() => {
            cy.get('.price').contains('$10.99');
            cy.get('.quantity').contains('10');
            cy.contains('Add to cart').should('be.enabled');
        });
    });

    it('when cart button is selected, opens an empty cart', function () {
        cy.visit('localhost:8080');
        cy.get('.show-cart').should('be.visible').click();
        cy.contains('Cart')
        cy.get('.cart-item-list').within(() => {
            cy.get('#cart-items').should('be.empty')
            cy.contains('Checkout (0.00)')
        });
    });

    it('when I add an item to the cart and checkout, it shows the item added and decrements the amount when checkout is complete', function () {
        cy.visit('localhost:8080');

        cy.get('[aria-label="Coffee Mug"]').within(() => {
            cy.contains('Add to cart').click();
        });

        cy.get('.show-cart').should('be.visible').click();
        cy.get('.cart-item-list').within(() => {
            cy.get('#cart-items').should('have.length',1);
            cy.contains('Coffee Mug (10.99)')
            cy.contains('Checkout (10.99)');
        });

        cy.visit('localhost:8080')

        cy.get('.show-cart').should('be.visible').click();
        cy.get('.cart-item-list').within(() => {
            cy.get('#cart-items').should('have.length',1);
            cy.contains('Coffee Mug (10.99)')
            cy.contains('Checkout (10.99)');
        });

        cy.get('.cart-total').click();

        cy.visit('localhost:8080')

        cy.get('[aria-label="Coffee Mug"]').within(() => {
            cy.get('.quantity').contains('9');
        });
    });
});