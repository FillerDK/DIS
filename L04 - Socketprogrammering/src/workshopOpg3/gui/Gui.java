package workshopOpg3.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import workshopOpg3.ThreadClient;
import workshopOpg3.gui.contents.ChatContent;
import workshopOpg3.gui.contents.ConnectContent;
import workshopOpg3.gui.contents.GuiContent;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Gui extends Application {

    private StackPane stackPane;
    private ConnectContent connectContent;
    private ChatContent chatContent;

    @Override
    public void start(Stage mainStage) {
        Stage window = mainStage;
        window.setTitle("Client");
        stackPane = new StackPane();

        initContent();
        Scene clientChat = new Scene(stackPane);

        window.setScene(clientChat);
        window.show();
    }

    private TextField txfPort = new TextField();
    private TextField txfIP = new TextField();

    private Socket clientSocket;
    private DataOutputStream outToServer;
    private BufferedReader inFromServer;

    public void initContent() {
        connectContent = new ConnectContent(this, clientSocket, outToServer);

        stackPane.getChildren().add(connectContent.getPane());
    }

    public void connect(String ip, int port, String navn) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            clientSocket = new Socket(ip, port);
            connected(navn);
        } catch (IOException e) {
            alert.setHeaderText("Something went wrong...");
            alert.setContentText("Try again!");
            alert.showAndWait();
        }
    }

    private void connected(String navn) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        boolean connected = true;

        try {
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );
            outToServer.writeBytes("Hello;" + navn + '\n');
        } catch (IOException e) {
            alert.setHeaderText("Something went wrong...");
            alert.setContentText("When typing to the server, something went wrong...");
            alert.showAndWait();
            connected = false;
        }

        if (connected) {
            try {
                chatContent = new ChatContent(this, clientSocket, outToServer);
            } catch (IOException e) {
                alert.setHeaderText("Something went wrong...");
                alert.setContentText("Chat content not created...");
                alert.showAndWait();
            }
            connectContent.getPane().setVisible(false);
            stackPane.getChildren().add(chatContent.getPane());

        }
    }

    public void messageRevieced(String message) {
        chatContent.recieveMessage(message);
    }
}