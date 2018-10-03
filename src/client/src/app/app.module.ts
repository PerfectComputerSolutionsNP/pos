import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {APIService} from "./api.service";
import { AppComponent } from './app.component';
import { FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
  APIService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
