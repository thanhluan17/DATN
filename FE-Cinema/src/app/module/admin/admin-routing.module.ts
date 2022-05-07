import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdminPageComponent} from './admin-page/admin-page.component';

const routes: Routes = [
  {
    path: '',
    component: AdminPageComponent,
    children: [
      {
        path: 'room',
        loadChildren: () => import('./admin-room-management/admin-room-management.module').then(module => module.AdminRoomManagementModule)
      },
      {
        path: 'movie',
        // tslint:disable-next-line:max-line-length
        loadChildren: () => import('./admin-movie-management/admin-movie-management.module').then(module => module.AdminMovieManagementModule)
      },
      {
        path: 'statistical',
        // tslint:disable-next-line:max-line-length
        loadChildren: () => import('./admin-statistical-management/admin-statistical-management.module').then(module => module.AdminStatisticalManagementModule)
      },
      {
        path: 'movie-ticket',
        // tslint:disable-next-line:max-line-length
        loadChildren: () => import('./admin-movie-ticket-management/admin-movie-ticket-management.module').then(module => module.AdminMovieTicketManagementModule)
      },
      {path: '', redirectTo: '', pathMatch: 'full'},
      {path: '**', redirectTo: '', pathMatch: 'full'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {
}
