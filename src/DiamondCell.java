import java.awt.Color;


public class DiamondCell implements Cell, Cloneable {
	
	private int centreX;
	private int centreY;
	
	private Color color;
	
	private DiamondCell voisinDroiteHaut;
	private DiamondCell voisinDroiteBas;
	private DiamondCell voisinGaucheBas;
	private DiamondCell voisinGaucheHaut;
	
	private String ctrlBy;
	
	/**
	 * 
	 * @param centreX			:
	 * @param centreY
	 * @param color
	 * @param voisinDroiteHaut
	 * @param voisinDroiteBas
	 * @param voisinGaucheBas
	 * @param voisinGaucheHaut
	 */
	public DiamondCell(int centreX, int centreY, Color color, DiamondCell voisinDroiteHaut, DiamondCell voisinDroiteBas, DiamondCell voisinGaucheBas, DiamondCell voisinGaucheHaut){
		
		this.centreX = centreX;
		this.centreY = centreY;
		
		this.color = color;
		
		this.voisinDroiteHaut	= voisinDroiteHaut;
		this.voisinDroiteBas 	= voisinDroiteBas;
		this.voisinGaucheBas 	= voisinGaucheBas;
		this.voisinGaucheHaut 	= voisinGaucheHaut;
		
		this.ctrlBy = "";
	}
	
	//sert à rien
	public DiamondCell clone() {
		
		DiamondCell hc = null;
    	try {
    		hc = (DiamondCell) super.clone();
    	} catch(CloneNotSupportedException cnse) { cnse.printStackTrace(System.err); }

    	if(hc.voisinDroiteHaut 	!= null)	hc.voisinDroiteHaut = null;
    	if(hc.voisinDroiteBas 	!= null)	hc.voisinDroiteBas 	= null;
    	if(hc.voisinGaucheBas 	!= null)	hc.voisinGaucheBas 	= null;
    	if(hc.voisinGaucheHaut 	!= null)	hc.voisinGaucheHaut = null;

	    return hc;
  	}
	
	public DiamondCell getVoisinDroiteHaut() {
		return voisinDroiteHaut;
	}

	public void setVoisinDroiteHaut(DiamondCell voisinDroiteHaut) {
		this.voisinDroiteHaut = voisinDroiteHaut;
	}


	public DiamondCell getVoisinDroiteBas() {
		return voisinDroiteBas;
	}

	public void setVoisinDroiteBas(DiamondCell voisinDroitBas) {
		this.voisinDroiteBas = voisinDroitBas;
	}

	public DiamondCell getVoisinGaucheBas() {
		return voisinGaucheBas;
	}

	public void setVoisinGaucheBas(DiamondCell voisinGaucheBas) {
		this.voisinGaucheBas = voisinGaucheBas;
	}


	public DiamondCell getVoisinGaucheHaut() {
		return voisinGaucheHaut;
	}

	public void setVoisinGaucheHaut(DiamondCell voisinGaucheHaut) {
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