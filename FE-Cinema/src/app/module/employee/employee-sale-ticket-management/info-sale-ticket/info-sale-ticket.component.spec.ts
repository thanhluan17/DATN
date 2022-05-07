import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoSaleTicketComponent } from './info-sale-ticket.component';

describe('InfoSaleTicketComponent', () => {
  let component: InfoSaleTicketComponent;
  let fixture: ComponentFixture<InfoSaleTicketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfoSaleTicketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoSaleTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
