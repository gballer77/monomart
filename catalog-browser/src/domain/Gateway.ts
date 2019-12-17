import {ProductApi} from "./product/ProductApi";
import CategoryApi from "./category/CategoryApi";
import {Catalog} from "./category/Catalog";

export class Gateway {
  readonly categoryApi: CategoryApi;
  readonly productApi: ProductApi;

  constructor(paramOverrides: Partial<{ categoryApi: CategoryApi, productApi: ProductApi }> = {}) {
    const params = Object.assign({
      categoryApi: new CategoryApi(),
      productApi: new ProductApi()
    }, paramOverrides);

    this.categoryApi = params.categoryApi;
    this.productApi = params.productApi;
  }

  init(): Promise<Catalog[]> {
    return this.categoryApi.getCategories();
  }
}
