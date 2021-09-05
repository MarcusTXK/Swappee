let apiUrl = '';

switch (process.env.NEXT_PUBLIC_APP_ENV) {
  case 'local':
    apiUrl = `${process.env.NEXT_PUBLIC_LOCAL_API}`;
    break;
  default:
    apiUrl = 'http://localhost:8022/swappee';
}

export default {
  API_URL: apiUrl,
};
