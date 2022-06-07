import {Component, OnInit} from '@angular/core';
import {BookTicketManagementService} from '../../../../service/employee/book-ticket-management.service';
import {ToastrService} from 'ngx-toastr';
import {Ticket} from '../../../../entity/ticket';

@Component({
  selector: 'app-book-ticket-list',
  templateUrl: './book-ticket-list.component.html',
  styleUrls: ['./book-ticket-list.component.scss']
})
export class BookTicketListComponent implements OnInit {

  p = 1;
  totalLength: any;
  optionSearch = 1;
  keySearch: any;
  bookedTicketList: Ticket[] = [];
  bookedTicketListNoPage: Ticket[] = [];
  cancelId: number;
  indexPagination = 1;
  selectPagination: number;
  totalPagination: number;

  constructor(private bookTicketManagementService: BookTicketManagementService,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.getBookTicketList();
  }

  getBookTicketList() {
    this.bookTicketManagementService.getAllBookedTicketList(0).subscribe(data => {
      if (data == null) {
        this.bookedTicketList = [];
      } else {
        this.bookedTicketList = data;
        // this.totalLength = data.length;
      }
    });
    this.bookTicketManagementService.getAllBookedTicketListNoPage().subscribe(data => {
      this.bookedTicketListNoPage = data;
      if ((this.bookedTicketListNoPage.length % 5) !== 0) {
        this.totalPagination = (Math.floor(this.bookedTicketListNoPage.length / 5)) + 1;
      } else {
        this.totalPagination = (Math.floor(this.bookedTicketListNoPage.length / 5));
      }
    });
  }

  search() {
    if (this.optionSearch === 1) {
      this.bookTicketManagementService.searchTicketByTicketId(this.keySearch).subscribe(data => {
        if (data == null) {
          this.toastr.warning('Không tìm thấy !', 'Vé Đã Đặt !');
        } else {
          this.bookedTicketList = data.content;
        }
      });
    } else if (this.optionSearch === 2) {
      this.bookTicketManagementService.searchTicketByUserId(this.keySearch).subscribe(data => {
        if (data == null) {
          this.toastr.warning('Không tìm thấy !', 'Vé Đã Đặt !');
        } else {
          this.bookedTicketList = data.content;
        }
      });
    } else if (this.optionSearch === 3) {
      this.bookTicketManagementService.searchTicketByIdCard(this.keySearch).subscribe(data => {
        if (data == null) {
          this.toastr.warning('Không tìm thấy !', 'Vé Đã Đặt !');
        } else {
          this.bookedTicketList = data.content;
        }
      });
    } else if (this.optionSearch === 4) {
      this.bookTicketManagementService.searchTicketByPhone(this.keySearch).subscribe(data => {
        if (data == null) {
          this.toastr.warning('Không tìm thấy !', 'Vé Đã Đặt !');
        } else {
          this.bookedTicketList = data.content;
        }
      });
    } else {
      this.bookTicketManagementService.searchTicketByName(this.keySearch).subscribe(data => {
        if (data == null) {
          this.toastr.warning('Không tìm thấy !', 'Vé Đã Đặt !');
        } else {
          this.bookedTicketList = data.content;
        }
      });
    }
  }

  cancelSuccess() {
    this.indexPagination = 1;
    this.getBookTicketList();
  }


  firstPage() {
    this.indexPagination = 1;
    this.getBookTicketList();
    if (this.indexPagination < this.totalPagination) {
      this.toastr.warning('Quá số trang tìm kiếm !', 'Vé Đã Đặt !');
    }
  }

  previousPage() {
    this.indexPagination = this.indexPagination - 1;
    if (this.indexPagination < this.totalPagination) {
      // this.toastr.warning('Quá số trang tìm kiếm !', 'Vé Đã Đặt !');
    }
    if (this.indexPagination === 0) {
      this.indexPagination = 1;
      this.getBookTicketList();
    } else {
      this.bookTicketManagementService.getAllBookedTicketList((this.indexPagination * 5) - 5).subscribe(data => {
        this.bookedTicketList = data;
      });
    }
  }

  nextPage() {
    this.indexPagination = this.indexPagination + 1;
    if (this.indexPagination > this.totalPagination) {
      this.indexPagination = this.indexPagination - 1;
      this.toastr.warning('Quá số trang tìm kiếm !', 'Vé Đã Đặt !');
    }
    this.bookTicketManagementService.getAllBookedTicketList((this.indexPagination * 5) - 5).subscribe(data => {
      this.bookedTicketList = data;
      if (data == null) {
        this.toastr.warning('Quá số trang tìm kiếm !', 'Vé Đã Đặt !');
      }
    });
  }

  lastPage() {
    if (this.indexPagination < this.totalPagination) {
      this.indexPagination = (Math.floor(this.bookedTicketListNoPage.length / 5)) + 1;
      this.bookTicketManagementService.getAllBookedTicketList((this.indexPagination * 5) - 5).subscribe(data => {
        this.bookedTicketList = data;
        if (data == null) {
          this.toastr.warning('Quá số trang tìm kiếm !', 'Vé Đã Đặt !');
        }
      });
    } else {
      this.firstPage();
      this.toastr.warning('Quá số trang tìm kiếm !', 'Vé Đã Đặt !');
    }
  }

  selectPage(selectPageIndex: number) {
    if (selectPageIndex <= this.totalPagination && selectPageIndex > 0) {
      this.indexPagination = selectPageIndex;
      this.bookTicketManagementService.getAllBookedTicketList((selectPageIndex * 5) - 5).subscribe(data => {
        this.bookedTicketList = data;
        if (data == null) {
          this.toastr.warning('Không có dữ liệu !', 'Vé Đã Đặt !');
          this.firstPage();
        }
      });
    } else {
      this.toastr.warning('Quá số trang tìm kiếm !', 'Vé Đã Đặt !');
    }
  }

}
