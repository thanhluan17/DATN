import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminStatisticalManagementRoutingModule } from './admin-statistical-management-routing.module';
import { CategoryStatisticsComponent } from './category-statistics/category-statistics.component';
import { MemberStatisticsComponent } from './member-statistics/member-statistics.component';
import { MovieStatisticsComponent } from './movie-statistics/movie-statistics.component';
import { ShowtimeStatisticsComponent } from './showtime-statistics/showtime-statistics.component';


@NgModule({
  declarations: [CategoryStatisticsComponent, MemberStatisticsComponent, MovieStatisticsComponent, ShowtimeStatisticsComponent],
  imports: [
    CommonModule,
    AdminStatisticalManagementRoutingModule
  ]
})
export class AdminStatisticalManagementModule { }
