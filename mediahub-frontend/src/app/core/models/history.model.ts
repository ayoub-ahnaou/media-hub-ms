export interface WatchHistory {
  id: number;
  userId: number;
  username: string;
  mediaId: number;
  progress: number;
  watchedAt: string;
}

export interface AddHistoryRequest {
  mediaId: number;
  progress: number;
}
