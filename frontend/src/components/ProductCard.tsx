import * as React from "react";
import {Product} from "../domain/product/Product";
import './ProductCard.css';

interface Props {
  product: Product;
  addToCart: (product: Product) => void;
}

export const ProductCard = (props: Props) => {
  const {name, price, description, imageSrc, imageAlt, quantity} = props.product;
  return (
    <section className="product-card" aria-label={name}>
      <header>
        <h3>{name}</h3>
      </header>
      <main>
        <img src={imageSrc} alt={imageAlt}/>
        <p className="description">{description}</p>
        <p className="price">${price}</p>
        <p className="quantity">Qty: {quantity}</p>
      </main>
      <footer>
        <button
          onClick={() => props.addToCart(props.product)}
          className="add-to-cart">
          Add to cart
        </button>
      </footer>
    </section>
  );
};
