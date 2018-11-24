import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompleteTransactionComponent } from './complete-transaction.component';

describe('CompleteTransactionComponent', () => {
  let component: CompleteTransactionComponent;
  let fixture: ComponentFixture<CompleteTransactionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompleteTransactionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompleteTransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
