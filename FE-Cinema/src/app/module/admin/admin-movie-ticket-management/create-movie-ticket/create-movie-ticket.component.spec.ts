import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMovieTicketComponent } from './create-movie-ticket.component';

describe('CreateMovieTicketComponent', () => {
  let component: CreateMovieTicketComponent;
  let fixture: ComponentFixture<CreateMovieTicketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateMovieTicketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateMovieTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
