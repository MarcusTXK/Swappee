import { FC } from 'react';
import { Box } from '@material-ui/core';

interface ACategoryPillProps {
  category: string;
}

const ACategoryPill: FC<ACategoryPillProps> = ({ category }) => {
  return (
    <Box className="a-category-pill">
      <p className="a-category-pill__categoryname">{category}</p>
    </Box>
  );
};

export default ACategoryPill;
