import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { SubscriptionStatusResponse, User } from '../../../core/models/user.model';

@Injectable({ providedIn: 'root' })
export class UserService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = '/api/users';

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${id}`);
  }

  getSubscriptionStatus(userId: number): Observable<SubscriptionStatusResponse> {
    return this.http.get<SubscriptionStatusResponse>(`${this.baseUrl}/${userId}/subscription-status`);
  }
}
