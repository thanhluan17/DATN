import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {RoomListComponent} from './room-list/room-list.component';
import {RoomCreateComponent} from './room-create/room-create.component';
import {RoomEditComponent} from './room-edit/room-edit.component';
import {RoomDeleteComponent} from './room-delete/room-delete.component';
import {SeatListComponent} from './seat-list/seat-list.component';
import {SeatCreateComponent} from './seat-create/seat-create.component';
import {SeatEditComponent} from './seat-edit/seat-edit.component';
import {SeatDeleteComponent} from './seat-delete/seat-delete.component';


const routes: Routes = [
  {path: '', component: RoomListComponent},
  {path: 'create', component: RoomCreateComponent},
  {path: 'edit/:id', component: RoomEditComponent},
  {path: 'delete/:id', component: RoomDeleteComponent},
  {path: 'seat/:id', component: SeatListComponent},
  {path: 'seat/create/:id', component: SeatCreateComponent},
  {path: 'seat/edit/:id', component: SeatEditComponent},
  {path: 'seat/delete/:id', component: SeatDeleteComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoomManagementRoutingModule {
}
