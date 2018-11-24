import { Component, OnInit } from '@angular/core';
import {Page} from '../../model/page.model';
import {HttpClient} from '@angular/common/http';
import {ApiService} from '../../service/api.service';
import {UtilityService} from '../../service/utility.service';
import {config} from '../../service/config.service';
import {Transaction} from '../../model/transaction.model';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.scss']
})
export class SalesComponent implements OnInit {

  transactions       : Page = new Page;
  currentTransaction : Transaction;

  constructor(private http: HttpClient, private api : ApiService) { }

  getTransactions() {
    this.api.httpGetAll(config.api.endpoint.transaction, 0, 10)
      .then(data => {this.transactions = data})
      .catch(UtilityService.logError);
  }

  ngOnInit() {
    this.getTransactions();
  }

  selectTransaction(transaction : Transaction) {

    this.currentTransaction = transaction;
  }

  printTransaction() {

    let title = document.title;

    document.title = `transaction-${this.currentTransaction.id}`;

    window.print();

    document.title = title;
  }

}
