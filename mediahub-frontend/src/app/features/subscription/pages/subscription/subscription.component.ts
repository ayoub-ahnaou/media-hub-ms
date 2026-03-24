import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Subscription } from '../../../../core/models/subscription.model';
import { AuthService } from '../../../../core/services/auth.service';
import { SubscriptionService } from '../../services/subscription.service';

@Component({
  selector: 'app-subscription',
  imports: [CommonModule, FormsModule],
  templateUrl: './subscription.component.html',
  styleUrl: './subscription.component.css'
})
export class SubscriptionComponent {
  private readonly authService = inject(AuthService);
  private readonly subscriptionService = inject(SubscriptionService);

  subscriptions: Subscription[] = [];
  mediaId: number | null = null;
  plan = 'STANDARD';
  endDate = '';
  loading = false;
  actionLoading = false;
  errorMessage = '';
  successMessage = '';

  constructor() {
    this.loadSubscriptions();
  }

  loadSubscriptions(): void {
    const userId = this.authService.userId();
    if (!userId) {
      this.errorMessage = 'Utilisateur non identifié.';
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    this.subscriptionService.getByUserId(userId).subscribe({
      next: (data) => {
        this.subscriptions = data;
        this.loading = false;
      },
      error: (error: Error) => {
        this.errorMessage = error.message;
        this.loading = false;
      }
    });
  }

  createSubscription(): void {
    const userId = this.authService.userId();
    if (!userId || !this.mediaId || !this.endDate) {
      this.errorMessage = 'mediaId et endDate sont obligatoires.';
      return;
    }

    this.actionLoading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.subscriptionService.create({
      userId,
      mediaId: this.mediaId,
      plan: this.plan,
      endDate: this.endDate
    }).subscribe({
      next: () => {
        this.successMessage = 'Abonnement créé avec succès.';
        this.actionLoading = false;
        this.mediaId = null;
        this.endDate = '';
        this.loadSubscriptions();
      },
      error: (error: Error) => {
        this.errorMessage = error.message;
        this.actionLoading = false;
      }
    });
  }

  cancelSubscription(subscriptionId: number): void {
    this.actionLoading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.subscriptionService.cancel(subscriptionId).subscribe({
      next: () => {
        this.successMessage = 'Abonnement annulé.';
        this.actionLoading = false;
        this.loadSubscriptions();
      },
      error: (error: Error) => {
        this.errorMessage = error.message;
        this.actionLoading = false;
      }
    });
  }
}
