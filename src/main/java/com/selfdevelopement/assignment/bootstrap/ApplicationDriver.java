package com.selfdevelopement.assignment.bootstrap;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.selfdevelopement.assignment.config.ProjectConfiguration;
import com.selfdevelopement.assignment.db.entities.User;
import com.selfdevelopement.assignment.resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ApplicationDriver extends Application<ProjectConfiguration> {
    private HibernateBundle<ProjectConfiguration> hibernate;

    public static void main(String args[]) throws Exception {
        new ApplicationDriver().run(args);
    }

    @Override
    public void run(ProjectConfiguration projectConfiguration, Environment environment) {
        Injector injector = Guice.createInjector(new ApplicationModule(hibernate));
        System.out.println("your configurations are " + projectConfiguration.getUrl());
        environment.jersey().register(injector.getInstance(UserResource.class));
    }

    @Override
    public void initialize(Bootstrap<ProjectConfiguration> bootstrap) {
        hibernate = buildHibernateBundle();
        bootstrap.addBundle(hibernate);

    }

    private HibernateBundle<ProjectConfiguration> buildHibernateBundle() {
        return new HibernateBundle<ProjectConfiguration>(User.class) {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(ProjectConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        };
    }
}