import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
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
import { StageAddComponent } from './gestion-pipline/stage-add/stage-add.component';
import { PiplineAddComponent } from './gestion-pipline/pipline-add/pipline-add.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { NbCardModule, NbFormFieldModule, NbInputModule, NbSelectModule, NbButtonModule, NbLayoutModule, NbStepperModule, NbAccordionModule } from '@nebular/theme';
import { BuildTriggerComponent } from './gestion-build/build-trigger/build-trigger.component';


@NgModule({
  declarations: [
    WelcomeComponent,
    EditTutorialComponent,
    AddTutoComponent,
    ProjectMamagementComponent,
    ProjectAddComponent,
    ProjectEditComponent,
    StageAddComponent,
    PiplineAddComponent,
    BuildTriggerComponent,
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
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    NbSelectModule,
    NbFormFieldModule,
    NbInputModule,

    
    

  ]
})
export class DashboardCiGrp2Module { 


}
