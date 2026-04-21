package com.p3.chatop.controller;

import com.p3.chatop.dto.MessageDto;
import com.p3.chatop.entity.Message;
import com.p3.chatop.entity.Rental;
import com.p3.chatop.entity.User;
import com.p3.chatop.service.MessageService;
import com.p3.chatop.service.RentalService;
import com.p3.chatop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody MessageDto dto) {

        if (dto.getUser_id() == null || dto.getRental_id() == null || dto.getMessage() == null) {
            return ResponseEntity.badRequest().body("{}");
        }

        User user = userService.findById(dto.getUser_id());
        Rental rental = rentalService.findById(dto.getRental_id());

        Message message = new Message();
        message.setUser(user);
        message.setRental(rental);
        message.setMessage(dto.getMessage());

        messageService.save(message);

        return ResponseEntity.ok(Map.of("message", "Message send with success"));
    }
}