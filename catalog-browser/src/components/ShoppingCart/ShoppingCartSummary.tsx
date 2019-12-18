import * as React from "react";
import {Dispatch, SetStateAction} from "react";

interface Props {
  itemCount: number
  updateShowCart: Dispatch<SetStateAction<boolean>>;
}

export const ShoppingCartSummary: React.FC<Props> = ({itemCount, updateShowCart}) => (
  <div className='summary'>
    <div className="item-count">{itemCount}</div>
    <button
      className={'show-cart'}
      onClick={() => updateShowCart(true)}>
      <span role="img" aria-label="show shopping cart">🛒</span>
    </button>
  </div>
);
