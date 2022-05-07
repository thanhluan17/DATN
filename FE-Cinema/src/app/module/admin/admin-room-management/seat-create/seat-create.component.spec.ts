import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SeatCreateComponent } from './seat-create.component';

describe('SeatCreateComponent', () => {
  let component: SeatCreateComponent;
  let fixture: ComponentFixture<SeatCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SeatCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SeatCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
