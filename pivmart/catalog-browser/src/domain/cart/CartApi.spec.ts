import {CartApi} from "./CartApi";
import {mockFetch} from "../mockFetch";
import {Product} from "../product/Product";
import {CartItem} from "./CartItem";

describe('CartApi', () => {
  let cartApi: CartApi;

  beforeEach(() => {
    cartApi = new CartApi();
  });

  test('list', async () => {
    mockFetch([{} as Product]);
    const cart = await cartApi.list();

    expect(cart).toEqual([{}]);
  });

  test('add', async () => {
    mockFetch({id: 'real_id'} as CartItem);

    const product = await cartApi.add({} as Product);

    expect(window.fetch).toHaveBeenCalled();
    expect(product.id).toEqual('real_id');
  });

  test('remove', async () => {
    window.fetch = jest.fn().mockReturnValue(Promise.resolve({json: () => {}}));

    await cartApi.remove('');

    expect(window.fetch).toHaveBeenCalled();
  });

  test('checkOut', async () => {
    window.fetch = jest.fn().mockReturnValue(Promise.resolve({json: () => {}}));

    await cartApi.checkOut();

    expect(window.fetch).toHaveBeenCalled();
  });
});
