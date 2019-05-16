package m2dl.pcr.akka.exo4;

import akka.actor.ActorRef;

import java.io.Serializable;

public class Param implements Serializable {

    private String message;

    private String client;

    public Param(String message, String client) {
        this.message = message;
        this.client = client;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

}
