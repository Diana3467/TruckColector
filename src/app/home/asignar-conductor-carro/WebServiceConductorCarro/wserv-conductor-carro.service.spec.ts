import { TestBed } from '@angular/core/testing';

import { WservConductorCarroService } from './wserv-conductor-carro.service';

describe('WservConductorCarroService', () => {
  let service: WservConductorCarroService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WservConductorCarroService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
