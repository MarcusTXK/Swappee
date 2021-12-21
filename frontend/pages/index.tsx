import { useState } from 'react';
import { Button, Box } from '@material-ui/core';
import MCarousel from 'components/molecules/MCarousel';
import Link from 'next/link';
import Head from 'next/head';
import { useSelector } from 'react-redux';

import { AppState } from 'redux-saga/interfaces';
import AContainer1440 from 'components/atoms/AContainer1440';
import MCategoriesSection from 'components/molecules/MCategoriesSection';
import MItemListingContainer from 'components/molecules/MItemListingContainer';
import MAppBar from 'components/molecules/MAppBar';
import MSearchBar from 'components/molecules/MSearchBar';
import MLoginDialog from 'components/organisms/OLoginDialog';
import AFilledButton from 'components/atoms/AFilledButton';

const Home = () => {
  const [isLogin, setLogin] = useState(false);
  const {
    user: { token },
  } = useSelector((state: AppState) => state);

  return (
    <>
      <Head>
        <title>Swappee</title>
      </Head>
      <AContainer1440>
        <MAppBar />
        <MCarousel />
        <MCategoriesSection />
        <Box className="home-page-items" boxShadow={3}>
          <p>Recent Items</p>
          <MItemListingContainer isDataLoaded={true} onClick={() => console.log('Load detailed item')} />
          <Box className="home-page-items__button">
            <AFilledButton buttonText="View More!" onClick={() => console.log('Load more items')} />
          </Box>
        </Box>
      </AContainer1440>
      <MLoginDialog
        isOpen={isLogin}
        onClose={() => setLogin(false)}
        handleForgotPassword={() => console.log('Forgot Password')}
        handleSignup={() => console.log('Sign Up')}
      />
    </>
  );
};

export default Home;
