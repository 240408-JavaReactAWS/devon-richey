package com.revature.controllers;


import com.revature.models.Movie;
import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService us;

    @Autowired
    public UserController(UserService us) { this.us = us; }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = us.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = us.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
       User loggedUser = us.login(user.getUsername(), user.getPassword());
        if (loggedUser != null) {
            return ResponseEntity.ok(loggedUser);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("{userId}/movies/{movieId}/watched")
    public ResponseEntity<User> addWatchedMovie(@PathVariable Integer userId, @PathVariable Integer movieId) {
        User user = us.addWatchedMovie(userId, movieId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/moviesWatched")
    public ResponseEntity<List<Movie>> getMoviesWatched(@RequestParam String username, @RequestParam String password) {
        User user = us.login(username, password);
        if (user != null) {
            List<Movie> movies = user.getMovies();
            return ResponseEntity.ok(movies);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
