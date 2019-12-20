import {useEffect, useState} from "react";
import {CartModel} from "./CartModel";
import {CartItem} from "./CartItem";

export function useCartModel(cartModel: CartModel): CartItem[] {
  const [, updateCart] = useState<string>(JSON.stringify(cartModel.getItems()));
  useEffect(() => {
    const subscription = cartModel.subscribe(updateCart);

    return () => cartModel.unsubscribe(subscription);
  }, [cartModel]);

  return cartModel.getItems();
}
