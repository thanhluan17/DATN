import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MovieSelectionComponent} from './movie-selection/movie-selection.component';
import {SeatSelectionComponent} from './seat-selection/seat-selection.component';
import {ConfirmSaleTicketComponent} from './confirm-sale-ticket/confirm-sale-ticket.component';
import {InfoSaleTicketComponent} from './info-sale-ticket/info-sale-ticket.component';


const routes: Routes = [
  {path: '', component: MovieSelectionComponent},
  {path: 'seat', component: SeatSelectionComponent},
  {path: 'confirmSaleTicket', component: ConfirmSaleTicketComponent},
  {path: 'info', component: InfoSaleTicketComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeSaleTicketManagementRoutingModule {
}
