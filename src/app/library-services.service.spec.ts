import { TestBed, inject } from '@angular/core/testing';

import { LibraryServicesService } from './library-browser.service';

describe('LibraryServicesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LibraryServicesService]
    });
  });

  it('should be created', inject([LibraryServicesService], (service: LibraryServicesService) => {
    expect(service).toBeTruthy();
  }));
});
