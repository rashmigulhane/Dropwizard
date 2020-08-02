package com.selfdevelopement.assignment.api;

import com.selfdevelopement.assignment.util.HttpOperation;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class QueueCallAPI {
    private final HttpOperation httpOperation;

    public void insert() {
        try {
            httpOperation.sendGET("http://azure-vote-back:8000/queue/addMsg");
        } catch (Exception e) {

        }

    }

}
