import { FC } from 'react';
import { Box, Button } from '@material-ui/core';
import AFilledButton from 'components/atoms/AFilledButton';
import MailOutlineIcon from '@material-ui/icons/MailOutline';
import Rating from '@material-ui/lab/Rating';
import MUserName from './MUserName';

// Hardcoded value, to be retrieved using API
const numRatings = 17;

interface MUserCardProps {
  openOfferItem: React.MouseEventHandler<HTMLButtonElement>;
}

const MUserCard: FC<MUserCardProps> = ({ openOfferItem }) => {
  return (
    <Box className="m-user-card" boxShadow={3}>
      <MUserName username="username" />
      {/* https://github.com/voronianski/react-star-rating-component/blob/master/example/index.js */}
      <Box className="m-user-card__ratings">
        <Rating name="user-rating" defaultValue={4} size="small" readOnly />
        <p className="m-user-card__ratings__reviews">{`(${numRatings} Reviews)`}</p>
      </Box>
      <Box className="m-user-card__buttons">
        <Button color="primary" variant="outlined" onClick={() => console.log('Email Swapper')}>
          <MailOutlineIcon className="m-user-card__buttons__mail" />
          Email
        </Button>
        <AFilledButton buttonText="Trade Item" onClick={openOfferItem} />
      </Box>
    </Box>
  );
};

export default MUserCard;
