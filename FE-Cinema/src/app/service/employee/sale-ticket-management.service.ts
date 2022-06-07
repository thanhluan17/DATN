import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Seat} from '../../entity/seat';
import {MovieTicket} from '../../entity/movieTicket';
import {User} from '../../entity/user';
import {HttpClient} from '@angular/common/http';
import {MemberTicketDTO} from '../../entity/memberTicketDTO';

@Injectable({
  providedIn: 'root'
})
export class SaleTicketManagementService {

  public listSeatSource = new BehaviorSubject<Seat[]>([]);
  public listSeatCurrent = this.listSeatSource.asObservable();
  public movieTicket = new BehaviorSubject<MovieTicket>(null);
  public currentMovieTicket = this.movieTicket.asObservable();
  public API_MOVIE_TICKET = 'http://localhost:8080/api/employee/saleTicket';
  public API_ROOM_SEAT = 'http://localhost:8080/api/roomSeat/saleTicket';
  public API_TICKET = 'http://localhost:8080/api/ticket/saleTicket';
  public user = new BehaviorSubject<User>(null);
  public currentUser = this.user.asObservable();

  constructor(private http: HttpClient) {
  }

  changeListChoseSeat(listSeat: Seat[]) {
    this.listSeatSource.next(listSeat);
  }

  changeMovieTicket(movieTicket: MovieTicket) {
    this.movieTicket.next(movieTicket);
  }

  changeUser(user: User) {
    this.user.next(user);
  }

  public showAllMovieTicket(): Observable<any> {
    return this.http.get(this.API_MOVIE_TICKET + '/listMovieTicket');
  }

  public showAllMovieTicketByMovieId(movieId: number): Observable<any> {
    return this.http.get(this.API_MOVIE_TICKET + '/listMovieTicket/movie/' + movieId);
  }

  public showAllMovieTicketByShowDate(showDate: string): Observable<any> {
    return this.http.get(this.API_MOVIE_TICKET + '/listMovieTicket/showDate/' + showDate);
  }

  public showAllMovieTicketByIdAndShowDate(movieId: number, showDate: string): Observable<any> {
    return this.http.get(this.API_MOVIE_TICKET + '/listMovieTicket/' + movieId + '/' + showDate);
  }

  public findMovieTicketBySelect(movieId: number, showDate: string, showTime: number): Observable<any> {
    return this.http.get(this.API_MOVIE_TICKET + '/movieTicket/' + movieId + '/' + showDate + '/' + showTime);
  }

  public findMovieTicketById(movieTicketId: number): Observable<any> {
    return this.http.get(this.API_MOVIE_TICKET + '/movieTicket/' + movieTicketId);
  }

  public showAllSeatByRoomId(roomId: number): Observable<any> {
    return this.http.get(this.API_ROOM_SEAT + '/listRoomSeat/' + roomId);
  }

  public createTicket(roomId: number, listTicketDTO: MemberTicketDTO[]): Observable<any> {
    return this.http.post(this.API_TICKET + '/createTicket/' + roomId, listTicketDTO);
  }

  public findUserByUserName(username: string): Observable<any> {
    return this.http.get(this.API_MOVIE_TICKET + '/user/' + username);
  }

  public createUserNoAccount(user: User): Observable<any> {
    return this.http.post(this.API_MOVIE_TICKET + '/user/create', user);
  }
}

