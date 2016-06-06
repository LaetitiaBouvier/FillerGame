import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <b> </b>
 */
public class HexaWebBoard extends HexaBoard implements Board{
	
	private static final long serialVersionUID = 1L;
	
	private String monAdresse;
	private String sonAdresse;
	
	/**
	 * Constructeur du joueur créant la partie en ligne
	 * 
	 * @param nb			( int )		: <br> taille d'un côté du tableau 		</br><br>
	 * @param nomJoueur1	( String ) 	: <br> nom du joueur local 				</br><br>
	 * @param monAdresse	( String ) 	: <br> adresse du joueur local 			</br><br>
	 * @param sonAdresse	( String ) 	: <br> addresse du joueur se connectant </br><br>
	 */
	public HexaWebBoard(int nb, String nomJoueur1, String nomJoueur2, String monAdresse, String sonAdresse) {
		
		super(nb, nomJoueur1, nomJoueur2, "", "", "Sans", "Sans", "Sans", "Sans");
		
		setMonAdresse(monAdresse);
		setSonAdresse(sonAdresse);
		
		//MAJ Joueur1
		ArrayList<Cell> hexasCtrl = this.getJoueur1().getCasesCtrl();
		
		for(int i = 0; i < hexasCtrl.size(); i++){
			hexasCtrl.get(i).setColor(this.getJoueur1().getCouleur());
		}
		
		hexasCtrl = getConnectedCellsOfSameColor(hexasCtrl);
		
		this.getJoueur1().setCasesCtrl(hexasCtrl);
		for(Cell cell : hexasCtrl){
			cell.setCtrlBy(this.getJoueur1().getNom());
		}
		
		//MAJ Joueur2
		hexasCtrl = this.getJoueur2().getCasesCtrl();
		
		for(int i = 0; i < hexasCtrl.size(); i++){
			hexasCtrl.get(i).setColor(this.getJoueur2().getCouleur());
		}
		
		hexasCtrl = getConnectedCellsOfSameColor(hexasCtrl);
		
		this.getJoueur2().setCasesCtrl(hexasCtrl);
		for(Cell cell : hexasCtrl){
			cell.setCtrlBy(this.getJoueur2().getNom());
		}
		
		//Génération et envois
		String saveStr = generateSaveString();
		
		Web.envoiePaquets(sonAdresse, saveStr);
	}
	
	/**
	 * Constructeur du joueur rejoignant la partie en ligne
	 * 
	 * @param saveStr		( String )	: <br> sauvegarde envoyée par le joueur hébergeant la partie</br><br>
	 * @param monAdresse	( String ) 	: <br> adresse du joueur local 								</br><br>
	 * @param sonAdresse 	( String ) 	: <br> addresse du joueur se connectant 					</br><br>
	 */
	public HexaWebBoard(String saveStr, String monAdresse, String sonAdresse){
		
		super(saveStr);
		
		setMonAdresse(monAdresse);
		setSonAdresse(sonAdresse);
		
		System.out.println("La cell [0][0] est ctrl par : "+this.grille[0][0].getCtrlBy());
		
		saveStr = Web.ecoutePaquets();
		
		Scanner scLine = new Scanner(saveStr);
		
		scLine.next();
		int nb = Integer.parseInt(scLine.next());
		scLine.close();
		
		initialisationGrille(nb, saveStr);
		
		this.grille = defVoisins(this.grille);
		
		defJoueurs(nb, saveStr);
	}
	
	/**
	 */
	@Override
	public Player nextMove(Color couleur){
		
		boolean flag = true;
		
		Player joueurAct = null;
		
		if(this.joueur1.isMyTurn() && flag){
			
			flag = false;
			
			joueurAct = this.joueur1;
			
			this.joueur1.setMyTurn(false);
			this.joueur2.setMyTurn(true);
		}
		
		if(this.joueur2.isMyTurn() && flag){
			
			flag = false;
			
			joueurAct = this.joueur2;

			this.joueur2.setMyTurn(false);
			this.joueur1.setMyTurn(true);
		}
		
		ArrayList<Cell> hexasCtrl = joueurAct.getCasesCtrl();
		
		for(int i = 0; i < hexasCtrl.size(); i++){
			hexasCtrl.get(i).setColor(couleur);
		}
		
		hexasCtrl = getConnectedCellsOfSameColor(hexasCtrl);
		
		joueurAct.setCasesCtrl(hexasCtrl);
		for(Cell cell : hexasCtrl){
			cell.setCtrlBy(joueurAct.getNom());
		}
		
		String saveStr = generateSaveString();
		
		Web.envoiePaquets(sonAdresse, saveStr);
		
		update(this.getGraphics());
		
		return null;
	}
	
	/**
	 * Cette fonction d'attendre le prochain coup de l'adversaire et de rafraichir son tableau en concéquence
	 */
	public void waitAndListen(){
		
		String saveStr = Web.ecoutePaquets();
		
		Scanner scLine = new Scanner(saveStr);
		
		scLine.next();
		int nb = Integer.parseInt(scLine.next());
		scLine.close();
		
		initialisationGrille(nb, saveStr);
		
		this.grille = defVoisins(this.grille);
		
		defJoueurs(nb, saveStr);
		
		update(this.getGraphics());
	}

	/**
	 * Getter permettant d'accéder à mon adresse
	 * 
	 * @return ( String ) Retourne mon adresse
	 */
	public String getMonAdresse() {
		return monAdresse;
	}

	/**
	 * Setter permettant de modifier mon adresse
	 * 
	 * @param monAdresse ( String ) : <br> adresse local </br><br>
	 */
	public void setMonAdresse(String monAdresse) {
		this.monAdresse = monAdresse;
	}

	/**
	 * Getter permettant d'accéder à mon adresse
	 * 
	 * @return ( String ) Retourne son adresse
	 */
	public String getSonAdresse() {
		return sonAdresse;
	}

	/**
	 * Setter permettant de modifier son adresse
	 * 
	 * @param sonAdresse ( String ) Retourne son adresse
	 */
	public void setSonAdresse(String sonAdresse) {
		this.sonAdresse = sonAdresse;
	}
}
