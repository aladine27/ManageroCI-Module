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
  hideToken = true; // Initial state to hide token

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private projectService: ProjectService
  ) {
    this.projectForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      gitUrl: ['', [Validators.required]],
      gitUsername: ['', [Validators.required]],
      token: ['', Validators.required] // Include token field in the form
    });
  }

  ngOnInit(): void {
    this.projectId = this.route.snapshot.paramMap.get('id')!;
    console.log('Project ID:', this.projectId);
    this.loadProjectDetails();
  }

  loadProjectDetails(): void {
    this.projectService.getProjectById(this.projectId).subscribe((project: Project) => {
      console.log('Project data retrieved:', project); // Log project data
      this.projectForm.patchValue({
        name: project.name,
        description: project.description,
        gitUrl: project.gitUrl,
        gitUsername: project.gitUsername,
        token: project.token // Include token when patching
      });
      console.log('Form values after patching:', this.projectForm.value); // Log form values
    });
  }

  toggleTokenVisibility(): void {
    this.hideToken = !this.hideToken;
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
