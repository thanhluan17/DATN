import {Component, OnInit} from '@angular/core';
import {TransactionHistory} from '../../../../entity/transactionHistory';
import {TransactionHistoryService} from '../../../../service/member/transaction-history.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../../../../service/security/token-storage.service';

@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.scss']
})
export class TransactionHistoryComponent implements OnInit {
  transactionHistoryList: TransactionHistory[] = [];
  username = this.tokenStore.getUser().user.account.username;

  constructor(private transactionHistoryService: TransactionHistoryService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private tokenStore: TokenStorageService) {
  }

  ngOnInit(): void {
    this.transactionHistoryService.findAll(this.username).subscribe(data => {
      this.transactionHistoryList = data;
      console.log(data);
    });
  }
}
