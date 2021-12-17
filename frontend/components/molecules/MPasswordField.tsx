import { FC } from 'react';
import { IconButton, TextField, InputAdornment, TextFieldProps } from '@material-ui/core';
import { Visibility, VisibilityOff } from '@material-ui/icons';

interface MPasswordFieldProps {
  password: string;
  handlePassword: (event: React.ChangeEvent<HTMLInputElement>) => void;
  isVisible: boolean;
  handleVisible: () => void;
}

const MPasswordField: FC<MPasswordFieldProps & TextFieldProps> = ({
  password,
  handlePassword,
  isVisible,
  handleVisible,
  ...other
}) => {
  return (
    <TextField
      label="Password"
      type={isVisible ? 'text' : 'password'}
      fullWidth
      variant="outlined"
      size="small"
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
