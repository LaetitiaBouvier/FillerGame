import java.awt.Color;

public class HexaWebBoard extends HexaBoard{
	
	private String monAdresse;
	private String sonAdresse;

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
	

}
