import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployeeSaleTicketManagementRoutingModule } from './employee-sale-ticket-management-routing.module';
import { ConfirmSaleTicketComponent } from './confirm-sale-ticket/confirm-sale-ticket.component';
import { InfoSaleTicketComponent } from './info-sale-ticket/info-sale-ticket.component';
import { MovieSelectionComponent } from './movie-selection/movie-selection.component';
import { SeatSelectionComponent } from './seat-selection/seat-selection.component';


@NgModule({
  declarations: [ConfirmSaleTicketComponent, InfoSaleTicketComponent, MovieSelectionComponent, SeatSelectionComponent],
  imports: [
    CommonModule,
    EmployeeSaleTicketManagementRoutingModule
  ]
})
export class EmployeeSaleTicketManagementModule { }
