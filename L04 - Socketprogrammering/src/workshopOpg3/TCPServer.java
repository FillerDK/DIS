package workshopOpg3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

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
}
