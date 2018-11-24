import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import {Page} from '../model/page.model';

@Injectable()
export class ApiService {

  constructor(private http: HttpClient) {
  }

  async httpGet<T>(url : string, id : number) {

    return this.http.get<T>(`${url}/id`).toPromise();
  }

  async httpGetAll(url : string, page : number, size : number) : Promise<Page> {

    return this.http.get<Page>(`${url}?page=${page}&size=${size}`).toPromise()
  }

  async httpPost(url : string, body : any) {

    return this.http.post(url, body).toPromise();
  }

  async httpPut(url : string, id : number, body : any) {

    return this.http.put(`${url}/${id}`, body).toPromise();
  }

  async httpDelete(url : string, id : number) {

    return this.http.delete(`${url}/${id}`).toPromise();
  }

}

