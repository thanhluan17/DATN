import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthLogin} from '../../../entity/authLogin';
import {UserSocial} from '../../../entity/userSocial';
import {TokenStorageService} from '../../../service/security/token-storage.service';
import {SecurityService} from '../../../service/security/security.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  textType = false;
  authLogin: AuthLogin;
  role = '';
  username: string;
  auth2: any;
  user: UserSocial;
  id: any;
  wrongAccountMessage: string;
  statusAccountMessage: string;
  unconfirmedEmailStatus = 1;
  activeStatus = 2;
  deletedStatus = 3;
  lockedStatus = 4;
  @ViewChild('loginRef', {static: true}) loginElement: ElementRef;

  constructor(private formBuilder: FormBuilder,
              private securityService: SecurityService,
              private tokenStorageService: TokenStorageService,
              private router: Router,
              private toastr: ToastrService,
  ) {
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9@.]+$'),
        Validators.minLength(6), Validators.maxLength(45)]],
      password: ['', [Validators.required,
        Validators.minLength(6), Validators.maxLength(45)]],
      remember: ['']
    });

    if (this.tokenStorageService.getToken() !== undefined) {
      const user = this.tokenStorageService.getUser();
      this.securityService.isLoggedIn = true;
      this.role = user.authorities[0].authority;
      this.username = user.username;
    }
  }

  get formUsername() {
    return this.form.get('username');
  }

  get formPassword() {
    return this.form.get('password');
  }

  public login(authLogin) {
    this.securityService.login(authLogin).subscribe(
      data => {
        if (this.form.value.remember) {
          this.tokenStorageService.saveTokenLocal(data.accessToken);
          this.tokenStorageService.saveUserLocal(data);
        } else {
          this.tokenStorageService.saveTokenSession(data.accessToken);
          this.tokenStorageService.saveUserLocal(data);
        }

        switch (this.tokenStorageService.getUser().user.account.accountStatus.accountStatusId) {
          case this.unconfirmedEmailStatus: {
            this.securityService.isLoggedIn = true;
            this.username = this.tokenStorageService.getUser().username;
            // this.role = this.tokenStorageService.getUser().authorities[0].authority;
            this.form.reset();

            // this.headerComponent.ngOnInit();
            this.router.navigateByUrl('/');
            break;
          }
          case this.deletedStatus: {
            console.log('Account has been deleted: LoginComponent');
            this.wrongAccountMessage = 'Username/M???t kh???u kh??ng ????ng. Vui l??ng th??? l???i!';
            this.clearStorage();
            break;
          }
          case this.lockedStatus: {
            console.log('Account has been locked: LoginComponent');
            this.statusAccountMessage = 'T??i kho???n ???? b??? kho??!';
            this.clearStorage();
            break;
          }
        }
      },
      err => {
        console.log('Error at login function on LoginComponent');
        this.wrongAccountMessage = 'Username/M???t kh???u kh??ng ????ng. Vui l??ng th??? l???i!';
        this.securityService.isLoggedIn = false;
      }
    );
  }

  submitForm() {
    this.wrongAccountMessage = '';
    this.statusAccountMessage = '';
    this.authLogin = new AuthLogin(this.formUsername.value, this.formPassword.value);
    this.login(this.authLogin);
  }

  toggleShowHide() {
    this.textType = !this.textType;
  }

  clearStorage() {
    this.securityService.isLoggedIn = false; // ktra ????ng nh???p hay ch??a
    this.tokenStorageService.signOut();
  }

  updatingFunction() {
    this.router.navigateByUrl('/login');
    this.toastr.info('Ch???c n??ng n??y ??ang ???????c c???p nh???t', 'Xin l???i v?? s??? b???t ti???n n??y', {
      timeOut: 2000,
      progressBar: true,
      progressAnimation: 'increasing',
    });
  }

  prepareLoginButton() {
    this.auth2.attachClickHandler(this.loginElement.nativeElement, {},
      (googleUser) => {
        console.log('googleUser: ' + googleUser.getBasicProfile());
        const profile = googleUser.getBasicProfile();
        this.id = profile.getId();
        this.user = new UserSocial(googleUser.getAuthResponse().id_token,
          profile.getName(),
          profile.getImageUrl(),
          profile.getEmail()
        );

        this.securityService.createUserGoogle(this.user).subscribe(data => {
          console.log('Success : ' + data);
          this.tokenStorageService.saveTokenLocal(data.accessToken);
          this.tokenStorageService.saveUserLocal(data);

          this.securityService.isLoggedIn = true;
          this.username = this.tokenStorageService.getUser().username;
          this.role = this.tokenStorageService.getUser().authorities[0].authority;
          this.router.navigateByUrl('/home').then(r => window.location.reload()); // it need a real path, not like this "" or "/"
        }, error => {
          console.log('get ' + error.err.message + ' at prepareLoginButton()');
        });
      });
  }
}

