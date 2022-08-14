import { FC } from 'react';
import FavoriteIcon from '@material-ui/icons/Favorite';
import FavoriteBorder from '@material-ui/icons/FavoriteBorder';

interface AHeartProps {
  onClick: React.MouseEventHandler<SVGSVGElement>;
  isLiked: boolean;
}

const AHeart: FC<AHeartProps> = ({ onClick, isLiked, ...other }) => {
  return (
    <div>
      {isLiked ? (
        <FavoriteIcon onClick={onClick} className="a-heart" />
      ) : (
        <FavoriteBorder onClick={onClick} className="a-heart" />
      )}
    </div>
  );
};

export default AHeart;
