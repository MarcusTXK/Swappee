import { Button, ButtonGroup } from '@material-ui/core';
import React, { FC } from 'react';

interface MProfileMenuProps {
  handleClickEditProfile: Function;
  handleClickChangePassword: Function;
}

const MProfileMenu: FC<MProfileMenuProps> = ({
  handleClickEditProfile = () => {},
  handleClickChangePassword = () => {},
}) => {
  return (
    <ButtonGroup orientation="vertical">
      <Button onClick={() => handleClickEditProfile()}>Edit Profile</Button>
      <Button onClick={() => handleClickChangePassword()}>Change Password</Button>
    </ButtonGroup>
  );
};

export default MProfileMenu;
