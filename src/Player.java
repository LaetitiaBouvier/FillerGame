import java.awt.Color;
import java.util.ArrayList;

/**
 * <b> Cette classe permet la gestion des joueurs </b>
 */
public class Player {

	private String nom;
	private Color couleur;
	private int nbCases;
	private ArrayList<Cell> casesCtrl;
	private boolean myTurn;
	private String IA;
	private Boolean isWinner;

	/**
	 * Ce constructeur permet de cr�er un joueur 
	 * 
	 * @param nom		( String )				: <br> nom du joueur 								</br><br>
	 * @param couleur	( Color )				: <br> couleur du joueur 							</br><br>
	 * @param nbCases	( int )					: <br> nombre de cellules contr�l�es par le joueur 	</br><br>
	 * @param casesCtrl	( ArrayList< Cell > ) 	: <br> liste des cellules contr�l�es par le joueur 	</br><br>
	 * @param IA		( String )				: <br> type d'IA associ�e au joueur					</br><br>
	 */
	public Player(String nom, Color couleur, int nbCases, ArrayList<Cell> casesCtrl, String IA){
		
		this.nom = nom;
		this.couleur = couleur;
		this.nbCases = nbCases;
		this.casesCtrl = casesCtrl;
		this.myTurn = false;
		this.IA = IA;
		isWinner = null;
	}
	
	/**
	 * Getter permttant d'acc�der au nom du joueur
	 * 
	 * @return ( String ) retourne le nom du joueur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Setter permettant de modifier le nom du joueur
	 * 
	 * @param nom ( String ) : <br> nom du joueur </br><br>
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Getter permettant d'acc�der � la couleur du joueur
	 * 
	 * @return ( Color ) retourne la couleur du joueur
	 */
	public Color getCouleur() {
		return couleur;
	}
	
	/**
	 * Setter permettant de modifier la couleur du joueur
	 * 
	 * @param couleur ( Color ) : <br> couleur du joueur </br><br>
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * Getter permettant d'acc�der au nombre de cellules contr�l�es par le joueur
	 * 
	 * @return ( int ) retourne le nombre de cellules contr�l�es par le joueur
	 */
	public int getNbCases() {
		return nbCases;
	}
	
	/**
	 * Setter permettant de modifier le nombre de cellules contr�l�es par le joueur
	 * 
	 * @param nbCases ( int ) : <br> nombre de cellules contr�l�es par le joueur </br><br>
	 */
	public void setNbCases(int nbCases) {
		this.nbCases = nbCases;
	}
	
	/**
	 * Getter permettant d'acc�der � liste des cellules contr�l�es par le joueur
	 * 
	 * @return ( ArrayList< Cell > ) retourne la liste des cellules contr�l�es par le joueur
	 */
	public ArrayList<Cell> getCasesCtrl() {
		return casesCtrl;
	}
	
	/**
	 * Setter permettant de modifier la liste des cellules contr�l�es par le joueur
	 * 
	 * @param casesCtrl ( ArrayList< Cell > ) : <br> liste des cellules contr�l�es par le joueur </br><br>
	 */
	public void setCasesCtrl(ArrayList<Cell> casesCtrl) {
		this.casesCtrl = casesCtrl;
	}

	/**
	 * Getter permettant d'acc�der au tour du joueur
	 * 
	 * @return ( boolean ) retourne si c'est au tour du joueur de jouer
	 */
	public boolean isMyTurn() {
		return myTurn;
	}

	/**
	 * Setter permettant de modifier le tour du joueur
	 * 
	 * @param myTurn ( boolean ) : <br> est-ce au tour du joueur de jouer </br><br
	 */
	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	/**
	 * Getter permettant d'acc�der au type d'IA associ�e au joueur
	 * 
	 * @return ( String ) retourne le type d'IA associ�e au joueur
	 */
	public String getIA() {
		return IA;
	}

	/**
	 * Setter permettant de modifier le type d'IA associ�e au joueur
	 * 
	 * @param IA ( String ) : <br> type d'IA associ�e au joueur </br><br>
	 */
	public void setIA(String IA) {
		this.IA = IA;
	}

	/**
	 * Getter permettant de savoir si le joueur � gagn� ou non la partie
	 * 
	 * @return ( Boolean) retourne si le joueur a gagn� ou non la partie
	 */
	public Boolean getIsWinner() {
		return isWinner;
	}

	/**
	 * Setter permettant de modifier si le joueur a gagn� ou non la partie
	 * 
	 * @param isWinner ( Boolean ) : <br> le joueur a-t-il gagn� la partie </br><br>
	 */
	public void setIsWinner(Boolean isWinner) {
		this.isWinner = isWinner;
	}
	
}
