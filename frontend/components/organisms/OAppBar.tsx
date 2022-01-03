import { Box, AppBar, Toolbar, Link, Grid, Button } from '@material-ui/core';
import MSearchBar from '../molecules/MSearchBar';
import logo from './logo.svg';
import Image from 'next/image';
import MAppBarButtons from 'components/molecules/MAppBarButtons';
import { useState } from 'react';

import { useDispatch, useSelector } from 'react-redux';
import { AppState } from 'redux-saga/interfaces';
import { getOtherUser } from 'redux-saga/actions';

const OAppBar = () => {
  const [showLoginModal, setShowLoginModal] = useState(false);
  const [showRegisterModal, setShowRegisterModal] = useState(false);
  const [showForgotPasswordModal, setShowForgotPasswordModal] = useState(false);
  const [isLoggedIn, setLogin] = useState(false);

  const handleOpenLoginModal = () => {
    setShowLoginModal(true);
  };

  const handleCloseLoginModal = () => {
    setShowLoginModal(false);
  };

  const handleOpenRegisterModal = () => {
    setShowRegisterModal(true);
  };

  const handleCloseRegisterModal = () => {
    setShowRegisterModal(false);
  };

  const handleOpenForgotPasswordModal = () => {
    setShowForgotPasswordModal(true);
  };

  const handleCloseForgotPasswordModal = () => {
    setShowForgotPasswordModal(false);
  };

  const handleLogin = () => {
    setLogin(true);
  };

  const handleLogout = () => {
    setLogin(false);
  };

  const { otherUserData } = useSelector((state: AppState) => state);
  const dispatch = useDispatch();

  let username = 'tayyantay';
  const fetchAPI = () => {
    dispatch(getOtherUser({ username }));
  };

  return (
    <Box>
      <Button onClick={fetchAPI}>Fetch Me</Button>
      <Box>{otherUserData.username}</Box>
      <Box>{otherUserData.id}</Box>
      <AppBar color="default" position="static" className="o-appbar">
        <Toolbar>
          <Grid container justifyContent="space-between" alignItems="center" wrap="nowrap" spacing={2}>
            <Grid item>
              <Link href="/" className="o-appbar__logo">
                <Image src={logo} alt="LOGO" />
              </Link>
            </Grid>
            <Grid item>
              <MSearchBar />
            </Grid>
            <Grid item>
              <MAppBarButtons
                handleOpenLoginModal={handleOpenLoginModal}
                handleCloseLoginModal={handleCloseLoginModal}
                handleOpenRegisterModal={handleOpenRegisterModal}
                handleCloseRegisterModal={handleCloseRegisterModal}
                handleOpenForgotPasswordModal={handleOpenForgotPasswordModal}
                handleCloseForgotPasswordModal={handleCloseForgotPasswordModal}
                handleLogin={handleLogin}
                handleLogout={handleLogout}
                isLoggedIn={isLoggedIn}
                showLoginModal={showLoginModal}
                showRegisterModal={showRegisterModal}
                showForgotPasswordModal={showForgotPasswordModal}
              />
            </Grid>
          </Grid>
        </Toolbar>
      </AppBar>
    </Box>
  );
};

export default OAppBar;
