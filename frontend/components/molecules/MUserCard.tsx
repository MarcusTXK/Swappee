import { FC } from 'react';
import { Box, Button } from '@material-ui/core';
import AFilledButton from 'components/atoms/AFilledButton';
import MailOutlineIcon from '@material-ui/icons/MailOutline';
import Rating from '@material-ui/lab/Rating';
import Image from 'next/image';
import { useSelector } from 'react-redux';
import { useRouter } from 'next/dist/client/router';
import { AppState } from 'redux-saga/interfaces';

// Hardcoded value, to be retrieved using API
const numRatings = 17;

interface MUserCardProps {
  openOfferItem: React.MouseEventHandler<HTMLButtonElement>;
}

const MUserCard: FC<MUserCardProps> = ({ openOfferItem }) => {
  const router = useRouter();
  const { itemId } = router.query;
  const item = useSelector((state: AppState) => state.items.find((item) => item.id == parseInt(itemId as string)));

  const avatar = undefined;

  return (
    <Box className="m-user-card" boxShadow={3}>
      <Box className="m-user-card__row">
        <Image
          className="m-user-card__row__avatar"
          src={avatar || `https://avatars.dicebear.com/api/bottts/${item?.userName}.svg`}
          width="30"
          height="30"
          alt="user avatar"
        />
        <Box>
          <p className="m-item-card__username">{item?.userName}</p>
          {/* https://github.com/voronianski/react-star-rating-component/blob/master/example/index.js */}
          <Box className="m-user-card__row__ratings">
            <Rating name="user-rating" defaultValue={4} size="small" readOnly />
            <p className="m-user-card__row__ratings__reviews">{`(${numRatings} Reviews)`}</p>
          </Box>
        </Box>
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
