
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Board extends Canvas {
	
	private Hexa[][] grille;		// grille[i][j] : j represente les lignes, i les éléments en colonne de chaque ligne
	private Player joueur1;
	private Player joueur2;
	private Player joueur3;
	private Player joueur4;
	
	/*REMARQUE :
	 * Joueur1 : en haut à hauche de la grille
	 * Joueur2 : en bas  à droite de la grille
	 * Joueur3 : en haut à droite de la grille
	 * Joueur4 : en bas  à gaucge de la grille
	 */
	public Board(int nb, String nomJoueur1, String nomJoueur2, String nomJoueur3, String nomJoueur4) {
		
		// Settings :
		setBackground (Color.black);
        setSize(20*nb +25, 20*nb +30);
		
        initialisationGrille(nb);
		
		defVoisins(nb);
		
		defJoueurs(nomJoueur1, nomJoueur2, nomJoueur3, nomJoueur4, nb);
	}
	
	private void initialisationGrille(int nb){
		
		int decalageX = 0;
		
		this.grille = new Hexa[nb][nb];
		
		for(int i = 0; i < grille.length; i++){
			for(int j = 0; j < grille[0].length; j++){
				
				// ATTENTION il se passe un truc dans les coins : il faut que les 4 coins soient de couleurs diff !
				
				if(j %2 == 0){ decalageX = -10;}
				if(j %2 == 1){ decalageX = 0;}
				
				Color color = Color.black;
				double random = Math.random();
				random *= 6;
				
				if(random < 1){
					color = Color.red;
				}else if(random >= 1 && random < 2){
					color = Color.orange;
				}else if(random >= 2 && random < 3){
					color = Color.yellow;
				}else if(random >= 3 && random < 4){
					color = Color.green;
				}else if(random >= 4 && random < 5){
					color = Color.blue;
				}else if(random >= 5 && random < 6){
					color = Color.magenta;
				}
				
				grille[i][j] = new Hexa(30+i*20+decalageX, 25+j*20, color, null, null, null, null, null, null);
			}
		}
	}
	
	private void defVoisins(int nb){
		
		for(int i = 0; i < grille.length; i++){
			for(int j = 0; j < grille[0].length; j++){
				
				if		(i == 0 && j == 0)	 	{ defFirstHExaFirstRow();		}
				else if	(i == nb-1 && j == 0)	{ defLastHexaFirstRow(nb);		}
				else if	(i == 0 && j == nb-1)	{ defFirstHexaLastRow(j, nb);	}
				else if (i == nb-1 && j == nb-1){ defLastHexaLastRow(j, nb);	}
				
				else if (i != 0 && i != nb-1 && j == 0)	  {	defFirstRowInBettween(i);			}
				else if (i == 0 && j != 0    && j != nb-1){	defFirstColumnInBettween(i, j);		}
				else if(i == nb-1 && j != 0  && j != nb-1){	defLastColumnInBettween(i, j, nb);	}
				else if(i != 0  && i != nb-1 && j == nb-1){	defLastRowInBettween(i, j, nb);		}
				
				else{	defNoBorderHexa(i, j);	}
			}
		}
	}
	
	private void defFirstHExaFirstRow(){
		
		this.grille[0][0].setVoisinDroiteHaut(null);
		this.grille[0][0].setVoisinGaucheHaut(null);
		this.grille[0][0].setVoisinGauche(null);
		this.grille[0][0].setVoisinGaucheBas(null);
		
		this.grille[0][0].setVoisinDroite(grille[1][0]);
		this.grille[0][0].setVoisinDroiteBas(grille[0][1]);
	}
	
	private void defLastHexaFirstRow(int nb){
		
		this.grille[nb-1][0].setVoisinDroiteHaut(null);
		this.grille[nb-1][0].setVoisinDroite(null);
		this.grille[nb-1][0].setVoisinGaucheHaut(null);
		
		this.grille[nb-1][0].setVoisinGauche(grille[nb-2][0]);
		this.grille[nb-1][0].setVoisinGaucheBas(grille[nb-2][1]);
		this.grille[nb-1][0].setVoisinDroiteBas(grille[nb-1][1]);
	}
	
	private void defFirstHexaLastRow(int j, int nb){
		
		if(j % 2 == 0){	// Si la dernière ligne est paire
			
			this.grille[0][nb-1].setVoisinDroiteBas(null);
			this.grille[0][nb-1].setVoisinGaucheBas(null);
			this.grille[0][nb-1].setVoisinGauche(null);
			this.grille[0][nb-1].setVoisinGaucheHaut(null);
			
			this.grille[0][nb-1].setVoisinDroiteHaut(grille[0][nb-2]);
			this.grille[0][nb-1].setVoisinDroite(grille[1][nb-1]);
		}
		if(j % 2 == 1){ // Si la dernière ligne est impaire
			
			this.grille[0][nb-1].setVoisinDroiteBas(null);
			this.grille[0][nb-1].setVoisinGaucheBas(null);
			this.grille[0][nb-1].setVoisinGauche(null);
			
			this.grille[0][nb-1].setVoisinGaucheHaut(grille[0][nb-2]);
			this.grille[0][nb-1].setVoisinDroiteHaut(grille[1][nb-2]);
			this.grille[0][nb-1].setVoisinDroite(grille[1][nb-1]);
		}
	}
	
	private void defLastHexaLastRow(int j, int nb){
		
		if(j % 2 == 0){	// Si la dernière ligne est paire
			
			this.grille[nb-1][nb-1].setVoisinDroite(null);
			this.grille[nb-1][nb-1].setVoisinDroiteBas(null);
			this.grille[nb-1][nb-1].setVoisinGaucheBas(null);
			
			this.grille[nb-1][nb-1].setVoisinDroiteHaut(grille[nb-1][nb-2]);
			this.grille[nb-1][nb-1].setVoisinGaucheHaut(grille[nb-2][nb-2]);
			this.grille[nb-1][nb-1].setVoisinGauche(grille[nb-2][nb-1]);
		}
		if(j % 2 == 1){	// Si la dernière ligne est impaire
			
			this.grille[nb-1][nb-1].setVoisinDroiteHaut(null);
			this.grille[nb-1][nb-1].setVoisinDroite(null);
			this.grille[nb-1][nb-1].setVoisinDroiteBas(null);
			this.grille[nb-1][nb-1].setVoisinGaucheBas(null);
			
			this.grille[nb-1][nb-1].setVoisinGauche(grille[nb-2][nb-1]);
			this.grille[nb-1][nb-1].setVoisinGaucheHaut(grille[nb-1][nb-2]);
		}
	}
	
	private void defFirstRowInBettween(int i){
		
		this.grille[i][0].setVoisinDroiteHaut(null);
		this.grille[i][0].setVoisinGaucheHaut(null);
		
		this.grille[i][0].setVoisinDroite(grille[i+1][0]);
		this.grille[i][0].setVoisinGauche(grille[i-1][0]);
		this.grille[i][0].setVoisinDroiteBas(grille[i][1]);
		this.grille[i][0].setVoisinGaucheBas(grille[i-1][1]);
	}
	
	private void defFirstColumnInBettween(int i, int j){
		
		this.grille[i][j].setVoisinGauche(null);
		
		if(j % 2 == 0){	// Si décalé vers la gauche
			this.grille[0][j].setVoisinDroiteHaut(grille[0][j-1]);
			this.grille[0][j].setVoisinDroite(grille[1][j]);
			this.grille[0][j].setVoisinDroiteBas(grille[0][j+1]);
			
			this.grille[0][j].setVoisinGaucheHaut(null);
			this.grille[0][j].setVoisinGaucheBas(null);
		}
		else{			// Sinon
			this.grille[0][j].setVoisinGaucheHaut(grille[0][j-1]);
			this.grille[0][j].setVoisinDroiteHaut(grille[1][j-1]);
			this.grille[0][j].setVoisinDroite(grille[1][j]);
			this.grille[0][j].setVoisinDroiteBas(grille[1][j+1]);
			this.grille[0][j].setVoisinGaucheBas(grille[0][j+1]);
		}
	}
	
	private void defLastColumnInBettween(int i, int j, int nb){
		
		this.grille[i][j].setVoisinDroite(null);
		
		if(j %2 == 0){	// Si décalé vers la gauche
			this.grille[nb-1][j].setVoisinDroiteHaut(grille[nb-1][j-1]);
			this.grille[nb-1][j].setVoisinGaucheHaut(grille[nb-2][j-1]);
			this.grille[nb-1][j].setVoisinGauche(grille[nb-2][j]);
			this.grille[nb-1][j].setVoisinGaucheBas(grille[nb-2][j+1]);
			this.grille[nb-1][j].setVoisinDroiteBas(grille[nb-1][j+1]);
		}
		else{			// Sinon
			this.grille[nb-1][j].setVoisinDroiteHaut(null);
			this.grille[nb-1][j].setVoisinDroiteBas(null);
			
			this.grille[nb-1][j].setVoisinGaucheHaut(grille[nb-1][j-1]);
			this.grille[nb-1][j].setVoisinGauche(grille[nb-2][j]);
			this.grille[nb-1][j].setVoisinGaucheBas(grille[nb-1][j+1]);
		}
	}
	
	private void defLastRowInBettween(int i, int j, int nb){
		
		this.grille[i][nb-1].setVoisinDroiteBas(null);
		this.grille[i][nb-1].setVoisinGaucheBas(null);
		
		if(j % 2 == 0){	// Si la dernière ligne est paire
			
			this.grille[i][nb-1].setVoisinDroite(grille[i+1][nb-1]);
			this.grille[i][nb-1].setVoisinDroiteHaut(grille[i][nb-2]);
			this.grille[i][nb-1].setVoisinGaucheHaut(grille[i-1][nb-2]);
			this.grille[i][nb-1].setVoisinGauche(grille[i-1][nb-1]);
		}
		if(j % 2 == 1){	// Si la dernière ligne est impaire
			
			this.grille[i][nb-1].setVoisinDroite(grille[i+1][nb-1]);
			this.grille[i][nb-1].setVoisinDroiteHaut(grille[i+1][nb-2]);
			this.grille[i][nb-1].setVoisinGaucheHaut(grille[i][nb-2]);
			this.grille[i][nb-1].setVoisinGauche(grille[i-1][nb-1]);
		}
	}
	
	private void defNoBorderHexa(int i, int j){
		
		if(j % 2 == 0){
			
			grille[i][j].setVoisinDroiteHaut(grille[i][j-1]);
			grille[i][j].setVoisinDroite(grille[i+1][j]);
			grille[i][j].setVoisinDroiteBas(grille[i][j+1]);
			grille[i][j].setVoisinGaucheBas(grille[i-1][j+1]);
			grille[i][j].setVoisinGauche(grille[i-1][j]);
			grille[i][j].setVoisinGaucheHaut(grille[i-1][j-1]);
		}
		if(j % 2 == 1){
			
			grille[i][j].setVoisinDroiteHaut(grille[i+1][j-1]);
			grille[i][j].setVoisinDroite(grille[i+1][j]);
			grille[i][j].setVoisinDroiteBas(grille[i+1][j+1]);
			grille[i][j].setVoisinGaucheBas(grille[i][j+1]);
			grille[i][j].setVoisinGauche(grille[i-1][j]);
			grille[i][j].setVoisinGaucheHaut(grille[i][j-1]);
		}
	}
	
	private void defJoueurs(String nomJoueur1, String nomJoueur2, String nomJoueur3, String nomJoueur4, int nb){
		
		ArrayList<Hexa> listeInitialeJ1 = new ArrayList<Hexa>();
		ArrayList<Hexa> listeInitialeJ2 = new ArrayList<Hexa>();
		
		listeInitialeJ1.add(grille[0][0]);
		listeInitialeJ1 = getConnectedSameColorHexas(listeInitialeJ1);
		
		this.joueur1 = new Player(nomJoueur1, grille[0][0].getColor(), listeInitialeJ1.size(), listeInitialeJ1);
		this.joueur1.setMyTurn(true);
		
		listeInitialeJ2.add(grille[nb-1][nb-1]);
		listeInitialeJ2 = getConnectedSameColorHexas(listeInitialeJ2);
		
		this.joueur2 = new Player(nomJoueur2, grille[nb-1][nb-1].getColor(), listeInitialeJ2.size(), listeInitialeJ2);
		
		if(!nomJoueur3.isEmpty()){
			
			ArrayList<Hexa> listeInitialeJ3 = new ArrayList<Hexa>();
			
			listeInitialeJ3.add(grille[nb-1][0]);
			listeInitialeJ3 = getConnectedSameColorHexas(listeInitialeJ3);
			
			this.joueur3 = new Player(nomJoueur3, grille[nb-1][0].getColor(), listeInitialeJ3.size(), listeInitialeJ3);
		}
		else{
			this.joueur3 = null;
		}
		
		if(!nomJoueur4.isEmpty()){
			
			ArrayList<Hexa> listeInitialeJ4 = new ArrayList<Hexa>();
			
			listeInitialeJ4.add(grille[0][nb-1]);
			listeInitialeJ4 = getConnectedSameColorHexas(listeInitialeJ4);
			
			this.joueur4 = new Player(nomJoueur4, grille[0][nb-1].getColor(), listeInitialeJ4.size(), listeInitialeJ4);
		}
		else{
			this.joueur4 = null;
		}
	}
	
	private ArrayList<Hexa> getConnectedSameColorHexas(ArrayList<Hexa> liste){
		
		boolean add = false;
		
		for(int i = 0; i < liste.size(); i++){
			
			Hexa hexa = liste.get(i);
			
			if( hexa.getVoisinDroiteHaut() != null && !liste.contains(hexa.getVoisinDroiteHaut()) ){
				if( liste.get(i).getVoisinDroiteHaut().getColor().getRGB() == hexa.getColor().getRGB()){
					
					liste.add(hexa.getVoisinDroiteHaut());	add = true;
				}
			}
			if( hexa.getVoisinDroite() != null && !liste.contains(hexa.getVoisinDroite()) ){
				if( hexa.getVoisinDroite().getColor().getRGB() == hexa.getColor().getRGB()){
					
					liste.add(hexa.getVoisinDroite());		add = true;
				}
			}
			if( hexa.getVoisinDroiteBas() != null && !liste.contains(hexa.getVoisinDroiteBas()) ){
				if( liste.get(i).getVoisinDroiteBas().getColor().getRGB() == hexa.getColor().getRGB()){
					
					liste.add(hexa.getVoisinDroiteBas());	add = true;
				}
			}
			if(hexa.getVoisinGaucheBas() != null && !liste.contains(hexa.getVoisinGaucheBas()) ){
				if( hexa.getVoisinGaucheBas().getColor().getRGB() == hexa.getColor().getRGB()){
					
					liste.add(hexa.getVoisinGaucheBas());	add = true;
				}
			}
			if(hexa.getVoisinGauche() != null && !liste.contains(hexa.getVoisinGauche()) ){
				if( hexa.getVoisinGauche().getColor().getRGB() == hexa.getColor().getRGB()){
					
					liste.add(hexa.getVoisinGauche());		add = true;
				}
			}
			if(hexa.getVoisinGaucheHaut() != null && !liste.contains(hexa.getVoisinGaucheHaut()) ){
				if( hexa.getVoisinGaucheHaut().getColor().getRGB() == hexa.getColor().getRGB()){
					
					liste.add(hexa.getVoisinGaucheHaut());	add = true;
				}
			}
		}
		if(add == false){	return liste;	}	// Condition de sortie de la récursion
		
		return getConnectedSameColorHexas(liste);
	}
	
	public void nextStep(Color couleur){
		
		boolean flag = true;
		
		Player joueur = null;
		
		if(this.joueur1.isMyTurn() && flag){
			
			flag = false;
			
			joueur = this.joueur1;
			
			this.joueur1.setMyTurn(false);
			this.joueur2.setMyTurn(true);
		}
		
		if(this.joueur2.isMyTurn() && flag){
			
			flag = false;
			
			joueur = this.joueur2;
			
			this.joueur2.setMyTurn(false);
			this.joueur1.setMyTurn(true);
		}
		
		ArrayList<Hexa> hexasCtrl = joueur.getCasesCtrl();
		
		for(int i = 0; i < hexasCtrl.size(); i++){
			hexasCtrl.get(i).setColor(couleur);
		}
		
		hexasCtrl = getConnectedSameColorHexas(hexasCtrl);
		
		joueur.setCasesCtrl(hexasCtrl);
		
		repaint();
	}
	
	@Override
	public void paint(Graphics g){
		
		super.paint(g);
		
		for(int i = 0; i < grille.length; i++){
			for(int j = 0; j< grille[0].length; j++){
				
				int x[] = {grille[i][j].getCentreX(),		grille[i][j].getCentreX()+10,	grille[i][j].getCentreX()+10,	grille[i][j].getCentreX(), 		grille[i][j].getCentreX()-10, 	grille[i][j].getCentreX()-10};
				int y[] = {grille[i][j].getCentreY()+15,	grille[i][j].getCentreY()+5, 	grille[i][j].getCentreY()-5, 	grille[i][j].getCentreY()-15, 	grille[i][j].getCentreY()-5, 	grille[i][j].getCentreY()+5};
				
				g.setColor(grille[i][j].getColor());
				g.fillPolygon(x, y, 6);
				
				g.setColor(Color.black);
				g.drawPolygon(x, y, 6);
			}
		}
	}
	
	@Override
	public void repaint(){
		super.repaint();
	}
	
	public Player getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(Player joueur1) {
		this.joueur1 = joueur1;
	}

	public Player getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(Player joueur2) {
		this.joueur2 = joueur2;
	}

	public Player getJoueur3() {
		return joueur3;
	}

	public void setJoueur3(Player joueur3) {
		this.joueur3 = joueur3;
	}

	public Player getJoueur4() {
		return joueur4;
	}

	public void setJoueur4(Player joueur4) {
		this.joueur4 = joueur4;
	}
}
