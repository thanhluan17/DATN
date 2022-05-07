import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoomManagementRoutingModule } from './admin-room-management-routing.module';
import { RoomCreateComponent } from './room-create/room-create.component';
import { RoomDeleteComponent } from './room-delete/room-delete.component';
import { RoomEditComponent } from './room-edit/room-edit.component';
import { RoomListComponent } from './room-list/room-list.component';
import { SeatCreateComponent } from './seat-create/seat-create.component';
import { SeatDeleteComponent } from './seat-delete/seat-delete.component';
import { SeatEditComponent } from './seat-edit/seat-edit.component';
import { SeatListComponent } from './seat-list/seat-list.component';


@NgModule({
  declarations: [RoomCreateComponent, RoomDeleteComponent, RoomEditComponent, RoomListComponent, SeatCreateComponent, SeatDeleteComponent, SeatEditComponent, SeatListComponent],
  imports: [
    CommonModule,
    AdminRoomManagementRoutingModule
  ]
})
export class AdminRoomManagementModule { }
