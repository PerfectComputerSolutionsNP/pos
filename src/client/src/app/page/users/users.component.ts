import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {config} from '../../service/config.service';
import {Page} from '../../model/page.model';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {



  constructor(private http: HttpClient) { }

  p : Page = new Page;
  name : string;
  getCategory() {

    this.http.get<Page>(config.api.endpoint.users + '?page=0&size=5')
      .subscribe(data => this.p = data );
  }


  ngOnInit() {
    this.getCategory();

  }


}
