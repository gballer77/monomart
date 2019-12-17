import * as React from "react";
import {useEffect, useState} from "react";
import {Product} from "../entities/Product";
import {ProductCard} from "../components/ProductCard";
import {ProductApi} from "../api/ProductApi";
import {ProductFilters} from "../components/ProductFilters";
import {Category} from "../entities/Category";
import './Home.css';

interface Props {
  products: Product[],
  categories: Category[]
}

export const Home: React.FC<Props> = ({products, categories}) => {
  const catalog = categories && categories[0] && categories[0].catalog || '';
  const [activeCategory, setActiveCategory] = useState(catalog);
  const [currentProducts, setCurrentProducts] = useState(products);

  useEffect(() => {
    ProductApi.getProducts(activeCategory)
      .then(newProducts => {
        setCurrentProducts(newProducts);
      });
  }, [activeCategory]);

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
        {currentProducts.map((product: Product) =>
          <ProductCard
            key={`${product.name}-${product.price}`}
            product={product}
          />
        )}
      </section>
    </main>
  );
};

