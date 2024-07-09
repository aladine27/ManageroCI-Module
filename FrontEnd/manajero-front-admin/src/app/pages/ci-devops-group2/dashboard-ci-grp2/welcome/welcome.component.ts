import { Component, OnInit } from '@angular/core';
import { Tutorial } from '../../../../models/tutorial.model';
import { TutorialService } from '../../../../services/tutorial.service';

@Component({
  selector: 'ngx-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss']
})
export class WelcomeComponent implements OnInit {
  tutorials: Tutorial[] = [];

  constructor(private tutorialService: TutorialService) { }

  ngOnInit(): void {
    this.tutorialService.getAll().subscribe((data: Tutorial[]) => {
      this.tutorials = data;
      console.log(this.tutorials);
    });
  }
}
