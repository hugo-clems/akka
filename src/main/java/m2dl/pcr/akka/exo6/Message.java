package m2dl.pcr.akka.exo6;

import java.io.Serializable;

public class Message implements Serializable {

    private String texte;

    public Message(String texte) {
        this.texte = texte;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    @Override
    public String toString() {
        return "Message{" +
                "texte='" + texte + '\'' +
                '}';
    }
}
