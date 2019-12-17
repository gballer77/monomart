import {render} from "@testing-library/react";
import * as React from "react";
import {ProductCard} from "./ProductCard";
import {ProductModel} from "../entities/Product";

describe("ProductCard", () => {
  it("should render with a item name, price, description, and image for a product", () => {
    const product = new ProductModel(
      "Fake name",
      19.99,
      "A fake description",
      "http://fakeimagelink",
      "Fake image alt text"
    );

    const {getByText, getByAltText} = render(
      <ProductCard product={product}/>
    );

    expect(getByText("Fake name")).not.toBeNull();
    expect(getByText("$19.99")).not.toBeNull();
    expect(getByText("A fake description")).not.toBeNull();
    expect(getByText("A fake description")).not.toBeNull();
    const image = getByAltText("Fake image alt text") as HTMLImageElement;
    expect(image.src).toEqual("http://fakeimagelink/");
  });
});
