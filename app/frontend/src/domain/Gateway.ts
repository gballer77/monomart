import {ProductApi} from "./product/ProductApi";
import {CatalogApi} from "./catalog/CatalogApi";
import {Catalog} from "./catalog/Catalog";
import {CartApi} from "./cart/CartApi";

interface Apis {
  catalogApi: CatalogApi
  productApi: ProductApi
  cartApi: CartApi
}

export class Gateway {
  readonly catalogApi: CatalogApi;
  readonly productApi: ProductApi;
  readonly cartApi: CartApi;

  constructor(paramOverrides: Partial<Apis> = {}) {
    const params = Object.assign({
      catalogApi: new CatalogApi(),
      productApi: new ProductApi(),
      cartApi: new CartApi()
    }, paramOverrides);

    this.catalogApi = params.catalogApi;
    this.productApi = params.productApi;
    this.cartApi = params.cartApi;
  }

  init(): Promise<Catalog[]> {
    return this.catalogApi.getCategories();
  }
}
