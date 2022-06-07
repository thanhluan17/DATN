import {Component, OnInit} from '@angular/core';
import {MovieTicket} from '../../../../entity/movieTicket';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {SaleTicketManagementService} from '../../../../service/employee/sale-ticket-management.service';

@Component({
  selector: 'app-movie-selection',
  templateUrl: './movie-selection.component.html',
  styleUrls: ['./movie-selection.component.scss']
})
export class MovieSelectionComponent implements OnInit {

  public listMovie: MovieTicket[];
  public listShowTime: MovieTicket[] = null;
  public listShowDate: MovieTicket[] = null;
  public movieId: number = null;
  public showDate: string = null;
  public showTimeId: number = null;
  public movieTicket: MovieTicket;
  public activeId = null;
  public minDate = new Date();
  public choseDate: string = null;


  constructor(private saleTicketService: SaleTicketManagementService,
              private toast: ToastrService,
              private route: Router) {
  }

  ngOnInit(): void {
    this.saleTicketService.showAllMovieTicket().subscribe((data) => {
      this.listMovie = data;
    });
  }

  changeShowDate(movieId: number) {
    this.listShowTime = null;
    this.activeId = movieId;
    this.movieId = movieId;
    this.showDate = null;
    this.showTimeId = null;
    if (this.choseDate === null) {
      this.saleTicketService.showAllMovieTicketByMovieId(movieId).subscribe((data) => {
        this.listShowDate = data;
      });
    } else {
      this.saleTicketService.showAllMovieTicketByIdAndShowDate(movieId, this.choseDate).subscribe((data) => {
        this.listShowTime = data;
      });
    }

  }

  changeShowTime(showDate: string) {
    this.showDate = showDate;
    this.showTimeId = null;
    this.saleTicketService.showAllMovieTicketByIdAndShowDate(this.movieId, showDate).subscribe((data) => {
      this.listShowTime = data;
    });
  }

  changeShowTimeId(showTimeId: number) {
    this.showTimeId = showTimeId;
  }

  checkMovieTicket() {
    if (this.choseDate !== null) {
      this.showDate = this.choseDate;
    }
    if (this.movieId !== null && this.showDate !== null && this.showTimeId !== null) {
      this.saleTicketService.findMovieTicketBySelect(this.movieId, this.showDate, this.showTimeId).subscribe((data) => {
        this.movieTicket = data;
        this.route.navigate(['employee/sale/tickets/seat'], {
            queryParams: {
              movieTicketId: this.movieTicket.movieTicketId
            }
          }
        );
      });
    } else {
      this.toast.warning('Bạn chưa chọn đầy đủ thông tin để tiếp tục', 'Thông Báo', {timeOut: 2000});
    }
  }

  changeDate(date: Date) {
    this.listMovie = null;
    this.listShowDate = null;
    this.listShowTime = null;
    this.showDate = null;
    this.showTimeId = null;
    this.choseDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
    this.saleTicketService.showAllMovieTicketByShowDate(this.choseDate).subscribe((data) => {
      this.listMovie = data;
      console.log(this.listMovie);
      if (this.listMovie === null) {
        this.toast.warning('Ngày bạn chọn chưa có phim xuất chiếu!', 'Thông Báo', {timeOut: 2000});
      }
    });
  }

  removeSearch() {
    if (this.choseDate !== null) {
      this.listShowTime = null;
      this.listShowDate = null;
      this.choseDate = null;
      document.getElementById('pick1')['value'] = '';
      this.ngOnInit();
    }
  }
}
