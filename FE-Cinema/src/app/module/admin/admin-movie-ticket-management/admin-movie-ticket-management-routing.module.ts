import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListMovieTicketComponent} from './list-movie-ticket/list-movie-ticket.component';
import {CreateMovieTicketComponent} from './create-movie-ticket/create-movie-ticket.component';
import {EditMovieTicketComponent} from './edit-movie-ticket/edit-movie-ticket.component';
import {DeleteMovieTicketComponent} from './delete-movie-ticket/delete-movie-ticket.component';


const routes: Routes = [
  {path: '', component: ListMovieTicketComponent},
  {path: 'create', component: CreateMovieTicketComponent},
  {path: 'edit/:id', component: EditMovieTicketComponent},
  {path: 'delete/:id', component: DeleteMovieTicketComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminMovieTicketManagementRoutingModule {
}
