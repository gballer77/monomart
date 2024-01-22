import * as React from "react";
import {render} from "@testing-library/react";
import {ShoppingCartSummary} from "./ShoppingCartSummary";

describe('ShoppingCartSummary', () => {
  it('displays a count of items', () => {
    let {getByText} = render(<ShoppingCartSummary itemCount={4} updateShowCart={jest.fn()}/>);

    getByText('4');
  });

  it('triggers updateShowCart when the shopping cart icon is clicked', () => {
    let spy = jest.fn();
    let {container} = render(<ShoppingCartSummary itemCount={0} updateShowCart={spy}/>);

    let buttons = container.getElementsByTagName('button');

    expect(buttons.length).toEqual(1);
    buttons[0].click();

    expect(spy).toHaveBeenCalled();
  });
});
