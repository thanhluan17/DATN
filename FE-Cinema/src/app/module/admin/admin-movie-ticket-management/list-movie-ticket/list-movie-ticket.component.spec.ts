import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListMovieTicketComponent } from './list-movie-ticket.component';

describe('ListMovieTicketComponent', () => {
  let component: ListMovieTicketComponent;
  let fixture: ComponentFixture<ListMovieTicketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListMovieTicketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListMovieTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
