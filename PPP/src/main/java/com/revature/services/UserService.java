package com.revature.services;

import com.revature.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDAO ud;

    @Autowired
    public UserService(UserDAO ud) { this.ud = ud; }
}
