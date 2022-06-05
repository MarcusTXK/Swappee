import { Box, Paper } from '@material-ui/core';
import OProfileListing from 'components/organisms/OProfileListing';
import OProfileListingMenu from 'components/organisms/OProfileListingMenu';
import OProfilePageBanner from 'components/organisms/OProfilePageBanner';
import { useState } from 'react';

const ProfilePage = () => {
  return (
    <Box className="p-profile">
      <OProfilePageBanner />
      <Box className="p-profile-content">
        <OProfileListingMenu />
        <OProfileListing />
      </Box>
    </Box>
  );
};

export default ProfilePage;
