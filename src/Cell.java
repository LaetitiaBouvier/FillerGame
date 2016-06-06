import java.awt.Color;


/**
 *	<b>Cette Interface permet de définir le "contrat" passé à tous les types de Cell l'implémentant </b>
 */
public interface Cell {

	/**
	 * Getter retournant l'abscisse du centre de la cellule
	 * 
	 * @return ( int ) Retourne l'abscisse du centre de la cellule
	 */
	public int getCentreX();
	/**
	 * Setter paramètrant l'abscisse du centre de la cellule
	 * 
	 * @param centreX	( int ) : <br>abscisse du centre de la cellule </br>
	 */
	public void setCentreX(int centreX);

	/**
	 * Getter retournant l'ordonnée du centre de la cellule
	 * 
	 * @return ( int ) Retourne l'ordonnée du centre de la cellule
	 */
	public int getCentreY();
	/**
	 * Setter paramètrant l'ordonnée du centre de la cellule
	 * 
	 * @param centreY	( int ) : <br>ordonnée du centre de la cellule </br>
	 */
	public void setCentreY(int centreY);

	/**
	 * Getter retournant la couleur de la cellule
	 * 
	 * @return ( Color ) Retourne la couleur de la cellule
	 */
	public Color getColor();
	/**
	 * Setter permettant de paramètrer la couleur de la cellule
	 * 
	 * @param color	( Color ) : <br>couleur de la cellule </br><br>
	 */
	public void setColor(Color color);

	/**
	 * Getter retournant le nom du joueur contrôlant la cellule
	 * 
	 * @return ( String ) Retourne le nom du joueur contrôlant la cellule
	 */
	public String getCtrlBy();
	/**
	 * Setter paramètrant le nom du joueur contrôlant la cellule
	 * 
	 * @param ctrlBy ( String )	: <br>nom du joueur contrôlant la cellule </b>
	 */
	public void setCtrlBy(String ctrlBy);
	
}
