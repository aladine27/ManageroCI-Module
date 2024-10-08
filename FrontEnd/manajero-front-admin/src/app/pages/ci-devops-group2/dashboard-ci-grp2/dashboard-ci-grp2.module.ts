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
import { NbCardModule, NbFormFieldModule, NbInputModule, NbSelectModule, NbButtonModule, NbLayoutModule, NbStepperModule, NbAccordionModule, NbSidebarModule, NbIconModule, NbMenuModule, NbListModule, NbToastrModule, NbThemeModule } from '@nebular/theme';
import { BuildTriggerComponent } from './gestion-build/build-trigger/build-trigger.component';
import { ViewDashboardComponent } from './view-dashboard/view-dashboard.component';
import { BuildDetailsComponent } from './gestion-build/build-details/build-details.component';
import { SonarAnalysisComponent } from './gestion-build/sonar-analysis/sonar-analysis.component';
import { StepperBuildComponent } from './gestion-build/stepper-build/stepper-build.component';
import { ProjectDetailsComponent } from './project-mamagement/project-details/project-details.component';
import { ViewKpiComponent } from './KPI-dashboard/view-kpi/view-kpi.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';

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
    ViewDashboardComponent,
    BuildDetailsComponent,
    SonarAnalysisComponent,
    StepperBuildComponent,
    ProjectDetailsComponent,
    ViewKpiComponent,

   
 
    
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
    NbSidebarModule, 
    NbSidebarModule,
    NbIconModule,
    NbMenuModule,
    NbListModule,
      NgxChartsModule,
     
    NbCardModule,
    NbButtonModule,
    NbThemeModule.forRoot({ name: 'default' }),
    NbLayoutModule,

      
    


    
    

  ],
  exports: [
    ProjectEditComponent
  ]
})
export class DashboardCiGrp2Module { 


}
