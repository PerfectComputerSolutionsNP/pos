import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {UtilityService} from '../../service/utility.service';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})
export class SearchBarComponent<T> implements OnInit {

  @Input()  url          : string;
  @Input()  paramName    : string;
  @Input()  placeholder  : string;
  @Output() eventEmitter : EventEmitter<Array<T>> = new EventEmitter<Array<T>>();

  param : string = "";

  constructor(private http : HttpClient) { }

  ngOnInit() {
  }

  search() {

    // TODO - Do not hard code page and pageSize

    let params = new HttpParams()
      .set(this.paramName, this.param)
      .set("page", "0")
      .set("size", "150");

    this.http.get<any>(`${this.url}`, {params : params})
      .toPromise()
      .then((response : any) => {

        this.eventEmitter.emit(response.content);
      })
      .catch(UtilityService.logError);
  }
}
