import {Component, OnInit} from '@angular/core';
import {Room} from '../../../../entity/room';
import {RoomManagementService} from '../../../../service/admin/room-management.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.scss']
})
export class RoomListComponent implements OnInit {

  roomList: Room[] = [];
  deleteId: number;
  deleteName: string;
  roomName = '';
  listRoomPagination: Room[] = [];
  selectPagination: number;
  page = 0;
  totalPage: number;
  numberSeat: number;

  constructor(private roomManagement: RoomManagementService,
              private toast: ToastrService) {
  }

  ngOnInit(): void {
    this.getData();
  }

  getData() {
    this.roomManagement.getAllRoom(this.page, this.roomName).subscribe(data => {
      this.roomList = data['content'];
      this.totalPage = data['totalPages'];
      if (this.roomList === null) {
        this.toast.warning('Thông tin dữ liệu hiện không có trong hệ thống', 'Thông báo');
      }
    });
  }

  deleteSuccess() {
    this.ngOnInit();
  }

  getSearch() {
    if (this.roomName !== '') {
      this.ngOnInit();
    } else {
      this.toast.warning('Bạn chưa nhập tên phòng', 'Thông báo', {timeOut: 2000});
    }
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
