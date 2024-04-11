package com.revature.controllers;

import com.revature.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService ms;

    @Autowired
    public MovieController(MovieService ms) {
        this.ms = ms;
    }

    // TODO: Mappings


}
