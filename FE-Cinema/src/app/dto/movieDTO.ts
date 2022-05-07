import {Movie} from '../entity/movie';

export interface MovieDTO {
  movie: Movie;
  categoryId: number;
}
