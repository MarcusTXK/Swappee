import { FC, useState, ChangeEvent } from 'react';
import { Box, Dialog, DialogTitle, TextField, Button, Typography } from '@material-ui/core';
import { Search, Title } from '@material-ui/icons';
import Pagination from '@material-ui/lab/Pagination';
import AFilledButton from 'components/atoms/AFilledButton';
import CloseIcon from '@material-ui/icons/Close';
import { ItemData } from 'redux-saga/interfaces';
import MItemListingContainer from '../molecules/MItemListingContainer';

interface OOpenOfferItemDialogProps {
  isOpen: boolean;
  onClick: React.MouseEventHandler<HTMLDivElement>;
  onNextButtonClick: React.MouseEventHandler<HTMLButtonElement>;
  onClose: React.MouseEventHandler<SVGSVGElement>;
}

const OOpenOfferItemDialog: FC<OOpenOfferItemDialogProps> = ({ isOpen, onClick, onClose, onNextButtonClick }) => {
  const [search, setSearch] = useState<String>('');
  const [page, setPage] = useState<number>(1);
  const [isDataLoaded, setIsDataLoaded] = useState(true);

  const [data, setData] = useState<ItemData[]>([
    {
      id: 1,
      imagePath: 3,
      name: 'Apple For Sale',
      status: '',
      brand: '',
      description: '',
      likes: 3,
      liked: false,
      userId: 3,
      userName: 'yanhanapple<3',
      createdDate: '',
      lastModifiedDate: '',
    },
    {
      id: 2,
      imagePath: 3,
      name: 'Bananas',
      status: '',
      brand: '',
      description: '',
      likes: 2,
      liked: false,
      userId: 1,
      userName: 'tauple',
      createdDate: '',
      lastModifiedDate: '',
    },
    {
      id: 3,
      imagePath: 3,
      name: 'Oranges',
      status: '',
      brand: '',
      description: '',
      likes: 2,
      liked: false,
      userId: 1,
      userName: 'yanhanapple<3',
      createdDate: '',
      lastModifiedDate: '',
    },
  ]);

  const handleChangePage = (_event: ChangeEvent<unknown>, value: number) => {
    setPage(value);
    setIsDataLoaded(false);
    // simulate fetching data
    setTimeout(() => setIsDataLoaded(true), 5);
  };

  return (
    <Dialog fullWidth={true} maxWidth="md" open={isOpen}>
      <Box className="o-offer-item">
        <Box className="o-offer-item__titlesearch">
          <Box className="o-offer-item__titlesearch__container">
            <p className="o-offer-item__titlesearch__container__title">Step 1: Choose which item to offer</p>
            <TextField
              value={search}
              variant="outlined"
              placeholder="Search"
              size="small"
              onChange={(e) => setSearch(e.target.value)}
              InputProps={{
                endAdornment: (
                  <>
                    <Button onClick={() => console.log('Search for items')}>
                      <Search />
                    </Button>
                  </>
                ),
              }}
            />
          </Box>
          <CloseIcon className="o-offer-item__titlesearch__close" onClick={onClose} />
        </Box>
        <p className="o-offer-item__note">
          Note: if the owner sets 'Strict', only items matching the preferred categories will be displayed.
        </p>
        <MItemListingContainer
          page={page}
          onClick={onClick}
          isDataLoaded={isDataLoaded}
          data={data}
          numItemCardsInOnePage={9}
        />
        <Box className="o-offer-item__pagination">
          <Pagination count={10} page={page} onChange={handleChangePage} />
          <AFilledButton buttonText="Next" onClick={onNextButtonClick} />
        </Box>
      </Box>
    </Dialog>
  );
};

export default OOpenOfferItemDialog;
