package com.example.media_service.service;

import com.example.media_service.dto.MediaRequestDTO;
import com.example.media_service.dto.MediaResponseDTO;
import com.example.media_service.entity.Media;
import com.example.media_service.enums.Category;
import com.example.media_service.enums.Genre;
import com.example.media_service.exception.MediaNotFoundException;
import com.example.media_service.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    private MediaResponseDTO toDTO(Media m) {
        return MediaResponseDTO.builder()
            .id(m.getId())
            .title(m.getTitle())
            .description(m.getDescription())
            .category(m.getCategory())
            .genre(m.getGenre())
            .director(m.getDirector())
            .releaseYear(m.getReleaseYear())
            .thumbnailUrl(m.getThumbnailUrl())
            .createdAt(m.getCreatedAt())
            .build();
    }

    @Override
    public MediaResponseDTO create(MediaRequestDTO dto) {
        Media media = Media.builder()
            .title(dto.getTitle())
            .description(dto.getDescription())
            .category(dto.getCategory())
            .genre(dto.getGenre())
            .director(dto.getDirector())
            .releaseYear(dto.getReleaseYear())
            .thumbnailUrl(dto.getThumbnailUrl())
            .streamUrl(dto.getStreamUrl())
            .build();
        return toDTO(mediaRepository.save(media));
    }

    @Override
    public MediaResponseDTO getById(Long id) {
        return mediaRepository.findById(id)
            .map(this::toDTO)
            .orElseThrow(() -> new MediaNotFoundException("Media not found: " + id));
    }

    @Override
    public List<MediaResponseDTO> getAll() {
        return mediaRepository.findAll().stream()
            .map(this::toDTO)
            .toList();
    }

    @Override
    public List<MediaResponseDTO> getByCategory(String category) {
        return mediaRepository.findByCategory(Category.valueOf(category.toUpperCase()))
            .stream().map(this::toDTO).toList();
    }

    @Override
    public List<MediaResponseDTO> getByGenre(String genre) {
        return mediaRepository.findByGenre(Genre.valueOf(genre.toUpperCase()))
            .stream().map(this::toDTO).toList();
    }

    @Override
    public List<MediaResponseDTO> search(String keyword) {
        return mediaRepository.searchByTitle(keyword)
            .stream().map(this::toDTO).toList();
    }

    @Override
    public MediaResponseDTO update(Long id, MediaRequestDTO dto) {
        Media media = mediaRepository.findById(id)
            .orElseThrow(() -> new MediaNotFoundException("Media not found: " + id));
        media.setTitle(dto.getTitle());
        media.setDescription(dto.getDescription());
        media.setCategory(dto.getCategory());
        media.setGenre(dto.getGenre());
        media.setDirector(dto.getDirector());
        media.setReleaseYear(dto.getReleaseYear());
        media.setThumbnailUrl(dto.getThumbnailUrl());
        media.setStreamUrl(dto.getStreamUrl());
        return toDTO(mediaRepository.save(media));
    }

    @Override
    public void delete(Long id) {
        if (!mediaRepository.existsById(id)) {
            throw new MediaNotFoundException("Media not found: " + id);
        }
        mediaRepository.deleteById(id);
    }
}
