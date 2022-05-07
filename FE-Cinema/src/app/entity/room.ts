import {StatusRoom} from './statusRoom';

export interface Room {
  roomId: number;
  roomName: string;
  roomStatus: StatusRoom;
}
