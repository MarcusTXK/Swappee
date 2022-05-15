import { Box, Button } from '@material-ui/core';
import type { NextPage } from 'next';
import Head from 'next/head';
import Image from 'next/image';
// import styles from '../styles/Home.module.css';
import MItemCard from 'components/molecules/MItemCard';
import MItemListingContainer from 'components/molecules/MItemListingContainer';
import { useState } from 'react';

const Testing: NextPage = () => {
  const [isDataLoaded, setIsDataLoaded] = useState(false);
  return (
    <div>
      <button onClick={() => setIsDataLoaded(!isDataLoaded)}>Change</button>
      <MItemListingContainer onClick={() => console.log('open')} isDataLoaded={isDataLoaded} />
      {/* <MItemCard hasAvatar = {false} isLiked = {true} isUsed = {false} isListing = {true} username = {"biscuitTheDog"} title = "Apple For SaleSaleSale SaleSaleSale" ></MItemCard>

   <MItemCard hasAvatar = {false} isLiked = {false} isUsed = {true} isListing = {true} 
   username = {"yanhanapple<3"} title = "Apple For Sale" ></MItemCard> */}
    </div>
  );
};

export default Testing;
