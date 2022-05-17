import { useState } from 'react';
import AContainer1440 from 'components/atoms/AContainer1440';
import MItemDetails from 'components/molecules/MItemDetails';
import ODetailedItemMiddle from 'components/organisms/ODetailedItemMiddle';
import OOpenOfferItemDialog from 'components/organisms/OOpenOfferItemDialog';
import OLeaveMessageDialog from 'components/organisms/OLeaveMessageDialog';
import MCarousel from '../components/molecules/MCarousel';

const DetailedItem = () => {
  const [isChooseOfferItemStage, setIsChooseOfferItemStage] = useState(false);
  const [isLeaveMessageStage, setIsLeaveMessageStage] = useState(false);

  const handleNextButtonClick = () => {
    setIsChooseOfferItemStage(false);
    setIsLeaveMessageStage(true);
  };

  const handleConfirmButtonClick = (text: string) => {
    console.log(`send trade offer to backend with message: ${text}`);
    setIsLeaveMessageStage(false);
  };

  const handleCloseDialog = () => {
    setIsLeaveMessageStage(false);
    setIsChooseOfferItemStage(false);
  };
  return (
    <AContainer1440>
      <MCarousel />
      <ODetailedItemMiddle
        openOfferItem={() => {
          setIsChooseOfferItemStage(true);
          setIsLeaveMessageStage(false);
        }}
      />
      <MItemDetails />
      <OOpenOfferItemDialog
        onClick={() => console.log('Set state to be unique ID of the item that was chosen')}
        isOpen={isChooseOfferItemStage}
        onNextButtonClick={handleNextButtonClick}
        onClose={handleCloseDialog}
      />
      <OLeaveMessageDialog
        onConfirmButtonClick={handleConfirmButtonClick}
        isOpen={isLeaveMessageStage}
        onClose={handleCloseDialog}
      />
    </AContainer1440>
  );
};

export default DetailedItem;
