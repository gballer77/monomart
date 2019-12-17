import {Home} from "../pages/Home";
import {cleanup, render} from "@testing-library/react";
import * as React from "react";
import {Product} from "../entities/Product";
import {Category} from "../entities/Category";
import {mockFetch} from "../api/mockFetch";
import {act} from "react-dom/test-utils";

describe("Index", () => {
  afterEach(cleanup);

  it("should render welcome dialogue", () => {
    const home = render(<Home categories={[]} products={[]}/>);

    expect(home.container.innerHTML).toContain("Welcome to the Workshop Shop!");
  });

  it("should render the passed in categories and products", async () => {
    const categories: Category[] = [
      new Category("category1", "Category 1"),
      new Category("category2", "Category 2")
    ];
    const products: Product[] = [
      {
        id: "12345",
        name: "Fake Product 1",
        description: "Fake Product 1 description",
        price: 19.99,
        imageSrc: "https://picsum.photos/200/300",
        imageAlt: "Product 1 Alt"
      },
      {
        id: "12346",
        name: "Fake Product 2",
        description: "Fake Product 2 description",
        price: 19.99,
        imageSrc: "https://picsum.photos/200/300",
        imageAlt: "Product 2 Alt"
      },
      {
        id: "12347",
        name: "Fake Product 3",
        description: "Fake Product 3 description",
        price: 19.99,
        imageSrc: "https://picsum.photos/200/300",
        imageAlt: "Product 3 Alt"
      }
    ];
    const {queryByText} = render(
      <Home categories={categories} products={products}/>
    );

    expect(queryByText("Category 1")).not.toBeNull();
    expect(queryByText("Category 2")).not.toBeNull();
    expect(queryByText("Fake Product 1")).not.toBeNull();
    expect(queryByText("Fake Product 2")).not.toBeNull();
    expect(queryByText("Fake Product 3")).not.toBeNull();
  });

  it("should not render the category section at all if no categories to filter on", async () => {
    mockFetch([]);
    let filters: HTMLElement | null = {} as HTMLElement;

    act(() => {
      const {queryByText} = render(<Home categories={[]} products={[]}/>);
      filters = queryByText("Filters:")
    });

    expect(filters).toBeNull();
  });
});
