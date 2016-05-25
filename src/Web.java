import com.tdebroc.filler.connector.PlayerConnector;
import com.tdebroc.filler.game.Game;

public class Web {
	
	final static String baseURL = "http://62.210.105.118:8081";

	public static Game createGame(String pseudo, int taille){
		
		int gameID = PlayerConnector.addGame(baseURL, taille);

		PlayerConnector playerConnector = new PlayerConnector(gameID, baseURL);
		
		playerConnector.registerPlayer(pseudo);
		Game game = playerConnector.getGame();
		
		return game;
	}
}
