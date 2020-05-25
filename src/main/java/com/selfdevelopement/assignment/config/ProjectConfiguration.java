package com.selfdevelopement.assignment.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectConfiguration extends Configuration {
    private String url;
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }
}