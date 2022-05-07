import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketCancelComponent } from './ticket-cancel.component';

describe('TicketCancelComponent', () => {
  let component: TicketCancelComponent;
  let fixture: ComponentFixture<TicketCancelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TicketCancelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketCancelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
