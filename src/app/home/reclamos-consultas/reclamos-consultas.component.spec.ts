import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReclamosConsultasComponent } from './reclamos-consultas.component';

describe('ReclamosConsultasComponent', () => {
  let component: ReclamosConsultasComponent;
  let fixture: ComponentFixture<ReclamosConsultasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReclamosConsultasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReclamosConsultasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
