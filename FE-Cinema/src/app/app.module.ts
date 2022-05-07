import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AngularFireModule} from '@angular/fire';
import {environment} from '../environments/environment';
import {AngularFireStorageModule} from '@angular/fire/storage';
import {HttpClientModule} from '@angular/common/http';
import {EmployeeModule} from './module/employee/employee.module';
import {MemberModule} from './module/member/member.module';
import {MainModule} from './module/main/main.module';
import {SecurityModule} from './module/security/security.module';
import {AngularFireDatabaseModule} from '@angular/fire/database';
import {BookingModule} from './module/booking/booking.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AdminMovieManagementModule} from './module/admin/admin-movie-management/admin-movie-management.module';
import {AdminMovieTicketManagementModule} from './module/admin/admin-movie-ticket-management/admin-movie-ticket-management.module';
import {ToastrModule} from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFireStorageModule,
    AngularFireDatabaseModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    EmployeeModule,
    MemberModule,
    MainModule,
    SecurityModule,
    BookingModule,
    AdminMovieTicketManagementModule,
    ReactiveFormsModule,
    RouterModule,
    FormsModule,
    NgbModule,
    AdminMovieManagementModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
