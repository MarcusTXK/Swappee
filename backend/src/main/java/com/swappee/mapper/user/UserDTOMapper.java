package com.swappee.mapper.user;

import com.swappee.domain.user.User;
import com.swappee.mapper.DTOMapper;
import com.swappee.model.user.UserDTO;
import com.swappee.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper implements DTOMapper<UserDTO, User> {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public UserDTO mapEntity(User entity) {
        logger.info("Start mapping - mapEntity: {}", entity);
        if (entity == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setVersion(entity.getVersion());
        dto.setDeleted(entity.isDeleted());

        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        //front end does not need Avatar BLOB
//        dto.setAvatar(entity.getAvatar());
        dto.setPhone(entity.getPhone());
        dto.setEmailOnly(entity.isEmailOnly());
        dto.setRole(entity.getRole().toString());
        dto.setScore(entity.getScore());
        dto.setTotalTraded(entity.getTotalTraded());
        dto.setResetToken(entity.getResetToken());
        logger.info("End mapping - mapEntity: {}", entity);
        return dto;
    }

    @Override
    public User mapDto(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        User entity = new User();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setVersion(dto.getVersion());
        entity.setDeleted(dto.isDeleted());

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setAvatar(dto.getAvatar());
        entity.setPhone(dto.getPhone());
        entity.setEmailOnly(dto.isEmailOnly());
        entity.setRole(User.Role.valueOf(dto.getRole()));
        entity.setScore(dto.getScore());
        entity.setTotalTraded(dto.getTotalTraded());
        entity.setResetToken((dto.getResetToken()));

        return entity;
    }
}
