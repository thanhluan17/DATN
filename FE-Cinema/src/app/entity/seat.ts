
import {Row} from './row';
import {Column} from './column';
import {SeatType} from './seatType';

export interface Seat {
  seatId: number;
  row: Row;
  column: Column;
  seatType: SeatType;
}
