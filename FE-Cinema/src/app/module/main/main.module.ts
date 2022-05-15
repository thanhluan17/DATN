import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {MainRoutingModule} from './main-routing.module';
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


@NgModule({
    declarations: [DetailMovieComponent, MainContentComponent, MainEventComponent, MainFooterComponent, MainHeaderComponent, MainHomePageComponent, MainInstructionComponent, MainPolicyComponent, MainSearchComponent, MovieCornerComponent, MovieTrailerComponent, PriceTicketClientComponent],
  exports: [
    MainHeaderComponent,
    MainFooterComponent
  ],
    imports: [
        CommonModule,
        MainRoutingModule
    ]
})
export class MainModule {
}
