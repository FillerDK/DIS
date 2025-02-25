package workshopOpg3.gui.contents;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import workshopOpg3.TextFieldInputStream;
import workshopOpg3.gui.Gui;

import java.io.*;
import java.net.Socket;

public class ChatContent {

    private final GridPane pane;

    private Label lblOverskrift = new Label("Chat");
    private TextArea txaChat = new TextArea();
    private TextField txfInput = new TextField();

    private final Gui gui;

    private Socket clientSocket;
    DataOutputStream outToServer;
    BufferedReader inFromUser;

    public ChatContent(Gui gui, Socket clientSocket, DataOutputStream outToServer) throws IOException {
        this.gui = gui;
        this.clientSocket = clientSocket;
        this.outToServer = outToServer;

        InputStream inputStream = new TextFieldInputStream(txfInput);
        this.inFromUser = new BufferedReader(new InputStreamReader(inputStream));

        pane = new GridPane();

        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        pane.add(lblOverskrift, 0, 0);

        txaChat.setPrefWidth(300);
        txaChat.setEditable(false);
        pane.add(txaChat, 0, 1);

        txfInput.setPrefWidth(300);
        pane.add(txfInput, 0, 2);
    }

    private void sendMessage() {

    }


    public void recieveMessage(String text) {
        txaChat.setText(txaChat.getText() + text);
    }

    public GridPane getPane() {
        return pane;
    }
}
