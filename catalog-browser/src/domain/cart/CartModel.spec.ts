import {CartModel} from "./CartModel";
import {Product} from "../product/Product";
import {CartApi} from "./CartApi";
import {CartItem} from "./CartItem";

describe('CartModel', () => {
  let cartModel: CartModel;
  let cartApi: CartApi;

  beforeEach(() => {
    cartApi = new CartApi();
    cartModel = new CartModel(cartApi);
  });

  test('init', () => {
    cartApi.list = jest.fn().mockReturnValue(Promise.resolve());
    cartModel.init();
    expect(cartApi.list).toHaveBeenCalled();
  });

  test('getItems', () => {
    // @ts-ignore
    cartModel.items = [{product: {name: 'foo'} as Product} as CartItem];

    expect(cartModel.getItems()[0].product.name).toEqual('foo');
  });

  test('addItem', async () => {
    let expectedProduct = {name: 'foo'} as Product;
    cartApi.add = jest.fn().mockReturnValue(Promise.resolve({id: 'real_id', product: expectedProduct}));

    await cartModel.addItem(expectedProduct);

    expect(cartModel.getItems().map(i => i.product.name)).toContain('foo');
    expect(cartApi.add).toHaveBeenCalled();
    expect(cartModel.getItems().map(i => i.id)).toContain('real_id');
  });

  test('removeItem', () => {
    cartApi.remove = jest.fn();

    cartModel.addItem({name: 'a'} as Product);
    cartModel.addItem({name: 'b'} as Product);
    cartModel.addItem({name: 'c'} as Product);
    cartModel.addItem({name: 'd'} as Product);

    cartModel.removeItem(2);

    expect(cartModel.getItems().map(i => i.product.name)).not.toContain('c');

    expect(cartApi.remove).toHaveBeenCalled();
  });

  test('checkOut', () => {
    cartApi.checkOut = jest.fn();

    cartModel.addItem({name: 'c'} as Product);
    cartModel.addItem({name: 'd'} as Product);

    cartModel.checkOut();

    expect(cartModel.getItems()).toEqual([]);
    expect(cartApi.checkOut).toHaveBeenCalled();
  });

  test('subscribe && updateListeners', () => {
    const spy = jest.fn();
    cartModel.subscribe(spy);

    cartModel.updateListeners();

    expect(spy).toHaveBeenCalled();
  });
});
