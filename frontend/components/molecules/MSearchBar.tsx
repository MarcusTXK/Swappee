import { useRef, useState, MouseEvent, ChangeEvent } from 'react';
import { Button, TextField, MenuList, MenuItem, Divider, ButtonGroup, Popper, Paper } from '@material-ui/core';
import { Search, ArrowDropDown } from '@material-ui/icons';

const MSearchBar = () => {
  const [search, setSearch] = useState<String>('');
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

  const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
    setSearch(event.target.value);
  };

  const handleSearch = (event: MouseEvent<HTMLElement>) => {
    alert('<3');
  };

  const options = ['Item', 'User'];

  return (
    <TextField
      value={search}
      className="m-searchbar"
      variant="outlined"
      placeholder={`Search by ${options[selectedIndex]}`}
      size="small"
      onChange={handleChange}
      InputProps={{
        endAdornment: (
          <>
            <Divider orientation="vertical" />
            <ButtonGroup variant="text" ref={anchorRef}>
              <Button className="m-searchbar-searchbutton" onClick={handleSearch}>
                <Search />
              </Button>
              <Button className="m-searchbar-dropdownbutton" onClick={handleToggle}>
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
