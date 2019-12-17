import CategoryApi from "./CategoryApi";
import {mockFetch} from "../mockFetch";

describe("CategoryApi", () => {
  it("should return a promise with the category results", async () => {
    mockFetch([{}, {}, {}]);
    const categoryApi = new CategoryApi();
    const getCategoriesResults = await categoryApi.getCategories();

    expect(getCategoriesResults).toHaveLength(3);
  });
});
