import { FC } from 'react';
import { Box } from '@material-ui/core';
import Image from 'next/image';

interface MUserNameProps {
  username: string;
  avatar?: string;
}

const MUserName: FC<MUserNameProps> = ({ username, avatar, ...other }) => (
  <Box className="username-box">
    <Image
      className="username-box__user-avatar"
      src={avatar || `https://avatars.dicebear.com/api/bottts/${username}.svg`}
      width="30"
      height="30"
      alt="user avatar"
    />
    <p className="m-item-card__username">{username}</p>
  </Box>
);

export default MUserName;
