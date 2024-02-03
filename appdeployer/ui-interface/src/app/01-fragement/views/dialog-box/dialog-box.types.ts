import {BehaviorSubject, Subject} from "rxjs";

export type DialogBoxFunctions = {
  open: Subject<void>,
  setTitle: BehaviorSubject<string>
}
