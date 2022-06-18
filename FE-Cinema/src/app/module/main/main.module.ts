import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DetailMovieComponent} from './detail-movie/detail-movie.component';
import {MainContentComponent} from './main-content/main-content.component';
import {MainEventComponent} from './main-event/main-event.component';
import {MainFooterComponent} from './main-footer/main-footer.component';
import {MainHeaderComponent} from './main-header/main-header.component';
import {MainHomePageComponent} from './main-home-page/main-home-page.component';
import {MainInstructionComponent} from './main-instruction/main-instruction.component';
import {MainPolicyComponent} from './main-policy/main-policy.component';
import {MainSearchComponent} from './main-search/main-search.component';
import {MovieCornerComponent} from './movie-corner/movie-corner.component';
import {MovieTrailerComponent} from './movie-trailer/movie-trailer.component';
import {PriceTicketClientComponent} from './price-ticket-client/price-ticket-client.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {MatDialogModule} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {AdminRoutingModule} from '../admin/admin-routing.module';
import {MatNativeDateModule} from '@angular/material/core';


@NgModule({
  declarations: [MainHomePageComponent, MainHeaderComponent, MainFooterComponent, MovieTrailerComponent, MainSearchComponent,
    DetailMovieComponent,
    PriceTicketClientComponent,
    MainContentComponent,
    MainSearchComponent,
    MovieCornerComponent,
    MainEventComponent,
    MainInstructionComponent,
    MainPolicyComponent],
  exports: [
    MainHeaderComponent,
    MainFooterComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    FormsModule,
    MatDialogModule,
    MatButtonModule,
    MatIconModule,
    AdminRoutingModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    MatNativeDateModule
  ]
})
export class MainModule {
}
