package com.p3.chatop.controller;

import com.p3.chatop.dto.RentalDto;
import com.p3.chatop.entity.Rental;
import com.p3.chatop.entity.User;
import com.p3.chatop.service.RentalService;
import com.p3.chatop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(Map.of("rentals", rentalService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(rentalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestParam String name,
            @RequestParam Double surface,
            @RequestParam Double price,
            @RequestParam String description,
            @RequestParam MultipartFile picture,
            Authentication authentication
    ) throws IOException {

        String email = authentication.getName();
        User owner = userService.findByEmail(email).orElseThrow();

        String fileName = UUID.randomUUID() + "_" + picture.getOriginalFilename();
        Path path = Paths.get("uploads/" + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, picture.getBytes());

        Rental rental = new Rental();
        rental.setName(name);
        rental.setSurface(surface);
        rental.setPrice(price);
        rental.setDescription(description);
        //rental.setPicture("/uploads/" + fileName);
        rental.setPicture("http://localhost:8080/uploads/" + fileName);
        rental.setOwner(owner);

        rentalService.save(rental);

        return ResponseEntity.ok(Map.of("message", "Rental created !"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody RentalDto dto
    ) {

        Rental rental = rentalService.findById(id);

        rental.setName(dto.getName());
        rental.setSurface(dto.getSurface());
        rental.setPrice(dto.getPrice());
        rental.setDescription(dto.getDescription());

        rentalService.save(rental);

        return ResponseEntity.ok(Map.of("message", "Rental updated !"));
    }
}