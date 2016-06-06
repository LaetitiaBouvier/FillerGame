import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.tdebroc.filler.connector.PlayerConnector;
import com.tdebroc.filler.game.Game;

/**
 * <b> Cette classe permet de r�aliser la plupart des fonctions de gestion de r�seau</b>
 *
 */
public class Web {
	
	final static String baseURL = "http://62.210.105.118:8081";

	/**
	 * Cette fonction permet de cr�er une partie pour l'IA comp�titive
	 */
	public static PlayerConnector createGame(String pseudo, int taille){
		
		int gameID = PlayerConnector.addGame(baseURL, taille);

		PlayerConnector playerConnector = new PlayerConnector(gameID, baseURL);
		
		playerConnector.registerPlayer(pseudo);
		
		return playerConnector;
	}
	
	/**
	 * Cette fonction permet de r�cup�rer une partie lanc�e pour l'IA comp�titive
	 */
	public static Game getGame(int gameID){
		
		PlayerConnector playerConnector = new PlayerConnector(gameID, baseURL);
		
		return playerConnector.getGame();
	}
	
	/**
	 * Cette fonction permet de r�cup�rer l'adresse r�seau d'un joueur
	 * 
	 * @return ( String ) Retourne l'adresse r�seau d'un joueur
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
	 * Cette fonction permet d'envoyer un message � son adversaire en r�seau
	 * 
	 * @param adresse 	( String ) : <br> adresse r�seau de l'adversaire </br><br>
	 * @param msg		( String ) : <br> message destin� � l'adversaire </br><br>
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
	 * Cette fonction permet d'�couter les messages envoy�s par l'adversaire
	 * 
	 * @return ( String ) Retourne le message envoy� par l'adversaire
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
