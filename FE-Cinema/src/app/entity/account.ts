import {AccountStatus} from './accountStatus';

import {User} from './user';

export interface Account {
  username: string;
  password: string;
  registerDay: string;
  point: string;
  accountStatus: AccountStatus;
  user: User;
}
