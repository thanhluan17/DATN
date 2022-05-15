import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {BookTicketService} from '../../../../service/member/book-ticket.service';

@Component({
  selector: 'app-ticket-cancel',
  templateUrl: './ticket-cancel.component.html',
  styleUrls: ['./ticket-cancel.component.scss']
})
export class TicketCancelComponent implements OnInit {

  @Input()
  deleteId: number;
  @Input()
  deleteTitle: string;
  @Output()
  deleteComplete = new EventEmitter<boolean>();

  constructor(private bookTicketsService: BookTicketService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
  }

  deletePost() {
    this.bookTicketsService.deleteByIdTickets(this.deleteId).subscribe(data => {
      document.getElementById('closeModal').click();
      this.deleteComplete.emit(true);
    });
    this.toastr.success('Xóa Thành Công !', 'Bài Đăng !');
  }
}
