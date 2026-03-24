import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Media } from '../../models/media.model';
import { MediaService } from '../../services/media.service';

@Component({
  selector: 'app-catalog',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './catalog.component.html',
  styleUrl: './catalog.component.css'
})
export class CatalogComponent {
  private readonly mediaService = inject(MediaService);

  medias: Media[] = [];
  loading = false;
  errorMessage = '';
  category = '';
  genre = '';

  constructor() {
    this.loadAll();
  }

  loadAll(): void {
    this.loading = true;
    this.errorMessage = '';

    this.mediaService.getAll().subscribe({
      next: (data) => {
        this.medias = data;
        this.loading = false;
      },
      error: (error: Error) => {
        this.errorMessage = error.message;
        this.loading = false;
      }
    });
  }

  searchByCategory(): void {
    if (!this.category.trim()) {
      this.loadAll();
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    this.mediaService.getByCategory(this.category.trim()).subscribe({
      next: (data) => {
        this.medias = data;
        this.loading = false;
      },
      error: (error: Error) => {
        this.errorMessage = error.message;
        this.loading = false;
      }
    });
  }

  searchByGenre(): void {
    if (!this.genre.trim()) {
      this.loadAll();
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    this.mediaService.getByGenre(this.genre.trim()).subscribe({
      next: (data) => {
        this.medias = data;
        this.loading = false;
      },
      error: (error: Error) => {
        this.errorMessage = error.message;
        this.loading = false;
      }
    });
  }

  resetFilters(): void {
    this.category = '';
    this.genre = '';
    this.loadAll();
  }
}
