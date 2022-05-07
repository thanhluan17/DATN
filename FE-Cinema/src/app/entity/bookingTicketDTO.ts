export class BookingTicketDTO {
  private _movieTicketId: number;
  private _userId: number;
  private _seatId: number;

  constructor(movieTicketId: number, userId: number, seatId: number) {
    this._movieTicketId = movieTicketId;
    this._userId = userId;
    this._seatId = seatId;
  }

  get movieTicketId(): number {
    return this._movieTicketId;
  }

  set movieTicketId(value: number) {
    this._movieTicketId = value;
  }

  get userId(): number {
    return this._userId;
  }

  set userId(value: number) {
    this._userId = value;
  }

  get seatId(): number {
    return this._seatId;
  }

  set seatId(value: number) {
    this._seatId = value;
  }
}
