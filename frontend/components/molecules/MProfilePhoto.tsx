import { Grid, Box, Button } from '@material-ui/core';
import Image from 'next/image';
import { FC } from 'react';

interface MProfilePhotoProps {
  username: string;
  uploadedPhoto?: string;
  handleUploadPhoto: Function;
}

const MProfilePhoto: FC<MProfilePhotoProps> = ({ username, uploadedPhoto = '', handleUploadPhoto = () => {} }) => {
  return (
    <Grid container direction="row" wrap="nowrap">
      <Grid>
        <Image
          src={uploadedPhoto || `https://avatars.dicebear.com/api/bottts/${username}.svg`}
          width="60"
          height="60"
          alt="profile picture"
          unoptimized={true}
        />
      </Grid>
      <Box pl={3}>
        <Grid container direction="column" justifyContent="center">
          <Grid>Max size of 2MB</Grid>
          <Grid>
            <Button size="small" color="primary" variant="outlined" component="label">
              Upload a photo
              <input
                id="photo"
                type="file"
                accept="image/jpeg, image/png"
                onChange={(e) => handleUploadPhoto(e)}
                hidden
              />
            </Button>
          </Grid>
        </Grid>
      </Box>
    </Grid>
  );
};

export default MProfilePhoto;
