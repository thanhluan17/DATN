import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditMovieTicketComponent } from './edit-movie-ticket.component';

describe('EditMovieTicketComponent', () => {
  let component: EditMovieTicketComponent;
  let fixture: ComponentFixture<EditMovieTicketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditMovieTicketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditMovieTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
