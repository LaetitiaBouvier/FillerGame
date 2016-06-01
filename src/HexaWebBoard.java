import java.awt.Color;
import java.util.ArrayList;

public class HexaWebBoard extends HexaBoard implements Board{
	
	private String monAdresse;
	private String sonAdresse;
	
	private int idLocal;

	/**
	 * Constructeur du joueur créant la partie 
	 * 
	 * @param nb
	 * @param nomJoueur1
	 * @param monAdresse
	 * @param sonAdresse
	 */
	public HexaWebBoard(int nb, String nomJoueur1, String nomJoueur2, String monAdresse, String sonAdresse) {
		
		super(nb, nomJoueur1, nomJoueur2, "", "", "Sans", "Sans", "Sans", "Sans");
		
		setMonAdresse(monAdresse);
		setSonAdresse(sonAdresse);
		setIdLocal(1);
		
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
	 * Constructeur du joueur rejoignant la partie
	 * 
	 * @param saveStr
	 */
	public HexaWebBoard(String saveStr){
		
		super(saveStr);
	}
	
	@Override
	public Player nextMove(Color couleur){
		
		//TODO
		
		return null;
	}

	public String getMonAdresse() {
		return monAdresse;
	}

	public void setMonAdresse(String monAdresse) {
		this.monAdresse = monAdresse;
	}

	public String getSonAdresse() {
		return sonAdresse;
	}

	public void setSonAdresse(String sonAdresse) {
		this.sonAdresse = sonAdresse;
	}

	public int getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}
}
