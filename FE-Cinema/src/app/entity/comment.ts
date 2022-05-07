import {User} from './user';
import {Movie} from './movie';

export interface Comments {
  commentId: number;
  content: string;
  user: User;
  date: string;
  movie: Movie;
  img: string;
  comment: [];
}
