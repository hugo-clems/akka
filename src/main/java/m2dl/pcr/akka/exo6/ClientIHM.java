package m2dl.pcr.akka.exo6;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.UUID;

public class ClientIHM extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField messageToSend;
    private JList allMessages;
    private UUID id = UUID.randomUUID();
    private ActorRef client;

    public ClientIHM(ActorRef serveur) {
        // Création du Client
        final ActorSystem actorSystem = ActorSystem.create(id + "actorSystem");
        client = actorSystem.actorOf(Props.create(ClientActor.class, serveur, this), id + "clientActor");
        serveur.tell(new Inscription(client), null);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        client.tell(messageToSend.getText(), null);
        messageToSend.setText("");
    }

    private void onCancel() {
        dispose();
    }

    public void loadMessages(List<String> messages) {
        allMessages.setListData(messages.toArray());
    }

    /*public static void main(String[] args) {
        ClientIHM dialog = new ClientIHM();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }*/

}
