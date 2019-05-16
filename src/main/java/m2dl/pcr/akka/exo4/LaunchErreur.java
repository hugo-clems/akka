package m2dl.pcr.akka.exo4;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class LaunchErreur {

    public static void main(String[] args) {
        final ActorSystem actorSystem = ActorSystem.create("launchErreurSystem", ConfigFactory.load("erreur"));
        actorSystem.actorOf(Props.create(ErreurActor.class), "erreurActor");
    }

}
