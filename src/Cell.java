import java.awt.Color;


/**
 *	<b>Cette Interface permet de d�finir le "contrat" pass� � tous les types de Cell l'impl�mentant </b>
 */
public interface Cell {

	/**
	 * Getter retournant l'abscisse du centre de la cellule
	 * 
	 * @return ( int ) Retourne l'abscisse du centre de la cellule
	 */
	public int getCentreX();
	/**
	 * Setter param�trant l'abscisse du centre de la cellule
	 * 
	 * @param centreX	( int ) : <br>abscisse du centre de la cellule </br>
	 */
	public void setCentreX(int centreX);

	/**
	 * Getter retournant l'ordonn�e du centre de la cellule
	 * 
	 * @return ( int ) Retourne l'ordonn�e du centre de la cellule
	 */
	public int getCentreY();
	/**
	 * Setter param�trant l'ordonn�e du centre de la cellule
	 * 
	 * @param centreY	( int ) : <br>ordonn�e du centre de la cellule </br>
	 */
	public void setCentreY(int centreY);

	/**
	 * Getter retournant la couleur de la cellule
	 * 
	 * @return ( Color ) Retourne la couleur de la cellule
	 */
	public Color getColor();
	/**
	 * Setter permettant de param�trer la couleur de la cellule
	 * 
	 * @param color	( Color ) : <br>couleur de la cellule </br><br>
	 */
	public void setColor(Color color);

	/**
	 * Getter retournant le nom du joueur contr�lant la cellule
	 * 
	 * @return ( String ) Retourne le nom du joueur contr�lant la cellule
	 */
	public String getCtrlBy();
	/**
	 * Setter param�trant le nom du joueur contr�lant la cellule
	 * 
	 * @param ctrlBy ( String )	: <br>nom du joueur contr�lant la cellule </b>
	 */
	public void setCtrlBy(String ctrlBy);
	
}
