package com.example.media_service.repository;

import com.example.media_service.entity.Media;
import com.example.media_service.enums.Category;
import com.example.media_service.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findByCategory(Category category);

    List<Media> findByGenre(Genre genre);

    List<Media> findByCategoryAndGenre(Category category, Genre genre);

    @Query("SELECT m FROM Media m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Media> searchByTitle(@Param("keyword") String keyword);
}
