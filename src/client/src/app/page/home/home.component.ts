import { Component, OnInit } from '@angular/core';
import {config} from '../../service/config.service';
import {Page} from '../../model/page.model';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  constructor(private http: HttpClient) { }

  p : Page = new Page;
  name : string;
  getCategory() {

    this.http.get<Page>(config.api.endpoint.category + '?page=1&size=150')
      .subscribe(data => this.p = data );
  }


  ngOnInit() {
    this.getCategory();

  }

}
