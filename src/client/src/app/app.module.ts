import { BrowserModule }            from '@angular/platform-browser';
import { NgModule }                 from '@angular/core';
import { HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { ApiService }               from "./service/api.service";
import { AppComponent }             from './app.component';
import { FormsModule }              from "@angular/forms";
import { AppRoutingModule}          from "./app-routing.module";
import { routingComponents}         from "./app-routing.module";
import { MenuComponent }            from './partial/menu/menu.component';
import { FooterComponent }          from './partial/footer/footer.component';
import { TwoPageComponent }         from './partial/two-page/two-page.component';
import { OnePageComponent }         from './partial/one-page/one-page.component';
import { CardPageComponent }        from './partial/card-page/card-page.component';
import { ReactiveFormsModule }      from "@angular/forms";
import { AuthGuard}                        from "./guard/auth.guard";
import { AuthenticationService}            from "./service/authentication.service";
import { JwtInterceptor}                   from "./service/jwt.interceptor";
import { CategoryCreationFormComponent }   from './page/inventory/category-creation-form/category-creation-form.component';
import { ProductCreationFormComponent }    from './page/inventory/product-creation-form/product-creation-form.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { EmailSettingComponent } from './page/settings/email-setting/email-setting.component';
import { MoneySettingComponent } from './page/settings/money-setting/money-setting.component';
import { CustomersComponent } from './page/customers/customers.component';
import { CustomerCreationFormComponent } from './page/customers/customer-creation-form/customer-creation-form.component';
import { CompleteTransactionComponent } from './page/home/complete-transaction/complete-transaction.component';
import {AngularDateTimePickerModule} from 'angular2-datetimepicker';
import { AlertDialogComponent } from './partial/alert-dialog/alert-dialog.component';
import { SearchBarComponent } from './partial/search-bar/search-bar.component';
import { CustomerSearchComponent } from './page/home/customer-search/customer-search.component';
import {UtilityService} from './service/utility.service';
import {ToastrModule} from 'ngx-toastr';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    MenuComponent,
    FooterComponent,
    TwoPageComponent,
    OnePageComponent,
    CardPageComponent,
    CategoryCreationFormComponent,
    ProductCreationFormComponent,
    EmailSettingComponent,
    MoneySettingComponent,
    CustomersComponent,
    CustomerCreationFormComponent,
    CompleteTransactionComponent,
    AlertDialogComponent,
    SearchBarComponent,
    CustomerSearchComponent
  ],
  imports: [
    NgbModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    AngularDateTimePickerModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [
    ApiService,
    AuthGuard,
    AuthenticationService,
    UtilityService,
    {
      provide  : HTTP_INTERCEPTORS,
      useClass : JwtInterceptor,
      multi    : true
    }
  ],

  entryComponents : [
    CategoryCreationFormComponent,
    ProductCreationFormComponent,
    CustomerCreationFormComponent,
    CompleteTransactionComponent,
    AlertDialogComponent,
    CustomerSearchComponent
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
