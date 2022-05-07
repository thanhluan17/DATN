import {User} from './user';
import {Seat} from './seat';
import {TicketStatus} from './ticketStatus';
import {MovieTicket} from './movieTicket';

export class Ticket {
  private _ticketId: number;
  private _createTime: string;
  private _user: User;
  private _seat: Seat;
  private _ticketStatus: TicketStatus;
  private _movieTicket: MovieTicket;


  constructor() {
  }


  get ticketId(): number {
    return this._ticketId;
  }

  set ticketId(value: number) {
    this._ticketId = value;
  }

  get createTime(): string {
    return this._createTime;
  }

  set createTime(value: string) {
    this._createTime = value;
  }

  get user(): User {
    return this._user;
  }

  set user(value: User) {
    this._user = value;
  }

  get seat(): Seat {
    return this._seat;
  }

  set seat(value: Seat) {
    this._seat = value;
  }

  get ticketStatus(): TicketStatus {
    return this._ticketStatus;
  }

  set ticketStatus(value: TicketStatus) {
    this._ticketStatus = value;
  }

  get movieTicket(): MovieTicket {
    return this._movieTicket;
  }

  set movieTicket(value: MovieTicket) {
    this._movieTicket = value;
  }

}
