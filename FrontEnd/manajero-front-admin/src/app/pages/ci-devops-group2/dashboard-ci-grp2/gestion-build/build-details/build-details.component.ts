import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from '../../../../../services/project.service';
import { Project } from '../../../../../models/project.model';

@Component({
  selector: 'ngx-build-details',
  templateUrl: './build-details.component.html',
  styleUrls: ['./build-details.component.scss']
})
export class BuildDetailsComponent implements OnInit {
  projectId: string;
  project: Project;
  buildDetails: any; // Updated property name
  showAlert: boolean = false;
  alertMessage: string = '';
  alertStatus: 'success' | 'danger' = 'success';

  constructor(
    private route: ActivatedRoute,
    private projectService: ProjectService
  ) {}

  ngOnInit(): void {
    this.projectId = this.route.snapshot.paramMap.get('id')!;
    console.log('Project ID:', this.projectId);  
    this.loadProjectDetails();
  }

  loadProjectDetails(): void {
    if (!this.projectId) {
      console.error('Project ID is missing');
      this.displayAlert('Identifiant du projet manquant', 'danger');
      return;
    }

    this.projectService.getProjectById(this.projectId).subscribe(
      (project: Project) => {
        console.log('Project ID:', this.projectId);  // Add this line to debug

        this.project = project;
        this.loadWorkflowRuns(); // Ensure workflow runs are loaded
      },
      error => {
        console.error('Erreur lors de la récupération des détails du projet', error);
        this.displayAlert('Échec lors de la récupération des détails du projet', 'danger');
      }
    );
  }
  

  loadWorkflowRuns(): void {
    this.projectService.getAllWorkflowRuns(this.project).subscribe(
      (response: any) => {
        // Assuming `response.workflow_runs` contains the relevant details
        if (response.workflow_runs.length > 0) {
          this.buildDetails = response.workflow_runs[0]; // Update according to your needs
        }
      },
      error => {
        console.error('Erreur lors de la récupération des runs des workflows', error);
        this.displayAlert('Échec lors de la récupération des runs des workflows', 'danger');
      }
    );
  }

  displayAlert(message: string, status: 'success' | 'danger'): void {
    this.alertMessage = message;
    this.alertStatus = status;
    this.showAlert = true;
    setTimeout(() => this.showAlert = false, 5000);  // L'alerte disparaît après 5 secondes
  }
}