import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from '../../../../../services/project.service';

@Component({
  selector: 'ngx-sonar-analysis',
  templateUrl: './sonar-analysis.component.html',
  styleUrls: ['./sonar-analysis.component.scss']
})
export class SonarAnalysisComponent implements OnInit {

  projectKey: string;
  sonarMetrics: any;
  analysisDate: string;



  constructor(
    private route: ActivatedRoute,
    private sonarService: ProjectService
  ) {}

  ngOnInit(): void {
    this.projectKey = this.route.snapshot.paramMap.get('id')!;
    this.loadSonarMetrics();
    this.loadSonarAnalysisDate();
  }

  loadSonarMetrics(): void {
    this.sonarService.getSonarMetrics(this.projectKey).subscribe(
      metrics => {
        this.sonarMetrics = metrics.component.measures;
        console.log('Sonar metrics:', this.sonarMetrics);
      },
      error => {
        console.error('Failed to fetch Sonar metrics', error);
      }
    );
  }

  loadSonarAnalysisDate(): void {
    this.sonarService.getSonarAnalysisDate(this.projectKey).subscribe(
      response => {
        console.log('Sonar analysis response:', response);
        if (response.analyses && response.analyses.length > 0) {
          this.analysisDate = response.analyses[0].date;
          console.log('Sonar analysis date:', this.analysisDate);
        } else {
          console.warn('No analysis date found');
        }
      },
      error => {
        console.error('Failed to fetch Sonar analysis date', error);
      }
    );
  }



}
