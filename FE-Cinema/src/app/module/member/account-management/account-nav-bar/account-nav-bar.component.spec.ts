import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountNavBarComponent } from './account-nav-bar.component';

describe('AccountNavBarComponent', () => {
  let component: AccountNavBarComponent;
  let fixture: ComponentFixture<AccountNavBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountNavBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
