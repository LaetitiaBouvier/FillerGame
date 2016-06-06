import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.tdebroc.filler.connector.PlayerConnector;
import com.tdebroc.filler.game.Game;

/**
 * <b> Cette classe permet de réaliser la plupart des fonctions de gestion de réseau</b>
 *
 */
public class Web {
	
	final static String baseURL = "http://62.210.105.118:8081";

	/**
	 * Cette fonction permet de créer une partie pour l'IA compétitive
	 */
	public static PlayerConnector createGame(String pseudo, int taille){
		
		int gameID = PlayerConnector.addGame(baseURL, taille);

		PlayerConnector playerConnector = new PlayerConnector(gameID, baseURL);
		
		playerConnector.registerPlayer(pseudo);
		
		return playerConnector;
	}
	
	/**
	 * Cette fonction permet de récupérer une partie lancée pour l'IA compétitive
	 */
	public static Game getGame(int gameID){
		
		PlayerConnector playerConnector = new PlayerConnector(gameID, baseURL);
		
		return playerConnector.getGame();
	}
	
	/**
	 * Cette fonction permet de récupérer l'adresse réseau d'un joueur
	 * 
	 * @return ( String ) Retourne l'adresse réseau d'un joueur
	 */
	public static String obtenirMonAdresse(){
		
		String adresse = "";
		
		try {
			String adressesStr = (""+InetAddress.getLocalHost());
			
			String[] adresses = adressesStr.split("/");
			adresse = adresses[1];
			
		} catch (UnknownHostException e) { e.printStackTrace(); }
		
		
		return adresse;
	}
	
	/**
	 * Cette fonction permet d'envoyer un message à son adversaire en réseau
	 * 
	 * @param adresse 	( String ) : <br> adresse réseau de l'adversaire </br><br>
	 * @param msg		( String ) : <br> message destiné à l'adversaire </br><br>
	 */
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
	
	/**
	 * Cette fonction permet d'écouter les messages envoyés par l'adversaire
	 * 
	 * @return ( String ) Retourne le message envoyé par l'adversaire
	 */
	public static String ecoutePaquets(){
		
		String msg = "";
		
		try {
			DatagramSocket socket = new DatagramSocket(2048);
			
			byte[] buffer = new byte[32768];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			
			socket.receive(packet);
			System.out.println(new String(packet.getData(), 0, packet.getLength()));
			msg = new String(packet.getData(), 0, packet.getLength());
			
			socket.close();
			
		} catch ( IOException e) { e.printStackTrace(); }
		
		return msg;
	}
}
