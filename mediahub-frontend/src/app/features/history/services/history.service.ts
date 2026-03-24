import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { AddHistoryRequest, WatchHistory } from '../../../core/models/history.model';

@Injectable({ providedIn: 'root' })
export class HistoryService {
  private readonly http = inject(HttpClient);
  private readonly usersBaseUrl = '/api/users';

  getHistoryByUser(userId: number): Observable<WatchHistory[]> {
    return this.http.get<WatchHistory[]>(`${this.usersBaseUrl}/${userId}/history`);
  }

  addOrUpdateHistory(userId: number, payload: AddHistoryRequest): Observable<WatchHistory> {
    return this.http.post<WatchHistory>(`${this.usersBaseUrl}/${userId}/history`, payload);
  }

  deleteHistory(userId: number, historyId: number): Observable<string> {
    return this.http.delete(`${this.usersBaseUrl}/${userId}/history/${historyId}`, { responseType: 'text' });
  }

  clearHistory(userId: number): Observable<string> {
    return this.http.delete(`${this.usersBaseUrl}/${userId}/history`, { responseType: 'text' });
  }
}
