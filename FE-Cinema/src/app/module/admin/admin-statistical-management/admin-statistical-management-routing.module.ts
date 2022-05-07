import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {MovieStatisticsComponent} from './movie-statistics/movie-statistics.component';
import {MemberStatisticsComponent} from './member-statistics/member-statistics.component';
import {CategoryStatisticsComponent} from './category-statistics/category-statistics.component';
import {ShowtimeStatisticsComponent} from './showtime-statistics/showtime-statistics.component';


const routes: Routes = [
  {path: 'movie', component: MovieStatisticsComponent},
  {path: 'member', component: MemberStatisticsComponent},
  {path: 'category', component: CategoryStatisticsComponent},
  {path: 'showtime', component: ShowtimeStatisticsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class AdminStatisticalManagementRoutingModule {
}
