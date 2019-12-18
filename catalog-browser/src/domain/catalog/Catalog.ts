export type Catalog = {
  catalogKey: string
  displayName: string
}

export class CatalogModel implements Catalog {
  constructor(public catalogKey: string,
              public displayName: string) {
  }
}
