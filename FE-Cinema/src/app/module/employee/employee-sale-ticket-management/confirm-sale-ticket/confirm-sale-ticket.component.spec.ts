import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmSaleTicketComponent } from './confirm-sale-ticket.component';

describe('ConfirmSaleTicketComponent', () => {
  let component: ConfirmSaleTicketComponent;
  let fixture: ComponentFixture<ConfirmSaleTicketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmSaleTicketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmSaleTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
