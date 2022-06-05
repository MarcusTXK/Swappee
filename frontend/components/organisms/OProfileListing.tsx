import { Box, Paper } from '@material-ui/core';
import { Pagination } from '@material-ui/lab';
import MItemListingContainer from 'components/molecules/MItemListingContainer';
import MSearchBar from 'components/molecules/MSearchBar';
import { ChangeEvent, SetStateAction, useState } from 'react';

const OProfileListing = () => {
  const [page, setPage] = useState<number>(1);
  const [input, setInput] = useState<string>('');

  const handleChange = (event: ChangeEvent<unknown>, page: number) => {
    setPage(page);
  };

  const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    setInput(event.target.value);
  };

  const handleSearch = () => {
    alert(input);
  };

  return (
    <Paper variant="outlined" className="o-profilepage-listing">
      <Box dir="row">
        <Box className="o-profilepage-listing-search">
          <MSearchBar variant="profile_page" input={input} handleInput={handleInput} handleSearch={handleSearch} />
        </Box>
        <MItemListingContainer isDataLoaded={true} onClick={() => console.log('Load detailed item')} />
        <Pagination count={10} page={page} onChange={handleChange} className="o-profilepage-listing-pagination" />
      </Box>
    </Paper>
  );
};

export default OProfileListing;
