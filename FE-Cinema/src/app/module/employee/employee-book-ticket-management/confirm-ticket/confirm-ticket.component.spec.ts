import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmTicketComponent } from './confirm-ticket.component';

describe('ConfirmTicketComponent', () => {
  let component: ConfirmTicketComponent;
  let fixture: ComponentFixture<ConfirmTicketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmTicketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
