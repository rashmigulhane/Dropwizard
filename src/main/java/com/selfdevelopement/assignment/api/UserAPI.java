package com.selfdevelopement.assignment.api;

import com.selfdevelopement.assignment.dao.UserDAO;
import com.selfdevelopement.assignment.db.entities.User;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class UserAPI {
    private final UserDAO userDAO;

    public boolean verifyUserCredentials(String username,
                                         String password) {
        boolean validUser = false;
        User user = userDAO.getUser(username);
        if(user!=null && user.getPassword().equals(password)) {
            validUser = true;
        }
        return validUser;
    }

    public void addUser(String username,
                           String password) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        userDAO.addUser(user);
    }
}