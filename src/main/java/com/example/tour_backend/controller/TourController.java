package com.example.tour_backend.controller;

import com.example.tour_backend.domain.tour.Tour;
import com.example.tour_backend.domain.user.User;
import com.example.tour_backend.domain.tour.TourRepository;
import com.example.tour_backend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/tours")
@RequiredArgsConstructor
public class TourController {
    private final TourRepository tourRepository;
    private final UserRepository userRepository;

    @GetMapping
    public List<Tour> getAll() {
        return tourRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tour getById(@PathVariable Long id) {
        return tourRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tour not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tour create(@RequestBody Tour tour) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        tour.setCreateDate(now.toLocalDateTime());
        tour.setModifiedDate(now.toLocalDateTime());
        return tourRepository.save(tour);
    }

    @PutMapping("/{id}")
    public Tour update(@PathVariable Long id, @RequestBody Tour updated) {
        return tourRepository.findById(id).map(t -> {
            t.setTitle(updated.getTitle());
            t.setStartDate(updated.getStartDate());
            t.setEndDate(updated.getEndDate());
            t.setModifiedDate(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
            return tourRepository.save(t);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tour not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        tourRepository.deleteById(id);
    }
}
