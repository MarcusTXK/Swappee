import { Box, Paper, Button, Grid, Card, Avatar } from '@material-ui/core';
import { Rating } from '@material-ui/lab';
import MProfileBanner from 'components/molecules/MProfileBanner';
import MUserDetails from 'components/molecules/MUserDetails';
import Image from 'next/image';

const OProfilePageBanner = () => {
  return (
    <Box className="o-profilepage-banner" alignItems="center">
      <MProfileBanner />
      <Box pt={2} pl={1}>
        <MUserDetails />
      </Box>
    </Box>
  );
};

export default OProfilePageBanner;
