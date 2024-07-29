import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import { PagesComponent } from './pages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ECommerceComponent } from './e-commerce/e-commerce.component';
import { NotFoundComponent } from './miscellaneous/not-found/not-found.component';
import { WelcomeComponent } from './ci-devops-group2/dashboard-ci-grp2/welcome/welcome.component';
import { EditTutorialComponent } from './ci-devops-group2/dashboard-ci-grp2/edit-tutorial/edit-tutorial.component';
import { AddTutoComponent } from './ci-devops-group2/dashboard-ci-grp2/add-tuto/add-tuto.component';
import { ProjectMamagementComponent } from './ci-devops-group2/dashboard-ci-grp2/project-mamagement/project-mamagement.component';
import { ProjectAddComponent } from './ci-devops-group2/dashboard-ci-grp2/project-mamagement/project-add/project-add.component';
import { ProjectEditComponent } from './ci-devops-group2/dashboard-ci-grp2/project-mamagement/project-edit/project-edit.component';
import { StageAddComponent } from './ci-devops-group2/dashboard-ci-grp2/gestion-pipline/stage-add/stage-add.component';
import { PiplineAddComponent } from './ci-devops-group2/dashboard-ci-grp2/gestion-pipline/pipline-add/pipline-add.component';

const routes: Routes = [{
  path: '',
  component: PagesComponent,
  children: [
    {
      path: 'dashboard',
      component: ECommerceComponent,
    },
    {
      path: 'iot-dashboard',
      component: DashboardComponent,
    },
    {
      path: 'layout',
      loadChildren: () => import('./layout/layout.module')
        .then(m => m.LayoutModule),
    },
    {
      path: 'forms',
      loadChildren: () => import('./forms/forms.module')
        .then(m => m.FormsModule),
    },
    {
      path: 'ui-features',
      loadChildren: () => import('./ui-features/ui-features.module')
        .then(m => m.UiFeaturesModule),
    },
    {
      path: 'modal-overlays',
      loadChildren: () => import('./modal-overlays/modal-overlays.module')
        .then(m => m.ModalOverlaysModule),
    },
    {
      path: 'extra-components',
      loadChildren: () => import('./extra-components/extra-components.module')
        .then(m => m.ExtraComponentsModule),
    },
    {
      path: 'maps',
      loadChildren: () => import('./maps/maps.module')
        .then(m => m.MapsModule),
    },
    {
      path: 'charts',
      loadChildren: () => import('./charts/charts.module')
        .then(m => m.ChartsModule),
    },
    {
      path: 'editors',
      loadChildren: () => import('./editors/editors.module')
        .then(m => m.EditorsModule),
    },
    {
      path: 'tables',
      loadChildren: () => import('./tables/tables.module')
        .then(m => m.TablesModule),
    },
    {
      path: 'miscellaneous',
      loadChildren: () => import('./miscellaneous/miscellaneous.module')
        .then(m => m.MiscellaneousModule),
    },
      {
      path: 'agile/ci-devops-group2', // Make sure this matches your existing structure
      children: [
        { path: 'project-management/edit-project/:id', component: ProjectEditComponent },
        { path: 'add-tuto', component: AddTutoComponent },
        { path: 'project-management', component: ProjectMamagementComponent },
        { path: 'project-management/add-project', component: ProjectAddComponent },
       
        { path: 'admin/add-stage', component: StageAddComponent }, // New path for StageAddComponent
        { path: 'admin/add-pipline', component: PiplineAddComponent }, // New path for StageAddComponent

        { path: 'dashboard', component: WelcomeComponent }, // Define the route for adding tutorial here
        { path: 'edit-tutorial/:id', component: EditTutorialComponent, },
        { path: '', component: WelcomeComponent }, // Assuming 'WelcomeComponent' is the default path
        { path: '**', component: NotFoundComponent }, // Handle 404 errors


      ]
    },


   
    {
      path: '',
      redirectTo: 'dashboard',
      pathMatch: 'full',
    },
    {
      path: '**',
      component: NotFoundComponent,
    },
    
  ],
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagesRoutingModule {
}
