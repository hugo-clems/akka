package m2dl.pcr.akka.exo3;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class InterActor extends UntypedActor {

    private ActorRef next;
    private ActorRef client;

    public InterActor(ActorRef next, ActorRef client) {
        this.next = next;
        this.client = client;
    }

    public void onReceive(Object msg) throws Exception {
        if (msg instanceof String) {
            Param p = new Param((String) msg, client);
            next.tell(p, getSelf());
        } else {
            unhandled(msg);
        }
    }

}
