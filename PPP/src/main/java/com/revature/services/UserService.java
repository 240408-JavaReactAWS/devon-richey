package com.revature.services;

import com.revature.models.Movie;
import com.revature.models.User;
import com.revature.repos.MovieDAO;
import com.revature.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserDAO ud;

    private MovieDAO md;

    @Autowired
    public UserService(UserDAO ud, MovieDAO md) {
        this.ud = ud;
        this.md = md;
    }


    public User addUser(User user) {
        return ud.save(user);
    }

    public User getUserById(Integer id) {
        return ud.findById(id).orElse(null);
    }

    public User addWatchedMovie(Integer userId, Integer movieId) {
        User user = ud.findById(userId).orElse(null);
        Movie movie = md.findById(movieId).orElse(null);
        if (user != null && movie != null) {
            List<Movie> movieList = user.getMovies();
            movieList.add(movie);
            user.setMovies(movieList);
            return ud.save(user);
        }
        return null;
    }

    public User login(String username, String password) {
        User user = ud.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

}
