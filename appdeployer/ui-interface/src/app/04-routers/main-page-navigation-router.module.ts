import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {
    path: 'directory/overview',
    loadComponent: () => import('../00-pages/01-directory-overview/directory-overview.component').then(m => m.DirectoryOverviewComponent)
  },
  {
    path: '',
    loadComponent: () => import('../00-pages/01-directory-overview/directory-overview.component').then(m => m.DirectoryOverviewComponent),
    children: [
      {
        path: 'directory/create',
        loadComponent: () => import('../00-pages/01-directory-overview/01-directory-create/directory-create.component').then(c => c['DirectoryCreateComponent'])
      },
      {
        path: 'file/create',
        loadComponent: () => import('../00-pages/01-directory-overview/02-file-upload/file-upload.component').then(c => c['FileUploadComponent'])
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    onSameUrlNavigation: 'reload',
    bindToComponentInputs: true
  })],
  exports: [RouterModule]
})
export class MainPageNavigationRouterModule {
}
