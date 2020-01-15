import {Product} from "./Product";

export class ProductApi {
  async getProducts(catalog: string): Promise<Product[]> {
    return fetch(`/api/products?catalog=${catalog}`)
        .then(response => response.json());
  }
}
