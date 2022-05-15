import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../entity/user';
import {AccountDTO} from '../../entity/accountDTO';
import {Account} from '../../entity/account';

@Injectable({
  providedIn: 'root'
})
export class AccountManagementService {

  API_URL_USER = 'http://localhost:8080/member';
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

  getUserById(username: string): Observable<User> {
    return this.httpClient.get<User>(this.API_URL_USER + '/infoUser/' + username);
  }

  editUser(user: User, username: string): Observable<User> {
    console.log(username);
    console.log(user);
    return this.httpClient.put<User>(this.API_URL_USER + '/editUser/' + username, user);
  }
  getUserByUserName(username: string): Observable<User> {
    return this.httpClient.get<User>(this.API_URL_USER + '/user/'+ username);
  };

  getPasswordOld(username: string): Observable<Account> {
    return this.httpClient.get<Account>(this.API_URL_USER + '/account/'+ username);
  }
  setNewPassword(accountDTO: AccountDTO): Observable<any> {
    return this.httpClient.post<any>(this.API_URL_USER + '/setPass', accountDTO, this.httpOptions);
  }

  sendEmailOTP(email: string): Observable<any> {
    return this.httpClient.get<any>(this.API_URL_USER + '/sendEmailOTP?email=' +email, this.httpOptions);
  }

}
