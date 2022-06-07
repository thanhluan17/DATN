import {Component, OnInit} from '@angular/core';
import {Ticket} from '../../../../entity/ticket';
import {RoomSeat} from '../../../../entity/roomSeat';
import {MovieTicket} from '../../../../entity/movieTicket';
import {BookTicketManagementService} from '../../../../service/employee/book-ticket-management.service';
import {ActivatedRoute} from '@angular/router';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';


@Component({
  selector: 'app-print-ticket',
  templateUrl: './print-ticket.component.html',
  styleUrls: ['./print-ticket.component.scss']
})
export class PrintTicketComponent implements OnInit {

  public ticketPrint: Ticket;
  listChoseSeat: RoomSeat[] = [];
  movieTicket: MovieTicket;
  totalMoney: number;

  constructor(private bookTicketService: BookTicketManagementService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    const index = this.activatedRoute.snapshot.params.ticketId;
    this.bookTicketService.printTicketByTicketId(index).subscribe(data => {
      this.ticketPrint = data;
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

  makePDF() {
    const element = document.getElementById('print');
    html2canvas(element).then((canvas) => {
      const imgData = canvas.toDataURL('image/png');
      const doc = new jsPDF();
      const imgHeight = canvas.height * 208 / canvas.width;
      doc.addImage(imgData, 0, 0, 190, imgHeight);
      doc.save('movie.pdf');
    });
  }

}
