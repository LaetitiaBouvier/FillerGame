import java.awt.Color;
import java.util.ArrayList;

/**
 * Cette interface permet de définir le contrat que doivent respecter les Board IntroBoard, SquareBoard, DiamondBoard et HexaBoard
 */
public interface Board {
	
	public Player nextMove(Color couleur);
	public Color nextEasyIAMove();
	public Color nextTroubleIAMove(Player joueur);
	public Color nextHardIAMove(Player joueur);
	public ArrayList<Color> getColorsFromPlayers();
	public ArrayList<Color> getFreeColors();
	public boolean isTheGameOver();
	public String generateSaveString();
	public Player getWinner();
	
	public int getHauteur();
	public int getLargeur();
	public Player getJoueur1();
	public Player getJoueur2();
	public Player getJoueur3();
	public Player getJoueur4();
}
