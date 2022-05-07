import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SeatEditComponent } from './seat-edit.component';

describe('SeatEditComponent', () => {
  let component: SeatEditComponent;
  let fixture: ComponentFixture<SeatEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SeatEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SeatEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
