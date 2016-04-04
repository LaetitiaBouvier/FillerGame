import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Cette classe permet la gestion de tableaux de cellules hexagonales
 */
public class HexaBoard extends Canvas implements Board{
	
	private Hexa[][] grille;		// grille[i][j] : j represente les lignes, i les éléments en colonne de chaque ligne
	private int hauteur;
	private int largeur;
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
	
	/**
	 * Ce constructeur permet la création d'un tableau en appelant l'initialisation de la grille, la définition des voisins des cellules héxagonales (liaison des cellules entre elles)
	 * et en appelant la définition des joueurs liés au tableau
	 * 
	 * @param nb			: entier représentant la taille d'un côté du tableau
	 * @param nomJoueur1	: chaîne de caractères représentant le nom du joueur 1
	 * @param nomJoueur2	: chaîne de caractères représentant le nom du joueur 2
	 * @param nomJoueur3	: chaîne de caractères représentant le nom du joueur 3
	 * @param nomJoueur4	: chaîne de caractères représentant le nom du joueur 4
	 * 
	 * @see initialisationGrille(int nb)
	 * @see defVoisins(int nb)
	 * @see defJoueurs(int nb)
	 */
	public HexaBoard(int nb, String nomJoueur1, String nomJoueur2, String nomJoueur3, String nomJoueur4) {
		
		int h = 20*nb +30;
		int l = 20*nb +30;
		
		// Settings :
		setBackground (Color.black);
        setSize(h, l);
        
        this.hauteur = h;
        this.largeur = l;
        
        initialisationGrille(nb);
		
		defVoisins();
		
		defJoueurs(nomJoueur1, nomJoueur2, nomJoueur3, nomJoueur4, nb);
	}
	
	/**
	 * Cette fonction initialise la grille des cellules (ici un pavage d'hexagones) en créant les cellules à leur place et en leur attribuant une couleur aléatoire
	 * 
	 * @param nb	: entier représentant la taille d'un côté du tableau
	 */
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
	
