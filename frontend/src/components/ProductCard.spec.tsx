import {cleanup, render} from "@testing-library/react";
import * as React from "react";
import {ProductCard} from "./ProductCard";
import {ProductModel} from "../domain/product/Product";

describe("ProductCard", () => {
  let product: ProductModel;

  afterEach(cleanup);

  beforeEach(() => {
    product = new ProductModel(
      "Fake name",
      '19.99',
      "A fake description",
      "http://fakeimagelink",
      "Fake image alt text",
        10
    );
  });

  it("renders item name, price, description, and image for a product", () => {
    const {getByText, getByAltText} = render(
      <ProductCard product={product} addToCart={jest.fn()}/>
    );

    expect(getByText("Fake name")).not.toBeNull();
    expect(getByText("$19.99")).not.toBeNull();
    expect(getByText("A fake description")).not.toBeNull();
    expect(getByText("A fake description")).not.toBeNull();
    const image = getByAltText("Fake image alt text") as HTMLImageElement;
    expect(image.src).toEqual("http://fakeimagelink/");
  });

  it('calls addToCart when Add to cart button is clicked', () => {
    const addToCart = jest.fn();
    const {getByText} = render(<ProductCard product={product} addToCart={addToCart}/>);
    let addToCartButton = getByText(/add/i);
    addToCartButton.click();
    expect(addToCart).toHaveBeenCalled();
  });
});
