import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieCornerComponent } from './movie-corner.component';

describe('MovieCornerComponent', () => {
  let component: MovieCornerComponent;
  let fixture: ComponentFixture<MovieCornerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieCornerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieCornerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
