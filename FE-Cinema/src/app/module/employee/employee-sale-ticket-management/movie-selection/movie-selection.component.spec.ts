import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieSelectionComponent } from './movie-selection.component';

describe('MovieSelectionComponent', () => {
  let component: MovieSelectionComponent;
  let fixture: ComponentFixture<MovieSelectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieSelectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
