import java.awt.Color;

public class SquareCell implements Cell, Cloneable{
	
	private int centreX;
	private int centreY;
	
	private Color color;
	
	private SquareCell voisinHaut;
	private SquareCell voisinDroite;
	private SquareCell voisinBas;
	private SquareCell voisinGauche;
	
	private String ctrlBy;
	
	public SquareCell(int centreX, int centreY, Color color, SquareCell voisinHaut, SquareCell voisinDroite, SquareCell voisinBas, SquareCell voisinGauche){
		
		this.centreX = centreX;
		this.centreY = centreY;
		
		this.color = color;
		
		this.setVoisinHaut(voisinHaut);
		this.setVoisinDroite(voisinDroite);
		this.setVoisinBas(voisinBas);
		this.setVoisinGauche(voisinGauche);
		
		this.ctrlBy = "";
	}
	//sert à rien
		public SquareCell clone() {
			
			SquareCell hc = null;
	    	try {
	    		hc = (SquareCell) super.clone();
	    	} catch(CloneNotSupportedException cnse) { cnse.printStackTrace(System.err); }

	    	if(hc.voisinDroite 	!= null)	hc.voisinDroite = null;
	    	if(hc.voisinBas 	!= null)	hc.voisinBas 	= null;
	    	if(hc.voisinGauche 		!= null)	hc.voisinGauche 	= null;
	    	if(hc.voisinHaut 	!= null)	hc.voisinHaut = null;

		    return hc;
	  	}
	public SquareCell getVoisinHaut() { return voisinHaut; }
	public void setVoisinHaut(SquareCell voisinHaut) { this.voisinHaut = voisinHaut; }

	public SquareCell getVoisinDroite() { return voisinDroite; }
	public void setVoisinDroite(SquareCell voisinDroite) { this.voisinDroite = voisinDroite; }

	public SquareCell getVoisinBas() { return voisinBas; }
	public void setVoisinBas(SquareCell voisinBas) { this.voisinBas = voisinBas; }

	public SquareCell getVoisinGauche() { return voisinGauche; }
	public void setVoisinGauche(SquareCell voisinGauche) { this.voisinGauche = voisinGauche; }

	@Override
	public int getCentreX() { return this.centreX; }

	@Override
	public void setCentreX(int centreX) { this.centreX = centreX; }

	@Override
	public int getCentreY() { return this.centreY; }

	@Override
	public void setCentreY(int centreY) { this.centreY = centreY; }

	@Override
	public Color getColor() { return this.color; }

	@Override
	public void setColor(Color color) { this.color = color; }

	@Override
	public String getCtrlBy() { return this.ctrlBy; }

	@Override
	public void setCtrlBy(String ctrlBy) { this.ctrlBy = ctrlBy; }

}
