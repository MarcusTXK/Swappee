import React, { FC } from 'react';
import { Box, Dialog, IconButton, TextField, Grid, DialogContent, Button } from '@material-ui/core';
import { useState, ChangeEvent } from 'react';
import { Close } from '@material-ui/icons';

interface MForgotPasswordDialogProps {
  showForgotPasswordModal?: boolean;
  handleCloseForgotPasswordModal: Function;
}

const MForgotPasswordDialog: FC<MForgotPasswordDialogProps> = ({
  showForgotPasswordModal = false,
  handleCloseForgotPasswordModal = () => {},
}) => {
  const [email, setEmail] = useState<String>('');

  const handleEmailChange = (event: ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };

  const handleSubmit = () => {
    alert(`You have entered ${email}`);
    setEmail('');
  };

  return (
    <Dialog open={showForgotPasswordModal} fullWidth className="m-forgotpassword-dialog">
      <Box display="flex" justifyContent="flex-end" mt={2} mr={2}>
        <IconButton aria-label="close" onClick={() => handleCloseForgotPasswordModal()} color="inherit">
          <Close />
        </IconButton>
      </Box>
      <DialogContent className="m-forgotpassword-dialog__content">
        <Grid container direction="column" spacing={6}>
          <Grid item>
            <Box component="h1">Forgot Password</Box>
          </Grid>
          <Grid item>
            <TextField
              label="Enter your email address"
              placeholder="username@email.com"
              variant="outlined"
              fullWidth
              className="m-forgotpassword-dialog__textfield"
              value={email}
              onChange={handleEmailChange}
            />
            <Button variant="contained" color="primary" fullWidth onClick={handleSubmit}>
              Reset Password
            </Button>
          </Grid>
        </Grid>
      </DialogContent>
    </Dialog>
  );
};

export default MForgotPasswordDialog;
