import {AfterViewInit, ChangeDetectionStrategy, Component, ElementRef, Input, signal, ViewChild} from '@angular/core';
import {DialogBoxFunctions} from "./dialog-box.types";

@Component({
  selector: 'app-dialog-box',
  standalone: true,
  imports: [],
  templateUrl: './dialog-box.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DialogBoxComponent implements AfterViewInit {

  title = signal('');

  @ViewChild('dialog', {read: ElementRef})
  dialogBoxRef: ElementRef<HTMLDialogElement> | undefined;

  @Input()
  interactor: DialogBoxFunctions | undefined;

  ngAfterViewInit(): void {
    this.interactor?.open.subscribe(() => {
      requestIdleCallback(() => {
        this.dialogBoxRef?.nativeElement.show()
      });
    });
    this.interactor?.setTitle.subscribe(e => this.title.set(e));
  }
}
