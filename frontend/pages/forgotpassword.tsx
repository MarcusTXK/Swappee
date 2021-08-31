import { Box } from '@material-ui/core';
import { Grid } from '@material-ui/core';
import { Paper } from '@material-ui/core';
import Image from 'next/image';
import MForgotPasswordForm from '../components/MForgotPasswordForm'
import forgotPasswordImage from '../public/forgot_password.svg';

const ForgotPasswordPage = () => {
  return (
    <Box component="body" px={8} pt={10}>
      <Paper variant="outlined">
        <Box py={7}>
          <Grid container justifyContent="space-around" spacing={2}>
            <Grid item xs={8} md={5}>
                <MForgotPasswordForm />
            </Grid>
            <Grid item>
              <Image src={forgotPasswordImage} alt="FORGOT_PASSWORD" height="300%" width="450%" />
            </Grid>
          </Grid>
        </Box>
      </Paper>
    </Box>
  );
};

export default ForgotPasswordPage;
