import * as React from "react";
import {CartModel} from "./CartModel";
import {useCartModel} from "./useCartModel";
import {render} from "@testing-library/react";

interface Props {
  cartModel: CartModel,
  renderSpy: () => void
}

const TestComponent: React.FC<Props> = ({cartModel, renderSpy}) => {
  renderSpy();
  const cart = useCartModel(cartModel);
  return <ul>{cart.map(i => <li>{i.product.name}</li>)}</ul>
};

describe('useCartModel', () => {
  it('rerenders when cart updates subscribers', () => {
    const cartModel = new CartModel();
    const renderSpy = jest.fn();
    render(<TestComponent cartModel={cartModel} renderSpy={renderSpy}/>);

    // @ts-ignore
    expect(cartModel.listeners.length).toEqual(1);

    cartModel.updateListeners();

    expect(renderSpy).toHaveBeenCalled();
  });

  it('unsubscribes when component unmounts', () => {
    const cartModel = new CartModel();
    let container = render(<TestComponent cartModel={cartModel} renderSpy={jest.fn()}/>);

    // @ts-ignore
    expect(cartModel.listeners.length).toEqual(1);

    container.unmount();

    // @ts-ignore
    expect(cartModel.listeners.length).toEqual(0);
  });
});
