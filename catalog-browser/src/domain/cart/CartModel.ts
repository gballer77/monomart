import {Product} from "../product/Product";
import {Dispatch, SetStateAction} from "react";

type Subscription = Dispatch<SetStateAction<string>>

export class CartModel {
  private listeners: Subscription[] = [];
  private items: Product[] = [];

  getItems() {
    return this.items;
  }

  addItem(product: Product) {
    this.items.push(product);
    this.updateListeners();
  }

  removeItem(index: number) {
    this.items.splice(index, 1);
    this.updateListeners();
  }

  checkOut() {
    this.items = [];
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
