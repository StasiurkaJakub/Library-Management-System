package com.tss.ProjektJakubStasiurka.mapper;

import com.tss.ProjektJakubStasiurka.dto.UserDTO;
import com.tss.ProjektJakubStasiurka.model.User;

public class UserMapper {

    // Entity -> DTO
    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}
