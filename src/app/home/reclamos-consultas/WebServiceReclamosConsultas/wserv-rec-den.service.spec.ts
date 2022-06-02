import { TestBed } from '@angular/core/testing';

import { WServRecDenService } from './wserv-rec-den.service';

describe('WServRecDenService', () => {
  let service: WServRecDenService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WServRecDenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
