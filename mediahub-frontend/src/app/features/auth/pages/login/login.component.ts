import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../../../core/services/auth.service';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  mode: 'login' | 'register' = 'login';
  username = '';
  email = '';
  password = '';
  loading = false;
  successMessage = '';
  errorMessage = '';

  switchMode(mode: 'login' | 'register'): void {
    this.mode = mode;
    this.successMessage = '';
    this.errorMessage = '';
  }

  submit(): void {
    this.loading = true;
    this.successMessage = '';
    this.errorMessage = '';

    if (!this.username.trim() || !this.password.trim()) {
      this.errorMessage = 'Username et password sont obligatoires.';
      this.loading = false;
      return;
    }

    if (this.mode === 'register') {
      this.authService.register({
        username: this.username.trim(),
        email: this.email.trim(),
        password: this.password
      }).subscribe({
        next: (message) => {
          this.successMessage = message || 'Inscription réussie. Connectez-vous.';
          this.loading = false;
          this.mode = 'login';
        },
        error: (error: Error) => {
          this.errorMessage = error.message;
          this.loading = false;
        }
      });
      return;
    }

    this.authService.login({
      username: this.username.trim(),
      password: this.password
    }).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/dashboard']);
      },
      error: (error: Error) => {
        this.errorMessage = error.message;
        this.loading = false;
      }
    });
  }
}
