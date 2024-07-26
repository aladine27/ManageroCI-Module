import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../../../../../services/project.service';
import { Project } from '../../../../../models/project.model';

@Component({
  selector: 'ngx-project-edit',
  templateUrl: './project-edit.component.html',
  styleUrls: ['./project-edit.component.scss']
})
export class ProjectEditComponent implements OnInit {
  projectForm: FormGroup;
  projectId: string;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private projectService: ProjectService
  ) {
    this.projectForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      gitUrl: ['', [Validators.required, Validators.pattern('https?://.+')]]
    });
  }

  ngOnInit(): void {
    this.projectId = this.route.snapshot.paramMap.get('id')!;
    this.loadProjectDetails();
  }

  loadProjectDetails(): void {
    this.projectService.getProjectById(this.projectId).subscribe((project: Project) => {
      this.projectForm.patchValue({
        name: project.name,
        description: project.description,
        gitUrl: project.gitUrl
      });
    });
  }

  onSubmit(): void {
    if (this.projectForm.valid) {
      this.projectService.updateProject(this.projectId, this.projectForm.value).subscribe(
        response => {
          console.log('Projet mis à jour avec succès', response);
          this.router.navigate(['/pages/agile/ci-devops-group2/project-management']);
        },
        error => {
          console.error('Erreur lors de la mise à jour du projet', error);
        }
      );
    }
  }
}
