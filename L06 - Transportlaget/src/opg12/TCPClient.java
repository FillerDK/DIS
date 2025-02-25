package opg12;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;


public class TCPClient {

	public static void main(String[] args) throws Exception, IOException {
		ArrayList<Person> personer = new ArrayList<>();
		personer.add(new Person(0, "Philip", "Randers"));
		personer.add(new Person(1, "Nils", "Aarhus"));
		personer.add(new Person(2, "Jens", "Pas"));
		personer.add(new Person(3, "Mads", "Aarhus"));

		String sentence;
		String modifiedSentence = "";
		
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("KONSOL: Skriv venligst modtagers ip: ");
		Socket clientSocket = new Socket(inFromUser.readLine(), 16000);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		
		BufferedReader inFromServer = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream()));

		System.out.print("KONSOL: Tag initiativ som (navn): ");
		sentence = "Hello;" + inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');

		String clientIP = clientSocket.getInetAddress().getHostAddress();
		System.out.println("KONSOL: Anmodning om chat sendt til " + clientIP);

		if (inFromServer.readLine().equalsIgnoreCase("NEJ")) {
			System.out.println("KONSOL: " + clientIP + " vil ikke chatte med dig...");
			System.out.println("KONSOL: Lukker ned...");
			clientSocket.close();
		} else {
			ThreadClient t1 = new ThreadClient(inFromServer, clientIP);
			t1.start();
			System.out.println("KONSOL: " + clientIP +
					" vil gerne chatte med dig, send en besked :)");
			boolean samtale = true;
			while (samtale) {
				//System.out.print("MIG: ");
				sentence = inFromUser.readLine();
				outToServer.writeBytes(sentence + '\n');
				switch (sentence) {
					case "END":
						System.out.println("KONSOL: Samtale afsluttet, lukker ned...");
						t1.slutSamtale();
						samtale = false;
					case "send personer":
						System.out.println("KONSOL: Sender personer til modtager.");
				}
			}
		}
	}

	public void sendPersoner(ArrayList<Person> a, DataOutputStream outStream) {
		for (int i = 0; i < a.size(); i++) {
			Person p = a.get(i);

			try {
				outStream.writeBytes("" + p.getId());
				outStream.writeBytes(p.getNavn());
				outStream.writeBytes(p.getBy() + '\n');
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
