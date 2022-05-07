
export interface UserPreview {
  userId: number;
  name: string;
  email: string;
  avatarUrl: string;
  accountStatus: AccountStatusPreview;
  address: AddressPreview;
}

export interface AccountStatusPreview {
  accountStatusId: number;
  accountStatusName: string;
}

export interface AddressPreview {
   wardName: string;
   districtName: string;
   provinceName: string;
}
