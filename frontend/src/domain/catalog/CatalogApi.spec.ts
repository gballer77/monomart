import {CatalogApi, getDefaultCatalog} from "./CatalogApi";
import {mockFetch} from "../mockFetch";
import {Catalog} from "./Catalog";

describe("CatalogApi", () => {
  test("getCategories", async () => {
    mockFetch([{}, {}, {}]);
    const catalogApi = new CatalogApi();
    const getCategoriesResults = await catalogApi.getCategories();

    expect(getCategoriesResults).toHaveLength(3);
  });
});

test('getDefaultCatalog', () => {
  const defaultCatalog = getDefaultCatalog([{id: 'expected'} as Catalog, {id: ''} as Catalog]);

  expect(defaultCatalog).toEqual('expected');
});
