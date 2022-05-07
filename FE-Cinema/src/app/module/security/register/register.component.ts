import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {User} from '../../../entity/user';
import {Province} from '../../../entity/province';
import {Ward} from '../../../entity/ward';
import {District} from '../../../entity/district';
import { SecurityService } from 'src/app/service/security/security.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  textType = false;
  form: FormGroup;
  defaultAvatar: string = 'https://firebasestorage.googleapis.com/v0/b/c1120g1.appspot.com' +
    '/o/SPRINT_02%2Fusers%2Favatar.png?alt=media&token=b54ffd5e-9a20-4110-a2e2-de839d8915a7';
  isMatchPassword = true;
  user: User[];
  wards: Ward[];
  provinces: Province[];
  districts: District[];
  listError: any = '';

  constructor(
    private formBuilder: FormBuilder,
    private securityService: SecurityService,
    private router: Router,
    private toastr: ToastrService,
  ) {
  }

  ngOnInit(): void {
    this.securityService.getAllProvince().subscribe(data => {
      this.provinces = data;
    });
    this.form = this.formBuilder.group({
      username: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9]+$'),
        Validators.minLength(6), Validators.maxLength(45)]],
      password: ['', [Validators.required,
        Validators.minLength(8), Validators.maxLength(45),
        Validators.pattern('^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$')]],
      confirmPassword: ['', [Validators.required]],
      email: ['', [Validators.required,
        Validators.pattern('^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$'),
        Validators.maxLength(253)
      ]],
      name: ['', [Validators.required,
        Validators.pattern(/^(\s*)([\p{Lu}]|[\p{Ll}]){2,}((\s*)(([\p{Lu}]|[\p{Ll}]){2,}))+(\s*)$/u),
        Validators.maxLength(45)]],
      birthday: ['', [Validators.required]],
      gender: ['1', [Validators.required]],
      idCard: ['', [Validators.required, Validators.pattern('^[\\d]{9,12}$')]],
      phone: ['', [Validators.required, Validators.pattern('^0[\\d]{9,10}$')]],
      ward: ['', [Validators.required]],
      district: [''],
      province: [''],
      // avatarUrl: [this.defaultAvatar],
    });
  }

  submitForm() {
    if (true) {
      this.securityService.createUserConfirmMail(this.form.value).subscribe(
        data => {
          this.router.navigateByUrl('/login');
          this.toastr.success('Hãy kiểm tra email và xác nhận tài khoản', 'Đăng kí thành công', {
            timeOut: 2000,
            progressBar: true,
            progressAnimation: 'increasing'
          });
          this.sendMail();
        },
        error => {
          this.toastr.error('Hãy kiểm tra lại đăng kí', 'Đăng kí thất bại', {
            timeOut: 2000,
            progressBar: true,
            progressAnimation: 'increasing'
          });
          if (error.status === 400) {
            console.log(error.error);
            this.listError = error.error;
          }
        }
      );
    }
  }

  checkConfirmPassword(inputPassword, inputPasswordConf) {
    if (inputPassword !== inputPasswordConf) {
      return this.isMatchPassword = false;
    } else {
      return this.isMatchPassword = true;
    }
  }

  secondForm() {
    if (this.isMatchPassword) {
      document.getElementById('first-form').style.display = 'none';
      document.getElementById('second-form').style.display = 'table';
    }
  }

  firstForm() {
    document.getElementById('first-form').style.display = 'table';
    document.getElementById('second-form').style.display = 'none';
  }

  toggleShowHide() {
    this.textType = !this.textType;
  }

  compareProvince(province1: Province, province2: Province): boolean {
    return province1 && province2 ? province1.provinceId === province2.provinceId : province1 === province2;
  }

  compareDistrict(district1: District, district2: District): boolean {
    return district1 && district2 ? district1.districtId === district2.districtId : district1 === district2;
  }

  compareWard(ward1: Ward, ward2: Ward): boolean {
    return ward1 && ward2 ? ward1.wardId === ward2.wardId : ward1 === ward2;
  }

  onChangeProvince(event) {
    const userProfile = this.form.controls.province.value;
    const provinceId = userProfile.provinceId;
    if (provinceId) {
      this.securityService.getAllDistrictByProvinceId(provinceId).subscribe(data => {
        this.districts = data;
        this.wards = null;
      });
    } else {
      this.districts = null;
      this.wards = null;
    }
  }

  onChangeDistrict(event) {
    const userInfo = this.form.controls.district.value;
    const districtId = userInfo.districtId;
    if (districtId) {
      this.securityService.getAllWardByDistrictId(districtId).subscribe(data => {
        this.wards = data;
      });
    } else {
      this.wards = null;
    }
  }

  sendMail() {
    if (this.form.valid) {
      this.securityService.sendEmailConfirm(this.form.value.email).subscribe(data => {
      });
    }
  }
}
