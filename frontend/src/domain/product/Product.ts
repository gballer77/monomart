export interface Product {
  id: string
  name: string,
  price: string,
  description: string,
  imageSrc: string,
  imageAlt: string,
  quantity: number
}

export class ProductModel implements Product {
  public id: string = '';

  constructor(public name: string,
              public price: string,
              public description: string,
              public imageSrc: string,
              public imageAlt: string,
              public quantity: number) {
  }
}
