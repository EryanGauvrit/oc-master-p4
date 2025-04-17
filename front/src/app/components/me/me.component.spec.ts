import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { expect } from '@jest/globals';
import { SessionService } from 'src/app/services/session.service';

import { Router } from '@angular/router';
import { of } from 'rxjs';
import { User } from 'src/app/interfaces/user.interface';
import { UserService } from 'src/app/services/user.service';
import { MeComponent } from './me.component';

describe('MeComponent', () => {
  let component: MeComponent;
  let fixture: ComponentFixture<MeComponent>;
  let router: Router;
  let userService: UserService;
  let matSnackBar: MatSnackBar;

  const mockSessionService = {
    sessionInformation: {
      admin: true,
      id: 1
    },
    logOut: jest.fn()
  }
  
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MeComponent],
      imports: [
        MatSnackBarModule,
        HttpClientModule,
        MatCardModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule
      ],
      providers: [{ provide: SessionService, useValue: mockSessionService }, UserService, MatSnackBar],
    })
      .compileComponents();

    router = TestBed.inject(Router);
    userService = TestBed.inject(UserService);
    matSnackBar = TestBed.inject(MatSnackBar);
    fixture = TestBed.createComponent(MeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call back', () => {
    const spy = jest.spyOn(window.history, 'back');
    component.back();
    expect(spy).toHaveBeenCalled();
  });

  it('should get user with ngOnInit', () => {
    const user: User = {
      admin: true,
      id: 1,
      firstName: 'John',
      lastName: 'Doe',
      createdAt: new Date(),
      updatedAt: new Date(),
      email: 'john.doe@test.fr',
      password: 'password'
    };
    const spySessionServiceGetById = jest.spyOn(userService, 'getById').mockReturnValue(of(user));
    component.ngOnInit();
    expect(spySessionServiceGetById).toHaveBeenCalledWith('1');
    expect(component.user).toEqual(user);
  }
  );

  it('should call delete and verify subscribe callback', () => {
    const spySessionServiceDelete = jest.spyOn(userService, 'delete').mockReturnValue(of(null));
    const spySnackBar = jest.spyOn(matSnackBar, 'open').mockReturnValue({
      message: 'Your account has been deleted !'
    } as any);
    

    const spyLogOut = jest.spyOn(mockSessionService, 'logOut');
    jest.spyOn(router, 'navigate');

    component.delete();

    expect(spySessionServiceDelete).toHaveBeenCalledWith('1');
    expect(spySnackBar).toHaveBeenCalledWith(
      'Your account has been deleted !',
      'Close',
      { duration: 3000 }
    );
    expect(spyLogOut).toHaveBeenCalled();
    expect(router.navigate).toHaveBeenCalledWith(['/']);
  });
});
