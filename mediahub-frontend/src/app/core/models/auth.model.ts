export interface LoginRequest {
  username: string;
  password: string;
  email?: string;
}

export interface LoginResponse {
  token: string;
  username: string;
}
