import { useState, MouseEvent } from 'react';
import { Button, TextField, Menu, MenuList, MenuItem, Divider } from '@material-ui/core';
import { Search } from '@material-ui/icons';

const MSampleSearchBar = () => {
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);

  const handleClick = (event: MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <TextField
      className="m-sample-searchbar"
      variant="outlined"
      label="Test"
      size="small"
      InputProps={{
        endAdornment: (
          <>
            <Divider orientation="vertical" />
            <Button onClick={handleClick}>
              <Search />
            </Button>
            <Menu anchorEl={anchorEl} open={Boolean(anchorEl)} onClose={handleClose}>
              <MenuList>
                <MenuItem>Items</MenuItem>
                <MenuItem>Users</MenuItem>
              </MenuList>
            </Menu>
          </>
        ),
      }}
    />
  );
};

export default MSampleSearchBar;
