import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-alert-dialog',
  templateUrl: './alert-dialog.component.html',
  styleUrls: ['./alert-dialog.component.scss']
})
export class AlertDialogComponent implements OnInit {

  @Input()  headingText     : string;
  @Input()  messageText     : string;
  @Input()  closeButtonText : string;
  @Output() eventEmitter    : EventEmitter<any> = new EventEmitter();

  constructor(private activeModal: NgbActiveModal) { }

  ngOnInit() {

    if (!this.headingText)
      this.headingText = "";

    if (!this.messageText)
      this.messageText = "";

    if (!this.closeButtonText)
      this.closeButtonText = "Okay"
  }

  close() {

    this.activeModal.close();
    this.eventEmitter.emit("close");
  }

}
