import {Product} from "../product/Product";
import {useEffect, useState} from "react";
import {CartModel} from "./CartModel";

export function useCartModel(cartModel: CartModel): Product[] {
  const [, updateCart] = useState<string>(JSON.stringify(cartModel.getItems()));
  useEffect(() => {
    const subscription = cartModel.subscribe(updateCart);

    return () => cartModel.unsubscribe(subscription);
  }, [cartModel]);

  return cartModel.getItems();
}
