import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { AddTutoComponent } from './add-tuto/add-tuto.component';

const routes: Routes = [
  { path: '', component: WelcomeComponent },
  { path: 'add-tuto', component: AddTutoComponent }, // Définition de la route pour l'ajout de tutoriel

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardCiGrp2RoutingModule { }
