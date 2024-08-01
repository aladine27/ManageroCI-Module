import { Component } from '@angular/core';

@Component({
  selector: 'ngx-view-dashboard',
  templateUrl: './view-dashboard.component.html',
  styleUrls: ['./view-dashboard.component.scss']
})
export class ViewDashboardComponent {
  buildStatus: string = 'pending'; // Remplacez avec la logique appropriée
  testStatus: string = 'pending'; // Remplacez avec la logique appropriée

  getIcon(status: string): string {
    switch (status) {
      case 'pending': return 'clock-outline';
      case 'success': return 'checkmark-outline';
      case 'failure': return 'close-outline';
      default: return 'question-mark-outline';
    }
  }

  getStatus(status: string): string {
    switch (status) {
      case 'pending': return 'basic';
      case 'success': return 'success';
      case 'failure': return 'danger';
      default: return 'info';
    }
  }

  makeBuild(): void {
    // Implémentez la logique pour démarrer un build
    console.log('Make Build button clicked');
  }

  viewBuildHistory(): void {
    // Implémentez la logique pour afficher l'historique des builds
    console.log('View Build History button clicked');
  }
}
