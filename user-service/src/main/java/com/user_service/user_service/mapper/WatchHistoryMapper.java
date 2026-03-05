package com.user_service.user_service.mapper;
import com.user_service.user_service.dto.WatchHistoryRequestDTO;
import com.user_service.user_service.dto.WatchHistoryResponseDTO;
import com.user_service.user_service.model.WatchHistory;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface WatchHistoryMapper {

    // ✅ WatchHistory → WatchHistoryResponseDTO
    // user.id   → userId
    // user.username → username
    @Mapping(source = "user.id",       target = "userId")
    @Mapping(source = "user.username", target = "username")
    WatchHistoryResponseDTO toDTO(WatchHistory watchHistory);

    // ✅ WatchHistoryRequestDTO → WatchHistory
    // user et watchedAt sont ignorés : assignés manuellement dans le service
    @Mapping(target = "id",        ignore = true)
    @Mapping(target = "user",      ignore = true)
    @Mapping(target = "watchedAt", ignore = true)
    WatchHistory toEntity(WatchHistoryRequestDTO dto);
}