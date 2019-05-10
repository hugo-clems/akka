package m2dl.pcr.akka.exo2;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;
import m2dl.pcr.akka.exo1.ByeActor;
import m2dl.pcr.akka.exo1.HelloActor;

public class EratoActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private int p;
    private ActorRef next;

    public EratoActor(int p) {
        log.info("\n\t" + p);
        this.p = p;
    }

    public EratoActor(int p, ActorRef next) {
        log.info("\n\t" + p);
        this.p = p;
        this.next = next;
    }

    Procedure<Object> bout = new Procedure<Object>() {
        public void apply(Object n) throws Exception {
            if (n instanceof Integer && ((Integer) n % p) != 0) {
                next = getContext().actorOf(Props.create(EratoActor.class, n), n + "-actor");
                getContext().become(inter, false);
            } else {
                unhandled(n);
            }
        }
    };

    Procedure<Object> inter = new Procedure<Object>() {
        public void apply(Object n) throws Exception {
            if (n instanceof Integer && ((Integer) n % p) != 0) {
                next.tell(n, getSelf());
            } else {
                unhandled(n);
            }
        }
    };

    @Override
    public void onReceive(Object n) throws Exception {
        if (n instanceof Integer) {
            bout.apply(n);
        } else {
            unhandled(n);
        }
    }

}
