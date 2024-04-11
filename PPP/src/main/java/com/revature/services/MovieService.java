package com.revature.services;

import com.revature.models.Movie;
import com.revature.repos.MovieDAO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieDAO md;

    @Autowired
    public MovieService(MovieDAO md) {
        this.md = md;
    }

    // TODO: Methods between Controller and DAO

    // Create a new Item
    public Movie createMovie(Movie movie) {
        return md.save(movie);
    }

    // View all Items
    public List<Movie> getAllMovies() {
        return md.findAll();
    }

    // View Single Item by ID
    public Movie findMovieById(int id) {
        return md.findById(id).orElseThrow(() -> new EntityNotFoundException("No Movie found with id: " + id));
    }

    // Update an Item
    public Movie updateMovieById(int id, Movie newMovie) {
        Optional<Movie> optionalMovie = md.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setGenre(newMovie.getGenre());
            movie.setTitle(newMovie.getTitle());
            movie.setReleaseYear(newMovie.getReleaseYear());
            md.save(movie);
            return movie;
        }
        throw new EntityNotFoundException("No Movie found with id: " + id);
    }

    // Delete an Item by ID
    public Movie deleteMovieById(int id) {
        Optional<Movie> optionalMovie = md.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            md.deleteById(id);
            return movie;
        }
        throw new EntityNotFoundException("No Movie found with id: " + id);
    }

}
