import java.awt.Color;


public class HexaCell implements Cell, Cloneable {
	
	private int centreX;
	private int centreY;
	
	private Color color;
	
	private HexaCell voisinDroiteHaut;
	private HexaCell voisinDroite;
	private HexaCell voisinDroiteBas;
	private HexaCell voisinGaucheBas;
	private HexaCell voisinGauche;
	private HexaCell voisinGaucheHaut;
	
	private String ctrlBy;
	
	/**
	 * 
	 * @param centreX			:
	 * @param centreY
	 * @param color
	 * @param voisinDroiteHaut
	 * @param voisinDroite
	 * @param voisinDroitBas
	 * @param voisinGaucheBas
	 * @param voisinGauche
	 * @param voisinGaucheHaut
	 */
	public HexaCell(int centreX, int centreY, Color color, HexaCell voisinDroiteHaut,HexaCell voisinDroite, HexaCell voisinDroitBas, HexaCell voisinGaucheBas, HexaCell voisinGauche, HexaCell voisinGaucheHaut){
		
		this.centreX = centreX;
		this.centreY = centreY;
		
		this.color = color;
		
		this.voisinDroiteHaut	= voisinDroiteHaut;
		this.voisinDroite 		= voisinDroite;
		this.voisinDroiteBas 	= voisinDroitBas;
		this.voisinGaucheBas 	= voisinGaucheBas;
		this.voisinGauche 		= voisinGauche;
		this.voisinGaucheHaut 	= voisinGaucheHaut;
		
		this.ctrlBy = "";
	}
	
	//sert à rien
	public HexaCell clone() {
		
		HexaCell hc = null;
    	try {
    		hc = (HexaCell) super.clone();
    	} catch(CloneNotSupportedException cnse) { cnse.printStackTrace(System.err); }

    	if(hc.voisinDroiteHaut 	!= null)	hc.voisinDroiteHaut = null;
    	if(hc.voisinDroite 		!= null)	hc.voisinDroite 	= null;
    	if(hc.voisinDroiteBas 	!= null)	hc.voisinDroiteBas 	= null;
    	if(hc.voisinGaucheBas 	!= null)	hc.voisinGaucheBas 	= null;
    	if(hc.voisinGauche 		!= null)	hc.voisinGauche 	= null;
    	if(hc.voisinGaucheHaut 	!= null)	hc.voisinGaucheHaut = null;

	    return hc;
  	}
	
	public HexaCell getVoisinDroiteHaut() {
		return voisinDroiteHaut;
	}

	public void setVoisinDroiteHaut(HexaCell voisinDroiteHaut) {
		this.voisinDroiteHaut = voisinDroiteHaut;
	}

	public HexaCell getVoisinDroite() {
		return voisinDroite;
	}

	public void setVoisinDroite(HexaCell voisinDroite) {
		this.voisinDroite = voisinDroite;
	}

	public HexaCell getVoisinDroiteBas() {
		return voisinDroiteBas;
	}

	public void setVoisinDroiteBas(HexaCell voisinDroitBas) {
		this.voisinDroiteBas = voisinDroitBas;
	}

	public HexaCell getVoisinGaucheBas() {
		return voisinGaucheBas;
	}

	public void setVoisinGaucheBas(HexaCell voisinGaucheBas) {
		this.voisinGaucheBas = voisinGaucheBas;
	}

	public HexaCell getVoisinGauche() {
		return voisinGauche;
	}

	public void setVoisinGauche(HexaCell voisinGauche) {
		this.voisinGauche = voisinGauche;
	}

	public HexaCell getVoisinGaucheHaut() {
		return voisinGaucheHaut;
	}

	public void setVoisinGaucheHaut(HexaCell voisinGaucheHaut) {
		this.voisinGaucheHaut = voisinGaucheHaut;
	}

	public int getCentreX() {
		return centreX;
	}

	public void setCentreX(int centreX) {
		this.centreX = centreX;
	}

	public int getCentreY() {
		return centreY;
	}

	public void setCentreY(int centreY) {
		this.centreY = centreY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getCtrlBy() {
		return ctrlBy;
	}

	public void setCtrlBy(String ctrlBy) {
		this.ctrlBy = ctrlBy;
	}
}