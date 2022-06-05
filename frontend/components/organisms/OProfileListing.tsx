import { Box, Paper } from '@material-ui/core';
import { Pagination } from '@material-ui/lab';
import MItemListingContainer from 'components/molecules/MItemListingContainer';
import MSearchBar from 'components/molecules/MSearchBar';
import { ChangeEvent, SetStateAction, useState } from 'react';
import { ItemData } from 'redux-saga/interfaces';

const OProfileListing = () => {
  const [page, setPage] = useState<number>(1);
  const [input, setInput] = useState<string>('');
  const dummyData = [
    {
      id: 1,
      imagePath: 3,
      name: 'Apple For Sale',
      status: '',
      brand: '',
      description: '',
      likes: 3,
      liked: false,
      userId: 3,
      userName: 'yanhanapple<3',
      createdDate: '',
      lastModifiedDate: '',
    },
    {
      id: 2,
      imagePath: 3,
      name: 'Bananas',
      status: '',
      brand: '',
      description: '',
      likes: 2,
      liked: false,
      userId: 1,
      userName: 'tauple',
      createdDate: '',
      lastModifiedDate: '',
    },
    {
      id: 3,
      imagePath: 3,
      name: 'Apple For Sale',
      status: '',
      brand: '',
      description: '',
      likes: 2,
      liked: false,
      userId: 1,
      userName: 'yanhanapple<3',
      createdDate: '',
      lastModifiedDate: '',
    },
    {
      id: 4,
      imagePath: 3,
      name: 'Apple For Sale',
      status: '',
      brand: '',
      description: '',
      likes: 2,
      liked: false,
      userId: 1,
      userName: 'yanhanapple<3',
      createdDate: '',
      lastModifiedDate: '',
    },
    {
      id: 5,
      imagePath: 3,
      name: 'Oranges',
      status: '',
      brand: '',
      description: '',
      likes: 2,
      liked: false,
      userId: 1,
      userName: 'yanhanapple<3',
      createdDate: '',
      lastModifiedDate: '',
    },
    {
      id: 6,
      imagePath: 3,
      name: 'Oranges',
      status: '',
      brand: '',
      description: '',
      likes: 2,
      liked: false,
      userId: 1,
      userName: 'yanhanapple<3',
      createdDate: '',
      lastModifiedDate: '',
    },
    {
      id: 7,
      imagePath: 3,
      name: 'Oranges',
      status: '',
      brand: '',
      description: '',
      likes: 2,
      liked: false,
      userId: 1,
      userName: 'yanhanapple<3',
      createdDate: '',
      lastModifiedDate: '',
    },
    {
      id: 8,
      imagePath: 3,
      name: 'Oranges',
      status: '',
      brand: '',
      description: '',
      likes: 2,
      liked: false,
      userId: 1,
      userName: 'yanhanapple<3',
      createdDate: '',
      lastModifiedDate: '',
    },
    {
      id: 9,
      imagePath: 3,
      name: 'Banana',
      status: '',
      brand: '',
      description: '',
      likes: 2,
      liked: false,
      userId: 1,
      userName: 'yanhanapple<3',
      createdDate: '',
      lastModifiedDate: '',
    },
    {
      id: 10,
      imagePath: 3,
      name: 'Banana',
      status: '',
      brand: '',
      description: '',
      likes: 2,
      liked: false,
      userId: 1,
      userName: 'yanhanapple<3',
      createdDate: '',
      lastModifiedDate: '',
    },
    {
      id: 11,
      imagePath: 3,
      name: 'Oranges',
      status: '',
      brand: '',
      description: '',
      likes: 2,
      liked: false,
      userId: 1,
      userName: 'yanhanapple<3',
      createdDate: '',
      lastModifiedDate: '',
    },
  ];
  const numItemCardsInOnePage = 9;

  const [data, setData] = useState<ItemData[]>(dummyData);

  const handleChange = (event: ChangeEvent<unknown>, page: number) => {
    setPage(page);
  };

  const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    setInput(event.target.value);
  };

  const handleSearch = () => {
    let filteredData = dummyData.filter((item) => {
      return item.name.toLowerCase().includes(input.toLowerCase());
    });
    setData(filteredData);
    console.log(filteredData);
  };

  return (
    <Paper variant="outlined" className="o-profilepage-listing">
      <Box dir="row">
        <Box className="o-profilepage-listing-search">
          <MSearchBar variant="profile_page" input={input} handleInput={handleInput} handleSearch={handleSearch} />
        </Box>
        <MItemListingContainer
          isDataLoaded={true}
          data={data}
          onClick={() => console.log('Load detailed item')}
          page={page}
          numItemCardsInOnePage={numItemCardsInOnePage}
        />
        <Pagination
          count={Math.ceil(data.length / numItemCardsInOnePage)}
          page={page}
          onChange={handleChange}
          className="o-profilepage-listing-pagination"
        />
      </Box>
    </Paper>
  );
};

export default OProfileListing;
