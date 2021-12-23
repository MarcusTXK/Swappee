import { FC } from 'react';
import { TextField, TextFieldProps } from '@material-ui/core';

interface MInputFieldProps {
  label: string;
  value: string;
  handleChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

const MInputField: FC<MInputFieldProps & TextFieldProps> = ({ label, value, handleChange, ...other }) => {
  return (
    <TextField
      label={label}
      type="text"
      fullWidth
      variant="outlined"
      value={value}
      onChange={handleChange}
      {...other}
    />
  );
};

export default MInputField;
