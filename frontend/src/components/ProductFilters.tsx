import * as React from "react";
import {Dispatch, SetStateAction} from "react";
import {Catalog} from "../domain/catalog/Catalog";
import './ProductFilters.css'

interface Props {
  filterCategories: Catalog[];
  activeCategory: string;
  setActiveFilterCategory: Dispatch<SetStateAction<string>>;
}

export const ProductFilters = (props: Props) => {
  const {filterCategories, activeCategory, setActiveFilterCategory} = props;

  return (
    <div className="filters">
      <h3>Filters:</h3>
      <div>
        {filterCategories.map((category, i) =>
          <button
            key={`${i}_${category.id}`}
            disabled={activeCategory === category.id}
            onClick={() => {
              setActiveFilterCategory(category.id);
            }}>
            {category.displayName}
          </button>
        )}
      </div>
    </div>
  );
};
