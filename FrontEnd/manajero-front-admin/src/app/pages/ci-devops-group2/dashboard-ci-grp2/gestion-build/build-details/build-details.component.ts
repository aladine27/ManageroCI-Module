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
  buildDetails: any[] = []; // Array to store multiple build details
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
        console.log('Project ID:', this.projectId);  

        this.project = project;
        this.loadWorkflowRuns();
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
        if (response.workflow_runs.length > 0) {
          this.buildDetails = response.workflow_runs; // Store all builds
        }
      },
      error => {
        console.error('Erreur lors de la récupération des runs des workflows', error);
        this.displayAlert('Échec lors de la récupération des runs des workflows', 'danger');
      }
    );
  }

  deleteBuild(buildId: number): void {
    this.projectService.deleteWorkflowRun(this.project, buildId).subscribe(
      response => {
        // Remove the deleted build from the buildDetails array
        this.buildDetails = this.buildDetails.filter(build => build.id !== buildId);
        this.displayAlert('Build supprimé avec succès', 'success');
      },
      error => {
        console.error('Erreur lors de la suppression du build', error);
        this.displayAlert('Échec lors de la suppression du build', 'danger');
      }
    );
  }

  displayAlert(message: string, status: 'success' | 'danger'): void {
    this.alertMessage = message;
    this.alertStatus = status;
    this.showAlert = true;
    setTimeout(() => this.showAlert = false, 5000);
  }
}
