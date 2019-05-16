package m2dl.pcr.akka.exo4;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaunchMain {

    private static final String URL_CRYPTEUR = "akka.tcp://launchCrypteurSystem@127.0.0.1:2553/user/crypteurActor";
    private static final String URL_ERREUR = "akka.tcp://launchErreurSystem@127.0.0.1:2554/user/erreurActor";
    private static final String URL_INTER = "akka.tcp://launchSystem@127.0.0.1:2555/user/interActor";
    private static final String URL_RECEPTEUR = "akka.tcp://launchSystem@127.0.0.1:2555/user/recepteurActor";

    public static final Logger log = LoggerFactory.getLogger(LaunchMain.class);

    public static void main(String... args) throws Exception {
        final ActorSystem actorSystem = ActorSystem.create("launchSystem", ConfigFactory.load("inter"));
        Thread.sleep(5000);

        actorSystem.actorOf(Props.create(InterActor.class, URL_ERREUR, URL_RECEPTEUR), "interActor");
        actorSystem.actorOf(Props.create(RecepteurActor.class), "recepteurActor");

        final ActorRef lookUpActor = actorSystem.actorOf(Props.create(LookUpActor.class, URL_CRYPTEUR), "lookUpActor");

        // Envoie du message
        lookUpActor.tell(new Param("Coucou", URL_INTER), null);

        Thread.sleep(1000);
        log.debug("Actor LaunchMain Shutdown Starting...");
        actorSystem.terminate();
    }

}
