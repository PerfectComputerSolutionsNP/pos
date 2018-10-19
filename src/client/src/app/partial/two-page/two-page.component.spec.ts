import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TwoPageComponent } from './two-page.component';

describe('TwoPageComponent', () => {
  let component: TwoPageComponent;
  let fixture: ComponentFixture<TwoPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TwoPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TwoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
