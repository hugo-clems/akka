package m2dl.pcr.akka.stringservices;

import akka.actor.UntypedActor;

public class CrypteurActor extends UntypedActor {

    public void onReceive(Object p) throws Exception {
        if (p instanceof Param) {
            Param localP = (Param) p;
            localP.getClient().tell(StringUtils.crypte(localP.getMessage()), getSelf());
        } else {
            unhandled(p);
        }
    }

}
