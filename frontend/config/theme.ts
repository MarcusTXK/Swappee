import { green } from '@material-ui/core/colors';
import { createTheme } from '@material-ui/core/styles';

// Create a theme instance
const theme = createTheme({
  palette: {
    primary: green,
    secondary: green,
  },
  typography: {
    button: {
      textTransform: 'none',
    },
  },
  overrides: {
    // Style sheet name ⚛️
    MuiButton: {
      // Name of the rule
      containedPrimary: {
        // Some CSS
        color: 'white',
        boxShadow: 'none',
        height: '40px',
      },
      outlinedPrimary: {
        height: '40px',
      },
    },
  },
});

export default theme;
