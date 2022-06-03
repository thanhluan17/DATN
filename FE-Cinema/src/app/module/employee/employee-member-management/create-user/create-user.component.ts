import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {User} from '../../../../entity/user';
import {Ward} from '../../../../entity/ward';
import {Province} from '../../../../entity/province';
import {District} from '../../../../entity/district';
import {MemberManagementService} from '../../../../service/employee/member-management.service';
import {Router} from '@angular/router';
import {AngularFireStorage} from '@angular/fire/storage';
import {ToastrService} from 'ngx-toastr';
import {finalize} from 'rxjs/operators';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent implements OnInit {

  formAddNewCustomer: FormGroup;
  userSendMail: User;
  selectedImg: any = null;
  isMessage: any;
  listError: any = '';
  isMessage2: any;
  isMessage3: any;
  isMessage1: any;
  imgSrc = '../assets/img/avatar.jpeg';
  public user: User[];
  public wards: Ward[];
  public provinces: Province[];
  public districts: District[];
  id = 0;
  loading = false;
  messageImageError = '';
  checkAccept = false;
  checkLoad = false;


  constructor(private memberManagementService: MemberManagementService,
              private router: Router,
              private storage: AngularFireStorage,
              private formBuilder: FormBuilder,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.memberManagementService.getAllProvince().subscribe(data => {
      this.provinces = data;
    });
    this.formAddNewCustomer = this.formBuilder.group({
      name: ['', [Validators.required, Validators.pattern(/^(\s*)([\p{Lu}]|[\p{Ll}]){2,}((\s*)(([\p{Lu}]|[\p{Ll}]){2,}))+(\s*)$/u),
        Validators.minLength(6), Validators.maxLength(45)]],
      email: ['', [Validators.required, Validators.pattern(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)]],
      phone: ['', [Validators.required, Validators.pattern('^0\\d{9,10}$')]],
      ward: ['', [Validators.required]],
      birthday: ['', [Validators.required]],
      gender: ['', [Validators.required]],
      idCard: ['', [Validators.required, Validators.pattern('^\\d{9}$'), Validators.maxLength(9)]],
      province: ['', [Validators.required]],
      district: ['', [Validators.required]],
      username: ['', [Validators.required, Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/)]],
      password: ['', [Validators.required, Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/)]],
      confirmPassword: ['', [Validators.required]],
      avatarUrl: [''],
      checkAccept: [false]
    });
  }

  // create new user
  addNewCustomer() {
    this.isMessage = false;
    this.loading = true;
    this.isMessage1 = false;
    this.isMessage2 = false;
    this.isMessage3 = false;
    if (this.formAddNewCustomer.valid) {
      if (this.formAddNewCustomer.value.password === this.formAddNewCustomer.value.confirmPassword) {
        const filePath = `user/${this.selectedImg.name.split('.').slice(0, -1).join('.')}_${new Date().getTime()}`;
        const fileRef = this.storage.ref(filePath);
        this.storage.upload(filePath, this.selectedImg).snapshotChanges().pipe(
          finalize(() => {
            fileRef.getDownloadURL().subscribe((url) => {
              this.checkLoad = true;
              this.formAddNewCustomer.value.avatarUrl = url;
              this.memberManagementService.createUser(this.formAddNewCustomer.value).subscribe(data => {
                this.sendMail();
                this.checkLoad = false;
                this.router.navigateByUrl('/employee/member/management');
                this.toastr.success('Them moi thanh cong', 'Notification', {
                  timeOut: 1000,
                  progressBar: true,
                  progressAnimation: 'increasing'
                });
              }, error => {
                if (error.status === 400) {
                  this.listError = error.error;
                } else if (error.status === 404) {
                  this.isMessage = true;
                }
              }, () => {
                this.loading = false;
              });
            });
          })).subscribe();
      } else {
        this.isMessage = true;
      }
    } else {
      this.memberManagementService.createUser(this.formAddNewCustomer.value).subscribe(data => {
        this.router.navigateByUrl('/employee/member/management');
        this.toastr.success('Them moi thanh cong', 'Notification', {
          timeOut: 1000,
          progressBar: true,
          progressAnimation: 'increasing'
        });
      }, error => {
        if (error.status === 400) {
          console.log(error.error);
          this.listError = error.error;
        } else if (error.status === 404) {
          this.isMessage = true;
        }
      }, () => {
        this.loading = false;
      });
    }
  }

  //    upload anh fire base

  showPreview(image: any) {

    if (image.target.files && image.target.files[0]) {
      const file = image.target.files[0].name;
      const path = file.substring(file.length - 3).toLowerCase();
      const path1 = file.substring(file.length - 4).toLowerCase();
      if (path === 'png' || path === 'jpg' || path1 === 'jpeg') {
        const reader = new FileReader();
        reader.onload = (e: any) => this.imgSrc = e.target.result;
        reader.readAsDataURL(image.target.files[0]);
        this.selectedImg = image.target.files[0];
        this.messageImageError = '';
      } else {
        this.imgSrc = null;
        this.messageImageError = '*Tệp ảnh bạn chọn không hợp lệ!';
        this.selectedImg = null;
      }
    } else {
      this.imgSrc = '../assets/img/avatar.jpeg';
      this.selectedImg = null;
    }
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

  onchangeProvince(provinceId) {
    if (provinceId) {
      this.memberManagementService.getAllDistrictByProvinceId(provinceId).subscribe(data => {
        this.districts = data;
        this.wards = null;
      });
    } else {
      this.districts = null;
      this.wards = null;
    }
  }

  onchangeDistrict(districtId) {
    if (districtId) {
      this.memberManagementService.getAllWardByDistrictId(districtId).subscribe(data => {
        this.wards = data;
      });
    } else {
      this.wards = null;
    }
  }

  removeImage() {
    this.imgSrc = null;
    this.selectedImg = null;
  }

  changeAccept() {
    this.checkAccept = true;
  }

  sendMail() {
    if (this.formAddNewCustomer.valid) {
      console.log('ádasd');
      this.memberManagementService.sendEmailApprove(this.formAddNewCustomer.value.email).subscribe(data => {
      });
    }
  }
}
