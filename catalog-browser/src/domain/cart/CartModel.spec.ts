import {CartModel} from "./CartModel";
import {Product} from "../product/Product";

describe('CartModel', () => {
  let cartModel: CartModel;

  beforeEach(() => {
    cartModel = new CartModel();
  });

  test('getItems', () => {
    // @ts-ignore
    cartModel.items = [{name: 'foo'} as Product];

    expect(cartModel.getItems()[0].name).toEqual('foo');
  });

  test('addItem', () => {
    cartModel.addItem({name: 'foo'} as Product);

    expect(cartModel.getItems()).toEqual([{name: 'foo'}]);
  });

  test('removeItem', () => {
    cartModel.addItem({name: 'a'} as Product);
    cartModel.addItem({name: 'b'} as Product);
    cartModel.addItem({name: 'c'} as Product);
    cartModel.addItem({name: 'd'} as Product);

    cartModel.removeItem(2);

    expect(cartModel.getItems().map(i => i.name)).not.toContain('c');
  });

  test('checkOut', () => {
    cartModel.addItem({name: 'c'} as Product);
    cartModel.addItem({name: 'd'} as Product);

    cartModel.checkOut();

    expect(cartModel.getItems()).toEqual([]);
  });

  test('subscribe && updateListeners', () => {
    const spy = jest.fn();
    cartModel.subscribe(spy);

    cartModel.updateListeners();

    expect(spy).toHaveBeenCalled();
  });
});
