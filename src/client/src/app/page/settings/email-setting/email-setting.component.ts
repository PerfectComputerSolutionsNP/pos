import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-email-setting',
  templateUrl: './email-setting.component.html',
  styleUrls: ['./email-setting.component.scss']
})
export class EmailSettingComponent implements OnInit {

  email    : string;
  password : string;

  constructor() { }

  ngOnInit() {
  }

  getEmail() {

    // TODO - HTTP GET the current email being used by the back-end. This feature must be implemented on back-end first
  }

  updateEmail() {

    // TODO - HTTP POST the new credentials. This feature must be implemented on back-end first
  }
}
