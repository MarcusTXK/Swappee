import { Box } from '@material-ui/core';
import AOutlinedButton from 'components/atoms/AOutlinedButton';
import AHeart from 'components/atoms/AHeart';
import { AppState } from 'redux-saga/interfaces';
import { useSelector } from 'react-redux';
import { useRouter } from 'next/dist/client/router';
import { useEffect } from 'react';
import { getItemList } from 'redux-saga/actions';
import { convertDate } from 'config/utils';

const MItemNameHistory = () => {
  const router = useRouter();
  const { itemId } = router.query;
  const item = useSelector((state: AppState) => state.items.find((item) => item.id == parseInt(itemId as string)));

  return (
    <Box className="m-item-name-history">
      <p className="m-item-name-history__item-name">{item?.name}</p>
      <p className="m-item-name-history__last-modified">Last Modified: {convertDate(item?.lastModifiedDate)}</p>
      <Box className="m-item-name-history__button-heart">
        <AOutlinedButton buttonText="History" onClick={() => console.log('History button clicked')} />
        <Box className="m-item-name-history__button-heart__heart">
          <AHeart />
          <p>{item?.likes}</p>
        </Box>
      </Box>
    </Box>
  );
};

export default MItemNameHistory;
