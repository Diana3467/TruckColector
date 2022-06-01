import { TestBed } from '@angular/core/testing';

import { WservRutConService } from './wserv-rut-con.service';

describe('WservRutConService', () => {
  let service: WservRutConService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WservRutConService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
