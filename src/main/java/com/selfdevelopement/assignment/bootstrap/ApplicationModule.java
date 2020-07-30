package com.selfdevelopement.assignment.bootstrap;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import io.dropwizard.setup.Environment;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;

@RequiredArgsConstructor
public class ApplicationModule extends AbstractModule {


    @Override
    protected void configure() {

    }



    @Provides
    @Singleton
    public MetricRegistry provideRegistry( Environment environment ) {

        CollectorRegistry collectorRegistry = new CollectorRegistry();
        MetricRegistry metricRegistry = environment.metrics();
        collectorRegistry.register(new DropwizardExports(metricRegistry));
        environment.admin().addServlet("metrics", new MetricsServlet(collectorRegistry))
                .addMapping("/metrics");
        return metricRegistry;
    }


   /* @Provides
    @Singleton
    public Configuration getConfiguration() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        try {
            configuration.setDirectoryForTemplateLoading(new File("/Users/rashmi.gulhane/Documents/Codebase/assignment/src/templates"));
            configuration.setDefaultEncoding("UTF-8");
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            configuration.setWrapUncheckedExceptions(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return configuration;
    }*/
}