import {Home} from "../pages/Home";
import {cleanup, render, RenderResult} from "@testing-library/react";
import * as React from "react";
import {Product} from "../entities/Product";
import {Category} from "../entities/Category";
import {mockFetch} from "../api/mockFetch";
import {act} from "react-dom/test-utils";
import {ProductApi} from "../api/ProductApi";

describe("Index", () => {
    afterEach(cleanup);

    it("should render welcome dialogue", () => {
        const productApi = new ProductApi();
        productApi.getProducts = jest.fn().mockReturnValue(Promise.resolve([]));

        let home: RenderResult | null = null;

        act(() => {
            home = render(
              <Home productApi={productApi} categories={[]} products={[]}/>
            );
        });

        expect(home!.container.innerHTML).toContain("Welcome to the Workshop Shop!");
    });

    it("should render the passed in categories and products", () => {
        const productApi = new ProductApi();
        productApi.getProducts = jest.fn().mockReturnValue(Promise.resolve([]));

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

        let container: RenderResult | null = null;
        act(() => {
            container = render(
                <Home productApi={productApi} categories={categories} products={products}/>
            );
        });

        expect(container!.queryByText("Category 1")).not.toBeNull();
        expect(container!.queryByText("Category 2")).not.toBeNull();
        expect(container!.queryByText("Fake Product 1")).not.toBeNull();
        expect(container!.queryByText("Fake Product 2")).not.toBeNull();
        expect(container!.queryByText("Fake Product 3")).not.toBeNull();
    });

    it("should not render the category section at all if no categories to filter on", () => {
        let filters: HTMLElement | null = {} as HTMLElement;

        const productApi = new ProductApi();
        productApi.getProducts = jest.fn().mockReturnValue(Promise.resolve([]));

        act(() => {
            const {queryByText} = render(
                <Home
                    productApi={productApi}
                    categories={[]}
                    products={[]}
                />);
            filters = queryByText("Filters:");
            expect(filters).toBeNull();
        });

    });
});
