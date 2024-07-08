import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NbLayoutModule, NbCardModule, NbStepperModule, NbAccordionModule, NbButtonModule } from '@nebular/theme'; // Import Nebular modules

import { DashboardCiGrp2RoutingModule } from './dashboard-ci-grp2-routing.module';
import { WelcomeComponent } from './welcome/welcome.component';


@NgModule({
  declarations: [
    WelcomeComponent
  ],
  imports: [
    CommonModule,
    DashboardCiGrp2RoutingModule,
    NbLayoutModule,
    NbCardModule,
    NbStepperModule,
    NbAccordionModule,
    NbButtonModule,
  ]
})
export class DashboardCiGrp2Module { }
