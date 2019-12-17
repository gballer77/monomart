import {ProductApi} from "./ProductApi";
import {mockFetch} from "../mockFetch";

describe("ProductApi", () => {
  it("should return a promise with product results", async () => {
    mockFetch([{}, {}, {}, {}, {}, {}, {}, {}, {}]);
    let productApi = new ProductApi();
    const getProductResults = await productApi.getProducts('');

    expect(getProductResults).toHaveLength(9);
  });
});
