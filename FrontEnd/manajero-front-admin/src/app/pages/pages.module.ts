import { NgModule } from '@angular/core';
import { NbMenuModule } from '@nebular/theme';

import { ThemeModule } from '../@theme/theme.module';
import { PagesComponent } from './pages.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { ECommerceModule } from './e-commerce/e-commerce.module';
import { PagesRoutingModule } from './pages-routing.module';
import { MiscellaneousModule } from './miscellaneous/miscellaneous.module';

import { ReactiveFormsModule } from '@angular/forms';
import { DashboardCiGrp2Module } from './ci-devops-group2/dashboard-ci-grp2/dashboard-ci-grp2.module';
import { BuildDetailsComponent } from './agile/ci-devops-group2/build-details/build-details.component';




@NgModule({
  imports: [
    PagesRoutingModule,
    ThemeModule,
    NbMenuModule,
    DashboardModule,
    ECommerceModule,
    MiscellaneousModule,
   
  ],
  declarations: [
   
   
    PagesComponent,
    BuildDetailsComponent,
   
  ],
})
export class PagesModule {}
