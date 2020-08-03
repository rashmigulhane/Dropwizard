package com.selfdevelopement.assignment.bootstrap;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.selfdevelopement.assignment.config.ProjectConfiguration;
import com.selfdevelopement.assignment.db.entities.User;
import com.selfdevelopement.assignment.resource.QueueResources;
import com.selfdevelopement.assignment.resource.UserResource;
import com.selfdevelopement.assignment.resource.UserToQueueResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;
import io.prometheus.client.exporter.MetricsServlet;



public class ApplicationDriver extends Application<ProjectConfiguration> {

    public static void main(String args[]) throws Exception {
        new ApplicationDriver().run(args);
    }

    @Override
    public void run(ProjectConfiguration projectConfiguration, Environment environment) {

        /**
         * Adding Prometheus Exporter
         * This will intercept all the jmx metrics and convert into format which prometheus wants
         */
        CollectorRegistry collectorRegistry = new CollectorRegistry();
               MetricRegistry metricRegistry = environment.metrics();
             collectorRegistry.register(new DropwizardExports(metricRegistry));
              environment.admin().addServlet("metrics", new MetricsServlet(collectorRegistry))
                      .addMapping("/metrics");

        Injector injector = Guice.createInjector(new ApplicationModule());
        environment.jersey().register(injector.getInstance(UserResource.class));
        environment.jersey().register(injector.getInstance(QueueResources.class));
        environment.jersey().register(injector.getInstance(UserToQueueResource.class));

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