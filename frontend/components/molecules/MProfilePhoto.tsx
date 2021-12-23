import { Grid, Box, Button } from '@material-ui/core';
import Image from 'next/image';
import profilePicture from '../../public/profilepicture.svg';

const ProfilePhoto = () => {
  return (
    <Grid container direction="row" wrap="nowrap">
      <Grid>
        <Image src={profilePicture} alt="profile picture" />
      </Grid>
      <Box pl={3}>
        <Grid container direction="column" justifyContent="center">
          <Grid>Max size of 2MB</Grid>
          <Grid>
            <Button size="small" color="primary" variant="outlined" component="label">
              Upload a photo
              <input type="file" hidden />
            </Button>
          </Grid>
        </Grid>
      </Box>
    </Grid>
  );
};

export default ProfilePhoto;
