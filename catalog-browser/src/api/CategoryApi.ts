import {Category} from "../entities/Category";

class CategoryApi {
  static async getCategories(): Promise<Category[]> {
    return fetch('/api/catalogs')
        .then(response => response.json());
  }
}

export default CategoryApi;
