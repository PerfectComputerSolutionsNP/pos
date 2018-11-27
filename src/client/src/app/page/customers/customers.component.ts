import {Component, OnInit, ViewChild} from '@angular/core';
import {Customer} from '../../model/customer.model';
import {Page} from '../../model/page.model';
import {ApiService} from '../../service/api.service';
import {config} from '../../service/config.service';
import {UtilityService} from '../../service/utility.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {CustomerCreationFormComponent} from './customer-creation-form/customer-creation-form.component';
import {SearchBarComponent} from '../../partial/search-bar/search-bar.component';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {

  searchUrl   = `${config.api.endpoint.customer}/search`;
  paramName   = "param";
  placeholder = "Search for customers";

  @ViewChild('searchBar')
  private searchBar : SearchBarComponent<Customer>;

  customers : Page = new Page();

  constructor(private api : ApiService, private modalService: NgbModal, private utility : UtilityService) { }

  ngOnInit() {

    this.getCustomers(null);
  }

  showCustomerForm(customer : Customer) {

    let modalRef = this.modalService.open(CustomerCreationFormComponent);

    modalRef.componentInstance.customer = customer;

    modalRef.componentInstance.eventEmitter.subscribe(result => {

      modalRef.close();

      this.getCustomers(null);
    })
  }

  getAllCustomer() {
    this.api.httpGetAll(config.api.endpoint.customer, 0, 150)
      .then(response => this.customers = response)
      .catch((error) => {
        this.utility.alertError(error);
      })
  }

  getCustomers(event : any) {

    if (!event) {
      this.getAllCustomer();
      return;
    }

    this.customers.content = event;
  }
}
