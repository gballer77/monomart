import { waitForElement } from "@testing-library/dom";
import { act, cleanup, render, RenderResult } from "@testing-library/react";
import * as React from "react";
import {Catalog, CatalogModel} from "../domain/category/Catalog";
import {ProductFilters} from "./ProductFilters";

describe("ProductFilters", () => {
  let renderResult: RenderResult;
  let setFilterCategorySpy: jest.Mock;

  beforeEach(async () => {
    setFilterCategorySpy = jest.fn();
    const filterCategories: Catalog[] = [
      new CatalogModel("homegoods", "Home Goods"),
      new CatalogModel("sportinggoods", "Sporting Goods")
    ];

    await act(async () => {
      renderResult = render(
        <ProductFilters
          activeCategory={"homegoods"}
          filterCategories={filterCategories}
          setActiveFilterCategory={setFilterCategorySpy}
        />
      );
    });

    await waitForElement(() => renderResult.getByText("Home Goods"));
  });

  afterEach(cleanup);

  it("should display the passed in filters as possible filter options", async () => {
    expect(renderResult.getByText("Home Goods")).not.toBeNull();
    expect(renderResult.getByText("Sporting Goods")).not.toBeNull();
  });

  it("should notify the parent that a filter has been selected", () => {
    const homeGoodsFilterButton = renderResult.getByText("Sporting Goods");
    homeGoodsFilterButton.click();

    expect(setFilterCategorySpy).toHaveBeenCalledTimes(1);
    expect(setFilterCategorySpy).toHaveBeenLastCalledWith("sportinggoods");
  });
});
