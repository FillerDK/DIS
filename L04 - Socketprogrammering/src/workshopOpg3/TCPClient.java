package workshopOpg3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class TCPClient {

	public static void main(String[] args) throws Exception, IOException {
		
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
				if (sentence.equals("END")) {
					System.out.println("KONSOL: Samtale afsluttet, lukker ned...");
					t1.slutSamtale();
					samtale = false;
				}
			}
		}
	}

}
