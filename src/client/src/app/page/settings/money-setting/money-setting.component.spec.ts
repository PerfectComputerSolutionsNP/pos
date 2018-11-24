import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoneySettingComponent } from './money-setting.component';

describe('MoneySettingComponent', () => {
  let component: MoneySettingComponent;
  let fixture: ComponentFixture<MoneySettingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoneySettingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoneySettingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
