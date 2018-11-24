import { Component, OnInit } from '@angular/core';
import {config} from '../../service/config.service';
import {Page} from '../../model/page.model';
import {HttpClient} from '@angular/common/http';
import {Transaction} from '../../model/transaction.model';
import {Item} from '../../model/item.model';
import {Product} from '../../model/product.model';
import {ApiService} from '../../service/api.service';
import {UtilityService} from '../../service/utility.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {CategoryCreationFormComponent} from '../inventory/category-creation-form/category-creation-form.component';
import {CompleteTransactionComponent} from './complete-transaction/complete-transaction.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent implements OnInit {

  items    : Map<number, Item> = new Map<number, Item>();
  products : Page = new Page;
  subtotal : number = 0;

  constructor(private http: HttpClient, private api : ApiService, private modalService: NgbModal) { }

  getProducts() {

    this.api.httpGetAll(config.api.endpoint.product, 0, 150)
      .then(response => this.products = response)
      .catch(UtilityService.logError)
  }

  ngOnInit() {

    this.getProducts();
  }

  prepareTransaction() : Transaction {

    let transaction = new Transaction();

    transaction.taxRate = 8.75;
    transaction.items   = this.getItems();

    transaction.cost = transaction.items
      .map(item => item.getCost())
      .reduce((total, sum) => total + sum);

    return transaction;
  }

  purchase() {

    let modalRef = this.modalService.open(CompleteTransactionComponent);

    modalRef.componentInstance.transaction = this.prepareTransaction();

    modalRef.componentInstance.eventEmitter.subscribe(result => {

      modalRef.close();
      this.reset();
    })

  }

  getItems() {

    let i = [] as Item[];

    this.items.forEach((val, key) => i.push(val));

    i.sort((a : Item, b : Item) => {

      if (a.product.weighted && b.product.weighted)
        return 0;

      return a.product.weighted ? 1 : -1;
    });

    return i;
  }

  computeCosts() {

    let sum : number = 0;

    this.items.forEach((val, key) => {
      sum += val.getDollars()
    });

    this.subtotal = sum;
  }

  addProduct(product : Product) {

    if (this.items.has(product.id))
      return;

    let item = new Item();

    if (product.weighted)
      item.weight = this.getProductWeight();

    item.quantity = 1;
    item.product  = product;

    this.items.set(product.id, item);

    this.computeCosts();
  }

  getProductWeight() {

    let weight = parseInt(prompt("Enter the weight of the product"));

    if (weight < 0 || isNaN(weight) || !isFinite(weight))
      throw new Error("Weight must be positive number");

    return weight;
  }

  removeItem(id : number) {

    this.items.delete(id);
    this.computeCosts();
  }

  reset() {

    this.items.clear();
    this.computeCosts();
  }

}
