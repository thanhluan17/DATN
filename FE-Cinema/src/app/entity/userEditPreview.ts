import {Ward} from './ward';

export interface UserEditPreview {
  userId: number;
  username: string;
  name: string;
  email: string;
  avatarUrl: string;
  birthday: string;
  gender: number;
  phone: string;
  password: string;
  ward: Ward;
}



