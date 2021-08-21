import { Button, Box } from '@material-ui/core';
import Link from 'next/link';

import AHead from 'components/atoms/AHead';

const Home = () => {
  return (
    <>
      <AHead />
      <Box component="body">
        <Box display="flex" alignItems="center" flexDirection="column" width="100%" pt={4}>
          <h1>
            Welcome to{' '}
            <Link href="https://github.com/MarcusTXK/Swappee">
              <a>Swappee!</a>
            </Link>
          </h1>
          <Box component="p" my={4}>
            A platform allowing users in a community to list items online and trade.
          </Box>
          <Box display="flex">
            <Box mr={2}>
              <Button color="primary" variant="outlined">
                Click Me!
              </Button>
            </Box>
            <Button color="primary" variant="contained">
              Don&apos;t Click Me!
            </Button>
          </Box>
        </Box>
      </Box>
    </>
  );
};

export default Home;
