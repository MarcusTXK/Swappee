import { FC } from 'react';
import { Button } from '@material-ui/core';

interface AFilledButtonProps {
  onClick: React.MouseEventHandler<HTMLButtonElement>;
  buttonText: string;
}

const AFilledButton: FC<AFilledButtonProps> = ({ onClick, buttonText, ...other }) => {
  return (
    <Button color="primary" variant="contained" onClick={onClick}>
      {buttonText}
    </Button>
  );
};

export default AFilledButton;
