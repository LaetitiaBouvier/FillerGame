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
	 * Ce constructeur permet de créer une cellule hexagonale en définissant les paramètres suivants :
	 * 
	 * @param centreX			( int ) 		: <br> abscisse du centre de la cellule 	</br><br>
	 * @param centreY			( int ) 		: <br> ordonnée du centre de la cellule 	</br><br>
	 * @param color				( Color ) 		: <br> couleur de la cellule 				</br><br>
	 * @param voisinDroiteHaut	( HexaCell )	: <br> cellule voisine en haut à droite 	</br><br>
	 * @param voisinDroite		( HexaCell )	: <br> cellule voisine à droite 			</br><br>
	 * @param voisinDroitBas	( HexaCell )	: <br> cellule voisine en bas à droite 		</br><br>
	 * @param voisinGaucheBas	( HexaCell )	: <br> cellule voisine en bas à gauche 		</br><br>
	 * @param voisinGauche		( HexaCell )	: <br> cellule voisine à gauche 			</br><br>
	 * @param voisinGaucheHaut	( HexaCell )	: <br> cellule voisine en haut à gauche		</br><br>
	 */
	public HexaCell(int centreX, int centreY, Color color, HexaCell voisinDroiteHaut,HexaCell voisinDroite, HexaCell voisinDroiteBas, HexaCell voisinGaucheBas, HexaCell voisinGauche, HexaCell voisinGaucheHaut){
		
		this.centreX = centreX;
		this.centreY = centreY;
		
		this.color = color;
		
		this.voisinDroiteHaut	= voisinDroiteHaut;
		this.voisinDroite 		= voisinDroite;
		this.voisinDroiteBas 	= voisinDroiteBas;
		this.voisinGaucheBas 	= voisinGaucheBas;
		this.voisinGauche 		= voisinGauche;
		this.voisinGaucheHaut 	= voisinGaucheHaut;
		
		this.ctrlBy = "";
	}
	
	/**
	 * Cette fonction est le getter permettant d'accéder au voisin situé en haut à droite
	 * 
	 * @return ( HexaCell ) Retourne la cellule voisine d'en haut à droite
	 */
	public HexaCell getVoisinDroiteHaut() {
		return voisinDroiteHaut;
	}

	/**
	 * Cette fonction est le setter permettant de paramètrer le voisin situé en haut à droite
	 * 
	 * @param voisinDroiteHaut ( HexaCell) : <br> cellule voisine en haut à droite 	</br><br>
	 */
	public void setVoisinDroiteHaut(HexaCell voisinDroiteHaut) {
		this.voisinDroiteHaut = voisinDroiteHaut;
	}

	/**
	 * Cette fonction est le getter permettant d'accéder au voisin situé à droite
	 * 
	 * @return ( HexaCell ) Retourne la cellule voisine de droite
	 */
	public HexaCell getVoisinDroite() {
		return voisinDroite;
	}

	/**
	 * Cette fonction est le setter permettant de paramètrer le voisin situé à droite
	 * 
	 * @param voisinDroite ( HexaCell) : <br> cellule voisine à droite 	</br><br>
	 */
	public void setVoisinDroite(HexaCell voisinDroite) {
		this.voisinDroite = voisinDroite;
	}

	/**
	 * Cette fonction est le getter permettant d'accéder au voisin situé en bas à droite
	 * 
	 * @return ( HexaCell ) Retourne la cellule voisine d'en bas à droite
	 */
	public HexaCell getVoisinDroiteBas() {
		return voisinDroiteBas;
	}

	/**
	 * Cette fonction est le setter permettant de paramètrer le voisin situé en bas à droite
	 * 
	 * @param voisinDroiteBas ( HexaCell) : <br> cellule voisine d'en bas à droite </br><br>
	 */
	public void setVoisinDroiteBas(HexaCell voisinDroitBas) {
		this.voisinDroiteBas = voisinDroitBas;
	}

	/**
	 * Cette fonction est le getter permettant d'accéder au voisin situé en bas à gauche
	 * 
	 * @return ( HexaCell ) Retourne la cellule voisine d'en bas à droite
	 */
	public HexaCell getVoisinGaucheBas() {
		return voisinGaucheBas;
	}

	/**
	 * Cette fonction est le setter permettant de paramètrer le voisin situé en bas à gauche
	 * 
	 * @param voisinGaucheBas ( HexaCell) : <br> cellule voisine d'en bas à gauche </br><br>
	 */
	public void setVoisinGaucheBas(HexaCell voisinGaucheBas) {
		this.voisinGaucheBas = voisinGaucheBas;
	}

	/**
	 * Cette fonction est le getter permettant d'accéder au voisin situé à gauche
	 * 
	 * @return ( HexaCell ) Retourne la cellule voisine de gauche
	 */
	public HexaCell getVoisinGauche() {
		return voisinGauche;
	}

	/**
	 * Cette fonction est le setter permettant de paramètrer le voisin situé à gauche
	 * 
	 * @param voisinGauche ( HexaCell) : <br> cellule voisine de gauche </br><br>
	 */
	public void setVoisinGauche(HexaCell voisinGauche) {
		this.voisinGauche = voisinGauche;
	}

	/**
	 * Cette fonction est le getter permettant d'accéder au voisin situé en haut à gauche
	 * 
	 * @return ( HexaCell ) Retourne la cellule voisine d'en haut à gauche
	 */
	public HexaCell getVoisinGaucheHaut() {
		return voisinGaucheHaut;
	}

	/**
	 * Cette fonction est le setter permettant de paramètrer le voisin situé à gauche en haut
	 * 
	 * @param voisinGauche ( HexaCell) : <br> cellule voisine d'en haut à gauche </br><br>
	 */
	public void setVoisinGaucheHaut(HexaCell voisinGaucheHaut) {
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