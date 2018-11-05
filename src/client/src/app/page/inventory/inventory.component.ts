import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {config} from '../../service/config.service';
import {Page} from '../../model/page.model';


@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.scss']
})



export class InventoryComponent implements OnInit {

  constructor(private http: HttpClient) { }

  p: Page = new Page;
  p1: Page = new Page;
  name: string;
  getCategory() {

     this.http.get<Page>(config.api.endpoint.category + '/?page=0&size=150')
      .subscribe(data => this.p = data );
  }

  getProduct() {

    this.http.get<Page>(config.api.endpoint.product + '/?page=0&size=100')
      .subscribe(data => this.p1 = data );

  }
  ngOnInit() {
    this.getCategory();
    this.getProduct();

  }

}
