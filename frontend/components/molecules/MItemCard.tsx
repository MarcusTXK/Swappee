import React, { FC, useState } from 'react';
import { Box, Typography } from '@material-ui/core';
import img from 'public/placeholder.jpg';
import Image from 'next/image';
import MUserName from 'components/molecules/MUserName';
import AHeart from 'components/atoms/AHeart';
import { useSelector } from 'react-redux';
import { AppState } from 'redux-saga/interfaces';

interface MItemCardProps {
  id: number;
  isLiked: boolean;
  isUsed: boolean;
  isListing: boolean;
  username: string;
  title: string;
  avatar?: string;
  imagePath?: number;
  avatarPath?: number;
  // setLiked: React.MouseEventHandler<SVGSVGElement>;
  onClick: React.MouseEventHandler<HTMLDivElement>;
}

const MItemCard: FC<MItemCardProps> = ({
  id,
  isLiked,
  isUsed,
  isListing,
  username,
  title,
  avatarPath,
  imagePath,
  onClick,
  ...other
}) => {
  const {
    user: { token },
    items,
  } = useSelector((state: AppState) => state);

  // const handleClickLike = (e: Event, liked: boolean, itemId: number) => {
  //   e.cancelBubble = true;
  //   if (e.stopPropagation) e.stopPropagation();

  //   const newLikeState: boolean = !liked;
  //   console.log(token);
  //   API.setHeaders({
  //     Authorization: `Bearer ${token}`,
  //   });
  //   API.get(`${ROUTES.CHANGE_LIKE_STATUS}/${itemId}/${newLikeState}`)
  //     .then((data) => console.log(data))
  //     .catch((e) => console.log(e));
  // };

  const [imgSrc, setImgSrc] = useState(`http://localhost:8022/swappee/api/public/picture/${imagePath}`);

  return (
    <div className="m-item-card" onClick={onClick}>
      <Image
        className="m-item-card__image"
        src={imgSrc}
        onError={() => {
          setImgSrc(img.src);
        }}
        width={200}
        height={200}
        alt="item image"
      />
      <Box padding="10px">
        <Box className="m-item-card__top-box">
          <p>{title}</p>
        </Box>
        {isUsed ? <Typography variant="caption">Used</Typography> : <Typography variant="caption">New</Typography>}
        <Box className="m-item-card__bottom-box">
          <MUserName username={username} avatarPath={avatarPath} />
          <AHeart isLiked={isLiked} onClick={(e) => console.log('Like item')} />
        </Box>
      </Box>
    </div>
  );
};

export default MItemCard;
