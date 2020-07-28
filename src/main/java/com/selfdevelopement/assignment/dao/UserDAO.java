package com.selfdevelopement.assignment.dao;

import com.selfdevelopement.assignment.db.entities.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserDAO {


    public User addUser(User user) {
        return null;
    }

    public User getUser(String username) {
        return null;
    }
}