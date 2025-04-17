import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { expect } from '@jest/globals';

import { Router } from '@angular/router';
import { of } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { RegisterComponent } from './register.component';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let formBuilder: FormBuilder;
  let authService: AuthService
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegisterComponent],
      providers: [
        FormBuilder,
        {
          provide: AuthService,
          useValue: {
            register: jest.fn().mockReturnValue(of()),
          },
        },
      ],
      imports: [
        BrowserAnimationsModule,
        HttpClientModule,
        ReactiveFormsModule,  
        MatCardModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule
      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(RegisterComponent);
    formBuilder = TestBed.inject(FormBuilder);
    authService = TestBed.inject(AuthService);
    router = TestBed.inject(Router);
    component = fixture.componentInstance;
    component.form.setValue({
      email: 'email',
      firstName: 'firstName',
      lastName: 'lastName',
      password: 'password'
    });
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call authService.register and navigate to /login on submit', () => {
    const spyRegister = jest.spyOn(authService, 'register').mockReturnValue(of(void 0));
    const routerSpy = jest.spyOn(router, 'navigate').mockReturnValue(Promise.resolve(true));

    component.submit();

    expect(spyRegister).toHaveBeenCalledWith(component.form.value);
    expect(routerSpy).toHaveBeenCalledWith(['/login']);
  });
});
