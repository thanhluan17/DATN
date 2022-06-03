import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Category} from '../../../../entity/category';
import {MovieDTO} from '../../../../dto/movieDTO';
import {MovieStatus} from '../../../../entity/movieStatus';
import {MovieManagementService} from '../../../../service/admin/movie-management.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {AngularFireStorage} from '@angular/fire/storage';
import {finalize} from 'rxjs/operators';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.scss']
})
export class AddMovieComponent implements OnInit {

  public imagePoster = null;
  public selectedImage: any = null;
  public endDateCompare = '';
  public startDateCompare = '';
  public formAddMovie: FormGroup;
  public listCategory: Category[];
  public categoryList: number[] = [];
  public listMovieDTO: MovieDTO[] = [];
  public statusOn: MovieStatus = {
    movieStatusId: 1,
    movieStatusName: 'Đang chiếu'
  };
  private messageImageError: string;

  constructor(public formBuilder: FormBuilder,
              public movieService: MovieManagementService,
              public router: Router,
              private activateRouter: ActivatedRoute,
              public toast: ToastrService,
              private storage: AngularFireStorage) {
  }

  ngOnInit(): void {
    this.formAddMovie = new FormGroup({
      movieId: new FormControl(null),
      movieName: new FormControl('', [Validators.required]),
      movieStatus: new FormControl(this.statusOn),
      posterMovie: new FormControl('', [Validators.required]),
      startDate: new FormControl('', [Validators.required]),
      endDate: new FormControl('', [Validators.required]),
      movieStudio: new FormControl('', [Validators.required]),
      actor: new FormControl('', [Validators.required]),
      director: new FormControl('', [Validators.required]),
      movieLength: new FormControl('', [Validators.required]),
      trailer: new FormControl('', [Validators.required])
    });


    this.movieService.getCategory().subscribe(category => {
      console.log(category);
      this.listCategory = category;
    });


  }

  submit(formAddMovie) {
    this.endDateCompare = this.formAddMovie.value.endDate;
    this.startDateCompare = this.formAddMovie.value.startDate;
    if (this.endDateCompare < this.startDateCompare) {
      this.toast.error('Ngày bắt đầu phải nhỏ hơn ngày kết thúc');
      // tslint:disable-next-line:triple-equals
    } else if (this.startDateCompare == this.endDateCompare) {
      this.toast.error('Ngày bắt đầu và  ngày kết thúc không được trùng nhau');
    } else {
      const name = this.selectedImage.name;
      const stringImage = name.substring(name.length - 3).toLowerCase();
      if (stringImage === 'png' || stringImage === 'jpg') {
        const fileName = 'imageMovie/' + name;
        const fileRef = this.storage.ref(fileName);
        this.storage.upload(fileName, this.selectedImage).snapshotChanges().pipe(
          finalize(() => {
            fileRef.getDownloadURL().subscribe((url) => {
                this.formAddMovie.value.posterMovie = url;
                if (this.categoryList.length > 0) {
                  // tslint:disable-next-line:prefer-for-of
                  for (let i = 0; i < this.categoryList.length; i++) {
                    const movieDTO: MovieDTO = {
                      movie: this.formAddMovie.value,
                      categoryId: this.categoryList[i]
                    };
                    this.listMovieDTO.push(movieDTO);
                  }
                  console.log(this.listMovieDTO);
                  this.movieService.formAddMovie(this.listMovieDTO).subscribe(data => {
                    this.toast.success('Thêm mới thành công!');
                    this.router.navigate(['/admin/movie/movie-list']);
                  });
                }
              }
            );
          })).subscribe();
      }
    }
  }

  showImage(image) {
    if (image.target.files && image.target.files[0]) {
      const file = image.target.files[0].name;
      const path = file.substring(file.length - 3).toLowerCase();
      if (path === 'png' || path === 'jpg') {
        const reader = new FileReader();
        reader.onload = (e: any) => this.imagePoster = e.target.result;
        reader.readAsDataURL(image.target.files[0]);
        this.selectedImage = image.target.files[0];
        this.messageImageError = '';
      } else {
        this.imagePoster = null;
        this.messageImageError = '*Tệp ảnh bạn chọn không hợp lệ!';
        this.selectedImage = null;
      }
    } else {
      this.selectedImage = null;
      this.messageImageError = '*Không được bỏ trống ảnh';
    }
  }

  selectCategory(categoryId: number) {
    if (this.categoryList.includes(categoryId)) {
      this.categoryList.splice(this.categoryList.indexOf(categoryId), 1);
    } else {
      this.categoryList.push(categoryId);
    }
  }

  removeImage() {
    this.imagePoster = null;
    this.selectedImage = null;
  }
}

