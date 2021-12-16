import React, { FC } from 'react';
import { Box, Button, Dialog, IconButton, TextField, Grid, DialogContent, Link } from '@material-ui/core';
import { useState, ChangeEvent } from 'react';
import { Close } from '@material-ui/icons';
import { useRouter } from 'next/dist/client/router';

interface MRegisterDialogProps {
  showRegisterModal?: boolean;
  handleCloseRegisterModal?: Function;
  handleOpenLoginModal?: Function;
}

const MRegisterDialog: FC<MRegisterDialogProps> = ({
  showRegisterModal = false,
  handleCloseRegisterModal = () => {},
  handleOpenLoginModal = () => {},
}) => {
  const Router = useRouter();
  const [user, setUser] = useState<String>('');
  const [pass, setPass] = useState<String>('');
  const [email, setEmail] = useState<String>('');
  const [mobile, setMobile] = useState<String>('');
  const handleUserChange = (event: ChangeEvent<HTMLInputElement>) => {
    setUser(event.target.value);
  };
  const handlePassChange = (event: ChangeEvent<HTMLInputElement>) => {
    setPass(event.target.value);
  };
  const handleEmailChange = (event: ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };
  const handleMobileChange = (event: ChangeEvent<HTMLInputElement>) => {
    setMobile(event.target.value);
  };
  const handleClick = () => {
    handleCloseRegisterModal();
    handleOpenLoginModal();
  };
  return (
    <Dialog open={showRegisterModal} fullWidth>
      <Box display="flex" justifyContent="flex-end" mt={2} mr={2}>
        <IconButton aria-label="close" onClick={() => handleCloseRegisterModal()} color="inherit">
          <Close />
        </IconButton>
      </Box>
      <DialogContent>
        <Grid container direction="column" spacing={1}>
          <Grid item>
            <TextField fullWidth value={user} variant="outlined" placeholder="Username" onChange={handleUserChange} />
          </Grid>
          <Grid item>
            <TextField fullWidth value={pass} variant="outlined" placeholder="Password" onChange={handlePassChange} />
          </Grid>
          <Grid item>
            <TextField fullWidth value={email} variant="outlined" placeholder="Email" onChange={handleEmailChange} />
          </Grid>
          <Grid item>
            <TextField
              fullWidth
              value={mobile}
              variant="outlined"
              placeholder="Mobile Number"
              onChange={handleMobileChange}
            />
          </Grid>
          <Grid item>
            Your mobile number is private and will only be revealed to users who you agree to trade with to facilitate
            communication.
          </Grid>
          <Grid item>
            <Button fullWidth variant="contained" color="primary">
              Sign Up
            </Button>
          </Grid>
          <Grid item container justifyContent="center">
            <Box>
              Have an account?
              <Button variant="text" color="primary" onClick={() => handleClick()}>
                Login now
              </Button>
            </Box>
          </Grid>
          <Grid item>
            By signing up you agree to Swappee's{' '}
            <Link href="#" onClick={() => Router.push('/terms_of_service')} underline="always">
              {' '}
              Terms of Service
            </Link>{' '}
            &{' '}
            <Link href="#" onClick={() => Router.push('/privacy_policy')} underline="always">
              {' '}
              Privacy Policy
            </Link>
          </Grid>
        </Grid>
      </DialogContent>
    </Dialog>
  );
};

export default MRegisterDialog;
