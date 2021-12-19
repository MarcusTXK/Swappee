import { Box } from '@material-ui/core';
import MItemDetails from 'components/molecules/MItemDetails';
import MItemNameHistory from 'components/molecules/MItemNameHistory';
import MUserCard from 'components/molecules/MUserCard';

const ODetailedItemMiddle = () => {
  return (
    <Box className="o-detailed-item-middle">
      <MItemNameHistory />
      <MUserCard />
    </Box>
  );
};

export default ODetailedItemMiddle;
