import { Box, Grid, Paper, TextField, Button } from '@material-ui/core';
import { useState, ChangeEvent } from 'react';
import Image from 'next/image';
import AContainer1440 from 'components/atoms/AContainer1440';
import forgotPasswordImage from '../public/forgot_password.svg';

const ForgotPasswordPage = () => {
  const [emailAddress, setEmailAddress] = useState('');

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    setEmailAddress(e.target.value);
  };

  const handleSubmit = () => {
    alert(`You have entered ${emailAddress}`);
    setEmailAddress('');
  };

  return (
    <AContainer1440>
      <Box px={8} pt={10}>
        <Paper variant="outlined">
          <Box py={7}>
            <Grid container justifyContent="space-around" spacing={2}>
              <Grid item xs={8} md={5}>
                <Box display="flex" flexDirection="column" justifyContent="center" height="100%">
                  <Box component="h1">Forgot Password</Box>
                  <Box my={6}>
                    <TextField
                      size="small"
                      label="Email Address"
                      placeholder="username@email.com"
                      variant="outlined"
                      value={emailAddress}
                      fullWidth
                      onChange={handleChange}
                    />
                  </Box>
                  <Button variant="contained" color="primary" fullWidth onClick={handleSubmit}>
                    Send
                  </Button>
                </Box>
              </Grid>
              <Grid item>
                <Image src={forgotPasswordImage} alt="FORGOT_PASSWORD" height="300%" width="450%" />
              </Grid>
            </Grid>
          </Box>
        </Paper>
      </Box>
    </AContainer1440>
  );
};

export default ForgotPasswordPage;
