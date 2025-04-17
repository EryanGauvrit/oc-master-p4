import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterTestingModule } from '@angular/router/testing';
import { expect } from '@jest/globals';

import { By } from '@angular/platform-browser';
import { provideRouter, Router } from '@angular/router';
import { AppComponent } from './app.component';
import { SessionService } from './services/session.service';


describe('AppComponent', () => {
  let fixture: ComponentFixture<AppComponent>;
  let component: AppComponent;
  let sessionService: SessionService;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideRouter([]), SessionService],
      imports: [
        RouterTestingModule,
        HttpClientModule,
        MatToolbarModule
      ],
      declarations: [
        AppComponent
      ],
    }).compileComponents();

    router = TestBed.inject(Router);
    sessionService = TestBed.inject(SessionService);
    sessionService.logIn({
      admin: false,
      firstName: 'John',
      lastName: 'Doe',
      id: 1,
      token: 'token',
      type: 'user',
      username: 'johndoe'
    });
    
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

  });

  it('should create the app', () => {
    expect(component).toBeTruthy();
  });

  it(`should receive true from $isLogged`, (done) => {
    
    sessionService.$isLogged().subscribe((value) => {
      expect(value).toBe(true);
      done();
    });
  });

  it(`should logout on button clicked`, () => {
    jest.spyOn(sessionService, 'logOut');
    jest.spyOn(router, 'navigate');
    

    const link = fixture.debugElement.query(By.css('.link:nth-child(3)'));
    link.triggerEventHandler('click' , null);

    expect(sessionService.logOut).toHaveBeenCalled();
    expect(router.navigate).toHaveBeenCalledWith(['']);
  });

});
