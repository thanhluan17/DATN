import {Injectable} from '@angular/core';
import {AuthLogin} from '../../entity/authLogin';
import {Observable} from 'rxjs';
import {UserSocial} from '../../entity/userSocial';
import {User} from '../../entity/user';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const API_URL = 'http://localhost:8080/api/';
const BASE_API_URL = 'http://localhost:8080/';
const API_URL_GOOGLE = 'http://localhost:8080/api/login/google';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {
  httpOptions: any;
  isLoggedIn: boolean;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      'Access-Control-Allow-Origin': 'http://localhost:4200',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
      'Access-Control-Allow-Credentials': 'true'
    };
  }

  login(authLogin: AuthLogin): Observable<any> {
    return this.http.post<any>(API_URL + 'login', authLogin, this.httpOptions);
  }

  checkEmail(email: string): Observable<string> {
    console.log('Email on service: ' + email);
    return this.http.get<string>(API_URL + 'checkEmail/' + email);
  }

  createUserGoogle(user: UserSocial): Observable<any> {
    return this.http.post<any>(API_URL_GOOGLE, user, this.httpOptions);
  }

  confirmEmail(username: string, email: string): Observable<any> {
    return this.http.put<any>(API_URL + 'register/confirmEmail/' + username + '/' + email, this.httpOptions);
  }

  createUserConfirmMail(user: User): Observable<User> {
    return this.http.post<User>(API_URL + 'createConfirm', user);
  }

  sendEmailConfirm(email: string): Observable<any> {
    return this.http.get(API_URL + 'emailConfirm?email=' + email);
  }

  getAllProvince(): Observable<any> {
    return this.http.get(BASE_API_URL + 'provinces', this.httpOptions);
  }

  getAllDistrictByProvinceId(id: number): Observable<any> {
    return this.http.get(BASE_API_URL + 'districts/' + id, this.httpOptions);
  }

  getAllWardByDistrictId(id: number): Observable<any> {
    return this.http.get(BASE_API_URL + 'wards/' + id, this.httpOptions);
  }
}
