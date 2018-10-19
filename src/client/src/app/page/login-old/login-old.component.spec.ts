import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginOldComponent } from './login-old.component';

describe('LoginOldComponent', () => {
  let component: LoginOldComponent;
  let fixture: ComponentFixture<LoginOldComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginOldComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginOldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
