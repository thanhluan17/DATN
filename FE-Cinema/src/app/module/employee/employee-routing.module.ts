import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EmployeePageComponent} from './employee-page/employee-page.component';


const routes: Routes = [
  {
    path: '',
    component: EmployeePageComponent,
    children: [
      {
        path: 'book/tickets',
        // tslint:disable-next-line:max-line-length
        loadChildren: () => import('./employee-book-ticket-management/employee-book-ticket-management.module').then(module => module.EmployeeBookTicketManagementModule)
      },
      {
        path: 'sale/tickets',
        // tslint:disable-next-line:max-line-length
        loadChildren: () => import('./employee-sale-ticket-management/employee-sale-ticket-management.module').then(module => module.EmployeeSaleTicketManagementModule)
      },
      {
        path: 'member/management',
        // tslint:disable-next-line:max-line-length
        loadChildren: () => import('./employee-member-management/employee-member-management.module').then(module => module.EmployeeMemberManagementModule)
      },
      {path: '', redirectTo: 'sale/tickets', pathMatch: 'full'},
      {path: '**', redirectTo: 'sale/tickets', pathMatch: 'full'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeRoutingModule {
}
