import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Media } from '../models/media.model';

@Injectable({ providedIn: 'root' })
export class MediaService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = '/api/media';

  getAll(): Observable<Media[]> {
    return this.http.get<Media[]>(this.baseUrl);
  }

  getById(id: number): Observable<Media> {
    return this.http.get<Media>(`${this.baseUrl}/${id}`);
  }

  getByCategory(category: string): Observable<Media[]> {
    const params = new HttpParams().set('category', category);
    return this.http.get<Media[]>(`${this.baseUrl}/search/category`, { params });
  }

  getByGenre(genre: string): Observable<Media[]> {
    const params = new HttpParams().set('genre', genre);
    return this.http.get<Media[]>(`${this.baseUrl}/search/genre`, { params });
  }
}
