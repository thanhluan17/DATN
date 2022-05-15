import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TransactionHistory} from '../../entity/transactionHistory';

@Injectable({
  providedIn: 'root'
})
export class TransactionHistoryService {


  API_URL_TRANSACTION = 'http://localhost:8080/member';
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


  findAll(username: string): Observable<TransactionHistory[]> {
    console.log(username);
    console.log(this.API_URL_TRANSACTION + '/transaction/' + username);
    return this.httpClient.get<TransactionHistory[]>(this.API_URL_TRANSACTION + '/transaction/' + username);
  }
}
