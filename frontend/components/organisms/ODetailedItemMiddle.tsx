import { FC } from 'react';
import { Box } from '@material-ui/core';
import MItemNameHistory from 'components/molecules/MItemNameHistory';
import MUserCard from 'components/molecules/MUserCard';

interface ODetailedItemMiddleProps {
  openOfferItem: Function;
}

const ODetailedItemMiddle: FC<ODetailedItemMiddleProps> = ({ openOfferItem }) => {
  return (
    <Box className="o-detailed-item-middle">
      <MItemNameHistory />
      <MUserCard openOfferItem={openOfferItem} />
    </Box>
  );
};

export default ODetailedItemMiddle;
