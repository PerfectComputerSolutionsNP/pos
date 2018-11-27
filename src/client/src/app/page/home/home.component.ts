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
import {Customer} from '../../model/customer.model';
import {CustomerSearchComponent} from './customer-search/customer-search.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent implements OnInit {

  customer    : Customer;

  pickupDateString : string = new Date().toDateString();

  pickupLater : boolean = false;
  items       : Map<number, Item> = new Map<number, Item>();
  products    : Page   = new Page;
  subtotal    : number = 0;
  total       : number = 0;
  tax         : number = 0;
  taxRate     : number = 8.75;
  tender      : number = 0;
  change      : number = 0;

  dateSetting = {
    timePicker: true,
    format: 'MMM, dd yyyy hh:mm a'
  };

  paymentOptions  = [
    {
      id : 1,
      name : "cash"
    },
    {
      id : 2,
      name : "credit"
    },
    {
      id : 3,
      name : "debit"
    },
    {
      id : 4,
      name : "purchase-order"
    }
  ];

  paymentType = this.paymentOptions[0];

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

    transaction.tender     = this.tender;
    transaction.taxRate    = this.taxRate;
    transaction.items      = this.getItems();

    if (this.pickupLater)
      transaction.pickupTime = new Date(this.pickupDateString);

    if (this.customer) {
      transaction.customer       = this.customer;
      transaction.notifyCustomer = true;
    }

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

  computeSubtotalDollars() {

    let sum : number = 0;

    this.items.forEach((item, key) => {
      sum += item.getUntaxedDollars()
    });

    this.subtotal = sum;
  }

  computeTotalDollars() {

    let sum : number = 0;

    this.items.forEach((item, id) => sum+= item.getTaxedDollars(this.taxRate));

   this.total = sum;
  }

  computeTax() {

    let tax : number = 0;

    this.items.forEach(item => {

      if (item.product.taxed)
        tax += item.getTax(this.taxRate);
    });

    this.tax = tax;
  }

  computeChange() {

    this.change = this.paymentType.name === 'cash' ?
      this.tender - this.total :
      this.total;
  }

  computeAll() {

    this.computeSubtotalDollars();
    this.computeTotalDollars();
    this.computeTax();
    this.computeChange();
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

    this.computeAll();
  }

  getProductWeight() {

    let weight = parseInt(prompt("Enter the weight of the product"));

    if (weight < 0 || isNaN(weight) || !isFinite(weight))
      throw new Error("Weight must be positive number");

    return weight;
  }

  removeItem(id : number) {

    this.items.delete(id);
    this.computeAll();
  }

  reset() {

    this.items.clear();
    this.computeAll();
  }

  formValid() {

    return this.items.size < 1;
  }

  setCustomer() {

    let modalRef = this.modalService.open(CustomerSearchComponent);

    modalRef.componentInstance.eventEmitter.subscribe( (customer : Customer) => {

      if (customer)
        this.customer = customer;

      modalRef.close();
    });

  }

  removeCustomer() {

    this.customer = null;
  }
}
