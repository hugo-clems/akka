package m2dl.pcr.akka.exo4;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;

public class InterActor extends UntypedActor {

    private String next;
    private String client;

    public InterActor(String next, String client) {
        this.next = next;
        this.client = client;
    }

    public void onReceive(Object msg) throws Exception {
        if (msg instanceof String) {
            Param p = new Param((String) msg, client);
            ActorSelection selection = getContext().actorSelection(next);
            selection.tell(p, getSelf());
        } else {
            unhandled(msg);
        }
    }

}
