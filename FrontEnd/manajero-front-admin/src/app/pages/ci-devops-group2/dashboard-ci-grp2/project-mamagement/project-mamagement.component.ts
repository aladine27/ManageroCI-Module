import { Component, OnInit } from '@angular/core';
import { Project } from '../../../../models/project.model';
import { ProjectService } from '../../../../services/project.service';

@Component({
  selector: 'ngx-project-mamagement',
  templateUrl: './project-mamagement.component.html',
  styleUrls: ['./project-mamagement.component.scss']
})
export class ProjectMamagementComponent implements OnInit {
  projects: Project[] = [];

  constructor(private projectService: ProjectService) { }

  ngOnInit(): void {
    this.loadProjects();
  }

  loadProjects(): void {
    this.projectService.getAllProjects().subscribe(
      (projects: Project[]) => {
        this.projects = projects;
      },
      (error) => {
        console.error('Error fetching projects', error);
      }
    );
  }

  viewProjectDetails(project: Project): void {
    // Logic to navigate to project details
  }

  openEditModal(project: Project): void {
    // Logic to open the edit modal
  }

  confirmDeleteProject(project: Project): void {
    // Logic to confirm and delete the project
  }
}