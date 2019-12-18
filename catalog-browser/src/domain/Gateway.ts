import {ProductApi} from "./product/ProductApi";
import {CatalogApi} from "./catalog/CatalogApi";
import {Catalog} from "./catalog/Catalog";

export class Gateway {
  readonly catalogApi: CatalogApi;
  readonly productApi: ProductApi;

  constructor(paramOverrides: Partial<{ catalogApi: CatalogApi, productApi: ProductApi }> = {}) {
    const params = Object.assign({
      catalogApi: new CatalogApi(),
      productApi: new ProductApi()
    }, paramOverrides);

    this.catalogApi = params.catalogApi;
    this.productApi = params.productApi;
  }

  init(): Promise<Catalog[]> {
    return this.catalogApi.getCategories();
  }
}
