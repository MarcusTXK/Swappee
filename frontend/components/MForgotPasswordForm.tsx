import { FormControl, Grid, TextField, Button } from '@material-ui/core';
import { Box } from '@material-ui/core';
import { useState } from 'react';

const MForgotPasswordForm = () => {
  const [emailAddress, setEmailAddress] = useState('');

  const handleChange = (e) => {
    setEmailAddress(e.target.value);
  };

  const handleSubmit = () => {
    alert(`You have entered ${emailAddress}`);
    setEmailAddress('');
  };

  return (
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
            value={emailAddress}
            onChange={handleChange}
          />
          <br />
          <Button variant="contained" color="primary" fullWidth onClick={handleSubmit}>
            Send
          </Button>
        </FormControl>
      </Grid>
    </Grid>
  );
};

export default MForgotPasswordForm;
