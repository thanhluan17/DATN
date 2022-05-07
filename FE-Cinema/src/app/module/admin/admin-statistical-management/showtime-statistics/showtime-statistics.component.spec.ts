import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowtimeStatisticsComponent } from './showtime-statistics.component';

describe('ShowtimeStatisticsComponent', () => {
  let component: ShowtimeStatisticsComponent;
  let fixture: ComponentFixture<ShowtimeStatisticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowtimeStatisticsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowtimeStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
