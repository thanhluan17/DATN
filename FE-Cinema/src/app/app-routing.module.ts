import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainHomePageComponent} from './module/main/main-home-page/main-home-page.component';
import {MainContentComponent} from './module/main/main-content/main-content.component';
import {MainSearchComponent} from './module/main/main-search/main-search.component';
import {DetailMovieComponent} from './module/main/detail-movie/detail-movie.component';
import {MovieCornerComponent} from './module/main/movie-corner/movie-corner.component';
import {MainEventComponent} from './module/main/main-event/main-event.component';
import {MainInstructionComponent} from './module/main/main-instruction/main-instruction.component';
import {MainPolicyComponent} from './module/main/main-policy/main-policy.component';
import {LoginComponent} from './module/security/login/login.component';
import {LogoutComponent} from './module/security/logout/logout.component';
import {RegisterComponent} from './module/security/register/register.component';
import {ConfirmEmailComponent} from './module/security/confirm-email/confirm-email.component';
import {LoginGoogleComponent} from './module/security/login-google/login-google.component';
import {MainBookingComponent} from './module/booking/main-booking/main-booking.component';
import {MovieSelectionComponent} from './module/booking/movie-selection/movie-selection.component';
import {MovieSelectedComponent} from './module/booking/movie-selected/movie-selected.component';
import {SeatSelectionComponent} from './module/booking/seat-selection/seat-selection.component';
import {ConfirmBookingComponent} from './module/booking/confirm-booking/confirm-booking.component';
import {BookingInformationComponent} from './module/booking/booking-information/booking-information.component';
import {PriceTicketClientComponent} from './module/main/price-ticket-client/price-ticket-client.component';
import {MemberPageComponent} from './module/member/member-page/member-page.component';
import {TransactionHistoryComponent} from './module/member/account-management/transaction-history/transaction-history.component';
import {AccountInfoComponent} from './module/member/account-management/account-info/account-info.component';
import {TicketBookingComponent} from './module/member/account-management/ticket-booking/ticket-booking.component';


const routes: Routes = [
  {
    path: 'admin',
    loadChildren: () =>
      import('./module/admin/admin.module').then(
        (module) => module.AdminModule
      ),
  },
  {
    path: 'employee',
    loadChildren: () =>
      import('./module/employee/employee.module').then(
        (module) => module.EmployeeModule
      ),
  },
  {
    path: '',
    component: MainHomePageComponent,
    children: [
      {path: '', component: MainContentComponent},
      {path: 'search', component: MainSearchComponent},
      {path: 'detail-movie/:id', component: DetailMovieComponent},
      {path: 'movie-corner', component: MovieCornerComponent},
      {path: 'events', component: MainEventComponent},
      {path: 'instructions', component: MainInstructionComponent},
      {path: 'policy', component: MainPolicyComponent},
    ],
  },

  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'register/confirmEmail/:username/:email', component: ConfirmEmailComponent},
  {path: 'loginGoogle', component: LoginGoogleComponent},
  {path: 'home', redirectTo: '/'},

  {
    path: 'booking',
    component: MainBookingComponent,
    children: [
      {path: '', redirectTo: 'movie', pathMatch: 'full'},
      {path: 'movie', component: MovieSelectionComponent},
      {path: 'movie/:id', component: MovieSelectedComponent},
      {path: 'seat', component: SeatSelectionComponent},
      {path: 'confirm', component: ConfirmBookingComponent},
      {path: 'information', component: BookingInformationComponent}
    ]
  },

  {path: 'ticket-price', component: PriceTicketClientComponent},

  {
    path: 'member', component: MemberPageComponent, children: [
      {
        path: 'info', component: AccountInfoComponent,
      }, {
        path: 'history', component: TransactionHistoryComponent,
      }, {
        path: 'booking', component: TicketBookingComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {scrollPositionRestoration: 'enabled'})],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
