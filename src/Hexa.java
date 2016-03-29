import java.awt.Color;


public class Hexa {
	
	private int centreX;
	private int centreY;
	
	private Color color;
	
	private Hexa voisinDroiteHaut;
	private Hexa voisinDroite;
	private Hexa voisinDroiteBas;
	private Hexa voisinGaucheBas;
	private Hexa voisinGauche;
	private Hexa voisinGaucheHaut;
	
	public Hexa(int centreX, int centreY, Color color, Hexa voisinDroiteHaut,Hexa voisinDroite, Hexa voisinDroitBas, Hexa voisinGaucheBas, Hexa voisinGauche, Hexa voisinGaucheHaut){
		
		this.centreX = centreX;
		this.centreY = centreY;
		
		this.color = color;
		
		this.voisinDroiteHaut	= voisinDroiteHaut;
		this.voisinDroite 		= voisinDroite;
		this.voisinDroiteBas 	= voisinDroitBas;
		this.voisinGaucheBas 	= voisinGaucheBas;
		this.voisinGauche 		= voisinGauche;
		this.voisinGaucheHaut 	= voisinGaucheHaut;
	}
	
	
	
	public Hexa getVoisinDroiteHaut() {
		return voisinDroiteHaut;
	}

	public void setVoisinDroiteHaut(Hexa voisinDroiteHaut) {
		this.voisinDroiteHaut = voisinDroiteHaut;
	}

	public Hexa getVoisinDroite() {
		return voisinDroite;
	}

	public void setVoisinDroite(Hexa voisinDroite) {
		this.voisinDroite = voisinDroite;
	}

	public Hexa getVoisinDroiteBas() {
		return voisinDroiteBas;
	}

	public void setVoisinDroiteBas(Hexa voisinDroitBas) {
		this.voisinDroiteBas = voisinDroitBas;
	}

	public Hexa getVoisinGaucheBas() {
		return voisinGaucheBas;
	}

	public void setVoisinGaucheBas(Hexa voisinGaucheBas) {
		this.voisinGaucheBas = voisinGaucheBas;
	}

	public Hexa getVoisinGauche() {
		return voisinGauche;
	}

	public void setVoisinGauche(Hexa voisinGauche) {
		this.voisinGauche = voisinGauche;
	}

	public Hexa getVoisinGaucheHaut() {
		return voisinGaucheHaut;
	}

	public void setVoisinGaucheHaut(Hexa voisinGaucheHaut) {
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
}