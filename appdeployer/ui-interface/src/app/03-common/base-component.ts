import {inject, Renderer2} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";

export class BaseComponent {
  protected render2: Renderer2 = inject(Renderer2);
  protected router: Router = inject(Router);
  protected activatedRouter = inject(ActivatedRoute);
  protected location = inject(Location);
}
