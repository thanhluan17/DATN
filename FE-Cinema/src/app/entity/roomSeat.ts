import {Room} from './room';
import {Seat} from './seat';
import {SeatStatus} from './seatStatus';

export interface RoomSeat {
  roomSeatId: number;
  room: Room;
  seat: Seat;
  seatStatus: SeatStatus;
}
