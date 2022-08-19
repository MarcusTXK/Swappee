import { Box, Paper, Divider, MenuItem, Radio, FormControlLabel, RadioGroup, Select } from '@material-ui/core';
import { ChangeEvent, FC, ReactNode } from 'react';

interface MProfileListingMenuProps {
  sortBy: string;
  handleSelectSortBy: (
    event: ChangeEvent<{
      name?: string | undefined;
      value: unknown;
    }>,
    child: ReactNode,
  ) => void;
  category: string;
  handleSelectCateogry: (
    event: ChangeEvent<{
      name?: string | undefined;
      value: unknown;
    }>,
    child: ReactNode,
  ) => void;
  condition: string;
  handleCondition: (
    event: ChangeEvent<{
      name?: string | undefined;
      value: unknown;
    }>,
    child: ReactNode,
  ) => void;
  tradeType: string;
  handleTradeType: (
    event: ChangeEvent<{
      name?: string | undefined;
      value: unknown;
    }>,
    child: ReactNode,
  ) => void;
}
const MProfileListingMenu: FC<MProfileListingMenuProps> = ({
  sortBy,
  handleSelectSortBy,
  category,
  handleSelectCateogry,
  condition,
  handleCondition,
  tradeType,
  handleTradeType,
  ...others
}) => {
  return (
    <Paper variant="outlined" className="o-profilepage-listing-menu">
      <Box dir="row">
        <Box>Sort by</Box>
        <Select variant="outlined" value={sortBy} onChange={handleSelectSortBy}>
          <MenuItem value="newest">Newest First</MenuItem>
          <MenuItem value="oldest">Oldest First</MenuItem>
        </Select>
        <Box padding={2}>
          <Divider variant="fullWidth" />
        </Box>

        <Box pb={3}>Filter</Box>
        <Box>Category</Box>
        <Select variant="outlined" value={category} onChange={handleSelectCateogry}>
          <MenuItem value={10}>Ten</MenuItem>
          <MenuItem value={20}>Twenty</MenuItem>
          <MenuItem value={30}>Thirty</MenuItem>
        </Select>

        <Box pt={3}>Condition</Box>
        <RadioGroup value={condition} onChange={handleCondition}>
          <FormControlLabel value="new" control={<Radio />} label="New" />
          <FormControlLabel value="used" control={<Radio />} label="Used" />
        </RadioGroup>

        <Box pt={3}>Trade type</Box>
        <RadioGroup value={tradeType} onChange={handleTradeType}>
          <FormControlLabel value="strict" control={<Radio />} label="Strict" />
          <FormControlLabel value="open_to_all" control={<Radio />} label="Open to all" />
        </RadioGroup>
      </Box>
    </Paper>
  );
};

export default MProfileListingMenu;
