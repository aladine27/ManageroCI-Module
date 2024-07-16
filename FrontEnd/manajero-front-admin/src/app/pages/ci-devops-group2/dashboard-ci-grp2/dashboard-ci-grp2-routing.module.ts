import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { EditTutorialComponent } from './edit-tutorial/edit-tutorial.component';

const routes: Routes = [
  { path: '', component: WelcomeComponent },
   { path: 'edit-tutorial/:id', component: EditTutorialComponent } ,
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardCiGrp2RoutingModule { }
