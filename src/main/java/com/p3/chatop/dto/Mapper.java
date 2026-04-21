package com.p3.chatop.dto;

import com.p3.chatop.entity.Rental;
import com.p3.chatop.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public UserResponseDto toUserDto(User u) {
        return new UserResponseDto(
                u.getId(),
                u.getName(),
                u.getEmail(),
                u.getCreatedAt(),
                u.getUpdatedAt()
        );
    }

    public RentalResponseDto toRentalDto(Rental r) {
        return new RentalResponseDto(
                r.getId(),
                r.getName(),
                r.getSurface(),
                r.getPrice(),
                r.getPicture(),
                r.getDescription(),
                r.getOwner().getId(),
                r.getCreatedAt(),
                r.getUpdatedAt()
        );
    }
}