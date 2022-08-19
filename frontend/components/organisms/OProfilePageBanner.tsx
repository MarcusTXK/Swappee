import { Box } from '@material-ui/core';
import MProfileBanner from 'components/molecules/MProfileBanner';
import MUserDetails from 'components/molecules/MUserDetails';

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
