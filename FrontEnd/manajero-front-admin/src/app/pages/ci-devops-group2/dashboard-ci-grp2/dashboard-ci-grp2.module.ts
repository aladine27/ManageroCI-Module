import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NbLayoutModule, NbCardModule, NbStepperModule, NbAccordionModule, NbButtonModule } from '@nebular/theme'; // Import Nebular modules
import { FormsModule } from '@angular/forms';
import { DashboardCiGrp2RoutingModule } from './dashboard-ci-grp2-routing.module';
import { WelcomeComponent } from './welcome/welcome.component';
import { EditTutorialComponent } from './edit-tutorial/edit-tutorial.component';
import { QuillModule } from 'ngx-quill';

import { AddTutoComponent } from './add-tuto/add-tuto.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ProjectMamagementComponent } from './project-mamagement/project-mamagement.component';
import { ProjectEditComponent } from './project-mamagement/project-edit/project-edit.component';
import { ProjectAddComponent } from './project-mamagement/project-add/project-add.component';


@NgModule({
  declarations: [
    WelcomeComponent,
    EditTutorialComponent,
    AddTutoComponent,
    ProjectMamagementComponent,
    ProjectAddComponent,
    ProjectEditComponent,
  ],
  imports: [
    QuillModule.forRoot(),
    CommonModule,
    DashboardCiGrp2RoutingModule,
    NbLayoutModule,
    NbCardModule,
    NbStepperModule,
    NbAccordionModule,
    NbButtonModule,
     FormsModule,

    ReactiveFormsModule
  ]
})
export class DashboardCiGrp2Module { 


}
