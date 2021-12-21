import { Box } from '@material-ui/core';
import AOutlinedButton from 'components/atoms/AOutlinedButton';
import AHeart from 'components/atoms/AHeart';

const MItemNameHistory = () => {
  return (
    <Box className="m-item-name-history">
      <p className="m-item-name-history__item-name">Item Name</p>
      <p className="m-item-name-history__last-modified">Last Modified: 14/08/2021</p>
      <Box className="m-item-name-history__button-heart">
        <AOutlinedButton buttonText="History" onClick={() => console.log('History button clicked')} />
        <Box className="m-item-name-history__button-heart__heart">
          <AHeart />
          <p>12</p>
        </Box>
      </Box>
    </Box>
  );
};

export default MItemNameHistory;
