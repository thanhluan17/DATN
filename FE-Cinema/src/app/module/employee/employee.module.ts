import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployeeRoutingModule } from './employee-routing.module';
import { EmployeePageComponent } from './employee-page/employee-page.component';
import {MainModule} from '../main/main.module';


@NgModule({
  declarations: [EmployeePageComponent],
    imports: [
        CommonModule,
        EmployeeRoutingModule,
        MainModule
    ]
})
export class EmployeeModule { }
