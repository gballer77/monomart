import * as React from "react";
import {useState} from "react";
import './ShoppingCart.css';
import {ShoppingCartSummary} from "./ShoppingCartSummary";
import {ShoppingCartItemList} from "./ShoppingCartItemList";
import {CartModel} from "../../domain/cart/CartModel";

interface Props {
  cartModel: CartModel
}

export const ShoppingCart: React.FC<Props> = ({cartModel}) => {
  const [showCart, updateShowCart] = useState<boolean>(false);

  return (
    <section className='shopping-cart'>
      {!showCart ?
        <ShoppingCartSummary
          itemCount={cartModel.getItems().length}
          updateShowCart={updateShowCart}/>
        :
        <ShoppingCartItemList
          cartModel={cartModel}
          updateShowCart={updateShowCart}/>
      }
    </section>
  );
};
