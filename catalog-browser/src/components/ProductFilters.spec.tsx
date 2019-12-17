import { waitForElement } from "@testing-library/dom";
import { act, cleanup, render, RenderResult } from "@testing-library/react";
import * as React from "react";
import {Category} from "../entities/Category";
import {ProductFilters} from "./ProductFilters";

describe("ProductFilters", () => {
  let renderResult: RenderResult;
  let setFilterCategorySpy: jest.Mock;

  beforeEach(async () => {
    setFilterCategorySpy = jest.fn();
    const filterCategories: Category[] = [
      new Category("homegoods", "Home Goods"),
      new Category("sportinggoods", "Sporting Goods")
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
    const homeGoodsFilterButton = renderResult.getByText("Home Goods");
    homeGoodsFilterButton.click();

    expect(setFilterCategorySpy).toHaveBeenCalledTimes(1);
    expect(setFilterCategorySpy).toHaveBeenLastCalledWith("homegoods");
  });
});
