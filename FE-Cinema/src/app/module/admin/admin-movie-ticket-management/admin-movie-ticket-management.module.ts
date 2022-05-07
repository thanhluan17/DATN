import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminMovieTicketManagementRoutingModule } from './admin-movie-ticket-management-routing.module';
import { CreateMovieTicketComponent } from './create-movie-ticket/create-movie-ticket.component';
import { EditMovieTicketComponent } from './edit-movie-ticket/edit-movie-ticket.component';
import { DeleteMovieTicketComponent } from './delete-movie-ticket/delete-movie-ticket.component';
import { ListMovieTicketComponent } from './list-movie-ticket/list-movie-ticket.component';


@NgModule({
  declarations: [CreateMovieTicketComponent, EditMovieTicketComponent, DeleteMovieTicketComponent, ListMovieTicketComponent],
  imports: [
    CommonModule,
    AdminMovieTicketManagementRoutingModule
  ]
})
export class AdminMovieTicketManagementModule { }
