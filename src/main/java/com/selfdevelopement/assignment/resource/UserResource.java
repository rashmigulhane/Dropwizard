package com.selfdevelopement.assignment.resource;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.annotation.Gauge;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.selfdevelopement.assignment.api.UserAPI;
import freemarker.template.Configuration;
import freemarker.template.Template;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.RequiredArgsConstructor;


import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.microsoft.applicationinsights.TelemetryClient;

@Path("/")
@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class UserResource {
    private final UserAPI userAPI;
    private final Configuration configuration;

    private TelemetryClient telemetry = new TelemetryClient();

    // Create a Dropwizard counter.

    @GET
    @Timed
    @Path("/")
    @UnitOfWork
    public String welcome() {
        telemetry.trackMetric("queueLength", 42.0);
        return "Hey welcome";
    }


    @GET
    @Timed(name = "time")
    @Path("/PPM")
    @Metered(name = "meteredd")
    @UnitOfWork
    public String emitingPrometheus() {
        return "Hey welcome";
    }

    @POST
    @Timed
    @Path("/login")
    @UnitOfWork
    public Response verifyUser(@NotNull @QueryParam("Username") String username,
                              @NotNull @QueryParam("Password") String password) {
         boolean isValid = userAPI.verifyUserCredentials(username, password);
         return formatResponse(isValid, username);
    }

    @POST
    @Timed
    @Path("/register")
    @UnitOfWork
    public boolean addUser(@NotNull @QueryParam("Username") String username,
                           @NotNull @QueryParam("Password") String password) {
         userAPI.addUser(username, password);
         return true;
    }

    private Response formatResponse(boolean isValid, String username) {
        try {
            Template temp;
            if(isValid)
                temp = configuration.getTemplate("Welcome.ftl");
            else
                temp = configuration.getTemplate("Invalid.ftl");
            Map root = new HashMap();
            root.put("user", username);
            Writer writer = new StringWriter();
            temp.process(root, writer);
            return Response.status(Response.Status.ACCEPTED).entity((writer.toString())).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(("Oops! Try again later")).build();
    }
}