import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {RoomManagementService} from '../../../../service/admin/room-management.service';
import {ActivatedRoute} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-room-delete',
  templateUrl: './room-delete.component.html',
  styleUrls: ['./room-delete.component.scss']
})
export class RoomDeleteComponent implements OnInit {

  @Input()
  deleteId: number;
  @Input()
  deleteName: string;

  @Output()
  deleteComplete = new EventEmitter<boolean>();

  constructor(public roomManagementService: RoomManagementService,
              private active: ActivatedRoute,
              private toast: ToastrService) {
  }

  ngOnInit(): void {
  }

  deleteRoom() {
    this.roomManagementService.deleteRoom(this.deleteId).subscribe(data => {
      document.getElementById('closeModal').click();
      this.deleteComplete.emit(true);
    });
    this.toast.success('Xóa Thành Công !', 'Phòng chiếu !');
  }
}
