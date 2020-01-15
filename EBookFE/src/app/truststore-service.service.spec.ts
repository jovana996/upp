import { TestBed, inject } from '@angular/core/testing';

import { TruststoreServiceService } from './truststore-service.service';

describe('TruststoreServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TruststoreServiceService]
    });
  });

  it('should be created', inject([TruststoreServiceService], (service: TruststoreServiceService) => {
    expect(service).toBeTruthy();
  }));
});
