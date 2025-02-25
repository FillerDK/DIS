package workshopOpg3.gui.contents;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import workshopOpg3.gui.Gui;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectContent {

    private final GridPane pane;

    private TextField txfPort = new TextField();
    private TextField txfIP = new TextField();
    private TextField txfName = new TextField();

    private final Gui gui;

    private Socket clientSocket;
    private DataOutputStream outToServer;

    public ConnectContent(Gui gui, Socket clientSocket, DataOutputStream outToServer) {
        this.gui = gui;
        this.clientSocket = clientSocket;
        this.outToServer = outToServer;

        pane = new GridPane();

        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblPort = new Label("Indtast port:");
        pane.add(lblPort, 0, 1);

        txfPort.setPrefWidth(300);
        pane.add(txfPort, 0, 2);

        Label lblIP = new Label("Indtast modtagers IP adresse:");
        pane.add(lblIP, 0, 3);

        txfIP.setPrefWidth(300);
        pane.add(txfIP, 0, 4);

        Label lblName = new Label("Navn du vil fremstå som:");
        pane.add(lblName, 0, 5);

        txfName.setPrefWidth(300);
        pane.add(txfName, 0, 6);

        Button btnConnect = new Button("Connect");
        GridPane.setHalignment(btnConnect, HPos.CENTER);
        pane.add(btnConnect, 0, 7);

        btnConnect.setOnAction(event -> connectAction());
    }

    public void connectAction() {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        String ip = txfIP.getText().trim(); // ip address of host
        int port = 0;

        boolean error = false;
        try {
            port = Integer.parseInt(txfPort.getText().trim()); // port of host
        } catch (NumberFormatException e) {
            error = true;
            alert.setHeaderText("Port have to be a number!");
            alert.setContentText("Try with another port number");
            alert.showAndWait();
        }

        if (!error) {
            String navn = txfName.getText().trim();
            gui.connect(ip, port, navn);
        }
    }

    public GridPane getPane() {
        return pane;
    }
}
