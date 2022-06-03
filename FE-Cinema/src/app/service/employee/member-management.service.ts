import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {TokenStorageService} from '../security/token-storage.service';
import {Observable} from 'rxjs';
import {User} from '../../entity/user';
import {UserEditPreview} from '../../entity/userEditPreview';

@Injectable({
  providedIn: 'root'
})
export class MemberManagementService {
  API_URL_ADDRESS = 'http://localhost:8080';
  private API_URL_USER = 'http://localhost:8080/employee/listUser';
  httpOptions: any;

  constructor(private httpClient: HttpClient,
              private tokenStorage: TokenStorageService) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ` + this.tokenStorage.getUser().token
      }),
      'Access-Control-Allow-Origin': 'http://localhost:4200/',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
      'Access-Control-Allow-Credentials': 'true'
    };
  }
  getAllUsers(index: number): Observable<any> {
    return this.httpClient.get(this.API_URL_USER + '/?index=' + index, this.httpOptions);
  }

  getUserById(id: number): Observable<any> {
    return this.httpClient.get(this.API_URL_USER + '/' + id, this.httpOptions);
  }

  deleteUser(id: number): Observable<any> {
    return this.httpClient.put<any>(this.API_URL_USER + '/delete/' + id, {}, this.httpOptions);
  }


  getAllProvince(): Observable<any> {
    return this.httpClient.get(this.API_URL_ADDRESS + '/provinces', this.httpOptions);
  }

  getAllDistrictByProvinceId(id: number): Observable<any> {
    return this.httpClient.get(this.API_URL_ADDRESS + '/districts/' + id, this.httpOptions);
  }

  getAllWardByDistrictId(id: number): Observable<any> {
    return this.httpClient.get(this.API_URL_ADDRESS + '/wards/' + id, this.httpOptions);
  }

  createUser(user: User): Observable<any> {
    return this.httpClient.post(this.API_URL_USER + '/create', user, this.httpOptions);
  }

  editUser(user: UserEditPreview): Observable<any> {
    return this.httpClient.put(this.API_URL_USER + '/edit', user, this.httpOptions);
  }

  searchUserBySomething(keySearch: string): Observable<any> {
    return this.httpClient.get<any>(this.API_URL_USER + '/search?q=' + keySearch, this.httpOptions);
  }

  searchUserByPagination(keySearch: string, index: number): Observable<any> {
    return this.httpClient.get<any>(this.API_URL_USER + '/searchPagination?q=' + keySearch + '&index=' + index, this.httpOptions);
  }

  findAllUsers(): Observable<any> {
    return this.httpClient.get(this.API_URL_USER + '/getAll', this.httpOptions);
  }

  sendEmailApprove(email: string): Observable<any> {
    return this.httpClient.get(this.API_URL_USER + '/email?email=' + email, this.httpOptions);
  }

}
