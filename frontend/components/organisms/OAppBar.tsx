import { Box, AppBar, Toolbar, Button, Grid } from '@material-ui/core';
import MSearchBar from '../molecules/MSearchBar';
import logo from './logo.svg';
import Image from 'next/image';
import { useRouter } from 'next/dist/client/router';
import MAppBarButtons from 'components/molecules/MAppBarButtons';
import { useState } from 'react';

const OAppBar = () => {
  const Router = useRouter();
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

  return (
    <Box>
      <AppBar color="default" position="static" className="o-appbar">
        <Toolbar>
          <Grid>
            <Button
              className="o-appbar__logo"
              disableRipple
              onClick={() => {
                Router.push('/');
              }}
            >
              <Image src={logo} alt="LOGO" />
            </Button>
          </Grid>
          <Box pl={5}>
            <MSearchBar />
          </Box>
          <Grid container justifyContent="flex-end">
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
        </Toolbar>
      </AppBar>
    </Box>
  );
};

export default OAppBar;
