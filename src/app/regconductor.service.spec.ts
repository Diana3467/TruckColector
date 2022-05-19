import { TestBed } from '@angular/core/testing';

import { RegconductorService } from './regconductor.service';

describe('RegconductorService', () => {
  let service: RegconductorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegconductorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
