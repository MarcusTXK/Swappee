import { FC, useState } from 'react';
import { Box, Typography } from '@material-ui/core';
import img from 'public/passportphoto.jpg';
import Image from 'next/image';
import MUserName from 'components/molecules/MUserName';
import FavoriteIcon from '@material-ui/icons/Favorite';
import FavoriteBorder from '@material-ui/icons/FavoriteBorder';

interface MItemCardProps {
  hasAvatar: boolean;
  isLiked: boolean;
  isUsed: boolean;
  isListing: boolean;
  username: string;
  title: string;
  avatar?: string;
}

const MItemCard: FC<MItemCardProps> = ({
  hasAvatar,
  isLiked,
  isUsed,
  isListing,
  username,
  title,
  avatar,
  ...other
}) => {
  const [isRed, setIsRed] = useState(false);

  const toggleLike = () => {
    setIsRed(!isRed);
    // Other code to change liked status in database.
  };
  return (
    <div className="m-item-card">
      <Image className="m-item-card__image" src={img} width="200px" height="200px" alt="item image" />
      <Box padding="10px">
        <Box className="m-item-card__top-box">
          <Typography variant="body1">{title}</Typography>
        </Box>
        {isUsed ? <Typography variant="caption">Used</Typography> : <Typography variant="caption">New</Typography>}
        <Box className="m-item-card__bottom-box">
          <MUserName username={username} />
          {isRed ? (
            <FavoriteIcon onClick={toggleLike} className="m-item-card__heart" />
          ) : (
            <FavoriteBorder onClick={toggleLike} className="m-item-card__empty-heart" />
          )}
        </Box>
      </Box>
    </div>
  );
};

export default MItemCard;
