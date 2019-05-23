package m2dl.pcr.akka.exo6;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.List;

public class ClientActor extends UntypedActor {

    private List<Message> messages;
    private ActorRef serveur;
    private ClientIHM clientIHM;

    public ClientActor(ActorRef serveur, ClientIHM clientIHM) {
        this.messages = new ArrayList<Message>();
        this.serveur = serveur;
        this.clientIHM = clientIHM;
    }

    public void onReceive(Object o) throws Exception {
        if (o instanceof String) {
            sendMessage((String) o);
        } else if (o instanceof List) {
            loadMessages((List<Message>) o);
        } else {
            unhandled(o);
        }
    }

    private void sendMessage(String texte) {
        Message msg = new Message(texte);
        serveur.tell(msg, getSelf());
    }

    public void loadMessages(List<Message> messages) {
        List<String> messagesTexte = new ArrayList<String>();
        for (Message message : messages) {
            messagesTexte.add(message.getTexte());
        }
        clientIHM.loadMessages(messagesTexte);
    }

}
