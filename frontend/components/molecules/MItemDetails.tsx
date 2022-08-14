import { Box } from '@material-ui/core';
import ACategoryPill from 'components/atoms/ACategoryPill';
import { useRouter } from 'next/dist/client/router';
import { useSelector } from 'react-redux';
import { AppState } from 'redux-saga/interfaces';

const categories = ['Video games', 'Books'];

const MItemDetails = () => {
  const router = useRouter();
  const { itemId } = router.query;
  const item = useSelector((state: AppState) => state.items.find((item) => item.id == parseInt(itemId as string)));

  return (
    <Box className="m-item-details">
      <Box className="m-item-details__description">
        <p className="m-item-details__description__title">Description</p>
        <p className="m-item-details__description__description">{item?.description}</p>
      </Box>
      <Box className="m-item-details__details">
        <p className="m-item-details__details__title">Details</p>
        <p className="m-item-details__details__description">Brand: {item?.brand}</p>
        <p>Preferred Categories: </p>
        <Box className="m-item-details__categories">
          {categories.map((category) => {
            return <ACategoryPill key={category} category={category} />;
          })}
        </Box>
      </Box>
    </Box>
  );
};

export default MItemDetails;
