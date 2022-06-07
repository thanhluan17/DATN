import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployeeBookTicketManagementRoutingModule } from './employee-book-ticket-management-routing.module';
import { BookTicketListComponent } from './book-ticket-list/book-ticket-list.component';
import { CancelTicketComponent } from './cancel-ticket/cancel-ticket.component';
import { ConfirmTicketComponent } from './confirm-ticket/confirm-ticket.component';
import { GetTicketComponent } from './get-ticket/get-ticket.component';
import { PrintTicketComponent } from './print-ticket/print-ticket.component';
import {FormsModule} from '@angular/forms';
import {NgxPrintModule} from 'ngx-print';


@NgModule({
  declarations: [BookTicketListComponent, CancelTicketComponent, ConfirmTicketComponent, GetTicketComponent, PrintTicketComponent],
  imports: [
    CommonModule,
    EmployeeBookTicketManagementRoutingModule,
    FormsModule,
    NgxPrintModule
  ]
})
export class EmployeeBookTicketManagementModule { }
