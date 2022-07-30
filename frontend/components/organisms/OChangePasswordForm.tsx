import { Box, Paper, Grid, Divider, Button } from '@material-ui/core';
import { useState, ChangeEvent } from 'react';
import MPasswordField from '../molecules/MPasswordField';

const OChangePasswordForm = () => {
  const [currPass, setCurrPass] = useState('');
  const [newPass, setNewPass] = useState('');
  const [confirmPass, setConfirmPass] = useState('');
  const [isSubmitted, setSubmitted] = useState(false);

  const isPasswordSame = newPass !== confirmPass && isSubmitted;

  const handleCurrPassChange = (event: ChangeEvent<HTMLInputElement>) => {
    setCurrPass(event.target.value);
    setSubmitted(false);
  };

  const handleNewPassChange = (event: ChangeEvent<HTMLInputElement>) => {
    setNewPass(event.target.value);
    setSubmitted(false);
  };

  const handleConfirmPassChange = (event: ChangeEvent<HTMLInputElement>) => {
    setConfirmPass(event.target.value);
    setSubmitted(false);
  };

  const handleClick = () => {
    setSubmitted(true);
  };

  return (
    <Paper variant="outlined" className="m-changepassword-form">
      <Box sx={{ px: 3, py: 3 }}>
        <Grid container direction="column" spacing={2}>
          <Grid item>
            <Box fontSize={30} paddingBottom={2}>
              Change Password
            </Box>
          </Grid>
          <Grid item>
            <MPasswordField label="Current Password" password={currPass} handlePassword={handleCurrPassChange} />
          </Grid>
          <Grid item>
            <MPasswordField
              label="New Password"
              password={newPass}
              handlePassword={handleNewPassChange}
              error={isPasswordSame}
            />
          </Grid>
          <Grid item>
            <MPasswordField
              label="Confirm Password"
              password={confirmPass}
              handlePassword={handleConfirmPassChange}
              error={isPasswordSame}
              helperText={isPasswordSame ? 'Passwords do not match' : ''}
            />
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
