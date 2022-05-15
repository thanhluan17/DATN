import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookingRoutingModule } from './booking-routing.module';
import { ConfirmBookingComponent } from './confirm-booking/confirm-booking.component';
import { MainBookingComponent } from './main-booking/main-booking.component';
import { MovieSelectedComponent } from './movie-selected/movie-selected.component';
import { MovieSelectionComponent } from './movie-selection/movie-selection.component';
import { SeatSelectionComponent } from './seat-selection/seat-selection.component';
import { BookingInformationComponent } from './booking-information/booking-information.component';
import {MainModule} from '../main/main.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';


@NgModule({
  declarations: [ConfirmBookingComponent, MainBookingComponent, MovieSelectedComponent, MovieSelectionComponent, SeatSelectionComponent, BookingInformationComponent],
  imports: [
    CommonModule,
    BookingRoutingModule,
    MainModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class BookingModule { }
