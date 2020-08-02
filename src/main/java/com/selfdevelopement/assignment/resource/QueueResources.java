package com.selfdevelopement.assignment.resource;

import com.codahale.metrics.annotation.Timed;
import com.selfdevelopement.assignment.api.QueueAPI;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/queue")
@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class QueueResources {
    private final QueueAPI queueAPI;

    @GET
    @Timed
    @Path("/get")
    @UnitOfWork
    public String welcome() {
        queueAPI.getQueueDetails();
        return "Hey welcome";
    }



    @GET
    @Timed
    @Path("/length")
    @UnitOfWork
    public String getlength() {
        return "length of the queue is " + queueAPI.getlength();
    }


    @GET
    @Timed
    @Path("/delete")
    @UnitOfWork
    public String getdeQueue() {
        boolean status = queueAPI.deQueue();
        return "DeQueue Operation was " + status;
    }

    @GET
    @Timed
    @Path("/peek")
    @UnitOfWork
    public String getpeekMessage() {
        String msg = queueAPI.peekMessage();
        return "Message Peek is " + msg;
    }


    @GET
    @Timed
    @Path("/addMsg")
    @UnitOfWork
    public String addMessageToQueue() {
        Boolean msg = queueAPI.addMessageToQueue("Hey hi");
        if(msg) {
            return "Message was successfully added to queue";
        } else {
            return "Operation Failed";
        }

    }
}
