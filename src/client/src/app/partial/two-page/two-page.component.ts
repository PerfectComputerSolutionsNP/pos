import {Component, Input, OnInit} from '@angular/core';

const max   = 12;

@Component({
  selector: 'app-two-page',
  templateUrl: './two-page.component.html',
  styleUrls: ['./two-page.component.css']
})
export class TwoPageComponent implements OnInit {

  @Input() inverse : boolean;
  @Input() left    : number;
  @Input() right   : number;

  constructor() { }

  ngOnInit() {

    if (this.left < 1)
      throw new Error("Attribute 'left' must be at least 1");

    if (this.right < 1)
      throw new Error("Attribute 'right' must be at least 1");

    if (this.left + this.right !== max)
      throw new Error(`Attributes 'left' and 'right' must add up to ${max}`);

  }

}
