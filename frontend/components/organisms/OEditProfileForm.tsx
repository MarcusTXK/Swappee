import { Box, TextField, Paper, Grid, Divider, Button, Input } from '@material-ui/core';
import { useState, ChangeEvent } from 'react';
import MInputField from '../molecules/MInputField';
import MProfilePhoto from '../molecules/MProfilePhoto';

const OEditProfileForm = () => {
  const [user, setUser] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [aboutMe, setAboutMe] = useState('');
  const [email, setEmail] = useState('');
  const [mobile, setMobile] = useState('');

  const handleUserChange = (event: ChangeEvent<HTMLInputElement>) => {
    setUser(event.target.value);
  };

  const handleFirstNameChange = (event: ChangeEvent<HTMLInputElement>) => {
    setFirstName(event.target.value);
  };

  const handleLastNameChange = (event: ChangeEvent<HTMLInputElement>) => {
    setLastName(event.target.value);
  };

  const handleAboutMeChange = (event: ChangeEvent<HTMLInputElement>) => {
    setAboutMe(event.target.value);
  };

  const handleEmailChange = (event: ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };

  const handleMobileChange = (event: ChangeEvent<HTMLInputElement>) => {
    setMobile(event.target.value);
  };

  const handleClick = () => {
    alert('Saved Successfully');
  };

  return (
    <Paper variant="outlined" className="m-editprofile-form">
      <Box sx={{ px: 3, py: 3 }}>
        <Grid container direction="column" spacing={2} alignItems="center" justifyContent="center">
          <Grid item>
            <Box fontSize={30}>Edit Profile</Box>
          </Grid>
          <Grid item>
            <Box fontSize={20}>Profile photo</Box>
          </Grid>
          <Grid item>
            <MProfilePhoto />
          </Grid>
          <Grid item>
            <Box fontSize={20} pt={3}>
              Public Profile
            </Box>
          </Grid>
          <Grid item>
            <MInputField label="Username" value={user} handleChange={handleUserChange} />
          </Grid>
          <Grid item>
            <MInputField label="First Name" value={firstName} handleChange={handleFirstNameChange} />
          </Grid>
          <Grid item>
            <MInputField label="Last Name" value={lastName} handleChange={handleLastNameChange} />
          </Grid>
          <Grid item>
            <TextField
              multiline={true}
              variant="outlined"
              label="About Me"
              className="m-editprofile-form__aboutme"
              value={aboutMe}
              onChange={handleAboutMeChange}
              fullWidth
            />
          </Grid>

          <Grid item>
            <Box fontSize={20} pt={3}>
              Contact Information
            </Box>
          </Grid>
          <Grid item>
            <MInputField label="Email" value={email} handleChange={handleEmailChange} />
          </Grid>
          <Grid item>
            <MInputField label="Mobile Number" value={mobile} handleChange={handleMobileChange} />
          </Grid>
          <Grid item>
            <Box fontSize={13} fontWeight={4}>
              Your mobile number is private and will only be revealed to users who you agree to trade with to facilitate
              communication.
            </Box>
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

export default OEditProfileForm;
