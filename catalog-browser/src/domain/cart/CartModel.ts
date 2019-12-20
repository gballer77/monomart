import {Product} from "../product/Product";
import {Dispatch, SetStateAction} from "react";
import {CartApi} from "./CartApi";
import {CartItem} from "./CartItem";

type Subscription = Dispatch<SetStateAction<string>>

export class CartModel {
  constructor(private cartApi: CartApi = new CartApi()) {
  }

  private listeners: Subscription[] = [];
  private items: CartItem[] = [];

  init() {
    this.cartApi.list()
      .then(cart => {
        this.items = cart;
        this.updateListeners();
      });
  }

  getItems() {
    return this.items;
  }

  addItem(product: Product) {
    const temporaryId = new Date().toISOString();
    this.items.push({product, id: temporaryId});
    this.cartApi.add(product)
      .then(cartItem => {
        const index = this.items.findIndex(item => item.id === temporaryId);
        this.items[index] = cartItem;
      });

    this.updateListeners();
  }

  removeItem(index: number) {
    const cartItems = this.items.splice(index, 1);
    if (cartItems.length > 0) {
      this.cartApi.remove(cartItems[0].id)
        .catch(e => console.error(e));
    }

    this.updateListeners();
  }

  checkOut() {
    this.items = [];
    this.cartApi.checkOut();
    this.updateListeners();
  }

  updateListeners() {
    this.listeners.forEach(update => update(JSON.stringify(this.items)));
  }

  subscribe(onUpdate: Subscription): number {
    const item = this.listeners.push(onUpdate);

    return item - 1;
  }

  unsubscribe(subscription: number) {
    this.listeners.splice(subscription, 1);
  }
}
