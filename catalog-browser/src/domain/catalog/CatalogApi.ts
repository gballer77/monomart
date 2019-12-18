import {Catalog} from "./Catalog";

export class CatalogApi {
  async getCategories(): Promise<Catalog[]> {
    return fetch('/api/catalogs')
      .then(response => response.json());
  }
}

export function getDefaultCatalog(categories: Catalog[]): string {
  if (categories && categories[0]) {
    return categories[0].catalogKey;
  }

  return '';
}
