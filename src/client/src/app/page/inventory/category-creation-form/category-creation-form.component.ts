import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Category} from '../../../model/category.model';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {HttpClient} from '@angular/common/http';
import {config} from '../../../service/config.service';
import {ApiService} from '../../../service/api.service';
import {UtilityService} from '../../../service/utility.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-category-creation-form',
  templateUrl: './category-creation-form.component.html',
  styleUrls: ['./category-creation-form.component.scss']
})
export class CategoryCreationFormComponent extends Category implements OnInit {

  @Input()  public category:     Category;
  @Output() public eventEmitter: EventEmitter<any> = new EventEmitter();

  constructor(
    private http    : HttpClient,
    private api     : ApiService,
    private utility : UtilityService,
    private toastr  : ToastrService) {
    super();
  }

  ngOnInit() {

    if (this.category) {

      this.id   = this.category.id;
      this.name = this.category.name;
    }
  }

  submit() {

    let payload = this.clone(this);

    const obs = this.id ?
      this.http.put(`${config.api.endpoint.category}/${this.id}`, payload) :
      this.http.post(config.api.endpoint.category, payload);

    obs.subscribe((response) => {

      this.eventEmitter.emit(response);

    }, (error) => {

      console.error(error);
    });
  }

  deleteCategory(id : number) {

    this.api.httpDelete(config.api.endpoint.category, id)
      .then(result => this.close(id))
      .catch((error) => {
        this.utility.alertError(error)
      })
  }

  close(id : number) {

    if (id)
      this.toastr.success(`Category successfully deleted`, "Success!");

    this.eventEmitter.emit(null);
  }
}
