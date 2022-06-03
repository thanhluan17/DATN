import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployeeMemberManagementRoutingModule } from './employee-member-management-routing.module';
import { CreateUserComponent } from './create-user/create-user.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { DeleteUserComponent } from './delete-user/delete-user.component';
import { ListUserComponent } from './list-user/list-user.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatRadioModule} from '@angular/material/radio';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';


@NgModule({
  declarations: [CreateUserComponent, EditUserComponent, DeleteUserComponent, ListUserComponent],
  imports: [
    CommonModule,
    EmployeeMemberManagementRoutingModule,
    FormsModule,
    MatRadioModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule
  ]
})
export class EmployeeMemberManagementModule { }
