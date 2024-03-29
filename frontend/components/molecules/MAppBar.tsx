import { Button, Box } from '@material-ui/core';
import Image from 'next/image';
import logo from 'public/logo.jpg';
import MSearchBar from './MSearchBar';

const handleClick = (event: React.MouseEventHandler<HTMLButtonElement>) => {
  console.log('hi');
};

const MAppBar = () => {
  return (
    <Box className="m-appbar">
      <Box className="m-appbar__logo">
        <Image height="50px" width="70px" src={logo} alt="logo" />
        <MSearchBar />
      </Box>
      <Box className="m-appbar__buttons">
        <Button color="primary" variant="contained">
          Trade
        </Button>

        <Button color="primary" variant="outlined">
          Menu
        </Button>
      </Box>
    </Box>
  );
};

export default MAppBar;
