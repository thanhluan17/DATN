export class AccountDTO {
  username: string;
  oldPassword: string;
  newPassword: string;
  otp: string;

  constructor(username: string, oldPassword: string, newPassword: string, otp: string) {
    this.username = username;
    this.oldPassword = oldPassword;
    this.newPassword = newPassword;
    this.otp = otp;
  }
}
