import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardCiGrp2RoutingModule } from './dashboard-ci-grp2-routing.module';
import { WelcomeComponent } from './welcome/welcome.component';


@NgModule({
  declarations: [
    WelcomeComponent
  ],
  imports: [
    CommonModule,
    DashboardCiGrp2RoutingModule
  ]
})
export class DashboardCiGrp2Module { }
