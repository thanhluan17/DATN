import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookTicketListComponent } from './book-ticket-list.component';

describe('BookTicketListComponent', () => {
  let component: BookTicketListComponent;
  let fixture: ComponentFixture<BookTicketListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookTicketListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookTicketListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
