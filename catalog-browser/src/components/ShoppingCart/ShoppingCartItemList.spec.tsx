import * as React from "react";
import {render} from "@testing-library/react";
import {cartTotal, ShoppingCartItemList, stringPriceToInt} from "./ShoppingCartItemList";
import {CartModel} from "../../domain/cart/CartModel";
import {Product} from "../../domain/product/Product";
import {CartItem} from "../../domain/cart/CartItem";

function makeProduct(name: string = '', price: string = '19.99') {
  return {name, price} as Product;
}

describe('ShoppingCartItemList', () => {
  it('displays a list of items', () => {
    const cartModel = new CartModel();
    cartModel.addItem(makeProduct('foo'));
    cartModel.addItem(makeProduct('bar'));
    const {getByText} = render(<ShoppingCartItemList cartModel={cartModel} updateShowCart={jest.fn()}/>);

    getByText(/foo/i);
    getByText(/bar/i);
  });

  it('calls updateShowCart when the hide button is clicked', () => {
    const updateShowCart = jest.fn();
    const {container} = render(<ShoppingCartItemList cartModel={new CartModel()} updateShowCart={updateShowCart}/>);
    let buttons = container.getElementsByTagName('button');

    expect(buttons.length).toBeGreaterThan(0);

    buttons[0].click();

    expect(updateShowCart).toHaveBeenCalled();
  });

  it('calls removeItem when X is clicked', () => {
    const cartModel = new CartModel();
    cartModel.removeItem = jest.fn();
    cartModel.addItem(makeProduct('foo'));
    const {container} = render(<ShoppingCartItemList cartModel={cartModel} updateShowCart={jest.fn()}/>);
    const listItems = container.getElementsByTagName('li');

    expect(listItems).toHaveLength(1);

    const itemButtons = listItems[0].getElementsByTagName('button');

    expect(itemButtons).toHaveLength(1);

    itemButtons[0].click();

    expect(cartModel.removeItem).toHaveBeenCalled();
  });

  it('calls checkOut when Checkout button is clicked', () => {
    const cartModel = new CartModel();
    cartModel.checkOut = jest.fn();
    const {getByText} = render(<ShoppingCartItemList cartModel={cartModel} updateShowCart={jest.fn()}/>);
    let checkoutButton = getByText(/checkout/i);
    checkoutButton.click();
    expect(cartModel.checkOut).toHaveBeenCalled();
  });

  it('displays the sum of the prices of the items in the cart', () => {
    const cartModel = new CartModel();
    cartModel.addItem(makeProduct('', '5.99'));
    cartModel.addItem(makeProduct('', '5.99'));
    const {getByText} = render(<ShoppingCartItemList cartModel={cartModel} updateShowCart={jest.fn()}/>);
    let checkoutButton = getByText(/checkout/i);
    expect(checkoutButton).toHaveTextContent('11.98');
  });
});

test('stringPriceToInt', () => {
  expect(stringPriceToInt('11.97')).toEqual(1197);
});

function makeCartItem(product: Product): CartItem {
  return {
    product,
    id: '',
  }
}

test('cartTotal', () => {

  const total = cartTotal([
    makeCartItem(makeProduct('', '1.43')),
    makeCartItem(makeProduct('', '2.15'))
  ]);

  expect(total).toEqual('3.58');
});
