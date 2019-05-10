package m2dl.pcr.akka.stringservices;

import akka.actor.UntypedActor;

public class ErreurActor extends UntypedActor {

    public void onReceive(Object p) throws Exception {
        if (p instanceof Param) {
            Param localP = (Param) p;
            localP.getClient().tell(StringUtils.ajouteCtrl(localP.getMessage()), getSelf());
        } else {
            unhandled(p);
        }
    }
}
