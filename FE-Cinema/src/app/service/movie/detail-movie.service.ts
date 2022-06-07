import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Movie} from '../../entity/movie';
import {Observable} from 'rxjs';
import {Category} from '../../entity/category';

@Injectable({
  providedIn: 'root'
})
export class DetailMovieService {
  API_URL_DETAIL_MOVIE = 'http://localhost:8080/api/movie';
  API_URL_MOVIE_CATEGORY = 'http://localhost:8080/api/category';
  httpOptions: any;
  constructor(private httpClient: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      'Access-Control-Allow-Origin': 'http://localhost:4200/',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
      'Access-Control-Allow-Credentials': 'true'
    };
  }

  getMovieById(id: number): Observable<Movie> {
    return this.httpClient.get<Movie>(this.API_URL_DETAIL_MOVIE + '/detail-movie/' + id);
  }

  getCategoryBiMovieId(id: number): Observable<Category[]> {
    return this.httpClient.get<Category[]>(this.API_URL_MOVIE_CATEGORY + '/' + id);
  }
}
