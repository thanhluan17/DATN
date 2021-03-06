import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {TokenStorageService} from '../../../../service/security/token-storage.service';
import {User} from '../../../../entity/user';
import { AccountManagementService } from 'src/app/service/member/account-management.service';
import {AccountDTO} from '../../../../entity/accountDTO';
import {Account} from '../../../../entity/account';

@Component({
  selector: 'app-account-info',
  templateUrl: './account-info.component.html',
  styleUrls: ['./account-info.component.scss']
})
export class AccountInfoComponent implements OnInit {
  rfEditForm: FormGroup;
  rfPasswordForm: FormGroup;
  users: User;
  username: string;
  accounts: Account;

  oldPass = '';
  notification: string;
  checkEmailOTP: string;
  inputEmailOTP: string;

  constructor(private accountManagementService: AccountManagementService,
              private formBuilder: FormBuilder,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private toastr: ToastrService,
              private tokenStore: TokenStorageService
  ) {
    this.username = this.tokenStore.getUser().user.account.username;
  }

  ngOnInit(): void {
    this.initForm();
    this.getUsername();
    this.initFormPassword();
    this.getPasswordOld();
  }


  private initForm() {
    this.rfEditForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      birthday: ['', [Validators.required]],
      gender: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(30)]],
      idCard: ['', [Validators.required, Validators.pattern('^[0-9]{9,12}$')]],
      phone: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
    });
  }

  getUsername() {
    this.accountManagementService.getUserByUserName(this.username).subscribe(data => {
      this.rfEditForm.patchValue(data);
      console.log(data);
    }, error => {
      console.log('Get ' + error + ' on getInfoUser()');
    });
  }

  onSubmit() {
    console.log(this.rfEditForm.value);
    this.users = this.rfEditForm.value;
    this.accountManagementService.editUser(this.rfEditForm.value, this.username).subscribe(data => {
      this.users = data;
      this.router.navigateByUrl('/member/info');
      this.toastr.success('Ch???nh S???a Th??nh C??ng ! !', 'T??i Kho???n ! ', {
        timeOut: 2000,
        progressBar: true,
        progressAnimation: 'increasing'
      });
    });
  }

  private initFormPassword() {
    this.rfPasswordForm = this.formBuilder.group({
      oldPassword: ['', [Validators.required, Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/)]],
      newPassword: ['', [Validators.required, Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/)]],
      confirmNewPassword: ['', [Validators.required, this.comparePassword]]
    }, {validators: this.comparePassword});
  }

  comparePassword(c: AbstractControl) {
    const value = c.value;
    return (value.newPassword === value.confirmNewPassword) ? null : {
      passwordNotMatch: true
    };
  }


  getPasswordOld() {
    if (this.oldPass == null || this.oldPass === '' || this.oldPass === undefined) {
      this.notification = 'Vui l??ng nh???p m???t kh???u';
    } else {
      this.accountManagementService.getPasswordOld(this.username).subscribe(data => {
        if (data) {
          this.rfPasswordForm.patchValue(data);
          this.accounts = data;
          this.notification = '';
          console.log(data);
        } else {
          this.notification = 'M???t kh???u kh??ng ????ng, b???n c?? ch???c ????y l?? m???t kh???u hi???n t???i c???a b???n kh??ng';
        }
      }, error => {
        console.log('Get ' + error + ' on getInfoUser()');
      });
    }
  }

  onSubmitPass() {
    console.log(this.inputEmailOTP);
    console.log(this.checkEmailOTP);
    if (this.rfPasswordForm.valid) {
      // tslint:disable-next-line:max-line-length
      this.accountManagementService.setNewPassword(new AccountDTO(this.username, this.rfPasswordForm.value.oldPassword, this.rfPasswordForm.value.newPassword, this.inputEmailOTP.toString())).subscribe(data => {
        this.toastr.success('?????i M???t Kh???u Th??nh C??ng', 'T??i Kho???n');
        this.router.navigateByUrl('login');
      }, error => {
        this.toastr.error('???? g???p s??? c???, ch??a th??? c???p nh???t l???i m???t kh???u cho b???n!', 'T??i Kho???n');
      });
    } else {
      this.toastr.warning('H??y nh???p ?????y ????? th??ng tin ?????i m???t kh???u!', 'Th??ng B??o', {timeOut: 2000});
    }
  }


  sendEmailOTP() {
    this.accountManagementService.sendEmailOTP(this.rfEditForm.value.email).subscribe(data => {
      console.log(data);
      this.checkEmailOTP = data;

    });
  }

  changeInputEmailOTP(inputOTP: string) {
    this.inputEmailOTP = inputOTP;
  }
}
