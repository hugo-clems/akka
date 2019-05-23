package m2dl.pcr.akka.exo6;

import akka.actor.ActorRef;

import java.io.Serializable;

public class Inscription implements Serializable {

    private ActorRef reference;

    public Inscription(ActorRef reference) {
        this.reference = reference;
    }

    public ActorRef getReference() {
        return reference;
    }

    public void setReference(ActorRef reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "Inscription{" +
                "reference=" + reference +
                '}';
    }
}
