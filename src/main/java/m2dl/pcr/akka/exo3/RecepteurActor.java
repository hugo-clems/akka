package m2dl.pcr.akka.exo3;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import m2dl.pcr.akka.stringservices.StringUtils;

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
