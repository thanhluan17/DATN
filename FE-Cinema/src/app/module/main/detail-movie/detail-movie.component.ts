import {Component, OnInit} from '@angular/core';
import {Movie} from '../../../entity/movie';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Category} from '../../../entity/category';
import {Comments} from '../../../entity/comment';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {DetailMovieService} from '../../../service/movie/detail-movie.service';
import {ToastrService} from 'ngx-toastr';
import {AngularFirestore} from '@angular/fire/firestore';
import {AngularFireStorage} from '@angular/fire/storage';
import {DomSanitizer} from '@angular/platform-browser';
import {MatDialog} from '@angular/material/dialog';
import {TokenStorageService} from '../../../service/security/token-storage.service';

@Component({
  selector: 'app-detail-movie',
  templateUrl: './detail-movie.component.html',
  styleUrls: ['./detail-movie.component.scss']
})
export class DetailMovieComponent implements OnInit {

  movie: Movie;
  formComment: FormGroup;
  categories: Category[];
  comments: Comments[];
  public date = new Date();
  user: Observable<any>;
  movie1: Observable<any>;
  idCommentEdit = 0;
  contentCommentEdit: any;
  loading = false;
  id: number;
  email = '';
  size = 5;
  page: [];
  idUser: number;
  token: string;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private detailMovieService: DetailMovieService,
              private toastr: ToastrService,
              private storage: AngularFireStorage,
              private afs: AngularFirestore,
              private sanitizer: DomSanitizer,
              private dialog: MatDialog,
              private formBuilder: FormBuilder,
              private tokenStore: TokenStorageService
  ) {
    this.token = this.tokenStore.getUser().user;

  }

  videoUrl() {
    return this.sanitizer.bypassSecurityTrustResourceUrl(this.movie.trailer);
  }

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params.id;
    this.detailMovieService.getMovieById(this.id).subscribe(data => {
      this.movie = data;
      console.log(data);
      console.log(this.id);
      this.detailMovieService.getCategoryBiMovieId(this.id).subscribe(dataCategory => {
        this.categories = dataCategory;
        console.log(dataCategory);
      });
    }, error => {
      this.toastr.error('Không tìm thấy phim', 'Thông báo');
    });
    this.formComment = this.formBuilder.group({
      content: ['']
    });
  }
}

