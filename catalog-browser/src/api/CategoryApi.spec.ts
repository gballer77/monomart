import CategoryApi from "./CategoryApi";
import {mockFetch} from "./mockFetch";

describe("CategoryApi", () => {
  it("should return a promise with the category results", async () => {
    mockFetch([{}, {}, {}]);
    const getCategoriesResults = await CategoryApi.getCategories();

    expect(getCategoriesResults).toHaveLength(3);
  });
});
