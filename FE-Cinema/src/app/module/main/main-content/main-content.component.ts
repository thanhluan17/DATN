import {Component, OnInit} from '@angular/core';
import {Movie} from '../../../entity/movie';
import {MovieService} from '../../../service/main/movie.service';

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss']
})
export class MainContentComponent implements OnInit {

  page = 0;
  size = 8;

  onShowingMovies: Movie[] = [];
  upComingMovies: Movie[] = [];
  top3Movies: Movie[] = [];
  promotingMovies: Movie[] = [];

  displayLoadMore = true;
  displayTrailer = false;
  trailer = '';

  constructor(private movieService: MovieService) {
  }

  ngOnInit(): void {
    (document.getElementById('header-search-input') as HTMLInputElement).value = '';
    this.getPromotingMovies();
    this.getTop3BySales();
    this.getOnShowingMovies();
  }

  getPromotingMovies() {
    this.movieService.getPromotingMovies().subscribe(data => {
      this.promotingMovies = data;
      for (let i = 0; i < this.promotingMovies.length; i++) {
        if (!this.promotingMovies[i].banner) {
          this.promotingMovies.splice(i, 1);
          i--;
        }
      }
    });
  }

  getTop3BySales() {
    this.movieService.getTop3BySales().subscribe(data => {
      this.top3Movies = data;
      console.log(this.top3Movies);
      this.getCategoriesString(this.top3Movies);
    });
  }

  getOnShowingMovies() {
    this.reset();
    this.movieService.getOnShowingMovies(this.page, this.size).subscribe(data => {
      this.onShowingMovies = data.content;
      this.getCategoriesString(this.onShowingMovies);
      if (this.page >= data.totalPages - 1) {
        this.displayLoadMore = false;
      }
    });
  }

  getUpComingMovies() {
    this.reset();
    this.movieService.getUpComingMovies(this.page, this.size).subscribe(data => {
      this.upComingMovies = data.content;
      this.getCategoriesString(this.upComingMovies);
      if (this.page >= data.totalPages - 1) {
        this.displayLoadMore = false;
      }
    });
  }

  loadMoreOnShowing() {
    this.page++;
    this.movieService.getOnShowingMovies(this.page, this.size).subscribe(data => {
      this.onShowingMovies = this.onShowingMovies.concat(data.content);
      this.getCategoriesString(this.onShowingMovies);
      if (this.page >= data.totalPages - 1) {
        this.displayLoadMore = false;
      }
    });
  }

  loadMoreUpComing() {
    this.page++;
    this.movieService.getUpComingMovies(this.page, this.size).subscribe(data => {
      this.upComingMovies = this.upComingMovies.concat(data.content);
      this.getCategoriesString(this.upComingMovies);
      if (this.page >= data.totalPages - 1) {
        this.displayLoadMore = false;
      }
    });
  }

  openTrailer(trailer: string) {
    this.trailer = trailer;
    this.displayTrailer = true;
  }

  closeTrailer() {
    this.displayTrailer = false;
  }

  reset() {
    this.page = 0;
    this.displayLoadMore = true;
  }

  getCategoriesString(movies: Movie[]) {
    movies.map(movie => {
      const categories: string[] = [];
      movie.movieCategorySet.map(item => {
        categories.push(item.category.categoryName);
      });
      movie.categories = categories.join(', ');
    });
  }

}