	/**
	 * Cette fonction définie les voisins de chaque cellule héxagonale (liaison des cellules entre elles) en fonction de son positionnement sur la grille
	 * 
	 */
	private void defVoisins(){
		
		for(int i = 0; i < grille.length; i++){
			for(int j = 0; j < grille[0].length; j++){
				
				if(j % 2 == 0){
					try{ grille[i][j].setVoisinDroiteHaut(grille[i][j-1]);	}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinDroiteHaut(null); 	}
					try{ grille[i][j].setVoisinDroite(grille[i+1][j]); 		}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinDroite(null); 		}
					try{ grille[i][j].setVoisinDroiteBas(grille[i][j+1]);	}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinDroiteBas(null); 	}
					try{ grille[i][j].setVoisinGaucheBas(grille[i-1][j+1]);	}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinGaucheBas(null); 	}
					try{ grille[i][j].setVoisinGauche(grille[i-1][j]);		}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinGauche(null); 		}
					try{ grille[i][j].setVoisinGaucheHaut(grille[i-1][j-1]);}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinGaucheHaut(null); 	}
				}
				if(j % 2 == 1){
					try{ grille[i][j].setVoisinDroiteHaut(grille[i+1][j-1]);}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinDroiteHaut(null); 	}
					try{ grille[i][j].setVoisinDroite(grille[i+1][j]);		}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinDroite(null); 		}
					try{ grille[i][j].setVoisinDroiteBas(grille[i+1][j+1]);	}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinDroiteBas(null);	}
					try{ grille[i][j].setVoisinGaucheBas(grille[i][j+1]);	}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinGaucheBas(null); 	}
					try{ grille[i][j].setVoisinGauche(grille[i-1][j]);		}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinGauche(null); 		}
					try{ grille[i][j].setVoisinGaucheHaut(grille[i][j-1]);	}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinGaucheHaut(null); 	}
				}
			}
		}
	}
	
	/**
	 * Cette fonction définie les joueurs liés au tableau en les créant 
	 * 
	 * @param nomJoueur1	: chaîne de caractère représentant le nom du joueur 1
	 * @param nomJoueur2	: chaîne de caractère représentant le nom du joueur 2
	 * @param nomJoueur3	: chaîne de caractère représentant le nom du joueur 3
	 * @param nomJoueur4	: chaîne de caractère représentant le nom du joueur 4
	 * @param nb			: entier représentant la taille d'un côté du tableau
	 * 
	 * @see getConnectedSameColorHexas(ArrayList<Hexa> listeHexa)
	 */
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
	
	/**
	 * Cette fonction récursive permet d'obtenir, à partir d'une liste initiale de celulles hexagonales, une liste "augmentée" : comprennant en plus leurs voisines de même couleur 
	 * 
	 * @param liste		: liste des cellules hexagonales à partir desquels on souhaite obtenir la liste "augmentée" 
	 * @return liste	: liste des cellules hexagonales comprennant la liste de départ et la liste "augmentée"
	 */
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
	
	/**
	 * Cette fonction fait progresser la partie, étape par étape, selon les couleurs d'actions prises : redéfinissant ainsi les listes de cellules contrôlées par les joueurs
	 * Cette fonction rafraichit ensuite le tableau (suite au différents changements de couleur) en appelant la fonction "repaint()"
	 * 
	 * @see getConnectedSameColorHexas(ArrayList<Hexa> liste)
	 * @see setCasesCtrl(ArrayList<Hexa> liste)
	 * @see repaint()
	 * 
	 * @param couleur	: couleur de l'action qu'on a décidé de prendre ce tour ci
	 */
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
			
			if(this.joueur3 == null){
				this.joueur2.setMyTurn(false);
				this.joueur1.setMyTurn(true);
			}
			if(this.joueur3 != null){
				this.joueur2.setMyTurn(false);
				this.joueur3.setMyTurn(true);
			}
		}
		
		if(this.joueur3 != null){
			if(this.joueur3.isMyTurn() && flag){
				
				flag = false;
				
				joueur = this.joueur3;
				
				if(this.joueur4 == null){
					this.joueur3.setMyTurn(false);
					this.joueur1.setMyTurn(true);
				}
				if(this.joueur4 != null){
					this.joueur3.setMyTurn(false);
					this.joueur4.setMyTurn(true);
				}
			}
		}
		
		if(this.joueur4 != null){
			if(this.joueur4.isMyTurn() && flag){
				
				flag = false;
				
				joueur = this.joueur4;
				
				this.joueur4.setMyTurn(false);
				this.joueur1.setMyTurn(true);
			}
		}
		
		ArrayList<Hexa> hexasCtrl = joueur.getCasesCtrl();
		
		for(int i = 0; i < hexasCtrl.size(); i++){
			hexasCtrl.get(i).setColor(couleur);
		}
		
		hexasCtrl = getConnectedSameColorHexas(hexasCtrl);
		
		joueur.setCasesCtrl(hexasCtrl);
		
		repaint();
	}
	
	/**
	 * Cette fonction retourne les couleurs occupées par tous les joueurs présents
	 * 
	 * @return couleurs		: retourne les couleurs de tous les joueurs autour du tableau
	 */
	public ArrayList<Color> getColorsFromPlayers(){
		
		ArrayList<Color> couleurs = new ArrayList<Color>();
		
		Color couleur1 = this.getJoueur1().getCasesCtrl().get(0).getColor();
		Color couleur2 = this.getJoueur2().getCasesCtrl().get(0).getColor();
		
		Color couleur3 = null;
		Color couleur4 = null;
		
		if(this.getJoueur3() != null){
			couleur3 = this.getJoueur3().getCasesCtrl().get(0).getColor();
		}
		if(this.getJoueur4() != null){
			couleur4 = this.getJoueur4().getCasesCtrl().get(0).getColor();
		}
		
		couleurs.add(couleur1);
		couleurs.add(couleur2);
		
		if(joueur3 != null){ couleurs.add(couleur3); }
		if(joueur4 != null){ couleurs.add(couleur4); }
		
		return couleurs;
	}
	
	/**
	 * Cette fonction retourne les couleurs libres qu'aucun joueur n'occupe
	 * 
	 * @return couleursLibres	: retourne les couleurs libres du tableau
	 */
	public ArrayList<Color> getFreeColors(){
		
		ArrayList<Color> couleursLibres = new ArrayList<Color>();
		ArrayList<Color> couleursOccupees = this.getColorsFromPlayers();
		
		if(!couleursOccupees.contains(Color.red)){
			couleursLibres.add(Color.red);
		}
		if(!couleursOccupees.contains(Color.orange)){
			couleursLibres.add(Color.orange);
		}
		if(!couleursOccupees.contains(Color.yellow)){
			couleursLibres.add(Color.yellow);
		}
		if(!couleursOccupees.contains(Color.green)){
			couleursLibres.add(Color.green);
		}
		if(!couleursOccupees.contains(Color.blue)){
			couleursLibres.add(Color.blue);
		}
		if(!couleursOccupees.contains(Color.magenta)){
			couleursLibres.add(Color.magenta);
		}
		return couleursLibres;
	}
	
	@Override
	/**
	 * Cette fonction dessine le tableau en tenant compte des coordonées et des couleurs de chaque cellule, ici il s'agit d'un pavage d'hexagones
	 */
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
	/**
	 * Cette fonction permet de raffraichir le dessin du tableau
	 */
	public void repaint(){ super.repaint(); }
	
	/**
	 * Getter retournant la hauteur du tableau
	 */
	public int getHauteur(){ return this.hauteur; }
	
	/**
	 * Getter retournant la largeur du tableau
	 */
	public int getLargeur(){ return this.largeur; }
	
	/**
	 * Getter retournant le joueur 1
	 */
	public Player getJoueur1() { return joueur1; }

	/**
	 * Getter retournant le joueur 2
	 */
	public Player getJoueur2() { return joueur2; }

	/**
	 * Getter retournant le joueur 3
	 */
	public Player getJoueur3() { return joueur3; }

	/**
	 * Getter retournant le joueur 4
	 */
	public Player getJoueur4() { return joueur4; }
}
