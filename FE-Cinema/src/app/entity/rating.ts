import {User} from './user';
import {Movie} from './movie';

export interface Rating {
  rating: number;
  user: User;
  movie: Movie;
}
