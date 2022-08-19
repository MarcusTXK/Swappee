import { Button, ButtonGroup } from '@material-ui/core';
import React, { FC } from 'react';

interface MProfileMenuProps {
  handleClickEditProfile: Function;
  handleClickChangePassword: Function;
  page: string;
}

const MProfileMenu: FC<MProfileMenuProps> = ({
  page = 'edit_profile',
  handleClickEditProfile = () => {},
  handleClickChangePassword = () => {},
}) => {
  return (
    <ButtonGroup orientation="vertical" className="m-profilemenu">
      <Button
        onClick={() => handleClickEditProfile()}
        className={page === 'edit_profile' ? 'm-profilemenu__selectedbutton' : ''}
      >
        Edit Profile
      </Button>
      <Button
        onClick={() => handleClickChangePassword()}
        className={page === 'change_password' ? 'm-profilemenu__selectedbutton' : ''}
      >
        Change Password
      </Button>
    </ButtonGroup>
  );
};

export default MProfileMenu;
