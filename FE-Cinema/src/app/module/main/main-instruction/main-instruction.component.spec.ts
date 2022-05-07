import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainInstructionComponent } from './main-instruction.component';

describe('MainInstructionComponent', () => {
  let component: MainInstructionComponent;
  let fixture: ComponentFixture<MainInstructionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainInstructionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainInstructionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
