import { useRef, useState, MouseEvent, ChangeEvent, FC } from 'react';
import { Button, TextField, MenuList, MenuItem, Divider, ButtonGroup, Popper, Paper } from '@material-ui/core';
import { Search, ArrowDropDown } from '@material-ui/icons';

interface MSearchBarProps {
  variant?: string;
  input: string;
  handleInput: (event: React.ChangeEvent<HTMLInputElement>) => void;
  handleSearch: (event: MouseEvent<HTMLButtonElement>) => void;
}

const MSearchBar: FC<MSearchBarProps> = ({ variant = '', input, handleInput, handleSearch, ...other }) => {
  const [open, setOpen] = useState(false);
  const anchorRef = useRef<HTMLDivElement>(null);
  const [selectedIndex, setSelectedIndex] = useState(0);

  const handleToggle = (event: MouseEvent<HTMLElement>) => {
    setOpen((prevOpen) => !prevOpen);
  };

  const handleMenuItemClick = (event: MouseEvent<HTMLLIElement>, index: number) => {
    setSelectedIndex(index);
    setOpen(false);
  };

  const options = ['Item', 'User'];
  return variant == 'profile_page' ? (
    <TextField
      value={input}
      className="m-searchbar"
      variant="outlined"
      placeholder={'Search'}
      size="small"
      onChange={handleInput}
      InputProps={{
        endAdornment: (
          <>
            <Divider orientation="vertical" />
            <Button className="m-searchbar__searchbutton" onClick={handleSearch}>
              <Search />
            </Button>
          </>
        ),
      }}
    />
  ) : (
    <TextField
      value={input}
      className="m-searchbar"
      variant="outlined"
      placeholder={`Search by ${options[selectedIndex]}`}
      size="small"
      onChange={handleInput}
      InputProps={{
        endAdornment: (
          <>
            <Divider orientation="vertical" />
            <ButtonGroup variant="text" ref={anchorRef}>
              <Button className="m-searchbar__searchbutton" onClick={handleSearch}>
                <Search />
              </Button>
              <Button className="m-searchbar__dropdownbutton" onClick={handleToggle}>
                <ArrowDropDown />
              </Button>
            </ButtonGroup>
            <Popper anchorEl={anchorRef.current} open={open} className="m-search-menulist">
              <Paper>
                <MenuList>
                  {options.map((option, index) => (
                    <MenuItem
                      key={option}
                      selected={index === selectedIndex}
                      onClick={(event) => handleMenuItemClick(event, index)}
                    >
                      {option}
                    </MenuItem>
                  ))}
                </MenuList>
              </Paper>
            </Popper>
          </>
        ),
      }}
    />
  );
};

export default MSearchBar;
