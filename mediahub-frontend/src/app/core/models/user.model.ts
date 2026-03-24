export type UserRole = 'ROLE_USER' | 'ROLE_ADMIN' | 'USER' | 'ADMIN' | string;

export interface User {
  id: number;
  username: string;
  email: string;
  role: UserRole;
  createdAt: string;
}

export interface SubscriptionStatusResponse {
  active: boolean;
}
