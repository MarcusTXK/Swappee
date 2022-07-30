import { Box } from '@material-ui/core';
import ACategoryPill from 'components/atoms/ACategoryPill';

const lorem =
  'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.';

const categories = ['Video games', 'Books'];

const MItemDetails = () => {
  return (
    <Box className="m-item-details">
      <Box className="m-item-details__description">
        <p className="m-item-details__description__title">Description</p>
        <p className="m-item-details__description__description">{lorem}</p>
      </Box>
      <Box className="m-item-details__details">
        <p className="m-item-details__details__title">Details</p>
        <p className="m-item-details__details__description">Brand: Lorem Ipsum</p>
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
