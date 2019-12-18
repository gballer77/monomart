import {Gateway} from "./Gateway";
import {ProductApi} from "./product/ProductApi";
import {CatalogApi} from "./catalog/CatalogApi";

describe('Gateway', () => {
  test('constructor - allows optional override of apis', () => {
    const productApi = new ProductApi();
    productApi.getProducts = jest.fn();
    const gateway = new Gateway({productApi});
    gateway.productApi.getProducts('');

    expect(productApi.getProducts).toHaveBeenCalled();
  });

  test('init', () => {
    const catalogApi = new CatalogApi();
    catalogApi.getCategories = jest.fn();
    const gateway = new Gateway({catalogApi});

    gateway.init();

    expect(catalogApi.getCategories).toHaveBeenCalled();
  });
});
