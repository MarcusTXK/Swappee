import { Box } from '@material-ui/core';
import MProfileMenu from 'components/molecules/MProfileMenu';
import OChangePasswordForm from 'components/organisms/OChangePasswordForm';
import OEditProfileForm from 'components/organisms/OEditProfileForm';
import { useState } from 'react';

const SettingsPage = () => {
  const [page, setPage] = useState('EditProfile');

  const handleClickEditProfile = () => {
    setPage('EditProfile');
  };

  const handleClickChangePassword = () => {
    setPage('ChangePassword');
  };

  const renderForm = () => {
    switch (page) {
      case 'EditProfile':
        return <OEditProfileForm />;
      case 'ChangePassword':
        return <OChangePasswordForm />;
    }
  };

  return (
    <Box px={4} py={3} className="p-settings" justifyContent="center">
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

export default SettingsPage;
