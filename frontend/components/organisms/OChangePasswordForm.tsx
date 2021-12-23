import { Box, Paper, Grid, Divider, Button } from '@material-ui/core';
import { useState, ChangeEvent } from 'react';
import MPasswordField from '../molecules/MPasswordField';

const OChangePasswordForm = () => {
  const [currPass, setCurrPass] = useState('');
  const [newPass, setNewPass] = useState('');
  const [confirmPass, setConfirmPass] = useState('');

  const handleCurrPassChange = (event: ChangeEvent<HTMLInputElement>) => {
    setCurrPass(event.target.value);
  };

  const handleNewPassChange = (event: ChangeEvent<HTMLInputElement>) => {
    setNewPass(event.target.value);
  };

  const handleConfirmPassChange = (event: ChangeEvent<HTMLInputElement>) => {
    setConfirmPass(event.target.value);
  };

  const handleClick = () => {
    alert('Saved Successfully');
  };

  return (
    <Paper variant="outlined" className="m-changepassword-form">
      <Box sx={{ px: 3, py: 3 }}>
        <Grid container direction="column" spacing={2}>
          <Grid item>
            <Box fontSize={30}>Change Password</Box>
          </Grid>
          <Grid item>
            <MPasswordField label="Current Password" password={currPass} handlePassword={handleCurrPassChange} />
          </Grid>
          <Grid item>
            <MPasswordField label="New Password" password={newPass} handlePassword={handleNewPassChange} />
          </Grid>
          <Grid item>
            <MPasswordField label="Confirm Password" password={confirmPass} handlePassword={handleConfirmPassChange} />
          </Grid>
          <Grid item>
            <Divider variant="fullWidth" />
          </Grid>
          <Grid container item justifyContent="flex-end">
            <Button variant="contained" color="primary" onClick={handleClick}>
              Save changes
            </Button>
          </Grid>
        </Grid>
      </Box>
    </Paper>
  );
};

export default OChangePasswordForm;
