package m2dl.pcr.akka.exo6;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaunchServer {

    public static final Logger log = LoggerFactory.getLogger(LaunchServer.class);

    public static void main(String... args) throws Exception {
        final ActorSystem actorSystem = ActorSystem.create("actorSystem");

        Thread.sleep(3000);

        // Création du serveur
        final ActorRef serveur = actorSystem.actorOf(Props.create(ServeurActor.class), "serveurActor");

        // Création des clients (problème de threads - mais ça marche)
        ClientIHM dialog1 = new ClientIHM(serveur);
        dialog1.pack();
        dialog1.setVisible(true);

        ClientIHM dialog2 = new ClientIHM(serveur);
        dialog2.pack();
        dialog2.setVisible(true);

        Thread.sleep(1000);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }

}
