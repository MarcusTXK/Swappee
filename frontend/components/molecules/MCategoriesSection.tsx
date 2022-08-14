import React, { FC } from 'react';
import { Box } from '@material-ui/core';
import img from 'public/passportphoto.jpg';
import ACategories from '../atoms/ACategories';
import { Category } from 'config/constants';

interface MCategoriesSectionProps {
  categories: Category[];
}

const MCategoriesSection: FC<MCategoriesSectionProps> = ({ categories, ...others }) => {
  return (
    <div>
      <Box className="m-categories-section" boxShadow={3}>
        Explore Categories
        <div className="m-categories-section__categories">
          {categories.map((category) => (
            <ACategories key={category.categoryName} categoryName={category.categoryName} img={category.image} />
          ))}
        </div>
      </Box>
    </div>
  );
};

export default MCategoriesSection;
