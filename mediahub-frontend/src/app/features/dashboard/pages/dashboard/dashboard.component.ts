import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { User } from '../../../../core/models/user.model';
import { AuthService } from '../../../../core/services/auth.service';
import { UserService } from '../../../user/services/user.service';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, RouterLink],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  private readonly authService = inject(AuthService);
  private readonly userService = inject(UserService);

  currentUser: User | null = null;
  activeSubscription: boolean | null = null;
  loading = false;
  errorMessage = '';

  constructor() {
    this.loadDashboard();
  }

  private loadDashboard(): void {
    this.loading = true;
    this.errorMessage = '';

    this.authService.resolveCurrentUser().subscribe({
      next: (user) => {
        this.currentUser = user;

        if (!user) {
          this.loading = false;
          return;
        }

        this.userService.getSubscriptionStatus(user.id).subscribe({
          next: (status) => {
            this.activeSubscription = status.active;
            this.loading = false;
          },
          error: (error: Error) => {
            this.errorMessage = error.message;
            this.loading = false;
          }
        });
      },
      error: (error: Error) => {
        this.errorMessage = error.message;
        this.loading = false;
      }
    });
  }
}
