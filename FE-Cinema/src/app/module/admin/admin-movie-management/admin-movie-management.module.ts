import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminMovieManagementRoutingModule } from './admin-movie-management-routing.module';
import { AddMovieComponent } from './add-movie/add-movie.component';
import { EditMovieComponent } from './edit-movie/edit-movie.component';
import { ListMovieComponent } from './list-movie/list-movie.component';


@NgModule({
  declarations: [AddMovieComponent, EditMovieComponent, ListMovieComponent],
  imports: [
    CommonModule,
    AdminMovieManagementRoutingModule
  ]
})
export class AdminMovieManagementModule { }
