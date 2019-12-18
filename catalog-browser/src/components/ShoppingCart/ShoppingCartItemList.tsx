import * as React from "react";
import {Dispatch, SetStateAction} from "react";
import {CartModel} from "../../domain/cart/CartModel";
import {Product} from "../../domain/product/Product";

interface Props {
  cartModel: CartModel
  updateShowCart: Dispatch<SetStateAction<boolean>>
}

export function stringPriceToInt(amount: string): number {
  return parseInt(amount.replace('.', ''));
}

export function cartTotal(cart: Product[]) {
  const totalWithoutCents = cart.map(item => item.price)
    .reduce((accumulator, amount) => accumulator + stringPriceToInt(amount), 0);

  const totalWithCents = totalWithoutCents / 100;

  return totalWithCents.toFixed(2);
}

export const ShoppingCartItemList: React.FC<Props> = ({cartModel, updateShowCart}) => (
  <section className='cart-item-list'>
    <button
      className={'close-cart'}
      onClick={() => updateShowCart(false)}>&raquo;</button>
    <header>
      <h2>Cart</h2>
    </header>
    <ul>
      {cartModel.getItems().map((product, i) =>
        <li key={i}>
          {product.name} ({product.price}) <button className={'remove-item'}
                                                   onClick={() => cartModel.removeItem(i)}>&times;</button>
        </li>
      )}
    </ul>
    <button className={'cart-total'} onClick={() => cartModel.checkOut()}>
      Checkout ({cartTotal(cartModel.getItems())})
    </button>
  </section>
);
