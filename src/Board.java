import java.awt.Color;
import java.util.ArrayList;


public interface Board {
	
	public void nextStep(Color couleur);
	public ArrayList<Color> getColorsFromPlayers();
	public ArrayList<Color> getFreeColors();
	
	public int getHauteur();
	public int getLargeur();
	public Player getJoueur1();
	public Player getJoueur2();
	public Player getJoueur3();
	public Player getJoueur4();
}
