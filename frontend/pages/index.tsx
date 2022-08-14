import { useState, useEffect } from 'react';
import { Box } from '@material-ui/core';
import MCarousel from 'components/molecules/MCarousel';
import Head from 'next/head';
import { useSelector, useDispatch } from 'react-redux';

import { AppState } from 'redux-saga/interfaces';
import { getItemList } from 'redux-saga/actions';
import AContainer1440 from 'components/atoms/AContainer1440';
import MCategoriesSection from 'components/molecules/MCategoriesSection';
import MItemListingContainer from 'components/molecules/MItemListingContainer';
import MLoginDialog from 'components/organisms/OLoginDialog';
import AFilledButton from 'components/atoms/AFilledButton';
import { numItemCardsInOnePage } from 'config/constants';

import books from '../public/categories/books.jpg';
import coll from '../public/categories/collectables.jpg';
import electronics from '../public/categories/electronics.jpg';
import fashion from '../public/categories/fashion.jpg';
import games from '../public/categories/games.jpg';
import others from '../public/categories/others.jpg';

const Home = () => {
  const [isLogin, setLogin] = useState(true);
  const [pagesShown, setPagesShown] = useState(1);
  const { items } = useSelector((state: AppState) => state);
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getItemList());
  }, []);

  const categories = [
    {
      categoryName: 'books',
      image: books,
    },
    {
      categoryName: 'collectibles',
      image: coll,
    },
    {
      categoryName: 'electronics',
      image: electronics,
    },
    {
      categoryName: 'fashion',
      image: fashion,
    },
    {
      categoryName: 'games',
      image: games,
    },
    {
      categoryName: 'others',
      image: others,
    },
  ];

  return (
    <>
      <Head>
        <title>Swappee</title>
      </Head>
      <AContainer1440>
        <MCarousel />
        <MCategoriesSection categories={categories} />
        <Box className="home-page-items" boxShadow={3}>
          <p>Recent</p>
          <MItemListingContainer page={pagesShown} data={items} />
          <Box className="home-page-items__button">
            {pagesShown * numItemCardsInOnePage < items.length && (
              <AFilledButton buttonText="View More!" onClick={() => setPagesShown(pagesShown + 1)} />
            )}
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
