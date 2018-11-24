import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product}             from '../../../model/product.model';
import {NgbActiveModal}      from '@ng-bootstrap/ng-bootstrap';
import {HttpClient} from '@angular/common/http';
import {config} from '../../../service/config.service';
import {ApiService} from '../../../service/api.service';
import {UtilityService} from '../../../service/utility.service';

@Component({
  selector: 'app-product-creation-form',
  templateUrl: './product-creation-form.component.html',
  styleUrls: ['./product-creation-form.component.css']
})
export class ProductCreationFormComponent extends Product implements OnInit {

  @Input()  public product:      Product           = new Product();
  @Output() public eventEmitter: EventEmitter<any> = new EventEmitter();

  constructor(private http: HttpClient, private api: ApiService) {
    super();
  }

  ngOnInit() {

    if (this.product) {
      this.id          = this.product.id;
      this.name        = this.product.name;
      this.cents       = this.product.cents;
      this.taxed       = this.product.taxed;
      this.weighted    = this.product.weighted;
      this.category    = this.product.category;
      this.description = this.product.description;
    }
  }

  submit() {

    let payload = this.clone(this);

    const obs = this.id ?
      this.http.put(`${config.api.endpoint.product}/${this.id}`, payload) :
      this.http.post(config.api.endpoint.product, payload);

    obs.subscribe((response) => {

      this.eventEmitter.emit("submit");

    }, UtilityService.logError);
  }

  close() {

    this.eventEmitter.emit(null);
  }

  remove() {

    // TODO - Prompt yes or no

    let obj = this.api.httpDelete(`${config.api.endpoint.product}`, this.id)
      .then((response) => {

        this.eventEmitter.emit("remove");
      })
      .catch(UtilityService.logError);
  }

}
