import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployeeMemberManagementRoutingModule } from './employee-member-management-routing.module';
import { CreateUserComponent } from './create-user/create-user.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { DeleteUserComponent } from './delete-user/delete-user.component';
import { ListUserComponent } from './list-user/list-user.component';


@NgModule({
  declarations: [CreateUserComponent, EditUserComponent, DeleteUserComponent, ListUserComponent],
  imports: [
    CommonModule,
    EmployeeMemberManagementRoutingModule
  ]
})
export class EmployeeMemberManagementModule { }
