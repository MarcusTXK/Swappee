import { Button, MenuList, MenuItem, Box, Popper, Paper, ClickAwayListener } from '@material-ui/core';
import { useState, MouseEvent } from 'react';
import { PersonOutline, MailOutline, Settings, ExitToApp, ArrowDropDown } from '@material-ui/icons';
import React, { FC } from 'react';
import { useRouter } from 'next/dist/client/router';

interface MMenuListProps {
  handleLogout?: Function;
}

const MMenuList: FC<MMenuListProps> = ({ handleLogout = () => {} }) => {
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);

  const handleToggle = (event: MouseEvent<HTMLElement>) => {
    setAnchorEl(anchorEl ? null : event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const open = Boolean(anchorEl);

  const Router = useRouter();

  return (
    <Box>
      <Button id="menu" color="primary" variant="outlined" onClick={handleToggle} endIcon={<ArrowDropDown />}>
        Menu
      </Button>
      <Popper id="menu" anchorEl={anchorEl} open={open}>
        <Paper>
          <ClickAwayListener onClickAway={handleClose}>
            <MenuList>
              <MenuItem onClick={() => Router.push('profile')}>
                <PersonOutline />
                Profile
              </MenuItem>
              <MenuItem onClick={handleToggle}>
                <MailOutline />
                Requests
              </MenuItem>
              <MenuItem onClick={handleToggle}>
                <Settings />
                Settings
              </MenuItem>
              <MenuItem onClick={() => handleLogout()}>
                <ExitToApp />
                Logout
              </MenuItem>
            </MenuList>
          </ClickAwayListener>
        </Paper>
      </Popper>
    </Box>
  );
};

export default MMenuList;
