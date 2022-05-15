import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MemberRoutingModule } from './member-routing.module';
import { MemberPageComponent } from './member-page/member-page.component';
import {MainModule} from '../main/main.module';


@NgModule({
  declarations: [MemberPageComponent],
    imports: [
        CommonModule,
        MemberRoutingModule,
        MainModule
    ]
})
export class MemberModule { }
