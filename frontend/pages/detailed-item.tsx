import AContainer1440 from 'components/atoms/AContainer1440';
import MItemDetails from 'components/molecules/MItemDetails';
import ODetailedItemMiddle from 'components/organisms/ODetailedItemMiddle';
import MCarousel from '../components/molecules/MCarousel';

const DetailedItem = () => {
  return (
    <AContainer1440>
      <MCarousel />
      <ODetailedItemMiddle />
      <MItemDetails />
    </AContainer1440>
  );
};

export default DetailedItem;
