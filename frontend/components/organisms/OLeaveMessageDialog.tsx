import { FC, useState } from 'react';
import { Box, Dialog, DialogTitle, TextareaAutosize } from '@material-ui/core';
import AFilledButton from 'components/atoms/AFilledButton';
import CloseIcon from '@material-ui/icons/Close';

interface OLeaveMessageDialogProps {
  isOpen: boolean;
  onConfirmButtonClick: Function;
  onClose: React.MouseEventHandler<SVGSVGElement>;
}

const OLeaveMessageDialog: FC<OLeaveMessageDialogProps> = ({ isOpen, onClose, onConfirmButtonClick }) => {
  const [text, setText] = useState<string>('');

  return (
    <Dialog fullWidth={true} maxWidth="md" open={isOpen}>
      <Box className="o-leave-message">
        <CloseIcon className="o-leave-message__close" onClick={onClose} />
        <DialogTitle id="alert-dialog-title">Step 2: Leave a message</DialogTitle>
        <Box className="o-leave-message__text-field">
          <TextareaAutosize
            className="o-leave-message__text-field__textarea"
            minRows={4}
            maxRows={4}
            placeholder="Write a custom message..."
            value={text}
            onChange={(e) => setText(e.target.value)}
          />
          <AFilledButton buttonText="Confirm Trade Offer" onClick={() => onConfirmButtonClick(text)} />
        </Box>
      </Box>
    </Dialog>
  );
};

export default OLeaveMessageDialog;
