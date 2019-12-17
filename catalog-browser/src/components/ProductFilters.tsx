import * as React from "react";
import {Catalog} from "../domain/category/Catalog";
import {Dispatch} from "react";
import {SetStateAction} from "react";
import './ProductFilters.css'

interface Props {
  filterCategories: Catalog[];
  activeCategory: string;
  setActiveFilterCategory: Dispatch<SetStateAction<string>>;
}

export const ProductFilters = (props: Props) => {
  const {filterCategories, activeCategory, setActiveFilterCategory} = props;

  // const filterClickHandler = (categoryId: string) => () => {
  //     console.log('was clicked', categoryId);
  //     setActiveFilterCategory(categoryId);
  // };

  return (
    <div className="filters">
      <h3>Filters:</h3>
      <div>
        {filterCategories.map((category, i) =>
          <button
            key={`${i}_${category.catalogKey}`}
            disabled={activeCategory === category.catalogKey}
            onClick={() => {
              setActiveFilterCategory(category.catalogKey);
            }}>
            {category.displayName}
          </button>
        )}
      </div>
    </div>
  );
};
