import { useState } from 'react';
import FavoriteIcon from '@material-ui/icons/Favorite';
import FavoriteBorder from '@material-ui/icons/FavoriteBorder';

const AHeart = () => {
  const [isRed, setIsRed] = useState(false);

  const toggleLike = () => {
    setIsRed(!isRed);
    // Other code to change liked status in database.
  };
  return (
    <div>
      {isRed ? (
        <FavoriteIcon onClick={toggleLike} className="a-heart" />
      ) : (
        <FavoriteBorder onClick={toggleLike} className="a-heart" />
      )}
    </div>
  );
};

export default AHeart;
