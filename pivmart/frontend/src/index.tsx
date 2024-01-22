import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import * as serviceWorker from './serviceWorker';
import {Home} from "./pages/Home";
import {Gateway} from "./domain/Gateway";
import {CartModel} from "./domain/cart/CartModel";

const gateway = new Gateway();
const cartModel = new CartModel(gateway.cartApi);
cartModel.init();
gateway.init().then(categories => {
  ReactDOM.render(<Home gateway={gateway} categories={categories}
                        cartModel={cartModel}/>, document.getElementById('root'));
});


// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
