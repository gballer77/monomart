
describe("Monomart", () => {


  it("should be able to click on add to cart then click cart then checkout", () => {

    cy.findAllByRole("button",{name:/add to cart/i}).first().click();

    cy.findByRole('img',{name: "show shopping cart"}).click();


  });
})

