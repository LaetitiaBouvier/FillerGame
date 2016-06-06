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
	 * Ce constructeur permet de créer une cellule en losange en définissant les paramètres suivants :
	 * 
	 * @param centreX			( int ) 		: <br> abscisse du centre de la cellule 	</br><br>
	 * @param centreY			( int ) 		: <br> ordonnée du centre de la cellule 	</br><br>
	 * @param color				( Color ) 		: <br> couleur de la cellule 				</br><br>
	 * @param voisinDroiteHaut	( DiamondCell )	: <br> cellule voisine en haut à droite 	</br><br>
	 * @param voisinDroitBas	( DiamondCell )	: <br> cellule voisine en bas à droite 		</br><br>
	 * @param voisinGaucheBas	( DiamondCell )	: <br> cellule voisine en bas à gauche 		</br><br>
	 * @param voisinGaucheHaut	( DiamondCell )	: <br> cellule voisine en haut à gauche		</br><br>
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
	
	/**
	 * Cette fonction est le getter permettant d'accéder au voisin situé en haut à droite
	 * 
	 * @return ( DiamondCell ) Retourne la cellule voisine d'en haut à droite
	 */
	public DiamondCell getVoisinDroiteHaut() {
		return voisinDroiteHaut;
	}
	
	/**
	 * Cette fonction est le setter permettant de paramètrer le voisin situé en haut à droite
	 * 
	 * @param voisinDroiteHaut ( DiamondCell) : <br> cellule voisine en haut à droite 	</br><br>
	 */
	public void setVoisinDroiteHaut(DiamondCell voisinDroiteHaut) {
		this.voisinDroiteHaut = voisinDroiteHaut;
	}

	/**
	 * Cette fonction est le getter permettant d'accéder au voisin situé en bas à droite
	 * 
	 * @return ( DiamondCell ) Retourne la cellule voisine d'en bas à droite
	 */
	public DiamondCell getVoisinDroiteBas() {
		return voisinDroiteBas;
	}

	/**
	 * Cette fonction est le setter permettant de paramètrer le voisin situé en bas à droite
	 * 
	 * @param voisinDroiteBas ( DiamondCell) : <br> cellule voisine d'en bas à droite </br><br>
	 */
	public void setVoisinDroiteBas(DiamondCell voisinDroitBas) {
		this.voisinDroiteBas = voisinDroitBas;
	}

	/**
	 * Cette fonction est le getter permettant d'accéder au voisin situé en bas à gauche
	 * 
	 * @return ( DiamondCell ) Retourne la cellule voisine d'en bas à droite
	 */
	public DiamondCell getVoisinGaucheBas() {
		return voisinGaucheBas;
	}

	/**
	 * Cette fonction est le setter permettant de paramètrer le voisin situé en bas à gauche
	 * 
	 * @param voisinGaucheBas ( DiamondCell) : <br> cellule voisine d'en bas à gauche </br><br>
	 */
	public void setVoisinGaucheBas(DiamondCell voisinGaucheBas) {
		this.voisinGaucheBas = voisinGaucheBas;
	}

	/**
	 * Cette fonction est le getter permettant d'accéder au voisin situé en haut à gauche
	 * 
	 * @return ( DiamondCell ) Retourne la cellule voisine d'en haut à gauche
	 */
	public DiamondCell getVoisinGaucheHaut() {
		return voisinGaucheHaut;
	}

	/**
	 * Cette fonction est le setter permettant de paramètrer le voisin situé à gauche en haut
	 * 
	 * @param voisinGauche ( DiamondCell) : <br> cellule voisine d'en haut à gauche </br><br>
	 */
	public void setVoisinGaucheHaut(DiamondCell voisinGaucheHaut) {
		this.voisinGaucheHaut = voisinGaucheHaut;
	}

	/**
	 */
	@Override
	public int getCentreX() {
		return centreX;
	}

	/**
	 */
	@Override
	public void setCentreX(int centreX) {
		this.centreX = centreX;
	}

	/**
	 */
	@Override
	public int getCentreY() {
		return centreY;
	}

	/**
	 */
	@Override
	public void setCentreY(int centreY) {
		this.centreY = centreY;
	}

	/**
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 */
	@Override
	public String getCtrlBy() {
		return ctrlBy;
	}

	/**
	 */
	@Override
	public void setCtrlBy(String ctrlBy) {
		this.ctrlBy = ctrlBy;
	}
}