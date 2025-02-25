package workshopOpg4;

import java.io.*;
import java.net.*;

class UDPClient {
	public static void main(String args[]) throws Exception {
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));
		DatagramSocket clientSocket = new DatagramSocket();
		clientSocket.setBroadcast(true);
		InetAddress IPAddress = InetAddress.getByName("255.255.255.255");
		//byte[] receiveData = new byte[1024];
		boolean samtale = true;
		while (samtale) {
			byte[] sendData = new byte[1024];
			String sentence = inFromUser.readLine();
			if (sentence.equals("END")) {
				samtale = false;
				clientSocket.close();
			} else {
				sendData = sentence.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData,
						sendData.length, IPAddress, 7777);
				clientSocket.send(sendPacket);
				//DatagramPacket receivePacket = new DatagramPacket(receiveData,
				//		receiveData.length);
				//clientSocket.receive(receivePacket);
				//String modifiedSentence = new String(receivePacket.getData());
				//System.out.println(modifiedSentence);
			}
		}
	}
}
