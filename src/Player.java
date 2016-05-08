import java.awt.Color;
import java.util.ArrayList;


public class Player implements Cloneable {

	private String nom;
	private Color couleur;
	private int nbCases;
	private ArrayList<HexaCell> casesCtrl;
	private boolean myTurn;
	private boolean isIA;
	private Boolean isWinner;
	
	public Player(String nom, Color couleur, int nbCases, ArrayList<HexaCell> casesCtrl, boolean isIA){
		
		this.nom = nom;
		this.couleur = couleur;
		this.nbCases = nbCases;
		this.casesCtrl = casesCtrl;
		this.myTurn = false;
		this.isIA = isIA;
		isWinner = null;
	}
	
	public Player clone(HexaCell[][] gridSimu) {
		
    	Player player = null;
    	try {
    		player = (Player) super.clone();
    	} catch(CloneNotSupportedException cnse) { cnse.printStackTrace(System.err); }
    	
    	ArrayList<HexaCell> clone = new ArrayList<HexaCell>();
    	
    	for(int i = 0; i < gridSimu.length; i++){
    		for(int j = 0; j < gridSimu[0].length; j++){
    			
    			for(HexaCell item : casesCtrl){
    				if( (item.getCentreX() == gridSimu[i][j].getCentreX()) && (item.getCentreY() == gridSimu[i][j].getCentreY()) ){
    					
    					clone.add(gridSimu[i][j]); break;
    				}
    			}
    		}
    	}
        
        player.casesCtrl = clone;
    	
	    return player;
  	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Color getCouleur() {
		return couleur;
	}
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	public int getNbCases() {
		return nbCases;
	}
	public void setNbCases(int nbCases) {
		this.nbCases = nbCases;
	}
	public ArrayList<HexaCell> getCasesCtrl() {
		return casesCtrl;
	}
	public void setCasesCtrl(ArrayList<HexaCell> casesCtrl) {
		this.casesCtrl = casesCtrl;
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	public boolean isIA() {
		return isIA;
	}

	public void setIA(boolean isIA) {
		this.isIA = isIA;
	}

	public Boolean getIsWinner() {
		return isWinner;
	}

	public void setIsWinner(Boolean isWinner) {
		this.isWinner = isWinner;
	}
	
	public String toStringColor(){
		
		String toStringColor = "";
		
		if(this.couleur == Color.red)		toStringColor = ("I'm red !");
		if(this.couleur == Color.orange)	toStringColor = ("I'm orange !");
		if(this.couleur == Color.yellow)	toStringColor = ("I'm yellow !");
		if(this.couleur == Color.green)		toStringColor = ("I'm green !");
		if(this.couleur == Color.blue)		toStringColor = ("I'm blue !");
		if(this.couleur == Color.magenta)	toStringColor = ("I'm magenta !");
		
		return toStringColor;
	}
}
