import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Stage } from '../../../../../models/stage.model';
import { StageService } from '../../../../../services/stage.service';
import { Router } from '@angular/router';
import { PipelineService } from '../../../../../services/pipeline.service';
import { Observable } from 'rxjs';
import { NbToastrService } from '@nebular/theme';
import { Pipeline } from '../../../../../models/pipeline.model';

@Component({
  selector: 'ngx-pipline-add',
  templateUrl: './pipline-add.component.html',
  styleUrls: ['./pipline-add.component.scss']
})
export class PiplineAddComponent implements OnInit {
  pipelineForm: FormGroup;
  stages: Stage[] = [];

  constructor(
    private fb: FormBuilder,
    private pipelineService: PipelineService,
    private stageService: StageService,
    private router: Router
  ) {
    this.pipelineForm = this.fb.group({
      name: ['', Validators.required],
      stages: [[], Validators.required] // Initialisation avec tableau vide et validation requise
    });
  }

  ngOnInit(): void {
    this.loadStages();
  }

  loadStages(): void {
    this.stageService.getAllStages().subscribe(
      (data) => this.stages = data,
      (error) => console.error('Erreur lors du chargement des stages', error)
    );
  }

  onSubmit(): void {
    if (this.pipelineForm.valid) {
      const { name, stages } = this.pipelineForm.value;
      this.pipelineService.createPipeline({ name, stages }).subscribe(
        (response) => {
          console.log('Pipeline créé avec succès', response);
          this.router.navigate(['/pipelines']);
        },
        (error) => console.error('Erreur lors de la création du pipeline', error)
      );
    }
  }
}