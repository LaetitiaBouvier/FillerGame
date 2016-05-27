import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.tdebroc.filler.connector.PlayerConnector;
import com.tdebroc.filler.game.Game;

public class Web {
	
	final static String baseURL = "http://62.210.105.118:8081";

	public static PlayerConnector createGame(String pseudo, int taille){
		
		int gameID = PlayerConnector.addGame(baseURL, taille);

		PlayerConnector playerConnector = new PlayerConnector(gameID, baseURL);
		
		playerConnector.registerPlayer(pseudo);
		
		return playerConnector;
	}
	
	public static Game getGame(int gameID){
		
		PlayerConnector playerConnector = new PlayerConnector(gameID, baseURL);
		
		return playerConnector.getGame();
	}
	
	public static String obtenirMonAdresse(){
		
		String adresse = "";
		
		try {
			String adressesStr = (""+InetAddress.getLocalHost());
			
			String[] adresses = adressesStr.split("/");
			adresse = adresses[1];
			
		} catch (UnknownHostException e) { e.printStackTrace(); }
		
		
		return adresse;
	}
	
	public static void envoiePaquets(String adresse, String msg){
		
		try {
			DatagramSocket socket = new DatagramSocket();
			
			InetAddress serverAddress = InetAddress.getByName(adresse);
			
			byte[] buffer = msg.getBytes();
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, 2048);
			packet.setData(msg.getBytes());
			
			socket.send(packet);
			socket.close();
			
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public static String ecoutePaquets(){
		
		String msg = "";
		
		try {
			DatagramSocket socket = new DatagramSocket(2048);
			
			byte[] buffer = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			
			socket.receive(packet);
			System.out.println(new String(packet.getData(), 0, packet.getLength()));
			msg = new String(packet.getData(), 0, packet.getLength());
			
			socket.close();
			
		} catch ( IOException e) { e.printStackTrace(); }
		
		return msg;
	}
}
