package m2dl.pcr.akka.exo2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class System {

    private static final int N = 20;

    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String... args) throws Exception {
        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        Thread.sleep(5000);

        final ActorRef erato = actorSystem.actorOf(Props.create(EratoActor.class, 2), "erato-actor");
        for (int i = 3; i <= N; i++) {
            erato.tell(i, null);
        }

        Thread.sleep(1000);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }

}
