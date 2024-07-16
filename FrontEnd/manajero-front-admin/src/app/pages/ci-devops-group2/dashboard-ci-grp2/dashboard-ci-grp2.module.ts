import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NbLayoutModule, NbCardModule, NbStepperModule, NbAccordionModule, NbButtonModule } from '@nebular/theme'; // Import Nebular modules
import { FormsModule } from '@angular/forms';
import { DashboardCiGrp2RoutingModule } from './dashboard-ci-grp2-routing.module';
import { WelcomeComponent } from './welcome/welcome.component';
import { EditTutorialComponent } from './edit-tutorial/edit-tutorial.component';
import { QuillModule } from 'ngx-quill';



@NgModule({
  declarations: [
    WelcomeComponent,
    EditTutorialComponent,
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

  ]
})
export class DashboardCiGrp2Module { 


}
