import { FC } from 'react';
import { Box } from '@material-ui/core';
import Image from 'next/image';

interface AUserNameProps {
  username: string;
  avatar?: string;
  // hasAvatar: boolean;
  // isLiked: boolean;
  // isUsed: boolean;
  // isListing: boolean;
  // username: string;
  // title: string;
  // avatar?: string
}

const AUserName: FC<AUserNameProps> = ({ username, avatar, ...other }) => (
  <Box className="username-box">
    <Image
      className="username-box__user-avatar"
      src={avatar || `https://avatars.dicebear.com/api/bottts/${username}.svg`}
      width="30"
      height="30"
      alt="user avatar"
    />

    <p className="a-item-card__username">{username}</p>
  </Box>
);

export default AUserName;
