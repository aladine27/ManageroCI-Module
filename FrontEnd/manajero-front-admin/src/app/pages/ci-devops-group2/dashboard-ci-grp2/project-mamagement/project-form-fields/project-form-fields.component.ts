import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProjectService } from '../../../../../services/project.service';

@Component({
  selector: 'ngx-project-form-fields',
  templateUrl: './project-form-fields.component.html',
  styleUrls: ['./project-form-fields.component.scss']
})
export class ProjectFormFieldsComponent {
  @Input() form: FormGroup;
  projectForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private projectService: ProjectService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.projectForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      gitUrl: ['', Validators.required],
      token: ['', Validators.required],
      sonarToken: ['', Validators.required],
    });
  }

  hideToken = true;
  hideSonarToken = true;

  toggleTokenVisibility() {
    this.hideToken = !this.hideToken;
  }

  toggleSonarTokenVisibility() {
    this.hideSonarToken = !this.hideSonarToken;
  }
}
