import { Box, Grid, Button } from '@material-ui/core';
import OAppBar from 'components/organisms/OAppBar';
import { useRouter } from 'next/dist/client/router';

import Image from 'next/image';
import errorImage from '../public/error_page.svg';

const ErrorPage = () => {
  const Router = useRouter();

  return (
    <Box component="body" paddingTop={7} px={3}>
      <OAppBar />
      <Grid container justifyContent="center" alignItems="center">
        <Grid item>
          <Image src={errorImage} alt="ERROR" width="600%" height="300%" />
        </Grid>
      </Grid>
      <Box textAlign="center" pt={2} pb={3}>
        <Box display="block" fontWeight={800} fontSize={{ xs: '23px', sm: '38px', md: '40px' }} pb={1}>
          PAGE NOT FOUND
        </Box>
        <Box>The page you are looking for was removed, renamed or might never have existed.</Box>
      </Box>
      <Grid container justifyContent="center" spacing={3}>
        <Grid item>
          <Button variant="contained" color="primary" onClick={() => Router.push('/')}>
            Go Home
          </Button>
        </Grid>
        <Grid item>
          <Button variant="outlined" color="primary" onClick={() => Router.push('/contact-us')}>
            Contact Us
          </Button>
        </Grid>
      </Grid>
    </Box>
  );
};

export default ErrorPage;
