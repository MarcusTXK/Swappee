import { Box, Paper } from '@material-ui/core';
import OProfileListing from 'components/organisms/OProfileListing';
import MProfileListingMenu from 'components/molecules/MProfileListingMenu';
import OProfilePageBanner from 'components/organisms/OProfilePageBanner';
import { useState } from 'react';

const ProfilePage = () => {
  return (
    <Box className="p-profile">
      <OProfilePageBanner />
      <Box className="p-profile-content">
        <OProfileListing />
      </Box>
    </Box>
  );
};

export default ProfilePage;
