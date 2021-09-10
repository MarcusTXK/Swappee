import { useState } from 'react';
import { Button, Box } from '@material-ui/core';
import Link from 'next/link';
import Head from 'next/head';
import { useSelector } from 'react-redux';

import { AppState } from 'redux-saga/interfaces';
import AContainer1440 from 'components/atoms/AContainer1440';
import MSampleSearchBar from 'components/molecules/MSampleSearchBar';
import MLoginDialog from 'components/organisms/OLoginDialog';

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
            <MSampleSearchBar />
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
