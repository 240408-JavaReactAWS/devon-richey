package com.revature.services;

import com.revature.models.Movie;
import com.revature.models.User;
import com.revature.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDAO ud;

    @Autowired
    public UserService(UserDAO ud) { this.ud = ud; }

    public User addUser(User user) {
        return ud.save(user);
    }

    public User getUserById(Integer id) {
        return ud.findById(id).orElse(null);
    }

    public User addWatchedMovie(Integer userId, Movie movie) {
        User user = ud.findById(userId).orElse(null);
        if (user != null) {
            user.getMovies().add(movie);
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
