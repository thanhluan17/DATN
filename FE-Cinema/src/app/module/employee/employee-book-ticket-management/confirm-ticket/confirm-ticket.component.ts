import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BookTicketManagementService} from '../../../../service/employee/book-ticket-management.service';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';

@Component({
  selector: 'app-confirm-ticket',
  templateUrl: './confirm-ticket.component.html',
  styleUrls: ['./confirm-ticket.component.scss']
})
export class ConfirmTicketComponent implements OnInit {

  @Input()
  receiveId: number;
  @Output()
  receiveComplete = new EventEmitter<boolean>();

  constructor(private bookTicketService: BookTicketManagementService,
              private toastr: ToastrService,
              private router: Router) { }

  ngOnInit(): void {
  }

  onReceive() {
    this.bookTicketService.confirmTicket(this.receiveId).subscribe(data  => {
      document.getElementById('closeModal').click();
      this.receiveComplete.emit(true);
      this.router.navigate(['/employee/book/tickets/book-ticket-list/get-ticket/print-ticket', this.receiveId]);
    });
    this.toastr.success('Nhận Vé Thành Công', 'Vé Đã Đặt');
  }
}
