import { HttpClientTestingModule, HttpTestingController, } from "@angular/common/http/testing";
import { TestBed } from "@angular/core/testing";
import { expect } from '@jest/globals';
import { AuthService } from "./auth.service";

describe('AuthService', () => {
    let service: AuthService;
    let httpTestingController: HttpTestingController;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
        });
        service = TestBed.inject(AuthService);
        httpTestingController = TestBed.inject(HttpTestingController);
    })

    afterEach(() => {
        httpTestingController.verify();
    })

    it('should be created', () => {
        expect(service).toBeTruthy();
    })

    it('register should return nothing', (done) => {
        service.register({
            email: 'email',
            firstName: 'firstName',
            lastName: 'lastName',
            password: 'password'
        }).subscribe({
            next: data => {
                expect(data).toEqual({});
                done();
            },
            error: jest.fn()
        })

        const req = httpTestingController.expectOne(`api/auth/register`);
        expect(req.request.method).toBe('POST');
        req.flush({});
    })

    it('login should return a session information', (done) => {
        service.login({
            email: 'email',
            password: 'password'
        }).subscribe({
            next: data => {
                expect(data).toStrictEqual({
                    token: 'token',
                    type: 'type',
                    id: 1,
                    admin: true,
                    username: 'username',
                    firstName: 'firstName',
                    lastName: 'lastName'
                });
                done();
            },
            error: jest.fn()
        })

        const req = httpTestingController.expectOne(`api/auth/login`);
        expect(req.request.method).toBe('POST');
        req.flush({
            token: 'token',
            type: 'type',
            id: 1,
            admin: true,
            username: 'username',
            firstName: 'firstName',
            lastName: 'lastName'
        });
    
    })

})