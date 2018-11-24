import {Component, OnInit} from '@angular/core';
import {MoneySettingComponent} from './money-setting/money-setting.component';
import {EmailSettingComponent} from './email-setting/email-setting.component';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent implements OnInit {

  currentSetting : string = "Email";

  settings : Array<string> = [
    "Money",
    "Email"
  ];

  constructor() { }

  ngOnInit() {
  }

}
