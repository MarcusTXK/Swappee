import { FC, useEffect, useState } from 'react';
import { Box } from '@material-ui/core';
import MItemCard from './MItemCard';
import MItemCardSkeleton from './MItemCardSkeleton';
import { ItemData } from 'redux-saga/interfaces';
import { useSelector, useStore } from 'react-redux';
import { numItemCardsInOnePage } from 'config/constants';
import { useRouter } from 'next/dist/client/router';
import { API, ROUTES } from 'config/api';

interface MItemListingContainerProps {
  page?: number;
  data: ItemData[];
}

const MItemListingContainer: FC<MItemListingContainerProps> = ({ page, data, ...other }) => {
  const elements = [0, 1, 2, 3, 4, 5, 6, 7, 8];

  const router = useRouter();
  const [isDataLoaded, setIsDataLoaded] = useState(false);

  useEffect(() => {
    setTimeout(() => setIsDataLoaded(true), 500);
  }, [data]);

  return (
    <Box className="m-listing-container">
      <Box className="m-listing-container__items">
        {isDataLoaded
          ? data.map((value, index: number) => {
              const { id, name, liked, avatarPath, userName, status, imagePath } = value;
              if (page != null) {
                if (index < page * numItemCardsInOnePage) {
                  return (
                    <MItemCard
                      id={id}
                      key={`item-card-${id}`}
                      avatarPath={avatarPath}
                      isLiked={liked}
                      isUsed={!value.new}
                      isListing={status === 'OPEN'}
                      username={userName}
                      title={name}
                      onClick={() => router.push(`/item/${id}`)}
                      imagePath={imagePath}
                    />
                  );
                }
                return null;
              }
              return (
                <MItemCard
                  id={12}
                  key={`item-card-${index}`}
                  isLiked={false}
                  isUsed={true}
                  isListing={true}
                  username="yanhanapple<3"
                  title="Apple For Sale"
                  onClick={() => {}}
                />
              );
            })
          : elements.map((value) => {
              return <MItemCardSkeleton key={`item-card-skeleton-${value}`} />;
            })}
      </Box>
    </Box>
  );
};

export default MItemListingContainer;
