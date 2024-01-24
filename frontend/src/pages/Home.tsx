import * as React from "react";
import {useEffect, useState} from "react";
import {Product} from "../domain/product/Product";
import {ProductCard} from "../components/ProductCard";
import {ProductFilters} from "../components/ProductFilters";
import {Catalog} from "../domain/catalog/Catalog";
import {Gateway} from "../domain/Gateway";
import './Home.css';
import {ShoppingCart} from "../components/ShoppingCart";
import {CartModel} from "../domain/cart/CartModel";
import {getDefaultCatalog} from "../domain/catalog/CatalogApi";
import {useCartModel} from "../domain/cart/useCartModel";

interface Props {
  gateway: Gateway,
  categories: Catalog[],
  cartModel: CartModel,
}

export const Home: React.FC<Props> = ({categories, gateway, cartModel}) => {
  const {productApi} = gateway;
  const catalog = getDefaultCatalog(categories);
  const [activeCategory, setActiveCategory] = useState<string>(catalog);
  const [currentProducts, setCurrentProducts] = useState<Product[]>([]);

  useEffect(() => {
    productApi.getProducts(activeCategory)
      .then(newProducts => {
        setCurrentProducts(newProducts);
      });
  });

  useCartModel(cartModel);

  return (
    <main>
      <ShoppingCart cartModel={cartModel}/>

      <header>
        <h1 className={"title"}>Monomart</h1>
      </header>

      {categories.length > 0 && (
        <ProductFilters
          filterCategories={categories}
          activeCategory={activeCategory}
          setActiveFilterCategory={setActiveCategory}
        />
      )}

      <section className="product-catalog">
        {currentProducts.map((product: Product, i) =>
          <ProductCard
            addToCart={(p) => {
              cartModel.addItem(p);
            }}
            key={i}
            product={product}/>
        )}
      </section>
    </main>
  );
};

