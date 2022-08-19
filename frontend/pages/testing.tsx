import { Box, Button } from '@material-ui/core';
import type { NextPage } from 'next';
// import styles from '../styles/Home.module.css';
import MItemCard from 'components/molecules/MItemCard';
import MItemListingContainer from 'components/molecules/MItemListingContainer';
import { useState } from 'react';
import { ItemData } from 'redux-saga/interfaces/data.interfaces';

const Testing: NextPage = () => {
  const [isDataLoaded, setIsDataLoaded] = useState(false);
  const [data, setData] = useState<ItemData[]>([
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
  ]);
  const [page, setPage] = useState<number>(1);

  return (
    <div>
      <button onClick={() => setIsDataLoaded(!isDataLoaded)}>Change</button>
      <MItemListingContainer
        onClick={() => console.log('open')}
        isDataLoaded={isDataLoaded}
        data={data}
        page={page}
        numItemCardsInOnePage={9}
      />
      {/* <MItemCard hasAvatar = {false} isLiked = {true} isUsed = {false} isListing = {true} username = {"biscuitTheDog"} title = "Apple For SaleSaleSale SaleSaleSale" ></MItemCard>

   <MItemCard hasAvatar = {false} isLiked = {false} isUsed = {true} isListing = {true} 
   username = {"yanhanapple<3"} title = "Apple For Sale" ></MItemCard> */}
    </div>
  );
};

export default Testing;
