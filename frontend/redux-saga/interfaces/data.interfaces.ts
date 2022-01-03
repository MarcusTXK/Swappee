export interface LoginData {
  token: string;
}

export interface ItemData {
  id: number;
  imagePath: number;
  name: string;
  status: string;
  brand: string;
  description: string;
  likes: number;
  liked: boolean;
  userId: number;
  userName: string;
  avatarPath?: number;
  createdDate: string;
  lastModifiedDate: string;
  new: true;
}

export interface GetItemListData {
  message: string;
  isSuccess: boolean;
  data: ItemData[];
}

export interface OtherUserData {
  id: string;
  username: string;
  emailOnly: boolean;
  score: number;
}

export interface GetOtherUsersData {
  message: string;
  isSuccess: boolean;
  data: OtherUserData;
}

export interface UserData {
  id: string;
  firstName: string;
  lastName: string;
  username: string;
  aboutMe?: string;
  email: string;
  mobileNumber: string;
}

export interface GetUserData {
  message: string;
  isSuccess: boolean;
  data: UserData;
}

export interface Error {
  message?: string;
  isSuccess?: boolean;
  data?: unknown;
}

export interface AppState {
  error: null | Error;
  user: LoginData;
  items: ItemData[];
  isLoginLoading: boolean;
  otherUsersData: OtherUserData;
  userData: UserData;
}
