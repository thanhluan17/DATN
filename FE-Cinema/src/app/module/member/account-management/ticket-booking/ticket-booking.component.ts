import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {TokenStorageService} from '../../../../service/security/token-storage.service';
import {BookTicketService} from '../../../../service/member/book-ticket.service';
import {Ticket} from '../../../../entity/ticket';

@Component({
  selector: 'app-ticket-booking',
  templateUrl: './ticket-booking.component.html',
  styleUrls: ['./ticket-booking.component.scss']
})
export class TicketBookingComponent implements OnInit {

  selectPagination: number;
  page = 0;
  totalPage: number;

  constructor(private bookTicketsService: BookTicketService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private toast: ToastrService,
              private tokenStore: TokenStorageService) {
    this.username = this.tokenStore.getUser().user.account.username;
  }

  ticketBooking: Ticket[] = [];
  username: string;
  deleteId: number;
  deleteTitle: string;


  ngOnInit(): void {
    // this.username = this.activatedRoute.snapshot.params["hoangsang123"];
    this.bookTicketsService.findAll(this.username, this.page).subscribe(data => {
      this.ticketBooking = data;
      this.ticketBooking = data.content;
      this.totalPage = data.totalPages;
      console.log(data);
    });
  }

  deleteSuccess() {
    this.ngOnInit();
  }

  lastPage() {
    this.page = this.totalPage - 1;
    this.ngOnInit();
  }

  firstPage() {
    this.page = 0;
    this.ngOnInit();
  }

  nextPage() {
    this.page += 1;
    this.ngOnInit();
  }

  previousPage() {
    this.page -= 1;
    this.ngOnInit();
  }

  selectPage(selectPage: number) {
    if (selectPage <= this.totalPage) {
      this.page = selectPage - 1;
      this.ngOnInit();
    } else {
      this.toast.warning('Số trang mà bạn nhập hiện tại không có dữ liệu', 'Thông Báo', {timeOut: 2000});
    }
  }

  changePage(page: number) {
    this.page = page;
    this.ngOnInit();
  }
}
