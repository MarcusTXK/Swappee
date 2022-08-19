import { Box, Grid } from '@material-ui/core';
import { Rating } from '@material-ui/lab';

const MUserDetails = () => {
  return (
    <Box>
      <Grid container>
        <Grid item>
          <Box fontSize={30}>John Doe</Box>
          <Box>@username</Box>
        </Grid>
        <Grid item>
          <Box pl={5} pt={1}>
            <Rating name="user-rating" value={4} size="large" readOnly />
          </Box>
        </Grid>
        <Grid item>
          <Box px={3} pt={2}>
            (8 reviews)
          </Box>
        </Grid>
      </Grid>
    </Box>
  );
};

export default MUserDetails;
