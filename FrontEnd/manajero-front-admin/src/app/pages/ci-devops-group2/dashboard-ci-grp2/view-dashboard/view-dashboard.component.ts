import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from '../../../../models/project.model';
import { ProjectService } from '../../../../services/project.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'ngx-view-dashboard',
  templateUrl: './view-dashboard.component.html',
  styleUrls: ['./view-dashboard.component.scss']
})
export class ViewDashboardComponent implements OnInit {
  projectId: string;
  project: Project;
  buildResult: any;
  sonarReport: any;
  showAlert: boolean = false;
  alertMessage: string = '';
  alertStatus: 'success' | 'danger' = 'success';

  constructor(
    private route: ActivatedRoute,
    private projectService: ProjectService,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.projectId = this.route.snapshot.paramMap.get('id')!;
    this.loadProjectDetails();
  }

  loadProjectDetails(): void {
    this.projectService.getProjectById(this.projectId).subscribe((project: Project) => {
      this.project = project;
    });
  }

  buildProject(): void {
    if (!this.project || !this.project.gitUsername || !this.project.gitRepo) {
      console.error('Projet ou paramètres GitHub manquants');
      return;
    }
  
    const url = `https://api.github.com/repos/${this.project.gitUsername}/${this.project.gitRepo}/actions/workflows/back-ci.yml/dispatches`;
    const body = JSON.stringify({ ref: 'GestionPiplineFixBranche' });
    const headers = {
      'Authorization': `Bearer ${this.project.token}`,
      'Accept': 'application/vnd.github.v3+json',
      'Content-Type': 'application/json'
    };
  
    this.http.post(url, body, { headers: headers }).subscribe(
      response => {
        console.log('Workflow déclenché avec succès', response);
        this.displayAlert('Workflow déclenché avec succès', 'success');
      },
      error => {
        console.error('Erreur lors du déclenchement du workflow', error);
        this.displayAlert('Échec lors du déclenchement du workflow', 'danger');
      }
    );
  }

  displayAlert(message: string, status: 'success' | 'danger'): void {
    this.alertMessage = message;
    this.alertStatus = status;
    this.showAlert = true;
    setTimeout(() => this.showAlert = false, 5000);
  }
  
  viewBuildDetails(projectId: string): void {
    this.router.navigate(['/pages/agile/ci-devops-group2/view-build-details', projectId]);
  }

  checkCodeQuality(): void {
    const url = `https://sonarcloud.io/api/measures/component?component=${this.project.gitRepo}&metricKeys=coverage,bugs,vulnerabilities,code_smells,duplicated_lines_density`;
    const headers = {
      'Authorization': `Bearer ${this.project.token}`
    };

    this.http.get(url, { headers: headers }).subscribe(
      response => {
        this.sonarReport = response;
        console.log('Rapport SonarQube:', response);
      },
      error => {
        console.error('Erreur lors de la récupération du rapport SonarQube', error);
        this.displayAlert('Échec lors de la récupération du rapport SonarQube', 'danger');
      }
    );
  }
}
