package com.example.media_service.service;

import com.example.media_service.dto.MediaRequestDTO;
import com.example.media_service.dto.MediaResponseDTO;

import java.util.List;

public interface MediaService {
    MediaResponseDTO create(MediaRequestDTO dto);
    MediaResponseDTO getById(Long id);
    List<MediaResponseDTO> getAll();
    List<MediaResponseDTO> getByCategory(String category);
    List<MediaResponseDTO> getByGenre(String genre);
    List<MediaResponseDTO> search(String keyword);
    MediaResponseDTO update(Long id, MediaRequestDTO dto);
    void delete(Long id);
}
