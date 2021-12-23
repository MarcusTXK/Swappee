import { Box } from '@material-ui/core';
import MProfileMenu from 'components/molecules/MProfileMenu';
import OChangePasswordForm from 'components/organisms/OChangePasswordForm';
import OEditProfileForm from 'components/organisms/OEditProfileForm';
import { useState } from 'react';

const ProfilePage = () => {
  const [page, setPage] = useState('edit_profile');

  const handleClickEditProfile = () => {
    setPage('edit_profile');
  };

  const handleClickChangePassword = () => {
    setPage('change_password');
  };

  const renderForm = () => {
    switch (page) {
      case 'edit_profile':
        return <OEditProfileForm />;
      case 'change_password':
        return <OChangePasswordForm />;
    }
  };

  return (
    <Box px={4} py={3} className="p-profile" justifyContent="center">
      <Box>
        <MProfileMenu
          handleClickEditProfile={handleClickEditProfile}
          handleClickChangePassword={handleClickChangePassword}
          page={page}
        />
      </Box>
      <Box sx={{ px: 5 }}>{renderForm()}</Box>
    </Box>
  );
};

export default ProfilePage;
