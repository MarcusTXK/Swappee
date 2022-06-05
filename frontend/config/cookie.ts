import UniversalCookie from 'universal-cookie';

const cookie = new UniversalCookie();

export function setCookieAccessToken(token: string) {
  cookie.set('token', token, {
    path: '/',
  });
}

export function getAccessToken() {
  return cookie.get('token');
}

export function revokeData() {
  cookie.remove('token', { path: '/' });
}
