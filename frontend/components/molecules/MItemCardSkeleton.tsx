import { FC } from 'react';
import { Box } from '@material-ui/core';
import Skeleton from '@material-ui/lab/Skeleton';

interface MItemCardSkeletonProps {}

const MItemCardSkeleton: FC<MItemCardSkeletonProps> = ({ ...other }) => {
  return (
    <Box className="m-skeleton-card">
      <Skeleton animation="wave" variant="rect" width={200} height={200} />
      <Skeleton variant="text" height={40} className="m-skeleton-card__title" />
      <Skeleton className="m-skeleton-card__used" variant="text" width={40} height={20} />
      <Box display="flex" flexDirection="row">
        <Skeleton className="m-skeleton-card__avatar" variant="circle" width={30} height={30} />
        <Skeleton variant="text" width={100} />
      </Box>
    </Box>
  );
};

export default MItemCardSkeleton;
