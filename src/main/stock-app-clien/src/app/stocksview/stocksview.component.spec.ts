import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StocksviewComponent } from './stocksview.component';

describe('StocksviewComponent', () => {
  let component: StocksviewComponent;
  let fixture: ComponentFixture<StocksviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StocksviewComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StocksviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
