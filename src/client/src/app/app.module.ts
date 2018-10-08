import { BrowserModule }            from '@angular/platform-browser';
import { NgModule }                 from '@angular/core';
import { HttpClientModule }         from '@angular/common/http';
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



@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    MenuComponent,
    FooterComponent,
    TwoPageComponent,
    OnePageComponent,
    CardPageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule

  ],
  providers: [
  APIService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
