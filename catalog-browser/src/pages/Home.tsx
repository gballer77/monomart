import * as React from "react";
import {useEffect, useState} from "react";
import {Product} from "../domain/product/Product";
import {ProductCard} from "../components/ProductCard";
import {ProductFilters} from "../components/ProductFilters";
import {Catalog} from "../domain/category/Catalog";
import {Gateway} from "../domain/Gateway";
import './Home.css';

interface Props {
  gateway: Gateway,
  categories: Catalog[],
}

export const Home: React.FC<Props> = ({categories, gateway}) => {
  const {productApi} = gateway;
  const catalog = (categories && categories[0] && categories[0].catalogKey) || '';
  const [activeCategory, setActiveCategory] = useState<string>(catalog);
  const [currentProducts, setCurrentProducts] = useState<Product[]>([]);

  useEffect(() => {
    productApi.getProducts(activeCategory)
      .then(newProducts => {
        setCurrentProducts(newProducts);
      });
  }, [productApi, activeCategory]);

  return (
    <main>
      <h1 className={"title"}>Welcome to the Workshop Shop!</h1>

      {categories.length > 0 && (
        <ProductFilters
          filterCategories={categories}
          activeCategory={activeCategory}
          setActiveFilterCategory={setActiveCategory}
        />
      )}

      <section className="product-catalog">
        {currentProducts.map((product: Product, i) => <ProductCard key={i} product={product}/>)}
      </section>
    </main>
  );
};

