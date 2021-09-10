import { FC, useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Box, Dialog, IconButton, TextField, Button, Link, CircularProgress } from '@material-ui/core';
import { Close } from '@material-ui/icons';

import { AppState } from 'redux-saga/interfaces';
import MPasswordField from 'components/molecules/MPasswordField';
import { COLOURS } from 'config/constants';
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
  const [isSubmitted, setSubmitted] = useState(false);
  const {
    user: { token },
    isLoginLoading,
  } = useSelector((state: AppState) => state);
  const dispatch = useDispatch();
  const isLoginFailed = isSubmitted && !token && !isLoginLoading;

  const handleUsername = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(event.target.value);
    setSubmitted(false);
  };

  const handlePassword = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
    setSubmitted(false);
  };

  const handleSubmit = () => {
    dispatch(login({ username, password }));
    setSubmitted(true);
  };

  useEffect(() => {
    if (token) {
      onClose();
    }
  }, [token]);

  return (
    <Dialog open={isOpen} fullWidth>
      <Box display="flex" justifyContent="flex-end" mt={1} mr={1}>
        <IconButton aria-label="close" onClick={() => onClose()} color="inherit">
          <Close />
        </IconButton>
      </Box>
      <Box alignItems="center">
        <Box margin={{ xs: '10px 10px 25px', md: '20px 40px 50px' }} color={COLOURS.LIGHT_GREY}>
          <TextField
            label="Username"
            fullWidth
            variant="outlined"
            size="small"
            value={username}
            onChange={handleUsername}
            error={isLoginFailed}
          />
          <Box mt={2} mb={1}>
            <MPasswordField
              password={password}
              handlePassword={handlePassword}
              isVisible={isShowPassword}
              handleVisible={() => setShowPassword(!isShowPassword)}
              error={isLoginFailed}
              helperText={isLoginFailed ? 'Invalid email or password' : ''}
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
              {isLoginLoading ? <CircularProgress /> : 'Login'}
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
