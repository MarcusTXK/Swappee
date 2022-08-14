import { FC, useEffect, useState } from 'react';
import { Box } from '@material-ui/core';
import Image from 'next/image';

interface MUserNameProps {
  username: string;
  avatarPath?: number;
}

const MUserName: FC<MUserNameProps> = ({ username, avatarPath, ...other }) => {
  const [imgSrc, setImgSrc] = useState(`https://avatars.dicebear.com/api/bottts/${username}.svg`);
  useEffect(() => {
    if (avatarPath) {
      setImgSrc(`http://localhost:8022/swappee/api/public/picture/${avatarPath}`);
    }
  }, []);

  return (
    <Box className="username-box">
      <Image
        className="username-box__user-avatar"
        src={imgSrc}
        onError={() => {
          setImgSrc(`https://avatars.dicebear.com/api/bottts/${username}.svg`);
        }}
        width="30"
        height="30"
        alt="user avatar"
      />
      <p className="m-item-card__username">{username}</p>
    </Box>
  );
};

export default MUserName;
