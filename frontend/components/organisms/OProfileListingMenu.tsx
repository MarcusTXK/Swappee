import { Box, Paper, Divider, MenuItem, Radio, FormControlLabel, RadioGroup, Select } from '@material-ui/core';
import { useState, ChangeEvent, SetStateAction } from 'react';

const OProfileListingMenu = () => {
  const [sortBy, setSortBy] = useState('newest');
  const [category, setCategory] = useState('');
  const [condition, setCondition] = useState('new');
  const [tradeType, setTradeType] = useState('strict');

  const handleSelectSortBy = (event: React.ChangeEvent<{ value: unknown }>) => {
    setSortBy(event.target.value as SetStateAction<string>);
  };

  const handleSelectCateogry = (event: React.ChangeEvent<{ value: unknown }>) => {
    setCategory(event.target.value as SetStateAction<string>);
  };

  const handleCondition = (event: React.ChangeEvent<HTMLInputElement>) => {
    setCondition(event.target.value);
  };

  const handleTradeType = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTradeType(event.target.value);
  };

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

export default OProfileListingMenu;
