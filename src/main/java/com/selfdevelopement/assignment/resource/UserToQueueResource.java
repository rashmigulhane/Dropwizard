package com.selfdevelopement.assignment.resource;

import com.codahale.metrics.annotation.Timed;
import com.selfdevelopement.assignment.api.QueueCallAPI;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/sv2")
@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class UserToQueueResource {
    private final QueueCallAPI queueCallAPI;


    @GET
    @Timed
    @Path("/insert")
    @UnitOfWork
    public String welcome() {
        queueCallAPI.insert();
        return "Hey welcome";
    }

}
