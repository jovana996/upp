import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ObradaTekstaComponent } from './obrada-teksta.component';

describe('ObradaTekstaComponent', () => {
  let component: ObradaTekstaComponent;
  let fixture: ComponentFixture<ObradaTekstaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ObradaTekstaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ObradaTekstaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
