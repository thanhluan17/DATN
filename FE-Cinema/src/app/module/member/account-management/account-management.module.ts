import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccountManagementRoutingModule } from './account-management-routing.module';
import { AccountInfoComponent } from './account-info/account-info.component';
import { TicketCancelComponent } from './ticket-cancel/ticket-cancel.component';
import { TransactionHistoryComponent } from './transaction-history/transaction-history.component';
import { AccountNavBarComponent } from './account-nav-bar/account-nav-bar.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TicketBookingComponent} from './ticket-booking/ticket-booking.component';


@NgModule({
  declarations: [AccountInfoComponent, TicketCancelComponent, TicketBookingComponent, TransactionHistoryComponent, AccountNavBarComponent],
  imports: [
    CommonModule,
    AccountManagementRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class AccountManagementModule { }
