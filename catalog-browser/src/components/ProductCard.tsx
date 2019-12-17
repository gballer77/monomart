import * as React from "react";
import {Product} from "../entities/Product";

interface Props {
  product: Product;
}

export const ProductCard = (props: Props) => {
  const {name, price, description, imageSrc, imageAlt} = props.product;

  return (
    <section data-testid={"product-card"}>
      <h3>{name}</h3>
      <img src={imageSrc} alt={imageAlt}/>
      <h4>${price}</h4>
      <p>{description}</p>
    </section>
  );
};
