package m2dl.pcr.akka.exo4;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;

public class LookUpActor extends UntypedActor {

    private static String urlCrypteur =  "";

    public LookUpActor(String urlCrypteur) {
        this.urlCrypteur = urlCrypteur;
    }

    public void onReceive(Object p) throws Exception {
        ActorSelection selection = getContext().actorSelection(urlCrypteur);
        selection.tell(p, getSelf());
    }

}
