import { Box, Button } from '@material-ui/core';
import AFilledButton from 'components/atoms/AFilledButton';
import MailOutlineIcon from '@material-ui/icons/MailOutline';
import Rating from '@material-ui/lab/Rating';
import Typography from '@material-ui/core/Typography';
import MUserName from './MUserName';

// Hardcoded value, to be retrieved using API
const numRatings = 17;

const MUserCard = () => {
  return (
    <Box className="m-user-card" boxShadow={3}>
      <MUserName username="username" />
      {/* https://github.com/voronianski/react-star-rating-component/blob/master/example/index.js */}
      <Box className="m-user-card__ratings">
        <Box component="fieldset" mb={3} borderColor="transparent">
          <Typography component="legend">Read only</Typography>
          <Rating name="user-rating" defaultValue={5} size="small" readOnly />
        </Box>
        <p className="m-user-card__ratings__reviews">{`(${numRatings} Reviews)`}</p>
      </Box>
      <Box className="m-user-card__buttons">
        <Button color="primary" variant="outlined" onClick={() => console.log('Email Swapper')}>
          <MailOutlineIcon className="m-user-card__buttons__mail" />
          Email
        </Button>
        <AFilledButton buttonText="Trade Item" onClick={() => console.log('Trade Item')} />
      </Box>
    </Box>
  );
};

export default MUserCard;
