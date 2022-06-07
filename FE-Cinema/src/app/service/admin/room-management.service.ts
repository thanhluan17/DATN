import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Room} from '../../entity/room';
import {Seat} from '../../entity/seat';
import {RoomSeat} from '../../entity/roomSeat';
import {StatusRoom} from '../../entity/statusRoom';
import {Row} from '../../entity/row';
import {SeatType} from '../../entity/seatType';

@Injectable({
  providedIn: 'root'
})
export class RoomManagementService {

  public baseUrl = 'http://localhost:8080/api/admin';
  public roomSeatUrl = 'http://localhost:8080/api/roomSeat';
  public seatTypeUrl = 'http://localhost:8080/api/seatType';
  public rowSeatUrl = 'http://localhost:8080/api/rowSeat';
  public roomUrl = 'http://localhost:8080/api/room';
  public roomStatusUrl = 'http://localhost:8080/api/roomStatus';
  public seatUrl = 'http://localhost:8080/api/seat';
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

  getAllRoom(page: number, keySearch: string): Observable<Room[]> {
    return this.httpClient.get<Room[]>(this.roomUrl + '/room?page=' + page + '&&roomName=' + keySearch);
  }

  getAllSeat(): Observable<Seat[]> {
    return this.httpClient.get<Seat[]>(this.baseUrl + '/seat');
  }

  getRoomById(id: number): Observable<Room> {
    return this.httpClient.get<Room>(this.roomUrl + '/room/' + id);
  }

  createRoom(room: Room): Observable<Room> {
    return this.httpClient.post<Room>(this.roomUrl + '/room/create-room', room);
  }

  createSeat(id: number): Observable<RoomSeat> {
    return this.httpClient.get<RoomSeat>(this.roomSeatUrl + '/seat/create-seat/' + id);
  }

  updateRoom(id, room): Observable<Room> {
    return this.httpClient.put<Room>(this.roomUrl + '/room/edit-room/' + id, room);
  }

  updateSeat(seat: Seat): Observable<Seat> {
    return this.httpClient.put<Seat>(this.seatUrl + '/seat/edit-seat', seat);
  }

  deleteRoom(id: number): Observable<Room> {
    return this.httpClient.get<Room>(this.roomUrl + '/room/delete-room/' + id);
  }

  deleteSeat(id: number): Observable<Seat> {
    return this.httpClient.get<Seat>(this.roomSeatUrl + '/seat/delete-seat/' + id);
  }

  getAllRoomStatus(): Observable<StatusRoom[]> {
    return this.httpClient.get<StatusRoom[]>(this.roomStatusUrl + '/room-status');
  }

  searchRoomAbsolute(roomName: string): Observable<Room[]> {
    return this.httpClient.get<Room[]>(this.roomUrl + '/room/searchAbsolute?' +
      'roomName=' + roomName);
  }

  getAllRow(): Observable<Row[]> {
    return this.httpClient.get<Row[]>(this.rowSeatUrl + '/row');
  }

  getAllSeatType(): Observable<SeatType[]> {
    return this.httpClient.get<SeatType[]>(this.seatTypeUrl + '/seat-type');
  }

  getAllSeatByRoomId(id: number): Observable<RoomSeat[]> {
    return this.httpClient.get<RoomSeat[]>(this.roomSeatUrl + '/roomSeat/' + id);
  }

  getSeatTotal(id: number): Observable<RoomSeat[]> {
    return this.httpClient.get<RoomSeat[]>(this.roomSeatUrl + '/getRoomSeat/' + id);
  }

  showSeatDelete(): Observable<RoomSeat[]> {
    return this.httpClient.get<RoomSeat[]>(this.roomSeatUrl + '/showSeatDelete/');
  }

  createSeatBySeatType(seatTypeId: number, seatId: number): Observable<Seat[]> {
    return this.httpClient.put<Seat[]>(this.seatUrl + '/createSeatBySeatType/' + seatTypeId + '/' + seatId, {seatTypeId, seatId});
  }
}
