import { FC, useState } from 'react';
import { useDispatch } from 'react-redux';
import { Box, Dialog, IconButton, TextField, Button, Link } from '@material-ui/core';
import { Close } from '@material-ui/icons';
import MPasswordField from 'components/molecules/MPasswordField';
import { colours } from 'config/constants';
import { login } from 'redux-saga/actions';

interface OLoginDialogProps {
  isOpen: boolean;
  onClose: Function;
  handleForgotPassword: () => void;
  handleSignup: () => void;
}

const OLoginDialog: FC<OLoginDialogProps> = ({ isOpen, onClose, handleForgotPassword, handleSignup }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isShowPassword, setShowPassword] = useState(false);
  const dispatch = useDispatch();

  const handleUsername = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(event.target.value);
  };

  const handlePassword = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };

  const handleSubmit = () => {
    dispatch(login({ username, password }));
  };

  return (
    <Dialog open={isOpen} fullWidth>
      <Box display="flex" justifyContent="flex-end" mt={1} mr={1}>
        <IconButton aria-label="close" onClick={() => onClose()} color="inherit">
          <Close />
        </IconButton>
      </Box>
      <Box alignItems="center">
        <Box margin={{ xs: '10px 10px 25px', md: '20px 40px 50px' }} color={colours.lightGrey}>
          <TextField
            label="Username"
            fullWidth
            variant="outlined"
            size="small"
            value={username}
            onChange={handleUsername}
          />
          <Box mt={2} mb={1}>
            <MPasswordField
              password={password}
              handlePassword={handlePassword}
              isVisible={isShowPassword}
              handleVisible={() => setShowPassword(!isShowPassword)}
            />
          </Box>
          <Link color="inherit" onClick={handleForgotPassword}>
            Forgot Password?
          </Link>
          <Box color="black" textAlign="center" mt={5}>
            <Button
              color="primary"
              variant="contained"
              fullWidth
              disabled={!username || !password}
              onClick={handleSubmit}
            >
              Login
            </Button>
            <Box mt={2}>
              Don't have an account? <Link onClick={handleSignup}>Sign up</Link>
            </Box>
          </Box>
        </Box>
      </Box>
    </Dialog>
  );
};

export default OLoginDialog;
