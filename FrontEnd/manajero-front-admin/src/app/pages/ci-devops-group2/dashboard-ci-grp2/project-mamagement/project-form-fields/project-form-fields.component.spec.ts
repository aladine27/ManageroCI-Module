import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectFormFieldsComponent } from './project-form-fields.component';

describe('ProjectFormFieldsComponent', () => {
  let component: ProjectFormFieldsComponent;
  let fixture: ComponentFixture<ProjectFormFieldsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectFormFieldsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectFormFieldsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
