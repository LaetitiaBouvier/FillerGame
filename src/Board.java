import java.awt.Color;
import java.util.ArrayList;

/**
 * <b>Cette Interface permet de définir le "contrat" passé à tous les types de Board l'implémentant </b>
 */
public interface Board {
	
	/**
	 * Cette fonction permet de faire progresser la partie
	 * 
	 * @param couleur ( Color ) : <br>couleur du mouvement à effectuer </br><br>
	 * 
	 * @return ( Player ) Retourne le prochain joueur devant joueuer seulement s'il s'agit d'une IA
	 */
	public Player nextMove(Color couleur);
	
	/**
	 * Cette fonction permet à une IA facile de jouer 
	 * 
	 * @return ( Color ) Retourne la couleur du mouvement effectué par l'IA
	 */
	public Color nextEasyIAMove();
	
	/**
	 * Cette fonction permet à une IA pénible de jouer
	 * 
	 * @param joueur ( Player ) : <br>joueur/IA jouant jouant le tour en cours </br><br>
	 * 
	 * @return ( Color ) Retourne la couleur du mouvement effectué par l'IA
	 */
	public Color nextTroubleIAMove(Player joueur);
	
	/**
	 * Cette fonction permet à une IA difficile de jouer
	 * 
	 * @param joueur ( Player ) : <br>joueur/IA jouant jouant le tour en cours </br><br>
	 * 
	 * @return ( Color ) Retourne la couleur du mouvement effectué par l'IA
	 */
	public Color nextHardIAMove(Player joueur);
	
	/**
	 * Cette fonction permet d'obtenir les couleurs occupées par les joueurs
	 * 
	 * @return ( ArrayList< Color > ) Retourne la liste des couleurs occupées par les joueurs
	 */
	public ArrayList<Color> getColorsFromPlayers();
	
	/**
	 * Cette fonction permet d'obtenir les couleurs disponibles
	 * 
	 * @return ( ArrayList< Color > ) Retourne la liste des couleurs disponibles
	 */
	public ArrayList<Color> getFreeColors();
	
	/**
	 * Cette fonction permet de savoir si la partie est terminée ou non
	 * 
	 * @return ( boolean ) Retourne si oui ou non la partie est terminée
	 */
	public boolean isTheGameOver();
	
	/**
	 * Cette fonction permet de générer une sauvegarde de la partie
	 * 
	 * @return ( String ) Retourne une sauvegarde de la partie
	 */
	public String generateSaveString();
	
	/**
	 * Cette fonction permet d'obtenir le gagnant de la partie si celle-ci est terminée
	 * 
	 * @return ( Player ) Retourne le gagnant de la partie
	 */
	public Player getWinner();
	
	/**
	 * Getter retournant la hauteur du tableau
	 * 
	 * @return ( int ) Retourne la hauteur du tableau
	 */
	public int getHauteur();
	
	/**
	 * Getter retournant la largeur du tableau
	 * 
	 * @return ( int ) Retourne la largeur du tableau
	 */
	public int getLargeur();
	
	/**
	 * Getter retournant le joueur 1
	 * 
	 * @return ( Player ) Retourne le joueur 1
	 */
	public Player getJoueur1();

	/**
	 * Getter retournant le joueur 2
	 * 
	 * @return ( Player ) Retourne le joueur 2
	 */
	public Player getJoueur2();
	
	/**
	 * Getter retournant le joueur 3
	 * 
	 * @return ( Player ) Retourne le joueur 3
	 */
	public Player getJoueur3();

	/**
	 * Getter retournant le joueur 4
	 * 
	 * @return ( Player ) Retourne le joueur 4
	 */
	public Player getJoueur4();
}
