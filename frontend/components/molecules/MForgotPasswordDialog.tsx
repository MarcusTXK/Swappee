import React, { FC } from 'react';
import { Box, Dialog, IconButton, TextField, Grid, DialogContent, Button, FormControl } from '@material-ui/core';
import { useState, ChangeEvent } from 'react';
import { Close } from '@material-ui/icons';

interface MForgotPasswordDialogProps {
  showForgotPasswordModal?: boolean;
  handleCloseForgotPasswordModal?: Function;
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
    <Dialog open={showForgotPasswordModal} fullWidth>
      <Box display="flex" justifyContent="flex-end" mt={2} mr={2}>
        <IconButton aria-label="close" onClick={() => handleCloseForgotPasswordModal()} color="inherit">
          <Close />
        </IconButton>
      </Box>
      <DialogContent>
        <Grid container direction="column" spacing={6}>
          <Grid item>
            <Box component="h1">Forgot Password</Box>
          </Grid>
          <Grid item>
            <FormControl fullWidth>
              <TextField
                label="Email Address"
                placeholder="username@email.com"
                variant="outlined"
                value={email}
                onChange={handleEmailChange}
              />
              <br />
              <Button variant="contained" color="primary" fullWidth onClick={handleSubmit}>
                Send
              </Button>
            </FormControl>
          </Grid>
        </Grid>
      </DialogContent>
    </Dialog>
  );
};

export default MForgotPasswordDialog;
