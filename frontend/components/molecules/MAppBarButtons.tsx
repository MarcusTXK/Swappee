import { Button, Grid } from '@material-ui/core';
import MMenuList from './MMenuList';
import React, { ChangeEventHandler, FC } from 'react';
import MLoginDialog from './MLoginDialog';
import MRegisterDialog from './MRegisterDialog';
import MForgotPasswordDialog from './MForgotPasswordDialog';

interface MAppBarButtonProps {
  user: string;
  pass: string;
  handleUserChange: ChangeEventHandler<HTMLInputElement | HTMLTextAreaElement>;
  handlePassChange: ChangeEventHandler<HTMLInputElement | HTMLTextAreaElement>;
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
  user = '',
  pass = '',
  handleUserChange = () => {},
  handlePassChange = () => {},
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
  return isLoggedIn ? (
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
  ) : (
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
          user={user}
          pass={pass}
          handleUserChange={handleUserChange}
          handlePassChange={handlePassChange}
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
  );
};

export default MAppBarButtons;
