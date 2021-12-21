import { FC, useState, ChangeEvent } from 'react';
import { Box, Dialog, DialogTitle, TextField, Button } from '@material-ui/core';
import { Search } from '@material-ui/icons';
import Pagination from '@material-ui/lab/Pagination';
import AFilledButton from 'components/atoms/AFilledButton';
import CloseIcon from '@material-ui/icons/Close';
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

  const handleChangePage = (_event: ChangeEvent<unknown>, value: number) => {
    setPage(value);
    setIsDataLoaded(false);
    // simulate fetching data
    setTimeout(() => setIsDataLoaded(true), 5);
  };

  return (
    <Dialog fullWidth={true} maxWidth="md" open={isOpen}>
      <Box className="o-offer-item">
        <CloseIcon className="o-offer-item__titlesearch__close" onClick={onClose} />
        <Box className="o-offer-item__titlesearch">
          <DialogTitle id="alert-dialog-title">Step 1: Choose which item to offer</DialogTitle>
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
        <p className="o-offer-item__note">
          Note: if the owner sets 'Strict', only items matching the preferred categories will be displayed.
        </p>
        <MItemListingContainer page={page} onClick={onClick} isDataLoaded={isDataLoaded} />
        <Box className="o-offer-item__pagination">
          <Pagination count={10} page={page} onChange={handleChangePage} />
          <AFilledButton buttonText="Next" onClick={onNextButtonClick} />
        </Box>
      </Box>
    </Dialog>
  );
};

export default OOpenOfferItemDialog;
