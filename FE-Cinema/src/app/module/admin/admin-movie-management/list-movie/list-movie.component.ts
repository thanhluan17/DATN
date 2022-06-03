import {Component, OnInit} from '@angular/core';
import {Movie} from '../../../../entity/movie';
import {MovieManagementService} from '../../../../service/admin/movie-management.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-list-movie',
  templateUrl: './list-movie.component.html',
  styleUrls: ['./list-movie.component.scss']
})
export class ListMovieComponent implements OnInit {

  public movieList: Movie[];
  totalPages: number;
  page = 0;
  selectedPage: number;
  idMovie: number;
  id: number;

  constructor(
    public movieService: MovieManagementService,
    public router: Router,
    public toast: ToastrService) {
  }

  ngOnInit(): void {

    this.movieService.getAllMovie(this.page).subscribe(movie => {
      this.movieList = movie.content;
      this.totalPages = movie.totalPages;
    });
  }

  getMovieId(id: number) {
    this.idMovie = id;
  }

  deleteMovie() {
    this.movieService.deleteMovie(this.idMovie).subscribe(data => {
    });
    this.idMovie = null;
    window.location.reload();
    this.toast.success('Xoá Thành Công !', 'Thông Báo');
    this.ngOnInit();
  }

  firstPage() {
    this.page = 0;
    this.ngOnInit();
  }

  previousPage() {
    this.page -= 1;
    this.ngOnInit();
  }

  nextPage() {
    this.page += 1;
    this.ngOnInit();
  }

  lastPage() {
    this.page = this.totalPages - 1;
    this.ngOnInit();
  }

  selectPage(page: number) {
    if (page < this.totalPages) {
      this.page = page;
      this.ngOnInit();
    } else {
      this.toast.warning('Trang hiện tại không có dữ liệu', 'Thông báo', {timeOut: 2000});
    }
  }

  sendMovieId(movieId: number) {
    console.log(this.router.navigate(['/admin/movie/edit-movie/' + movieId]));
  }
}
