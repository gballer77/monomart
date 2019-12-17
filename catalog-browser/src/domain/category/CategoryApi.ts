import {Catalog} from "./Catalog";

class CategoryApi {
  async getCategories(): Promise<Catalog[]> {
    return fetch('/api/catalogs')
        .then(response => response.json());
  }
}

export default CategoryApi;
