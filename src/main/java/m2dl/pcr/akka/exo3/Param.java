package m2dl.pcr.akka.exo3;

import akka.actor.ActorRef;

import java.io.Serializable;

public class Param implements Serializable {

    private String message;

    private ActorRef client;

    public Param(String message, ActorRef client) {
        this.message = message;
        this.client = client;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ActorRef getClient() {
        return client;
    }

    public void setClient(ActorRef client) {
        this.client = client;
    }

}
