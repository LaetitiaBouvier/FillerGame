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
	
	/**
	 * Ce constructeur permet de cr�er une cellule carr� en d�finissant les param�tres suivants :
	 * 
	 * @param centreX		( int ) 		: <br> abscisse du centre de la cellule 	</br><br>
	 * @param centreY		( int ) 		: <br> ordonn�e du centre de la cellule 	</br><br>
	 * @param color			( Color ) 		: <br> couleur de la cellule 				</br><br>
	 * @param voisinHaut	( SquareCell )	: <br> cellule voisine en haut 				</br><br>
	 * @param voisinDroite	( SquareCell )	: <br> cellule voisine � droite 			</br><br>
	 * @param voisinBas		( SquareCell )	: <br> cellule voisine en bas  				</br><br>
	 * @param voisinGauche	( SquareCell )	: <br> cellule voisine � gauche 			</br><br>
	 */
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
	
	/**
	 * Cette fonction est le getter permettant d'acc�der au voisin situ� en haut
	 * 
	 * @return ( SquareCell ) Retourne la cellule voisine du haut
	 */
	public SquareCell getVoisinHaut() {
		return voisinHaut;
	}
	
	/**
	 * Cette fonction est le setter permettant de param�trer le voisin situ� en haut
	 * 
	 * @param voisinDroite ( SquareCell) : <br> cellule voisine en haut	</br><br>
	 */
	public void setVoisinHaut(SquareCell voisinHaut) {
		this.voisinHaut = voisinHaut;
	}

	/**
	 * Cette fonction est le getter permettant d'acc�der au voisin situ� � droite
	 * 
	 * @return ( SquareCell ) Retourne la cellule voisine de droite
	 */
	public SquareCell getVoisinDroite() {
		return voisinDroite;
	}
	
	/**
	 * Cette fonction est le setter permettant de param�trer le voisin situ� � droite
	 * 
	 * @param voisinDroite ( SquareCell) : <br> cellule voisine � droite 	</br><br>
	 */
	public void setVoisinDroite(SquareCell voisinDroite) {
		this.voisinDroite = voisinDroite;
	}

	/**
	 * Cette fonction est le getter permettant d'acc�der au voisin situ� en bas
	 * 
	 * @return ( SquareCell ) Retourne la cellule voisine du bas
	 */
	public SquareCell getVoisinBas() {
		return voisinBas;
	}
	
	/**
	 * Cette fonction est le setter permettant de param�trer le voisin situ� en bas
	 * 
	 * @param voisinDroite ( SquareCell) : <br> cellule voisine en bas 	</br><br>
	 */
	public void setVoisinBas(SquareCell voisinBas) {
		this.voisinBas = voisinBas;
	}

	
	/**
	 * Cette fonction est le getter permettant d'acc�der au voisin situ� � gauche
	 * 
	 * @return ( HexaCell ) Retourne la cellule voisine de gauche
	 */
	public SquareCell getVoisinGauche() {
		return voisinGauche;
	}
	
	/**
	 * Cette fonction est le setter permettant de param�trer le voisin situ� � gauche
	 * 
	 * @param voisinGauche ( HexaCell) : <br> cellule voisine de gauche </br><br>
	 */
	public void setVoisinGauche(SquareCell voisinGauche) {
		this.voisinGauche = voisinGauche;
	}

	/**
	 */
	@Override
	public int getCentreX() { return this.centreX; }

	/**
	 */
	@Override
	public void setCentreX(int centreX) { this.centreX = centreX; }

	/**
	 */
	@Override
	public int getCentreY() { return this.centreY; }

	/**
	 */
	@Override
	public void setCentreY(int centreY) { this.centreY = centreY; }
	
	/**
	 */
	@Override
	public Color getColor() { return this.color; }

	/**
	 */
	@Override
	public void setColor(Color color) { this.color = color; }

	/**
	 */
	@Override
	public String getCtrlBy() { return this.ctrlBy; }

	/**
	 */
	@Override
	public void setCtrlBy(String ctrlBy) { this.ctrlBy = ctrlBy; }
}
