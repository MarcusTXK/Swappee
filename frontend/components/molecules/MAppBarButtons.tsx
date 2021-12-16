import { Box, Button, ButtonGroup } from '@material-ui/core';
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
      <ButtonGroup>
        <Box pl={3}>
          <Button
            variant="contained"
            color="primary"
            style={{ maxWidth: '80px', minWidth: '80px' }}
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
        </Box>
        <Box px={3}>
          <Button
            variant="outlined"
            color="primary"
            style={{ maxWidth: '80px', minWidth: '80px' }}
            onClick={() => handleOpenRegisterModal()}
          >
            Register
          </Button>
          <MRegisterDialog
            showRegisterModal={showRegisterModal}
            handleCloseRegisterModal={handleCloseRegisterModal}
            handleOpenLoginModal={handleOpenLoginModal}
          />
        </Box>
      </ButtonGroup>
    );
  }
  return (
    <ButtonGroup>
      <Box pl={3}>
        <Button variant="contained" color="primary" style={{ maxWidth: '80px', minWidth: '80px' }}>
          Trade
        </Button>
      </Box>
      <Box px={3}>
        <MMenuList handleLogout={handleLogout} />
      </Box>
    </ButtonGroup>
  );
};

export default MAppBarButtons;
