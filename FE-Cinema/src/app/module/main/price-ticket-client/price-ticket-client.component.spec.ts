import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PriceTicketClientComponent } from './price-ticket-client.component';

describe('PriceTicketClientComponent', () => {
  let component: PriceTicketClientComponent;
  let fixture: ComponentFixture<PriceTicketClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PriceTicketClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PriceTicketClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
