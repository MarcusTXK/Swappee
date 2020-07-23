package com.swappee.mapper.user;

import com.swappee.domain.user.User;
import com.swappee.model.user.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {
    public UserDTO mapEntity(User entity) {
        if (entity == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastModifiedDate(entity.getLastModifiedDate());

        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        dto.setAvatar(entity.getAvatar());
        dto.setPhone(entity.getPhone());
        dto.setEmailOnly(entity.isEmailOnly());
        dto.setRole(entity.getRole());
        dto.setScore(entity.getScore());
        dto.setTotalTraded(entity.getTotalTraded());

        return dto;
    }

    public User mapDto(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        User entity = new User();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setAvatar(dto.getAvatar());
        entity.setPhone(dto.getPhone());
        entity.setEmailOnly(dto.isEmailOnly());
        entity.setRole(dto.getRole());
        entity.setScore(dto.getScore());
        entity.setTotalTraded(dto.getTotalTraded());

        return entity;
    }
}
