import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteMovieTicketComponent } from './delete-movie-ticket.component';

describe('DeleteMovieTicketComponent', () => {
  let component: DeleteMovieTicketComponent;
  let fixture: ComponentFixture<DeleteMovieTicketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteMovieTicketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteMovieTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
