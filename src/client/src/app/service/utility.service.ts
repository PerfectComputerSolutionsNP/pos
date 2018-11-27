import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AlertDialogComponent} from '../partial/alert-dialog/alert-dialog.component';
import {Injectable} from '@angular/core';

@Injectable()
export class UtilityService {

  constructor(private modalService : NgbModal) { }

  static logError(error : any) {

    console.error(error)
  }

  alertError(error : any) {

    // TODO - This is a development feature!!!

    let modalRef = this.modalService.open(AlertDialogComponent);

    modalRef.componentInstance.headingText     = error.error.exception;
    modalRef.componentInstance.messageText     = error.error.message;
    modalRef.componentInstance.closeButtonText = "Dismiss";

    modalRef.componentInstance.eventEmitter.subscribe(result => {

      console.log(error);
    })
  }

}
