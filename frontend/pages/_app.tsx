import type { AppProps } from 'next/app';
import { ThemeProvider } from '@material-ui/core/styles';

import theme from 'config/theme';
import 'styles/scss/style.scss';

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <ThemeProvider theme={theme}>
      <Component {...pageProps} />
    </ThemeProvider>
  );
}
export default MyApp;
