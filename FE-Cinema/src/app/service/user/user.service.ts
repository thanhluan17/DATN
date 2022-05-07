import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {TokenStorageService} from '../security/token-storage.service';
import {Observable} from 'rxjs';
import {User} from '../../entity/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  httpOptions: any;
  API_URL = 'http://localhost:8080/account';

  constructor(private httpClient: HttpClient,
              private tokenStore: TokenStorageService) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      'Access-Control-Allow-Origin': 'http://localhost:4200/',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
      'Access-Control-Allow-Credentials': 'true'
    };
  }

  getUserByUserName(username: string): Observable<User> {
    console.log(this.tokenStore.getUser().user.account.username);
    return this.httpClient.get<User>(this.API_URL + '/' + username);
  }
}
