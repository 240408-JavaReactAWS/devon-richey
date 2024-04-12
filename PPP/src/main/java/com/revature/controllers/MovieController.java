package com.revature.controllers;

import com.revature.models.Movie;
import com.revature.services.MovieService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService ms;

    @Autowired
    public MovieController(MovieService ms) {
        this.ms = ms;
    }

    // Create New Movie
    @PostMapping
    public Movie createMovieHandler(@RequestBody Movie movie) {
        return ms.createMovie(movie);
    }

    // View All Movies
    @GetMapping
    public List<Movie> getAllMoviesHandler() {
        return ms.getAllMovies();
    }

    // View Single Movie By ID
    @GetMapping("{id}")
    public ResponseEntity<Movie> getMovieByIdHandler(@PathVariable int id) {
        Movie movie;
        try {
            movie = ms.findMovieById(id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        return new ResponseEntity<>(movie, OK);
    }

    // Update an Item
    @PatchMapping("{id}")
    public ResponseEntity<Movie> updateMovieByIdHandler(@PathVariable int id, @RequestBody Movie newMovie) {
        Movie movie;
        try {
            movie = ms.updateMovieById(id, newMovie);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        return new ResponseEntity<>(movie, OK);
    }

    // Delete an Item by ID
    @DeleteMapping("{id}")
    public ResponseEntity<Movie> deleteMovieByIdHandler(@PathVariable int id) {
        Movie movie;
        try {
            movie = ms.deleteMovieById(id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        return new ResponseEntity<>(movie, OK);
    }


}
