package m2dl.pcr.akka.stringservices;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class RecepteurActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public void onReceive(Object msg) throws Exception {
        if (msg instanceof String) {
            String message = StringUtils.decrypte(StringUtils.verifieCtrl((String) msg));
            log.info("\n\t" + message);
        } else {
            unhandled(msg);
        }
    }

}
