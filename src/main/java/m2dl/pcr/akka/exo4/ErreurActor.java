package m2dl.pcr.akka.exo4;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import m2dl.pcr.akka.stringservices.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErreurActor extends UntypedActor {

    public void onReceive(Object p) throws Exception {
        if (p instanceof Param) {
            Param localP = (Param) p;
            ActorSelection selection = getContext().actorSelection(localP.getClient());
            selection.tell(StringUtils.ajouteCtrl(localP.getMessage()), getSelf());
        } else {
            unhandled(p);
        }
    }
}
