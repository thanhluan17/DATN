import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SecurityRoutingModule } from './security-routing.module';
import { ConfirmEmailComponent } from './confirm-email/confirm-email.component';
import { LoginComponent } from './login/login.component';
import { LoginGoogleComponent } from './login-google/login-google.component';
import { LogoutComponent } from './logout/logout.component';
import { RegisterComponent } from './register/register.component';
import {ReactiveFormsModule} from '@angular/forms';


@NgModule({
  declarations: [ConfirmEmailComponent, LoginComponent, LoginGoogleComponent, LogoutComponent, RegisterComponent],
  imports: [
    CommonModule,
    SecurityRoutingModule,
    ReactiveFormsModule
  ]
})
export class SecurityModule { }
