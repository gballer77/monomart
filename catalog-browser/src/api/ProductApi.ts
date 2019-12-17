import {Product} from "../entities/Product";

export class ProductApi {
  static async getProducts(category: string): Promise<Product[]> {
    return await fetch(`/api/catalogs/${category}`)
      .then(response => response.json());
  }
}
