import {Component, OnInit} from '@angular/core';
import {Ticket} from '../../../../entity/ticket';
import {RoomSeat} from '../../../../entity/roomSeat';
import {MovieTicket} from '../../../../entity/movieTicket';
import {BookTicketManagementService} from '../../../../service/employee/book-ticket-management.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-get-ticket',
  templateUrl: './get-ticket.component.html',
  styleUrls: ['./get-ticket.component.scss']
})
export class GetTicketComponent implements OnInit {

  public ticketDetail: Ticket;
  listChoseSeat: RoomSeat[] = [];
  movieTicket: MovieTicket;
  receiveId: number;
  totalMoney: number;

  constructor(private bookTicketService: BookTicketManagementService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getBookedTicket();
  }

  getBookedTicket() {
    const index = this.activatedRoute.snapshot.params.ticketId;
    this.bookTicketService.getBookedTicketByIndex(index).subscribe(data => {
      console.log(data);
      this.ticketDetail = data;
    });
    if (this.bookTicketService.listChoseSeat.length !== 0) {
      this.listChoseSeat = this.bookTicketService.listChoseSeat;
      this.movieTicket = this.bookTicketService.movieTicket;
      this.totalMoney = this.getTotalMoney(this.listChoseSeat);
    }
  }

  getTotalMoney(listChoseSeat: RoomSeat[]) {
    let total = 0;
    for (const roomSeat of listChoseSeat) {
      if (roomSeat.seat.seatType.seatTypeId === 1) {
        total += this.movieTicket.ticketPrice;
      } else {
        total += this.movieTicket.ticketPrice * (4 / 3);
      }
    }
    return Math.round(total);
  }

  printBookedTicket() {
    const index = this.activatedRoute.snapshot.params.ticketId;
    this.bookTicketService.printTicketByTicketId(index).subscribe(data => {
      this.ticketDetail = data;
    });
  }

  receiveSuccess() {
    this.printBookedTicket();
  }
}
