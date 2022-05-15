import { FC } from 'react';
import { Button, ButtonProps } from '@material-ui/core';

interface AOutlinedButtonProps {
  onClick: React.MouseEventHandler<HTMLButtonElement>;
  buttonText: string;
}

const AOutlinedButton: FC<AOutlinedButtonProps & ButtonProps> = ({ onClick, buttonText, ...other }) => {
  return (
    <Button color="primary" variant="outlined" onClick={onClick} {...other}>
      {buttonText}
    </Button>
  );
};

export default AOutlinedButton;
