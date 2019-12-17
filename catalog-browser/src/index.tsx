import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import * as serviceWorker from './serviceWorker';
import CategoryApi from "./api/CategoryApi";
import {ProductApi} from "./api/ProductApi";
import {Product} from "./entities/Product";
import {Category} from "./entities/Category";
import {Home} from "./pages/Home";

async function init(): Promise<{ categories: Category[], products: Product[] }> {
  const categories = await CategoryApi.getCategories();
  const products = await ProductApi.getProducts(categories[0].catalog);
  return {categories, products};
}

init().then(({categories, products}) => {
  ReactDOM.render(<Home categories={categories} products={products}/>, document.getElementById('root'));
});


// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
