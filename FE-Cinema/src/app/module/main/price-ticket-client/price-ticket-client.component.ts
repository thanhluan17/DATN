import {Component, OnInit} from '@angular/core';
import {MovieTicket} from '../../../entity/movieTicket';
import {TicketPriceService} from '../../../service/ticket/ticket-price.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-price-ticket-client',
  templateUrl: './price-ticket-client.component.html',
  styleUrls: ['./price-ticket-client.component.scss']
})
export class PriceTicketClientComponent implements OnInit {
  showDate: string;
  movietickets: MovieTicket[] = [];
  minDate = new Date();
  // priceBefore17H: number = 0;
  // priceAfter17H: number = 0;
  projectionType = 1;
  date: string = null;
  classButton2D = 'btn-orange';
  classButton3D = 'btn-outline';

  constructor(private ticketClientService: TicketPriceService,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
  }

  priceTicketBefor17H() {
    if (this.date !== null) {
      if (this.projectionType === 1) {
        switch (this.date) {
          case 'Mon':
          case 'Tue':
          case 'Wed':
          case 'Thu':
            return 45000;
          default:
            return 60000;
        }
      } else {
        switch (this.date) {
          case 'Mon':
          case 'Tue':
          case 'Wed':
          case 'Thu':
            return 60000;
          default:
            return 75000;
        }
      }
    }
  }


  priceTicketAfter17H() {
    if (this.date !== null) {
      if (this.projectionType === 1) {
        switch (this.date) {
          case 'Mon':
          case 'Tue':
          case 'Wed':
          case 'Thu':
            return Math.round(4 * 45000 / 3);
          default:
            return Math.round(5 * 60000 / 4);
        }
      } else {
        switch (this.date) {
          case 'Mon':
          case 'Tue':
          case 'Wed':
          case 'Thu':
            return Math.round(5 * 60000 / 4);
          default:
            return Math.round(5 * 80000 / 4);
        }
      }
    }
  }


  change() {
    this.classButton3D = 'btn-outline';
    this.classButton2D = 'btn-orange';
    document.getElementById('side-a').style.display = 'block';
    document.getElementById('side-b').style.display = 'none';
    if (this.date !== null) {
      this.projectionType = 1;
    } else {
      this.toastr.error('Bạn chưa chọn ngày chiếu !', 'Thông báo');
    }
  }

  change2() {
    this.classButton3D = 'btn-orange';
    this.classButton2D = 'btn-outline';
    document.getElementById('side-b').style.display = 'block';
    document.getElementById('side-a').style.display = 'none';
    if (this.date !== null) {
      this.projectionType = 2;
    } else {
      this.toastr.error('Bạn chưa chọn ngày chiếu !', 'Thông báo');
    }
  }

  onChangDate(date: Date) {
    // console.log(typeof this.showDate);
    // this.ticketClientService.getAllMovieByDate(this.showDate).subscribe(data => {
    //   this.movietickets = data;
    //   console.log(data);
    //   this.toastr.success("Find successfully", "Notification");
    //   console.log(data);
    // }, error => {
    //   this.toastr.error("Not Found", "Notification");
    // })
    this.date = date.toString().substring(0, 3);
    // switch (dateOfWeek) {
    //   case 'Mon':
    //   case 'Tue':
    //   case 'Web':
    //   case 'Thu':
    //     this.price = 45000;
    //     break;
    //   default:
    //     this.price = 60000;
    // }


  }

}


