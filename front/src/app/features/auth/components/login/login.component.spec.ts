import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { SessionService } from 'src/app/services/session.service';

import { Router } from '@angular/router';
import { expect } from '@jest/globals';
import { of } from 'rxjs';
import { SessionInformation } from 'src/app/interfaces/sessionInformation.interface';
import { AuthService } from '../../services/auth.service';
import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authService: AuthService;
  let sessionService: SessionService;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      providers: [
        FormBuilder,
        {
          provide: AuthService,
          useValue: {
            login: jest.fn().mockReturnValue(of({ id: 1, admin: true, token: 'token' })),
          },
        },
        {
          provide: SessionService,
          useValue: {
            logIn: jest.fn(),
          },
        },
      ],
      imports: [
        RouterTestingModule,
        BrowserAnimationsModule,
        HttpClientModule,
        MatCardModule,
        MatIconModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule,
      ],
    }).compileComponents();

    router = TestBed.inject(Router);
    authService = TestBed.inject(AuthService);
    sessionService = TestBed.inject(SessionService);
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    component.form.setValue({
      email: 'email',
      password: 'password',
    }); // Initialize the form with the mock builder
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call submit and handle login correctly', () => {

    const response: SessionInformation = {
      id: 1,
      admin: true,
      token: 'token',
      firstName: 'John',
      lastName: 'Doe',
      type: 'user',
      username: 'johndoe',
    };

    const spyLogin = jest.spyOn(authService, 'login').mockReturnValue(of(response));
    const spyLogIn = jest.spyOn(sessionService, 'logIn');
    const spyNavigate = jest.spyOn(router, 'navigate').mockReturnValue(Promise.resolve(true));

    component.submit();

    expect(spyLogin).toHaveBeenCalledWith(component.form.value);
    expect(spyLogIn).toHaveBeenCalledWith(response);
    expect(spyNavigate).toHaveBeenCalledWith(['/sessions']);
  });
});