import {Component, OnInit} from '@angular/core';
import {RoomSeat} from '../../../entity/roomSeat';
import {MovieTicket} from '../../../entity/movieTicket';
import {User} from '../../../entity/user';
import {BookTicketService} from '../../../service/member/book-ticket.service';
import {Router} from '@angular/router';
import {TokenStorageService} from '../../../service/security/token-storage.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-booking-information',
  templateUrl: './booking-information.component.html',
  styleUrls: ['./booking-information.component.scss']
})
export class BookingInformationComponent implements OnInit {

  isConfirmed = false;
  listChoseSeat: RoomSeat[] = [];
  movieTicket: MovieTicket;
  user: User;
  totalMoney: number;

  constructor(private bookTicketsService: BookTicketService,
              private router: Router,
              private tokenStorageService: TokenStorageService,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.user = this.tokenStorageService.getUser().user;

    if (this.bookTicketsService.listChoseSeat.length !== 0) {
      this.listChoseSeat = this.bookTicketsService.listChoseSeat;
      this.movieTicket = this.bookTicketsService.movieTicket;
      this.totalMoney = this.getTotalMoney(this.listChoseSeat);
    }
  }

  getTotalMoney(listChoseSeat: RoomSeat[]) {
    let total = 0;
    for (const roomSeat of listChoseSeat) {
      if (roomSeat.seat.seatType.seatTypeId === 1) {
        // tslint:disable-next-line:radix
        total += parseInt(this.movieTicket.ticketPrice.toString());
      } else {
        // tslint:disable-next-line:radix
        total += parseInt(this.movieTicket.ticketPrice.toString()) * (4 / 3);
      }
    }
    return Math.round(total);
  }
}

