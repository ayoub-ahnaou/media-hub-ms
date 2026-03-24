import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { WatchHistory } from '../../../../core/models/history.model';
import { AuthService } from '../../../../core/services/auth.service';
import { HistoryService } from '../../services/history.service';

@Component({
  selector: 'app-history',
  imports: [CommonModule, FormsModule],
  templateUrl: './history.component.html',
  styleUrl: './history.component.css'
})
export class HistoryComponent {
  private readonly authService = inject(AuthService);
  private readonly historyService = inject(HistoryService);

  history: WatchHistory[] = [];
  mediaId: number | null = null;
  progress = 0;
  loading = false;
  actionLoading = false;
  errorMessage = '';
  successMessage = '';

  constructor() {
    this.loadHistory();
  }

  loadHistory(): void {
    const userId = this.authService.userId();
    if (!userId) {
      this.errorMessage = 'Utilisateur non identifié.';
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    this.historyService.getHistoryByUser(userId).subscribe({
      next: (data) => {
        this.history = data;
        this.loading = false;
      },
      error: (error: Error) => {
        this.errorMessage = error.message;
        this.loading = false;
      }
    });
  }

  addHistory(): void {
    const userId = this.authService.userId();
    if (!userId || !this.mediaId) {
      this.errorMessage = 'mediaId est obligatoire.';
      return;
    }

    this.actionLoading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.historyService.addOrUpdateHistory(userId, {
      mediaId: this.mediaId,
      progress: this.progress
    }).subscribe({
      next: () => {
        this.successMessage = 'Historique mis à jour.';
        this.mediaId = null;
        this.progress = 0;
        this.actionLoading = false;
        this.loadHistory();
      },
      error: (error: Error) => {
        this.errorMessage = error.message;
        this.actionLoading = false;
      }
    });
  }

  deleteOne(historyId: number): void {
    const userId = this.authService.userId();
    if (!userId) {
      return;
    }

    this.actionLoading = true;
    this.errorMessage = '';

    this.historyService.deleteHistory(userId, historyId).subscribe({
      next: () => {
        this.actionLoading = false;
        this.loadHistory();
      },
      error: (error: Error) => {
        this.errorMessage = error.message;
        this.actionLoading = false;
      }
    });
  }

  clearAll(): void {
    const userId = this.authService.userId();
    if (!userId) {
      return;
    }

    this.actionLoading = true;
    this.errorMessage = '';

    this.historyService.clearHistory(userId).subscribe({
      next: () => {
        this.successMessage = 'Historique vidé.';
        this.actionLoading = false;
        this.loadHistory();
      },
      error: (error: Error) => {
        this.errorMessage = error.message;
        this.actionLoading = false;
      }
    });
  }
}
