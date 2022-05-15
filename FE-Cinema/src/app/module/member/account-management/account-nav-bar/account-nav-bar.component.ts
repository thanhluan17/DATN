import {Component, OnInit} from '@angular/core';
import {AccountManagementService} from '../../../../service/member/account-management.service';
import {TokenStorageService} from '../../../../service/security/token-storage.service';
import {User} from '../../../../entity/user';
import {Account} from '../../../../entity/account';

@Component({
  selector: 'app-account-nav-bar',
  templateUrl: './account-nav-bar.component.html',
  styleUrls: ['./account-nav-bar.component.scss']
})
export class AccountNavBarComponent implements OnInit {

  username: string;
  accounts: Account;
  user: User;

  constructor(private accountManagementService: AccountManagementService,
              private tokenStore: TokenStorageService
  ) {
    this.username = this.tokenStore.getUser().user.account.username;
  }


  ngOnInit(): void {
    this.getUsername();
    this.getPasswordOld();
  }

  getPasswordOld() {
    this.accountManagementService.getPasswordOld(this.username).subscribe(data => {
      this.accounts = data;
    }, error => {
      // console.log("Get " + error + " on getInfoUser()");
    });
  }

  getUsername() {
    this.accountManagementService.getUserByUserName(this.username).subscribe(data => {
      // this.rfEditForm.patchValue(data)
      this.user = data;
    }, error => {
      console.log('Get ' + error + ' on getInfoUser()');
    });
  }
}

