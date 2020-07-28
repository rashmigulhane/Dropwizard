package com.selfdevelopement.assignment.bootstrap;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.selfdevelopement.assignment.config.ProjectConfiguration;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import io.dropwizard.hibernate.HibernateBundle;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
public class ApplicationModule extends AbstractModule {


    @Override
    protected void configure() {

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