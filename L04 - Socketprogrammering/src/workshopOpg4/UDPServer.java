package workshopOpg4;

import java.net.*;

class UDPServer {

	public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(7777);
		//byte[] sendData = new byte[1024];
		while (true) {
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData());
			InetAddress IPAddress = receivePacket.getAddress();
			//int port = receivePacket.getPort();
			String sendSentence = IPAddress.getHostAddress() + ": " + sentence;
			//sendData = sendSentence.getBytes();
			//DatagramPacket sendPacket = new DatagramPacket(sendData,
			//		sendData.length, IPAddress, port);
			//serverSocket.send(sendPacket);
			System.out.println(sendSentence);
		}
	}
}
