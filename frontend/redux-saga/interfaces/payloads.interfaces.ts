import { OtherUserData } from './data.interfaces';

export interface LoginPayload {
  username: string;
  password: string;
}

export interface GetUserPayload {
  username: string;
}

export interface GetOtherUserPayload {
  username: string;
  otherUsersData: OtherUserData[];
}
