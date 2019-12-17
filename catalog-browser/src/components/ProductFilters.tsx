import * as React from "react";
import {Category} from "../entities/Category";
import {Dispatch} from "react";
import {SetStateAction} from "react";
import './ProductFilters.css'

interface Props {
  filterCategories: Category[];
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
            key={`${i}_${category.catalog}`}
            disabled={activeCategory === category.catalog}
            onClick={() => {
              setActiveFilterCategory(category.catalog);
            }}>
            {category.displayName}
          </button>
        )}
      </div>
    </div>
  );
};
