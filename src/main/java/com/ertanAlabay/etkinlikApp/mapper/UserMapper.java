package com.ertanAlabay.etkinlikApp.mapper;

import com.ertanAlabay.etkinlikApp.dto.UserDTO;
import com.ertanAlabay.etkinlikApp.model.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        return user;
    }
}
