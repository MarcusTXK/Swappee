import { Button, Box, Grid } from '@material-ui/core';
import MMenuList from './MMenuList';
import React, { FC } from 'react';
import MLoginDialog from './MLoginDialog';
import MRegisterDialog from './MRegisterDialog';
import MForgotPasswordDialog from './MForgotPasswordDialog';

interface MAppBarButtonProps {
  handleOpenLoginModal?: Function;
  handleCloseLoginModal?: Function;
  handleOpenRegisterModal?: Function;
  handleCloseRegisterModal?: Function;
  handleOpenForgotPasswordModal?: Function;
  handleCloseForgotPasswordModal?: Function;
  handleLogin?: Function;
  handleLogout?: Function;
  isLoggedIn?: boolean;
  showLoginModal?: boolean;
  showRegisterModal?: boolean;
  showForgotPasswordModal?: boolean;
}

const MAppBarButtons: FC<MAppBarButtonProps> = ({
  handleOpenLoginModal = () => {},
  handleCloseLoginModal = () => {},
  handleOpenRegisterModal = () => {},
  handleCloseRegisterModal = () => {},
  handleOpenForgotPasswordModal = () => {},
  handleCloseForgotPasswordModal = () => {},
  handleLogin = () => {},
  handleLogout = () => {},
  isLoggedIn = false,
  showLoginModal = false,
  showRegisterModal = false,
  showForgotPasswordModal = false,
}) => {
  if (!isLoggedIn) {
    return (
      <Box ml={3}>
        <Grid container spacing={3} direction="row" justifyContent="flex-end" wrap="nowrap" className="m-appbarbuttons">
          <Grid item>
            <Button
              variant="contained"
              color="primary"
              className="m-appbarbuttons__button"
              onClick={() => handleOpenLoginModal()}
            >
              Login
            </Button>
            <MLoginDialog
              showLoginModal={showLoginModal}
              handleCloseLoginModal={handleCloseLoginModal}
              handleOpenRegisterModal={handleOpenRegisterModal}
              handleOpenForgotPasswordModal={handleOpenForgotPasswordModal}
              handleLogin={handleLogin}
            />
            <MForgotPasswordDialog
              showForgotPasswordModal={showForgotPasswordModal}
              handleCloseForgotPasswordModal={handleCloseForgotPasswordModal}
            />
          </Grid>
          <Grid item>
            <Button
              variant="outlined"
              color="primary"
              className="m-appbarbuttons__button"
              onClick={() => handleOpenRegisterModal()}
            >
              Register
            </Button>
            <MRegisterDialog
              showRegisterModal={showRegisterModal}
              handleCloseRegisterModal={handleCloseRegisterModal}
              handleOpenLoginModal={handleOpenLoginModal}
            />
          </Grid>
        </Grid>
      </Box>
    );
  }
  return (
    <Box ml={3}>
      <Grid container justifyContent="flex-end" spacing={3} wrap="nowrap" className="m-appbarbuttons">
        <Grid item>
          <Button variant="contained" color="primary" className="m-appbarbuttons__button">
            Trade
          </Button>
        </Grid>
        <Grid item>
          <MMenuList handleLogout={handleLogout} />
        </Grid>
      </Grid>
    </Box>
  );
};

export default MAppBarButtons;
