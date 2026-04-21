package com.p3.chatop.service;

import com.p3.chatop.entity.Rental;
import com.p3.chatop.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;

    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    public Rental findById(Integer id) {
        return rentalRepository.findById(id).orElseThrow();
    }

    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }
}