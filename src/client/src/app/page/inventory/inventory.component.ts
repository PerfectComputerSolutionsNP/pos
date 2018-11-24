import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {config} from '../../service/config.service';
import {Page} from '../../model/page.model';
import {Product} from '../../model/product.model';
import {Category} from '../../model/category.model';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ProductCreationFormComponent} from './product-creation-form/product-creation-form.component';
import {CategoryCreationFormComponent} from './category-creation-form/category-creation-form.component';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.scss']
})

export class InventoryComponent implements OnInit {

  // https://ng-bootstrap.github.io/#/components/modal/examples
  // https://getbootstrap.com/docs/4.0/components/forms/
  // https://itnext.io/creating-forms-inside-modals-with-ng-bootstrap-221e4f1f5648
  // https://medium.com/@izzatnadiri/how-to-pass-data-to-and-receive-from-ng-bootstrap-modals-916f2ad5d66e

  // TODO - Implement dynamic pagination

  constructor(private http: HttpClient, private modalService: NgbModal) {}

  categories: Page = new Page;
  products:   Page = new Page;

  getCategories() {

     this.http.get<Page>(config.api.endpoint.category + '?page=0&size=150')
      .subscribe(data => this.categories = data);
  }

  getProducts() {

    this.http.get<Page>(config.api.endpoint.product + '?page=0&size=100')
      .subscribe(data => this.products = data);
  }

  showCategoryForm(category: Category) {

    let modalRef = this.modalService.open(CategoryCreationFormComponent);

    modalRef.componentInstance.category = category;
    modalRef.componentInstance.eventEmitter.subscribe((result) => {

      this.getCategories();

      modalRef.close();
    });
  }

  showProductForm(product: Product) {

    let modalRef = this.modalService.open(ProductCreationFormComponent);

    modalRef.componentInstance.product = product;
    modalRef.componentInstance.eventEmitter.subscribe((result) => {

      if (result)
        this.getProducts();

      modalRef.close();
    });
  }

  ngOnInit() {
    this.getCategories();
    this.getProducts();
  }

}
