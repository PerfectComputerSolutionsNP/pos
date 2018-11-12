import { BrowserModule }            from '@angular/platform-browser';
import { NgModule }                 from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { APIService }               from "./api.service";
import { AppComponent }             from './app.component';
import { FormsModule }              from "@angular/forms";
import { AppRoutingModule}          from "./app-routing.module";
import { routingComponents}         from "./app-routing.module";
import { MenuComponent }            from './partial/menu/menu.component';
import { FooterComponent }          from './partial/footer/footer.component';
import { TwoPageComponent } from './partial/two-page/two-page.component';
import { OnePageComponent } from './partial/one-page/one-page.component';
import { CardPageComponent } from './partial/card-page/card-page.component';
import { ReactiveFormsModule } from "@angular/forms";
import {AuthGuard} from "./guard/auth.guard";
import {AuthenticationService} from "./service/authentication.service";
import {JwtInterceptor} from "./service/jwt.interceptor";
import { CategoryCreationFormComponent } from './page/category-creation-form/category-creation-form.component';
import { ProductCreationFormComponent } from './page/product-creation-form/product-creation-form.component';
import { ModalComponent } from './partial/modal/modal.component';


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
    ModalComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule

  ],
  providers: [
    APIService,
    AuthGuard,
    AuthenticationService,
    {
      provide  : HTTP_INTERCEPTORS,
      useClass : JwtInterceptor,
      multi    : true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
