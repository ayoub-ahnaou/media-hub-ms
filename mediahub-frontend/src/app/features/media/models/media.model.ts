export type MediaType = 'MOVIE' | 'SERIES' | 'PODCAST' | string;

export interface Media {
  id: number;
  title: string;
  type: MediaType;
  category: string;
  genre: string;
  description: string;
  releaseYear: number;
}
