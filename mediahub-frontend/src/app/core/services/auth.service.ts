import { HttpClient } from '@angular/common/http';
import { Injectable, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, of, switchMap, tap } from 'rxjs';
import { LoginRequest, LoginResponse } from '../models/auth.model';
import { User } from '../models/user.model';
import { UserService } from '../../features/user/services/user.service';

const TOKEN_KEY = 'mediahub.token';
const USERNAME_KEY = 'mediahub.username';
const USER_ID_KEY = 'mediahub.userId';
const USER_ROLE_KEY = 'mediahub.userRole';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly router = inject(Router);
  private readonly userService = inject(UserService);

  readonly isAuthenticated = signal<boolean>(this.hasToken());
  readonly username = signal<string>(localStorage.getItem(USERNAME_KEY) ?? '');
  readonly userId = signal<number | null>(this.getStoredNumber(USER_ID_KEY));
  readonly role = signal<string>(localStorage.getItem(USER_ROLE_KEY) ?? '');

  private readonly authBaseUrl = '/api/auth';

  login(request: LoginRequest): Observable<User | null> {
    return this.http.post<LoginResponse>(`${this.authBaseUrl}/login`, request).pipe(
      tap((response) => {
        localStorage.setItem(TOKEN_KEY, response.token);
        localStorage.setItem(USERNAME_KEY, response.username);
        this.username.set(response.username);
        this.isAuthenticated.set(true);
      }),
      switchMap((response) => this.resolveAndStoreUser(response.username))
    );
  }

  register(request: LoginRequest): Observable<string> {
    return this.http.post(`${this.authBaseUrl}/register`, request, { responseType: 'text' });
  }

  resolveCurrentUser(): Observable<User | null> {
    const username = this.username();
    if (!username) {
      return of(null);
    }

    return this.resolveAndStoreUser(username);
  }

  getToken(): string | null {
    return localStorage.getItem(TOKEN_KEY);
  }

  logout(): void {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(USERNAME_KEY);
    localStorage.removeItem(USER_ID_KEY);
    localStorage.removeItem(USER_ROLE_KEY);
    this.username.set('');
    this.userId.set(null);
    this.role.set('');
    this.isAuthenticated.set(false);
    this.router.navigate(['/login']);
  }

  private resolveAndStoreUser(username: string): Observable<User | null> {
    return this.userService.getAllUsers().pipe(
      switchMap((users) => {
        const currentUser = users.find((user) => user.username === username) ?? null;

        if (currentUser) {
          localStorage.setItem(USER_ID_KEY, String(currentUser.id));
          localStorage.setItem(USER_ROLE_KEY, currentUser.role);
          this.userId.set(currentUser.id);
          this.role.set(currentUser.role);
        }

        return of(currentUser);
      })
    );
  }

  private hasToken(): boolean {
    return !!localStorage.getItem(TOKEN_KEY);
  }

  private getStoredNumber(key: string): number | null {
    const value = localStorage.getItem(key);
    if (!value) {
      return null;
    }

    const parsed = Number(value);
    return Number.isNaN(parsed) ? null : parsed;
  }
}
