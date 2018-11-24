import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductCreationFormComponent } from './product-creation-form.component';

describe('ProductCreationFormComponent', () => {
  let component: ProductCreationFormComponent;
  let fixture: ComponentFixture<ProductCreationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductCreationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductCreationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
