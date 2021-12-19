import { FC } from 'react';
import { Button } from '@material-ui/core';

interface AOutlinedButtonProps {
  onClick: React.MouseEventHandler<HTMLButtonElement>;
  buttonText: string;
}

const AOutlinedButton: FC<AOutlinedButtonProps> = ({ onClick, buttonText, ...other }) => {
  return (
    <Button color="primary" variant="outlined" onClick={onClick}>
      {buttonText}
    </Button>
  );
};

export default AOutlinedButton;
