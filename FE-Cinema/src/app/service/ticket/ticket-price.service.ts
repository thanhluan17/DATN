import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MovieTicket} from '../../entity/movieTicket';

@Injectable({
  providedIn: 'root'
})
export class TicketPriceService {
  API_TICKET_PRICE_CLIENT = 'http://localhost:8080/api/ticket-price';
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

  getAllMovieByDate(showDate: string): Observable<MovieTicket[]> {
    return this.httpClient.get<MovieTicket[]>(this.API_TICKET_PRICE_CLIENT + '/' + '?showDate=' + showDate);
  }
}
