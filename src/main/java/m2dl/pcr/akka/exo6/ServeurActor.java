package m2dl.pcr.akka.exo6;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.List;

public class ServeurActor extends UntypedActor {

    private List<Message> messages;
    private List<ActorRef> clients;

    public ServeurActor() {
        messages = new ArrayList<Message>();
        clients = new ArrayList<ActorRef>();
    }

    public void onReceive(Object o) throws Exception {
        if (o instanceof Inscription) {
            inscription((Inscription) o);
        } else if (o instanceof Message) {
            messagerie((Message) o);
        } else {
            unhandled(o);
        }
    }

    private void inscription(Inscription i) {
        clients.add(i.getReference());
    }

    private void messagerie(Message m) {
        messages.add(m);

        for (ActorRef client : clients) {
            client.tell(messages, getSelf());
        }
    }

}
