import { FC } from 'react';
import { Box } from '@material-ui/core';
import MItemCard from './MItemCard';
import MItemCardSkeleton from './MItemCardSkeleton';
import { ItemData } from 'redux-saga/interfaces';

interface MItemListingContainerProps {
  isDataLoaded: boolean;
  data: ItemData[];
  numItemCardsInOnePage: number;
  onClick: React.MouseEventHandler<HTMLDivElement>;
  page?: number;
}

const MItemListingContainer: FC<MItemListingContainerProps> = ({
  isDataLoaded,
  data,
  numItemCardsInOnePage,
  onClick,
  page,
  ...other
}) => {
  return (
    <Box className="m-listing-container">
      <Box className="m-listing-container__items">
        {isDataLoaded
          ? data.length == 0
            ? 'No results found'
            : data.map((value, index: Number) => {
                // eslint-disable-next-line no-console
                if (page != null) {
                  if (index < page * numItemCardsInOnePage && index >= (page - 1) * numItemCardsInOnePage) {
                    return (
                      <MItemCard
                        key={`item-card-${value.id}`}
                        hasAvatar={false}
                        isLiked={false}
                        isUsed={true}
                        isListing={true}
                        username={value.userName}
                        title={value.name}
                        onClick={onClick}
                      />
                    );
                  }
                  return null;
                }
                return (
                  <MItemCard
                    key={`item-card-${value.id}`}
                    hasAvatar={false}
                    isLiked={false}
                    isUsed={true}
                    isListing={true}
                    username={value.userName}
                    title={value.name}
                    onClick={onClick}
                  />
                );
              })
          : data.map((value) => {
              return <MItemCardSkeleton key={`item-card-skeleton-${value}`} />;
            })}
      </Box>
    </Box>
  );
};

export default MItemListingContainer;
