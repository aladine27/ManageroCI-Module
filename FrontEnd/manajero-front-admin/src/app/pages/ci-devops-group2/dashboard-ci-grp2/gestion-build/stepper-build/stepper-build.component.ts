import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProjectService } from '../../../../../services/project.service';
import { Project } from '../../../../../models/project.model';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'ngx-stepper-build',
  templateUrl: './stepper-build.component.html',
  styleUrls: ['./stepper-build.component.scss']
})
export class StepperBuildComponent implements OnInit {
  projectId: string;
  project: Project;


  constructor(
    private projectService: ProjectService,
    private router: Router,
    private route: ActivatedRoute,
    private http: HttpClient,

  ) {}

  ngOnInit(): void {
    this.projectId = this.route.snapshot.paramMap.get('id')!;
  }


  redirectToViewDashboard(): void {
    this.router.navigate(['/pages/agile/ci-devops-group2/viewdashboard', this.projectId]);
  }
}
