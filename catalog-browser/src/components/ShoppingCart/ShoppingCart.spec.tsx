import * as React from "react";
import {render} from "@testing-library/react";
import {ShoppingCart} from "./ShoppingCart";
import {CartModel} from "../../domain/cart/CartModel";

describe('ShoppingCart', () => {
  it('displays ShoppingCartSummary by default', () => {
    const {getByText} = render(<ShoppingCart cartModel={new CartModel()}/>);
    getByText('🛒');
  });

  it('shows ShoppingCartItemList when shopping cart button is clicked', () => {
    const {getByText} = render(<ShoppingCart cartModel={new CartModel()}/>);

    let shoppingCartButton = getByText('🛒');

    shoppingCartButton.click();

    getByText(/cart/i);
  });

  it('shows ShoppingCartSummary hide button is clicked in ItemList', () => {
    const {getByText} = render(<ShoppingCart cartModel={new CartModel()}/>);

    const shoppingCartButton = getByText('🛒');

    shoppingCartButton.click();

    const hideButton = getByText('»');

    hideButton.click();

    getByText('🛒');
  });
});
