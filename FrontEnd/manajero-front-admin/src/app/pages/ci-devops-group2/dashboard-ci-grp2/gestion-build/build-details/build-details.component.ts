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
  buildDetails: any[] = [];
  successfulBuilds: number = 0;
  failedBuilds: number = 0;
  inProgressBuilds: number = 0;
  averageBuildTime: number = 0; // en millisecondes

  // Variables pour les données des graphiques
  pieChartData: any[] = [];
  colorScheme = {
    domain: ['#2196f3', '#4caf50', '#ff9800', '#f44336']
  };
  isChartVisible: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private projectService: ProjectService
  ) {}

  ngOnInit(): void {
    this.projectId = this.route.snapshot.paramMap.get('id')!;
    this.loadProjectDetails();
  }

  loadProjectDetails(): void {
    this.projectService.getProjectById(this.projectId).subscribe(
      (project: Project) => {
        this.project = project;
        this.loadWorkflowRuns();
      },
      error => console.error('Erreur lors de la récupération des détails du projet', error)
    );
  }
  deleteBuild(buildId: number): void {
    this.projectService.deleteWorkflowRun(this.project, buildId).subscribe(
      () => {
        console.log(`Build avec ID ${buildId} supprimé avec succès.`);
        // Mettre à jour la liste des builds après suppression
        this.buildDetails = this.buildDetails.filter(build => build.id !== buildId);
        this.calculateKPIs();
        this.loadBuildDetails(); // Recharger les données pour les graphiques
      },
      error => console.error('Erreur lors de la suppression du build', error)
    );
  }


  loadWorkflowRuns(): void {
    this.projectService.getAllWorkflowRuns(this.project).subscribe(
      (response: any) => {
        if (response.workflow_runs.length > 0) {
          this.buildDetails = response.workflow_runs;
          this.calculateKPIs();
          this.loadBuildDetails(); // Charger les données pour les graphiques
        }
      },
      error => console.error('Erreur lors de la récupération des runs des workflows', error)
    );
  }

  calculateKPIs(): void {
    this.successfulBuilds = this.buildDetails.filter(build => build.conclusion === 'success').length;
    this.failedBuilds = this.buildDetails.filter(build => build.conclusion === 'failure').length;
    this.inProgressBuilds = this.buildDetails.filter(build => build.status === 'in_progress').length;
    
    // Calculer le temps moyen de construction
    const buildTimes = this.buildDetails.map(build => new Date(build.completed_at).getTime() - new Date(build.created_at).getTime());
    this.averageBuildTime = buildTimes.length > 0 ? (buildTimes.reduce((a, b) => a + b, 0) / buildTimes.length) : 0;
  }

  loadBuildDetails(): void {
    this.pieChartData = [
      {
        name: 'Total Builds',
        value: this.buildDetails.length
      },
      {
        name: 'Succès',
        value: this.successfulBuilds
      },
      {
        name: 'Échoués',
        value: this.failedBuilds
      },
      {
        name: 'En Cours',
        value: this.inProgressBuilds
      }
    ];
  }

  toggleChart(): void {
    this.isChartVisible = !this.isChartVisible;
  }

  formatLabel(value: any): string {
    return `${value} (${((value / this.buildDetails.length) * 100).toFixed(1)}%)`;
  }
}
