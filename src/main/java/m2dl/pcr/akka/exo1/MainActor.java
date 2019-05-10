package m2dl.pcr.akka.exo1;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

public class MainActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef helloActorRef;
    private ActorRef byeActorRef;

    public MainActor() {
        log.info("MainActor constructor");
        helloActorRef = getContext().actorOf(Props.create(HelloActor.class), "hello-actor");
        byeActorRef = getContext().actorOf(Props.create(ByeActor.class), "bye-actor");
    }

    Procedure<Object> hello = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                helloActorRef.tell(msg, getSelf());
                getContext().become(goodbye,false);
            } else {
                unhandled(msg);
            }
        }
    };

    Procedure<Object> goodbye = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                byeActorRef.tell(msg, getSelf());
                getContext().unbecome();
            } else {
                unhandled(msg);
            }
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof String) {
            hello.apply(msg);
        } else {
            unhandled(msg);
        }
    }

}
