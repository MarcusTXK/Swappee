import { useState } from 'react';
import { Button, Box } from '@material-ui/core';
import MCarousel from 'components/molecules/MCarousel';
import Link from 'next/link';
import Head from 'next/head';
import { useSelector } from 'react-redux';

import { AppState } from 'redux-saga/interfaces';
import AContainer1440 from 'components/atoms/AContainer1440';
<<<<<<< HEAD
import OAppBar from 'components/organisms/OAppBar';
=======
import MCategoriesSection from 'components/molecules/MCategoriesSection';
import MItemListingContainer from 'components/molecules/MItemListingContainer';
import MAppBar from 'components/molecules/MAppBar';
import MSearchBar from 'components/molecules/MSearchBar';
import MLoginDialog from 'components/organisms/OLoginDialog';
>>>>>>> 9cd736496a8693f9e277008c2c85a8ba590e9071

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
      <OAppBar />
      <AContainer1440>
        <MAppBar />
        <MCarousel />
        <MCategoriesSection />
        <div className="home-container">
          <MItemListingContainer isDataLoaded={true} />
        </div>
        <Box display="flex" alignItems="center" flexDirection="column" width="100%" pt={4}>
          <h1>
            Welcome to{' '}
            <Link href="https://github.com/MarcusTXK/Swappee">
              <a>Swappee!</a>
            </Link>
          </h1>
          <Box component="p" my={4}>
            A platform allowing users in a community to list items online and trade.
          </Box>
          <Box mb={4}>
            <OAppBar />
          </Box>
          <Box display="flex">
            <Box mr={2}>
              <Button
                color="primary"
                variant="contained"
                onClick={() => (token ? console.log('Trade') : setLogin(true))}
              >
                {token ? 'Trade' : 'Login'}
              </Button>
            </Box>
            <Button
              color="primary"
              variant="outlined"
              onClick={() => (token ? console.log('Menu') : console.log('Register'))}
            >
              {token ? 'Menu' : 'Register'}
            </Button>
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
