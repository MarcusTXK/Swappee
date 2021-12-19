import { Box, Button } from '@material-ui/core';
import StarRatingComponent from 'react-star-rating-component';
import StarBorderIcon from '@material-ui/icons/StarBorder';
import StarIcon from '@material-ui/icons/Star';
import StarHalfIcon from '@material-ui/icons/StarHalf';
import AFilledButton from 'components/atoms/AFilledButton';
import MailOutlineIcon from '@material-ui/icons/MailOutline';
import MUserName from './MUserName';

// Hardcoded value, to be retrieved using API
const numRatings = 17;

const MUserCard = () => {
  return (
    <Box className="m-user-card">
      <MUserName username="username" />
      {/* https://github.com/voronianski/react-star-rating-component/blob/master/example/index.js */}
      <Box className="m-user-card__ratings">
        <StarRatingComponent
          name=""
          value={3.5}
          starCount={5}
          editing={false}
          emptyStarColor="#ffb400"
          renderStarIcon={(index: number, value: number) => {
            return <span>{index <= value ? <StarIcon /> : <StarBorderIcon />}</span>;
          }}
          renderStarIconHalf={() => {
            return (
              <span>
                <span style={{ position: 'absolute' }}>
                  <StarBorderIcon />
                </span>
                <span>
                  <StarHalfIcon className="a-star" />
                </span>
              </span>
            );
          }}
        />
        <p className="m-user-card__ratings__reviews">{`(${numRatings} Reviews)`}</p>
      </Box>
      <Box className="m-user-card__buttons">
        <Button color="primary" variant="outlined" onClick={() => console.log("Email Swapper")}>
          <MailOutlineIcon className="m-user-card__buttons__mail"/>
          Email
        </Button>
        <AFilledButton buttonText="Trade Item" onClick={() => console.log('Trade Item')} />
      </Box>
    </Box>
  );
};

export default MUserCard;
