export interface Subscription {
  id: number;
  userId: number;
  mediaId: number;
  plan: string;
  status: 'ACTIVE' | 'CANCELLED' | 'EXPIRED' | string;
  startDate: string;
  endDate: string;
}

export interface CreateSubscriptionRequest {
  userId: number;
  mediaId: number;
  plan: string;
  endDate: string;
}
