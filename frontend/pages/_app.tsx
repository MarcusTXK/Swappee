import { useEffect } from 'react';
import type { AppProps } from 'next/app';
import { ThemeProvider } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import { PersistGate } from 'redux-persist/integration/react';
import { Store } from 'redux';
import { useStore } from 'react-redux';

import { wrapper, StoreProperties } from 'config/store';
import theme from 'config/theme';
import 'styles/scss/style.scss';

const MyApp = ({ Component, pageProps }: AppProps) => {
  const store: Store & StoreProperties = useStore();
  useEffect(() => {
    // Remove the server-side injected CSS.
    const jssStyles = document.querySelector('#jss-server-side');
    if (jssStyles) {
      jssStyles.parentElement!.removeChild(jssStyles);
    }
  }, []);
  return (
    <PersistGate loading={null} persistor={store.persistor!}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <Component {...pageProps} />
      </ThemeProvider>
    </PersistGate>
  );
};

export default wrapper.withRedux(MyApp);
