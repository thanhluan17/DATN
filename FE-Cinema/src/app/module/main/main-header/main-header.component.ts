import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../../../service/security/token-storage.service';
import {SecurityService} from '../../../service/security/security.service';
import {Router} from '@angular/router';
import {User} from '../../../entity/user';
import {UserService} from '../../../service/user/user.service';

@Component({
  selector: 'app-main-header',
  templateUrl: './main-header.component.html',
  styleUrls: ['./main-header.component.scss']
})
export class MainHeaderComponent implements OnInit {

  username = '';
  role = '';
  user: User;
  avatarUrl = 'https://firebasestorage.googleapis.com/v0/b/c1120g1.appspot.com/o/login%2Fuser.jpg?alt=media&token=d3149a38-f6f3-42d2-b8bf-b79d78049b89';

  constructor(private router: Router,
              private tokenStore: TokenStorageService,
              private userService: UserService,
              private securityService: SecurityService) {
  }

  ngOnInit(): void {
    if (this.tokenStore.getToken()) {
      const user = this.tokenStore.getUser();
      console.log(user);
      this.securityService.isLoggedIn = true;
      this.role = user.authorities[0].authority;
      this.username = user.username;
    }
    if (this.tokenStore.getToken()) {
      this.user = this.tokenStore.getUser().user;
      this.role = this.tokenStore.getUser().authorities[0].authority;

    }
  }

  search(keySearch: string) {
    console.log(keySearch);
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate(['/search'], {queryParams: {q: keySearch}});
    });
  }

  logout() {
    this.tokenStore.signOut();
    this.router.navigateByUrl('/login');
  }

  showProfile() {
    document.getElementById('profile').style.display = 'block';
  }

  hideProfile() {
    document.getElementById('profile').style.display = 'none';
  }

}
