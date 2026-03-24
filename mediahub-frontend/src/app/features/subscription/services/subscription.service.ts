import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateSubscriptionRequest, Subscription } from '../../../core/models/subscription.model';

@Injectable({ providedIn: 'root' })
export class SubscriptionService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = '/api/subscriptions';

  getByUserId(userId: number): Observable<Subscription[]> {
    return this.http.get<Subscription[]>(`${this.baseUrl}/users/${userId}`);
  }

  create(payload: CreateSubscriptionRequest): Observable<Subscription> {
    return this.http.post<Subscription>(this.baseUrl, payload);
  }

  cancel(subscriptionId: number): Observable<Subscription> {
    return this.http.patch<Subscription>(`${this.baseUrl}/${subscriptionId}/cancel`, {});
  }
}
