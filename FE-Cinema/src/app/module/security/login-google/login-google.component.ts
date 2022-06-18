import { Component, OnInit } from '@angular/core';
import {SecurityService} from '../../../service/security/security.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login-google',
  templateUrl: './login-google.component.html',
  styleUrls: ['./login-google.component.scss']
})
export class LoginGoogleComponent implements OnInit {

  constructor(
    private securityService: SecurityService,
    private router: Router,
  ) {
  }

  ngOnInit(): void {
    this.router.navigateByUrl('/');
  }

}
