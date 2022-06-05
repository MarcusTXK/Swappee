import { Avatar, Box, Button, Grid, Paper } from '@material-ui/core';
import Image from 'next/image';

const MProfileBanner = () => {
  return (
    <Box>
      <Box pl={5}>
        <Avatar className="o-profilepage-banner-image">
          <Image src={`https://avatars.dicebear.com/api/bottts/1.svg`} alt="profile picture" height="100" width="100" />
        </Avatar>
      </Box>
      <Box>
        <Paper className="o-profilepage-banner-top"></Paper>
        <Paper className="o-profilepage-banner-bottom">
          <Grid container justifyContent="flex-end">
            <Grid item>
              <Box pt={1} pr={1}>
                <Button variant="outlined" color="primary">
                  Edit Profile
                </Button>
              </Box>
            </Grid>
          </Grid>
        </Paper>
      </Box>
    </Box>
  );
};

export default MProfileBanner;
