import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Media } from '../../models/media.model';
import { MediaService } from '../../services/media.service';

@Component({
  selector: 'app-media-detail',
  imports: [CommonModule, RouterLink],
  templateUrl: './media-detail.component.html',
  styleUrl: './media-detail.component.css'
})
export class MediaDetailComponent {
  private readonly route = inject(ActivatedRoute);
  private readonly mediaService = inject(MediaService);

  media?: Media;
  loading = false;
  errorMessage = '';

  constructor() {
    this.loadMedia();
  }

  private loadMedia(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    const id = Number(idParam);

    if (!idParam || Number.isNaN(id)) {
      this.errorMessage = 'Identifiant média invalide.';
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    this.mediaService.getById(id).subscribe({
      next: (data) => {
        this.media = data;
        this.loading = false;
      },
      error: (error: Error) => {
        this.errorMessage = error.message;
        this.loading = false;
      }
    });
  }
}
