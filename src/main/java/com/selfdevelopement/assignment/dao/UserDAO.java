package com.selfdevelopement.assignment.dao;

import com.selfdevelopement.assignment.db.entities.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserDAO extends AbstractDAO<User> {

    @Inject
    public UserDAO(SessionFactory sessionFactory) {

        super(sessionFactory);
    }

    public User addUser(User user) {
        return persist(user);
    }

    public User getUser(String username) {
        return get(username);
    }
}