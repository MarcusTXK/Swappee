import { Box, AppBar, Toolbar, Link, Grid, Button } from '@material-ui/core';
import MSearchBar from '../molecules/MSearchBar';
import logo from './logo.svg';
import Image from 'next/image';
import MAppBarButtons from 'components/molecules/MAppBarButtons';
import { ChangeEvent, useState } from 'react';

import { useDispatch, useSelector } from 'react-redux';
import { AppState } from 'redux-saga/interfaces';
import { getOtherUser, login } from 'redux-saga/actions';

const OAppBar = () => {
  const [showLoginModal, setShowLoginModal] = useState(false);
  const [showRegisterModal, setShowRegisterModal] = useState(false);
  const [showForgotPasswordModal, setShowForgotPasswordModal] = useState(false);
  const [isLoggedIn, setLogin] = useState(false);
  const [user, setUser] = useState('');
  const [pass, setPass] = useState('');

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
    dispatch(login({ username: user, password: pass }));
    setLogin(true);
  };

  const handleLogout = () => {
    setLogin(false);
  };

  const handleUserChange = (event: ChangeEvent<HTMLInputElement>) => {
    setUser(event.target.value);
  };
  const handlePassChange = (event: ChangeEvent<HTMLInputElement>) => {
    setPass(event.target.value);
  };

  const otherUserData = useSelector((state: AppState) => state.otherUserData);
  const otherUsersData = useSelector((state: AppState) => state.otherUsersData);
  const dispatch = useDispatch();

  return (
    <Box>
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
                user={user}
                pass={pass}
                handleUserChange={handleUserChange}
                handlePassChange={handlePassChange}
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
