import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MemberRoutingModule } from './member-routing.module';
import { MemberPageComponent } from './member-page/member-page.component';


@NgModule({
  declarations: [MemberPageComponent],
  imports: [
    CommonModule,
    MemberRoutingModule
  ]
})
export class MemberModule { }
