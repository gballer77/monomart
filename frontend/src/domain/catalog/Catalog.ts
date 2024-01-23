export type Catalog = {
  id: string
  displayName: string
}

export class CatalogModel implements Catalog {
  constructor(public id: string,
              public displayName: string) {
  }
}
