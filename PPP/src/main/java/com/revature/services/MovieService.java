package com.revature.services;

import com.revature.repos.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private MovieDAO md;

    @Autowired
    public MovieService(MovieDAO md) {
        this.md = md;
    }

    // TODO: Methods between Controller and DAO

}
