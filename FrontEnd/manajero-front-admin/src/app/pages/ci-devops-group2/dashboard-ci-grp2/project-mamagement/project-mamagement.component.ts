import { Component, OnInit } from '@angular/core';
import { Project } from '../../../../models/project.model';
import { ProjectService } from '../../../../services/project.service';
import { NbToastrService } from '@nebular/theme';

@Component({
  selector: 'ngx-project-mamagement',
  templateUrl: './project-mamagement.component.html',
  styleUrls: ['./project-mamagement.component.scss']
})
export class ProjectMamagementComponent implements OnInit {
  projects: Project[] = [];

  constructor(private projectService: ProjectService,
     private toastrService: NbToastrService

  ) { }

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

  
    deleteProject(id: string): void {
    this.projectService.deleteProject(id).subscribe(
      () => {
        this.toastrService.success('Projet supprimé avec succès', 'Succès');
        this.loadProjects(); // Recharger la liste des projets après suppression
      },
      error => {
        console.error('Error deleting project', error);
        this.toastrService.danger('Erreur lors de la suppression du projet', 'Erreur');
      }
    );
  }
  confirmDeleteProject(project: Project): void {
    if (confirm(`Êtes-vous sûr de vouloir supprimer le projet ${project.name} ?`)) {
      this.deleteProject(project.id);
    }
  }
}