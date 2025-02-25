package opg12;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer {
	static boolean samtale = true;

	public static void main(String[] args) throws Exception {
		System.out.println("SERVER: Server startet!");

		String sentence;
		String clientSentence;
		ServerSocket welcomSocket = new ServerSocket(6789);

		while(true){
			System.out.println("SERVER: Venter på opringning...");
			Socket connectionSocket = welcomSocket.accept();
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			String[] clientName = inFromClient.readLine().split(";");

			if (clientName[0].equals("Hello")) {
				System.out.println("SERVER: " + clientName[1] + " vil gerne chatte med dig...");
				System.out.print("SERVER: Vil du chatte (JA/NEJ)? ");
				sentence = inFromUser.readLine();

				if (sentence.equals("NEJ")) {
					outToClient.writeBytes(sentence + '\n');
				} else {
					ThreadServer t1 = new ThreadServer(inFromClient, clientName[1]);
					t1.start();
					outToClient.writeBytes(sentence + '\n');
					while (samtale) {
						//System.out.print("MIG: ");
						sentence = inFromUser.readLine();
						outToClient.writeBytes(sentence + '\n');
					}
				}
			}
		}
	}

	public void modtagPersoner(ArrayList<Person> a, BufferedReader inStream) {
		String[] input;
		try {
			input = inStream.readLine().split(" ");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		for (int i = 0; i < a.size(); i++) {
			a.add(new Person(Integer.parseInt(input[0]), input[1], input[2]));
		}
	}
}
