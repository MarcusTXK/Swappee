import React, { FC } from 'react';
import {
  Box,
  Button,
  Dialog,
  IconButton,
  TextField,
  Grid,
  DialogContent,
  Checkbox,
  FormControlLabel,
  Link,
} from '@material-ui/core';
import { useState, ChangeEvent } from 'react';
import { Close } from '@material-ui/icons';

interface MLoginDialogProps {
  showLoginModal?: boolean;
  handleCloseLoginModal: Function;
  handleOpenRegisterModal: Function;
  handleOpenForgotPasswordModal: Function;
  handleLogin: Function;
}

const MLoginDialog: FC<MLoginDialogProps> = ({
  showLoginModal = false,
  handleCloseLoginModal = () => {},
  handleOpenRegisterModal = () => {},
  handleOpenForgotPasswordModal = () => {},
  handleLogin = () => {},
}) => {
  const [user, setUser] = useState<String>('');
  const [pass, setPass] = useState<String>('');
  const handleUserChange = (event: ChangeEvent<HTMLInputElement>) => {
    setUser(event.target.value);
  };
  const handlePassChange = (event: ChangeEvent<HTMLInputElement>) => {
    setPass(event.target.value);
  };
  const handleClickSignUp = () => {
    handleCloseLoginModal();
    handleOpenRegisterModal();
  };
  const handleClickForgotPassword = () => {
    handleCloseLoginModal();
    handleOpenForgotPasswordModal();
  };
  const handleClickLogin = () => {
    handleCloseLoginModal();
    handleLogin();
  };

  return (
    <Dialog open={showLoginModal} fullWidth>
      <Box display="flex" justifyContent="flex-end" mt={2} mr={2}>
        <IconButton aria-label="close" onClick={() => handleCloseLoginModal()} color="inherit">
          <Close />
        </IconButton>
      </Box>
      <DialogContent>
        <Grid container direction="column" spacing={2} alignContent="center">
          <Grid item>
            <TextField fullWidth value={user} variant="outlined" placeholder="Username" onChange={handleUserChange} />
          </Grid>
          <Grid item>
            <TextField fullWidth value={pass} variant="outlined" placeholder="Password" onChange={handlePassChange} />
          </Grid>
          <Grid container item direction="row" justifyContent="space-between" alignItems="center">
            <Link href="#" onClick={() => handleClickForgotPassword()}>
              Forgot Password?
            </Link>
            <FormControlLabel control={<Checkbox size="small" />} label="Remember Me" />
          </Grid>
          <Grid item>
            <Button fullWidth color="primary" variant="contained" onClick={() => handleClickLogin()}>
              Login
            </Button>
          </Grid>
          <Grid item container justifyContent="center">
            <Box>
              Don't have an account?
              <Button variant="text" color="primary" onClick={() => handleClickSignUp()}>
                Sign up
              </Button>
            </Box>
          </Grid>
        </Grid>
      </DialogContent>
    </Dialog>
  );
};

export default MLoginDialog;
