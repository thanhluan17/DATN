import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {RoomSeat} from '../../../entity/roomSeat';
import {MovieTicket} from '../../../entity/movieTicket';
import {User} from '../../../entity/user';
import {UserNoAccountDTO} from '../../../entity/userNoAccountDTO';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {PaypalDTO} from '../../../entity/paypalDTO';
import {BookTicketService} from '../../../service/member/book-ticket.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../../../service/security/token-storage.service';
import {ToastrService} from 'ngx-toastr';
import {Location} from '@angular/common';

declare var paypal;

@Component({
  selector: 'app-confirm-booking',
  templateUrl: './confirm-booking.component.html',
  styleUrls: ['./confirm-booking.component.scss']
})
export class ConfirmBookingComponent implements OnInit {

  isConfirmed = false;
  listChoseSeat: RoomSeat[] = [];
  movieTicket: MovieTicket;
  user: User;
  userNoAccountDTO: UserNoAccountDTO;
  createUserForm: FormGroup;
  totalMoney: number;
  ticketName = '';
  paypalDTO: PaypalDTO;
  paymentId = '';
  payerId = '';

  constructor(private location: Location,
              private formBuilder: FormBuilder,
              private bookTicketsService: BookTicketService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private tokenStorageService: TokenStorageService,
              private toastrService: ToastrService) {
  }

  @ViewChild('paypal', {static: true}) paypalElement: ElementRef;

  ngOnInit(): void {
    if (this.tokenStorageService.getToken()) {
      this.user = this.tokenStorageService.getUser().user;
    } else {
      this.createForm();
    }

    if (this.bookTicketsService.listChoseSeat.length !== 0) {
      this.listChoseSeat = this.bookTicketsService.listChoseSeat;
      console.log(this.listChoseSeat);
      this.movieTicket = this.bookTicketsService.movieTicket;
      this.totalMoney = this.getTotalMoney(this.listChoseSeat);
      this.ticketName = '';
      for (const roomSeat of this.listChoseSeat) {
        this.ticketName += (roomSeat.seat.row.rowName + roomSeat.seat.column.columnName) + ' ';
      }
    } else {
      this.location.back();
    }

    this.loadPayment();
  }

  private loadExternalScript(scriptUrl: string) {
    return new Promise((resolve, reject) => {
      const scriptElement = document.createElement('script');
      scriptElement.src = scriptUrl;
      scriptElement.onload = resolve;
      document.body.appendChild(scriptElement);
    });
  }

  createForm() {
    this.createUserForm = new FormGroup(
      {
        name: new FormControl('', [Validators.required, Validators.pattern(/^(\s)*([\p{Lu}]|[\p{Ll}]){2,}((\s)(([\p{Lu}]|[\p{Ll}]){2,}))+(\s*)$/u)]),
        email: new FormControl('', [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]),
        idCard: new FormControl('', [Validators.required, Validators.pattern('^(\\d{9}|\\d{12})$')]),
        phone: new FormControl('', [Validators.required, Validators.pattern('(09|03)[0-9]{8}')])
      }
    );
  }

  loadPayment() {
    this.loadExternalScript('https://www.paypalobjects.com/api/checkout.js').then(() => {
      paypal.Button.render({
        env: 'sandbox',
        client: {
          production: 'Ab_hzWp-kJ1PNyG66SS-VKMi0eHYnf2Rl09cyFvi_rqgenO74_swgYtLp-VmLUtDD5Cgx4yVgTKL9_TW',
          sandbox: 'Ab_hzWp-kJ1PNyG66SS-VKMi0eHYnf2Rl09cyFvi_rqgenO74_swgYtLp-VmLUtDD5Cgx4yVgTKL9_TW'
        },
        commit: true,
        payment(data, actions) {
          return actions.payment.create({
            payment: {
              transactions: [
                {
                  amount: {total: '4.00', currency: 'USD'}
                }
              ]
            }
          });
        },
        onAuthorize(data, actions) {
          return actions.payment.execute().then(x => {
            console.log('PAYPAL : OK');
          });
        }
      }, this.paypalElement.nativeElement);
    });
  }

  confirmCreate() {
    if (this.user == null) {
      if (this.createUserForm.valid) {
        this.userNoAccountDTO = this.createUserForm.value;
        this.bookTicketsService.createUserNoAccount(this.userNoAccountDTO).subscribe(data => {
          this.user = data;
        });
      } else {
        this.toastrService.warning('Bạn nhập thông tin user không hợp lệ', 'Thông Báo', {timeOut: 2000});
      }
    }
    this.createTicket();
  }

  createTicket(): void {
    let flag = true;
    for (const roomSeat of this.listChoseSeat) {
      this.bookTicketsService.createTicketDTO(this.movieTicket.movieTicketId,
        this.user.userId, roomSeat.seat.seatId).subscribe(data => {
      }, error => {
        console.log('get ' + error + ' at createTicketDTO() on ConfirmBookingComponent');
        flag = false;
      });
    }

    if (flag) {
      setTimeout(x => {
        this.toastrService.success('Bạn đã đặt vé thành công', 'Thông báo!');
        this.router.navigateByUrl('booking/information').then();
        this.isConfirmed = true;
      }, 3000);
    } else {
      this.toastrService.warning('Đã có lỗi xảy ra!', 'Thông báo!');
    }
  }

  back() {
    this.location.back();
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
