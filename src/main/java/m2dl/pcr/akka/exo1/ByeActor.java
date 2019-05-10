package m2dl.pcr.akka.exo1;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ByeActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public ByeActor() {
        log.info("ByeActor constructor");
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof String) {
            log.info("Good bye " + msg);
        } else {
            unhandled(msg);
        }
    }

}
