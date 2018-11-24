import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {config} from '../../service/config.service';
import {Page} from '../../model/page.model';
import {ApiService} from '../../service/api.service';
import {UtilityService} from '../../service/utility.service';
import {User} from '../../model/user.model';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ProductCreationFormComponent} from '../inventory/product-creation-form/product-creation-form.component';
import {UserRegistrationComponent} from './user-registration/user-registration.component';
import {s} from '@angular/core/src/render3';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  // TODO - Implement dynamic pagination

  constructor(private modalService: NgbModal, private http: HttpClient, private api : ApiService) { }

  users : Page = new Page;

  getUsers() {

    this.api.httpGetAll(config.api.endpoint.users, 0, 10)
        .then(data => this.users = data)
        .catch(UtilityService.logError);
  }

  ngOnInit() {

    this.getUsers();
  }

  showUserForm(user : User) {

    let modalRef = this.modalService.open(UserRegistrationComponent);

    modalRef.componentInstance.customer = user;
  }

  formatAuthorityString(user : User) : string {

    let authorities = [] as Array<string>;

    user.authorities.forEach(auth => authorities.push(auth.name));

    return authorities.toString()
  }

}
