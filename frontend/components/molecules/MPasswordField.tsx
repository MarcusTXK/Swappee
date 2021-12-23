import { FC, useState } from 'react';
import { IconButton, TextField, InputAdornment, TextFieldProps } from '@material-ui/core';
import { Visibility, VisibilityOff } from '@material-ui/icons';

interface MPasswordFieldProps {
  label?: string;
  password: string;
  handlePassword: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

const MPasswordField: FC<MPasswordFieldProps & TextFieldProps> = ({
  label = 'Password',
  password,
  handlePassword,
  ...other
}) => {
  const [isVisible, setVisible] = useState(false);
  const handleVisible = () => {
    setVisible(!isVisible);
  };
  return (
    <TextField
      label={label}
      type={isVisible ? 'text' : 'password'}
      fullWidth
      variant="outlined"
      value={password}
      onChange={handlePassword}
      InputProps={{
        endAdornment: (
          <InputAdornment position="end">
            <IconButton aria-label="toggle password visibility" onClick={handleVisible}>
              {isVisible ? <Visibility /> : <VisibilityOff />}
            </IconButton>
          </InputAdornment>
        ),
      }}
      {...other}
    />
  );
};

export default MPasswordField;
