import { FC } from 'react';
import { Box } from '@material-ui/core';
import MItemCard from './MItemCard';
import MItemCardSkeleton from './MItemCardSkeleton';

interface MItemListingContainerProps {
  isDataLoaded: boolean;
  onClick: React.MouseEventHandler<HTMLDivElement>;
  page?: number;
}

const numItemCardsInOnePage = 9;

const MItemListingContainer: FC<MItemListingContainerProps> = ({ isDataLoaded, onClick, page, ...other }) => {
  const elements = [0, 1, 2, 3, 4, 5, 6, 7, 8];
  return (
    <Box className="m-listing-container">
      <Box className="m-listing-container__items">
        {isDataLoaded
          ? elements.map((value, index: Number) => {
              // eslint-disable-next-line no-console
              if (page != null) {
                if (index < page * numItemCardsInOnePage && index >= (page - 1) * numItemCardsInOnePage) {
                  return (
                    <MItemCard
                      key={`item-card-${value}`}
                      hasAvatar={false}
                      isLiked={false}
                      isUsed={true}
                      isListing={true}
                      username="yanhanapple<3"
                      title="Apple For Sale"
                      onClick={onClick}
                    />
                  );
                }
                return null;
              }
              return (
                <MItemCard
                  key={`item-card-${value}`}
                  hasAvatar={false}
                  isLiked={false}
                  isUsed={true}
                  isListing={true}
                  username="yanhanapple<3"
                  title="Apple For Sale"
                  onClick={onClick}
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
