import { FC } from 'react';
import Box, { BoxProps } from '@material-ui/core/Box';

interface AContainer1440Props {
  children: React.ReactNode;
}

const AContainer1440: FC<AContainer1440Props & BoxProps> = ({ children, ...other }) => (
  <Box width="100%" mx="auto" maxWidth={1440} px={{ xs: 5, md: 20 }} {...other}>
    {children}
  </Box>
);

export default AContainer1440;
