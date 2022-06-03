import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MemberManagementService} from '../../../../service/employee/member-management.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-delete-user',
  templateUrl: './delete-user.component.html',
  styleUrls: ['./delete-user.component.scss']
})
export class DeleteUserComponent implements OnInit {

  @Input()
  deleteName: string;

  @Input()
  deleteId: number;

  user: any;

  @Output()
  deleteComplete = new EventEmitter<boolean>();


  constructor(private memberService: MemberManagementService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  deletePatient() {
    this.memberService.deleteUser(this.deleteId).subscribe(data => {
      document.getElementById('closeModal').click();
      this.deleteComplete.emit(true);
    });
  }
}
