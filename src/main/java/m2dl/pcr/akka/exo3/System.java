package m2dl.pcr.akka.exo3;

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

        Thread.sleep(3000);

        final ActorRef recepteur = actorSystem.actorOf(Props.create(RecepteurActor.class), "recepteur-actor");
        final ActorRef crypteur = actorSystem.actorOf(Props.create(CrypteurActor.class), "crypteur-actor");
        final ActorRef erreur = actorSystem.actorOf(Props.create(ErreurActor.class), "erreur-actor");
        final ActorRef inter = actorSystem.actorOf(Props.create(InterActor.class, erreur, recepteur), "inter-actor");

        // -> CryptageProvider -> Recepteur
        //crypteur.tell(new Param("Coucou", recepteur), null);

        // -> ErreurControleProvider -> Recepteur
        //erreur.tell(new Param("Coucou", recepteur), null);

        // -> CryptageProvider -> ErreurControleProvider -> Recepteur
        crypteur.tell(new Param("Coucou", inter), null);

        Thread.sleep(1000);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }

}
