import {ProductApi} from "./ProductApi";
import {mockFetch} from "./mockFetch";

describe("ProductApi", () => {
  it("should return a promise with product results", async () => {
    mockFetch([{}, {}, {}, {}, {}, {}, {}, {}, {}]);
    const getProductResults = await ProductApi.getProducts('');

    expect(getProductResults).toHaveLength(9);
  });
});
