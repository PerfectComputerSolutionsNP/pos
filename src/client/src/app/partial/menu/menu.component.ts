import { Component, OnInit } from '@angular/core';
import {AuthGuard} from "../../guard/auth.guard";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  constructor(public authGuard : AuthGuard) { }

  ngOnInit() {
  }

}
