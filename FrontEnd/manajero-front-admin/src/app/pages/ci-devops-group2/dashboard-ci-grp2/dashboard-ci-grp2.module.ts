import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NbLayoutModule, NbCardModule, NbStepperModule, NbAccordionModule, NbButtonModule } from '@nebular/theme'; // Import Nebular modules

import { DashboardCiGrp2RoutingModule } from './dashboard-ci-grp2-routing.module';
import { WelcomeComponent } from './welcome/welcome.component';
import { AddTutoComponent } from './add-tuto/add-tuto.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    WelcomeComponent,
    AddTutoComponent
  ],
  imports: [
    CommonModule,
    DashboardCiGrp2RoutingModule,
    NbLayoutModule,
    NbCardModule,
    NbStepperModule,
    NbAccordionModule,
    NbButtonModule,
    ReactiveFormsModule
  ]
})
export class DashboardCiGrp2Module { }
