import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Project } from '../../../../models/project.model';
import { ProjectService } from '../../../../services/project.service';

@Component({
  selector: 'ngx-view-dashboard',
  templateUrl: './view-dashboard.component.html',
  styleUrls: ['./view-dashboard.component.scss']
})
export class ViewDashboardComponent implements OnInit {
  projectId: string;
  project: Project;

  constructor(
    private route: ActivatedRoute,
    private projectService: ProjectService
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
    // Implémente ici la logique pour le bouton "Build"
    console.log('Build action triggered for project ID:', this.projectId);
    // Appelle le service approprié ou exécute la logique pour démarrer la construction
  }
}
