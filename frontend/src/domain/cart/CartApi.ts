import {CartItem} from "./CartItem";
import {Product} from "../product/Product";

export class CartApi {
  list(): Promise<CartItem[]> {
    return fetch('/api/v1/cart')
      .then(response => response.json());
  }

  add(product: Product): Promise<CartItem> {
    return fetch('/api/v1/cart', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(product)
    })
      .then(response => response.json());
  }

  remove(id: string): Promise<void> {
    return fetch(`/api/v1/cart/${id}`, {method: 'PUT'})
      .then(response => response.json());
  }

  checkOut() {
    fetch('/api/v1/cart/checkout', {method: 'POST'});
  }
}
