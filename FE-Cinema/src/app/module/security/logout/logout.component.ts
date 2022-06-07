import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from '../../../service/security/token-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent implements OnInit {

  constructor(
    private tokenStorageService: TokenStorageService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.logout();
  }

  logout() {
    this.tokenStorageService.signOut();
    this.router.navigateByUrl('/login');
  }
}
