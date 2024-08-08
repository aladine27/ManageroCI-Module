// src/app/services/project.service.ts

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project } from '../models/project.model';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private baseUrl = 'http://localhost:8093/api/projects'; // URL de base pour les API
  private sonarCloudApiUrl = 'https://sonarcloud.io/api/measures/component'; // URL de l'API de SonarCloud

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  // Obtenir tous les projets
  getAllProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.baseUrl}/all`);
  }

  // Obtenir un projet par ID
  getProjectById(id: string): Observable<Project> {
    return this.http.get<Project>(`${this.baseUrl}/getprojectbyid/${id}`);
  }

  // Créer un nouveau projet
  addProject(project: Project): Observable<Project> {
    return this.http.post<Project>(`${this.baseUrl}/add`, project, this.httpOptions);
  }

  // Mettre à jour un projet existant
  updateProject(id: string, project: Project): Observable<Project> {
    return this.http.put<Project>(`${this.baseUrl}/modify/${id}`, project, this.httpOptions);
  }

  // Supprimer un projet
  deleteProject(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }

  getAllWorkflowRuns(project: Project): Observable<any> {
    const url = `https://api.github.com/repos/${project.gitUsername}/${project.gitRepo}/actions/runs`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${project.token}`,
      'Accept': 'application/vnd.github.v3+json'
    });
  
    return this.http.get<any>(url, { headers: headers });
  }

  deleteWorkflowRun(project: Project, buildId: number): Observable<any> {
    const url = `https://api.github.com/repos/${project.gitUsername}/${project.gitRepo}/actions/runs/${buildId}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${project.token}`,
      'Accept': 'application/vnd.github.v3+json'
    });

    return this.http.delete<any>(url, { headers: headers });
  }
  
  getProjectQualityReport(project: Project): Observable<any> {
    // URL de votre proxy local
    const url = `http://localhost:8093/api/sonar/measures?component=${project.projectKey}&metricKeys=bugs,vulnerabilities,code_smells`;
  
    // Configuration des headers avec le token SonarCloud
    const headers = new HttpHeaders({
      'Authorization': `token ${project.sonarToken}`,
      'Accept': 'application/json'
    });
  
    // Envoi de la requête GET à l'API proxy
    return this.http.get<any>(url, { headers });
  }


}
