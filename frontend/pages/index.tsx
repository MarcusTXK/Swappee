import { Button, Box } from '@material-ui/core';
import Link from 'next/link';
import Head from 'next/head';
import AContainer1440 from 'components/atoms/AContainer1440';
import OAppBar from 'components/organisms/OAppBar';

const Home = () => {
  return (
    <>
      <Head>
        <title>Swappee</title>
      </Head>
      <OAppBar />
      <AContainer1440>
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
          <Box mb={4}>
            <OAppBar />
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
      </AContainer1440>
    </>
  );
};

export default Home;
