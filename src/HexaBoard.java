import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 * Cette classe permet la gestion de tableaux de cellules hexagonales
 */
public class HexaBoard extends Canvas implements Board{
	
	private HexaCell[][] grille;		// grille[i][j] : j represente les lignes, i les éléments en colonne de chaque ligne
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
	public HexaBoard(int nb, String nomJoueur1, String nomJoueur2, String nomJoueur3, String nomJoueur4, boolean isIA1, boolean isIA2, boolean isIA3, boolean isIA4) {
		
		int h = 20*nb +30;
		int l = 20*nb +80;
		
		// Settings :
		setBackground (Color.black);
        setSize(h, l);
        
        this.hauteur = h;
        this.largeur = l;
        
        initialisationGrille(nb);
		
		this.grille = defVoisins(this.grille);
		
		defJoueurs(nb, nomJoueur1, nomJoueur2, nomJoueur3, nomJoueur4, isIA1, isIA2, isIA3, isIA4);
	}
	
	/**
	 * Cette fonction initialise la grille des cellules (ici un pavage d'hexagones) en créant les cellules à leur place et en leur attribuant une couleur aléatoire
	 * 
	 * @param nb	: entier représentant la taille d'un côté du tableau
	 */
	private void initialisationGrille(int nb){
		
		int decalageX = 0;
		int margeY = 50;
		
		this.grille = new HexaCell[nb][nb];
		
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
				
				grille[i][j] = new HexaCell(30+i*20+decalageX, 30+j*20+margeY, color, null, null, null, null, null, null);
			}
		}
	}
	
	/**
	 * Cette fonction définie les voisins de chaque cellule héxagonale (liaison des cellules entre elles) en fonction de son positionnement sur la grille
	 * 
	 */
	private HexaCell[][] defVoisins(HexaCell[][] grille){
		
		for(int i = 0; i < grille.length; i++){
			for(int j = 0; j < grille.length; j++){
				
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
		
		return grille;
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
	private void defJoueurs( int nb, String nomJoueur1, String nomJoueur2, String nomJoueur3, String nomJoueur4, boolean isIA1, boolean isIA2, boolean isIA3, boolean isIA4){
		
		ArrayList<HexaCell> listeInitialeJ1 = new ArrayList<HexaCell>();
		ArrayList<HexaCell> listeInitialeJ2 = new ArrayList<HexaCell>();
		
		listeInitialeJ1.add(grille[0][0]);			grille[0][0].setCtrlBy(nomJoueur1);
		listeInitialeJ1 = getConnectedCellsOfSameColor(listeInitialeJ1);
		
		this.joueur1 = new Player(nomJoueur1, grille[0][0].getColor(), listeInitialeJ1.size(), listeInitialeJ1, isIA1);
		this.joueur1.setMyTurn(true);
		
		listeInitialeJ2.add(grille[nb-1][nb-1]);	grille[nb-1][nb-1].setCtrlBy(nomJoueur2);
		listeInitialeJ2 = getConnectedCellsOfSameColor(listeInitialeJ2);
		
		this.joueur2 = new Player(nomJoueur2, grille[nb-1][nb-1].getColor(), listeInitialeJ2.size(), listeInitialeJ2, isIA2);
		
		if(!nomJoueur3.isEmpty()){
			
			ArrayList<HexaCell> listeInitialeJ3 = new ArrayList<HexaCell>();
			
			listeInitialeJ3.add(grille[nb-1][0]);	grille[nb-1][0].setCtrlBy(nomJoueur3);
			listeInitialeJ3 = getConnectedCellsOfSameColor(listeInitialeJ3);
			
			this.joueur3 = new Player(nomJoueur3, grille[nb-1][0].getColor(), listeInitialeJ3.size(), listeInitialeJ3, isIA3);
		}
		else{
			this.joueur3 = null;
		}
		
		if(!nomJoueur4.isEmpty()){
			
			ArrayList<HexaCell> listeInitialeJ4 = new ArrayList<HexaCell>();
			
			listeInitialeJ4.add(grille[0][nb-1]);	grille[0][nb-1].setCtrlBy(nomJoueur4);
			listeInitialeJ4 = getConnectedCellsOfSameColor(listeInitialeJ4);
			
			this.joueur4 = new Player(nomJoueur4, grille[0][nb-1].getColor(), listeInitialeJ4.size(), listeInitialeJ4, isIA4);
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
	public static ArrayList<HexaCell> getConnectedCellsOfSameColor(ArrayList<HexaCell> listeIni){
		
		ArrayList<HexaCell> liste = (ArrayList<HexaCell>) listeIni.clone();
		
		boolean add = false;
		
		for(int i = 0; i < liste.size(); i++){
			
			HexaCell hexa = liste.get(i);
			
			if( hexa.getVoisinDroiteHaut() != null && !liste.contains(hexa.getVoisinDroiteHaut()) ){
				if( hexa.getVoisinDroiteHaut().getColor().getRGB() == hexa.getColor().getRGB() && hexa.getVoisinDroiteHaut().getCtrlBy().isEmpty()){
					
					liste.add(hexa.getVoisinDroiteHaut());	add = true;
				}
			}
			if( hexa.getVoisinDroite() != null && !liste.contains(hexa.getVoisinDroite()) ){
				if( hexa.getVoisinDroite().getColor().getRGB() == hexa.getColor().getRGB() && hexa.getVoisinDroite().getCtrlBy().isEmpty()){
					
					liste.add(hexa.getVoisinDroite());		add = true;
				}
			}
			if( hexa.getVoisinDroiteBas() != null && !liste.contains(hexa.getVoisinDroiteBas()) ){
				if( hexa.getVoisinDroiteBas().getColor().getRGB() == hexa.getColor().getRGB() && hexa.getVoisinDroiteBas().getCtrlBy().isEmpty()){
					
					liste.add(hexa.getVoisinDroiteBas());	add = true;
				}
			}
			if(hexa.getVoisinGaucheBas() != null && !liste.contains(hexa.getVoisinGaucheBas()) ){
				if( hexa.getVoisinGaucheBas().getColor().getRGB() == hexa.getColor().getRGB() && hexa.getVoisinGaucheBas().getCtrlBy().isEmpty()){
					
					liste.add(hexa.getVoisinGaucheBas());	add = true;
				}
			}
			if(hexa.getVoisinGauche() != null && !liste.contains(hexa.getVoisinGauche()) ){
				if( hexa.getVoisinGauche().getColor().getRGB() == hexa.getColor().getRGB() && hexa.getVoisinGauche().getCtrlBy().isEmpty()){
					
					liste.add(hexa.getVoisinGauche());		add = true;
				}
			}
			if(hexa.getVoisinGaucheHaut() != null && !liste.contains(hexa.getVoisinGaucheHaut()) ){
				if( hexa.getVoisinGaucheHaut().getColor().getRGB() == hexa.getColor().getRGB() && hexa.getVoisinGaucheHaut().getCtrlBy().isEmpty()){
					
					liste.add(hexa.getVoisinGaucheHaut());	add = true;
				}
			}
		}
		if(add == false){	return liste;	}	// Condition de sortie de la récursion
		
		return getConnectedCellsOfSameColor(liste);
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
	public Player nextMove(Color couleur){
		
		boolean flag = true;
		
		Player joueurAct = null;
		Player joueurSui = null;
		
		if(this.joueur1.isMyTurn() && flag){
			
			flag = false;
			
			joueurAct = this.joueur1;
			
			this.joueur1.setMyTurn(false);
			this.joueur2.setMyTurn(true);
			joueurSui = this.joueur2;
		}
		
		if(this.joueur2.isMyTurn() && flag){
			
			flag = false;
			
			joueurAct = this.joueur2;
			
			if(this.joueur3 == null){
				this.joueur2.setMyTurn(false);
				this.joueur1.setMyTurn(true);
				joueurSui = this.joueur1;
			}
			if(this.joueur3 != null){
				this.joueur2.setMyTurn(false);
				this.joueur3.setMyTurn(true);
				joueurSui = this.joueur3;
			}
		}
		
		if(this.joueur3 != null){
			if(this.joueur3.isMyTurn() && flag){
				
				flag = false;
				
				joueurAct = this.joueur3;
				
				if(this.joueur4 == null){
					this.joueur3.setMyTurn(false);
					this.joueur1.setMyTurn(true);
					joueurSui = this.joueur1;
				}
				if(this.joueur4 != null){
					this.joueur3.setMyTurn(false);
					this.joueur4.setMyTurn(true);
					joueurSui = this.joueur4;
				}
			}
		}
		
		if(this.joueur4 != null){
			if(this.joueur4.isMyTurn() && flag){
				
				flag = false;
				
				joueurAct = this.joueur4;
				
				this.joueur4.setMyTurn(false);
				this.joueur1.setMyTurn(true);
				joueurSui = this.joueur1;
			}
		}
		
		ArrayList<HexaCell> hexasCtrl = joueurAct.getCasesCtrl();
		
		for(int i = 0; i < hexasCtrl.size(); i++){
			hexasCtrl.get(i).setColor(couleur);
		}
		
		hexasCtrl = getConnectedCellsOfSameColor(hexasCtrl);
		
		joueurAct.setCasesCtrl(hexasCtrl);
		for(HexaCell hexa : hexasCtrl){
			hexa.setCtrlBy(joueurAct.getNom());
		}
		
		repaint();
		
		if(joueurSui.isIA()){
			
			return joueurSui;
		}
		else{
			return null;
		}
	}
	
	public Color nextSimpleIAMove(){	// IA Aléatoire
		
		ArrayList<Color> freeColors = getFreeColors();
		
		Random r = new Random();
		int alea = r.nextInt(freeColors.size());
		
		Color color = freeColors.get(alea);
		
		return color;
	}
	
	public Color nextIntermediateIAMove(Player joueur){
		
		ArrayList<Color> freeColors = getFreeColors();
		
		int max  = joueur.getCasesCtrl().size();
		ArrayList<HexaCell> hexasCtrl = joueur.getCasesCtrl();
		
		Color colorIni = joueur.getCasesCtrl().get(0).getColor();
		Color color = Color.black;
		
		for(int i = 0; i < hexasCtrl.size(); i++)	{ hexasCtrl.get(i).setColor(Color.red); 	}
		int redList = getConnectedCellsOfSameColor(hexasCtrl).size();
		if( redList >= max 		&& freeColors.contains(Color.red))		{ color = Color.red; 		max = redList;		}
		
		for(int i = 0; i < hexasCtrl.size(); i++)	{ hexasCtrl.get(i).setColor(Color.orange); 	}
		int orangeList = getConnectedCellsOfSameColor(hexasCtrl).size();
		if( orangeList >= max 	&& freeColors.contains(Color.orange))	{ color = Color.orange; 	max = orangeList;	}
		
		for(int i = 0; i < hexasCtrl.size(); i++)	{ hexasCtrl.get(i).setColor(Color.yellow); 	}
		int yellowList = getConnectedCellsOfSameColor(hexasCtrl).size();
		if( yellowList >= max 	&& freeColors.contains(Color.yellow))	{ color = Color.yellow; 	max = yellowList;	}
		
		for(int i = 0; i < hexasCtrl.size(); i++)	{ hexasCtrl.get(i).setColor(Color.green); 	}
		int greenList = getConnectedCellsOfSameColor(hexasCtrl).size();
		if( greenList >= max 	&& freeColors.contains(Color.green))	{ color = Color.green; 		max = greenList;	}
		
		for(int i = 0; i < hexasCtrl.size(); i++)	{ hexasCtrl.get(i).setColor(Color.blue); 	}
		int blueList = getConnectedCellsOfSameColor(hexasCtrl).size();
		if( blueList >= max 		&& freeColors.contains(Color.blue))	{ color = Color.blue; 		max = blueList;		}
		
		for(int i = 0; i < hexasCtrl.size(); i++){ hexasCtrl.get(i).setColor(Color.magenta); 	}
		int magentaList = getConnectedCellsOfSameColor(hexasCtrl).size();
		if( magentaList >= max 	&& freeColors.contains(Color.magenta))	{ color = Color.magenta; 	max = magentaList;	}
		
		for(int i = 0; i < hexasCtrl.size(); i++){ hexasCtrl.get(i).setColor(colorIni); }
		
		//try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
		
		return color;
	}
	
	public Color nextAdvancedIAMove(){
		
		int gridSize = (this.grille.length)*(this.grille.length);
		
		HexaCell[][] gridSimu = new HexaCell[grille.length][grille.length];
		
		for(int i = 0; i < grille.length; i++){
			for(int j = 0; j < grille.length; j++){
				gridSimu[i][j] = grille[i][j].clone();
			}
		}
		
		gridSimu = defVoisins(gridSimu);
		
		Player playerSimu1 = this.joueur1.clone(gridSimu);
		Player playerSimu2 = this.joueur2.clone(gridSimu);
		Player playerSimu3 = null;
		Player playerSimu4 = null;
		
		if(this.joueur3 != null){ playerSimu3 = this.joueur3.clone(gridSimu); }
		if(this.joueur4 != null){ playerSimu4 = this.joueur4.clone(gridSimu); }
		
		Player[] players = {playerSimu1, playerSimu2, playerSimu3, playerSimu4};
		
		int nbJoueurs = 2;
		if(players[2] != null){ nbJoueurs++; }
		if(players[3] != null){ nbJoueurs++; }
		
		//System.out.println("!!! "+playerSimu1.getCasesCtrl().size());
		
		DecisionTree dt = new DecisionTree();
		dt = decisionSimu(dt, gridSize, nbJoueurs, 0, players, gridSimu);
		
		int h = DecisionTree.heightTree(dt);
		
		System.out.println("hauteur : "+h);
		
		printSmallTree(dt, gridSize);
		
		return null;
	}
	
	public void nextLimitedIAMove(){
		
		int nbPlayers = 2;
		
		ArrayList<Color> couleursNonDispo = new ArrayList<Color>();
		
		String playerName1 = this.joueur1.getNom();		Color color1 = this.joueur1.getCouleur();	couleursNonDispo.add(color1);
		String playerName2 = this.joueur2.getNom();		Color color2 = this.joueur2.getCouleur();	couleursNonDispo.add(color2);
		String playerName3 = ""; 						Color color3 = null;	
		String playerName4 = ""; 						Color color4 = null;	
		
		if(this.joueur3 != null){ playerName3 = this.joueur3.getNom(); color3 = this.joueur3.getCouleur(); couleursNonDispo.add(color3); nbPlayers++;}
		if(this.joueur4 != null){ playerName4 = this.joueur4.getNom(); color4 = this.joueur4.getCouleur(); couleursNonDispo.add(color4); nbPlayers++;}
		
		ArrayList<Color> couleursDispo = getFreeLimitedColors(couleursNonDispo);
	}
	
	public void limitedSimu(){
		
	}
	
	private ArrayList<Color> getFreeLimitedColors(ArrayList<Color> couleursNonDispo){
		
		ArrayList<Color> couleursDispo = new ArrayList<Color>();
		
		if(!couleursNonDispo.contains(Color.red)	){ 	couleursDispo.add(Color.red); 		}
		if(!couleursNonDispo.contains(Color.orange)	){ 	couleursDispo.add(Color.orange); 	}
		if(!couleursNonDispo.contains(Color.yellow)	){ 	couleursDispo.add(Color.yellow); 	}
		if(!couleursNonDispo.contains(Color.green)	){ 	couleursDispo.add(Color.green); 	}
		if(!couleursNonDispo.contains(Color.blue)	){ 	couleursDispo.add(Color.blue); 		}
		if(!couleursNonDispo.contains(Color.magenta)){	couleursDispo.add(Color.magenta);	}
		
		return couleursDispo;
	}
	
	public void printSmallTree(DecisionTree dt, int gridSize){	// Juste pour afficher un arbre d'une grille 4x4, pour voir un peu à quoi ressemble l'arbe...
	
		if(gridSize == 16){
			
			//for(int i = 0; i < 40; i++) System.out.print(" ");
			//System.out.print(dt.getPlayer().getNom()+" : "+dt.getPlayer().getCouleur());
			
			System.out.print("\n");
									if(dt.getRedChild() 	!= null){ 	System.out.print(dt.getRedChild().getPlayer().getNom()		+"r"); }else{	System.out.print("  "); }
			System.out.print("  ");	if(dt.getOrangeChild() 	!= null){	System.out.print(dt.getOrangeChild().getPlayer().getNom()	+"o"); }else{	System.out.print("  "); }
			System.out.print("  ");	if(dt.getYellowChild()	!= null){ 	System.out.print(dt.getYellowChild().getPlayer().getNom()	+"y"); }else{	System.out.print("  "); }
			System.out.print("  ");	if(dt.getGreenChild() 	!= null){ 	System.out.print(dt.getGreenChild().getPlayer().getNom()	+"g"); }else{	System.out.print("  "); }
			System.out.print("  ");	if(dt.getBlueChild() 	!= null){ 	System.out.print(dt.getBlueChild().getPlayer().getNom()		+"b"); }else{	System.out.print("  "); }
			System.out.print("  ");	if(dt.getMagentaChild() != null){ 	System.out.print(dt.getMagentaChild().getPlayer().getNom()	+"m"); }else{	System.out.print("  "); }
			
			System.out.print("\n");
			if(dt.getRedChild() != null){
				
				if(dt.getRedChild().getRedChild()     != null) 	System.out.print(dt.getRedChild().getRedChild().getPlayer().getNom()		+"r");
				if(dt.getRedChild().getOrangeChild()  != null) 	System.out.print(dt.getRedChild().getOrangeChild().getPlayer().getNom()		+"o");
				if(dt.getRedChild().getYellowChild()  != null) 	System.out.print(dt.getRedChild().getYellowChild().getPlayer().getNom()		+"y");
				if(dt.getRedChild().getGreenChild()   != null) 	System.out.print(dt.getRedChild().getGreenChild().getPlayer().getNom()		+"g");
				if(dt.getRedChild().getBlueChild() 	  != null) 	System.out.print(dt.getRedChild().getBlueChild().getPlayer().getNom()		+"b");
				if(dt.getRedChild().getMagentaChild() != null) 	System.out.print(dt.getRedChild().getMagentaChild().getPlayer().getNom()	+"m");
			}else{	System.out.print("  "); }
					System.out.print("  ");
			if(dt.getOrangeChild() != null){
				
				if(dt.getOrangeChild().getRedChild()     != null) 	System.out.print(dt.getOrangeChild().getRedChild().getPlayer().getNom()		+"r");
				if(dt.getOrangeChild().getOrangeChild()  != null) 	System.out.print(dt.getOrangeChild().getOrangeChild().getPlayer().getNom()	+"o");
				if(dt.getOrangeChild().getYellowChild()  != null) 	System.out.print(dt.getOrangeChild().getYellowChild().getPlayer().getNom()	+"y");
				if(dt.getOrangeChild().getGreenChild()   != null) 	System.out.print(dt.getOrangeChild().getGreenChild().getPlayer().getNom()	+"g");
				if(dt.getOrangeChild().getBlueChild() 	 != null) 	System.out.print(dt.getOrangeChild().getBlueChild().getPlayer().getNom()	+"b");
				if(dt.getOrangeChild().getMagentaChild() != null) 	System.out.print(dt.getOrangeChild().getMagentaChild().getPlayer().getNom()	+"m");
			}else{	System.out.print("  "); }
					System.out.print("  ");
			if(dt.getYellowChild() != null){
				
				if(dt.getYellowChild().getRedChild()     != null) 	System.out.print(dt.getYellowChild().getRedChild().getPlayer().getNom()		+"r");
				if(dt.getYellowChild().getOrangeChild()  != null) 	System.out.print(dt.getYellowChild().getOrangeChild().getPlayer().getNom()	+"o");
				if(dt.getYellowChild().getYellowChild()  != null) 	System.out.print(dt.getYellowChild().getYellowChild().getPlayer().getNom()	+"y");
				if(dt.getYellowChild().getGreenChild()   != null) 	System.out.print(dt.getYellowChild().getGreenChild().getPlayer().getNom()	+"g");
				if(dt.getYellowChild().getBlueChild() 	 != null) 	System.out.print(dt.getYellowChild().getBlueChild().getPlayer().getNom()	+"b");
				if(dt.getYellowChild().getMagentaChild() != null) 	System.out.print(dt.getYellowChild().getMagentaChild().getPlayer().getNom()	+"m");
			}else{	System.out.print("  "); }
					System.out.print("  ");
			if(dt.getGreenChild() != null){
				
				if(dt.getGreenChild().getRedChild()     != null) 	System.out.print(dt.getGreenChild().getRedChild().getPlayer().getNom()		+"r");
				if(dt.getGreenChild().getOrangeChild()  != null) 	System.out.print(dt.getGreenChild().getOrangeChild().getPlayer().getNom()	+"o");
				if(dt.getGreenChild().getYellowChild()  != null) 	System.out.print(dt.getGreenChild().getYellowChild().getPlayer().getNom()	+"y");
				if(dt.getGreenChild().getGreenChild()   != null) 	System.out.print(dt.getGreenChild().getGreenChild().getPlayer().getNom()	+"g");
				if(dt.getGreenChild().getBlueChild() 	!= null) 	System.out.print(dt.getGreenChild().getBlueChild().getPlayer().getNom()		+"b");
				if(dt.getGreenChild().getMagentaChild() != null) 	System.out.print(dt.getGreenChild().getMagentaChild().getPlayer().getNom()	+"m");
			}else{	System.out.print("  "); }
					System.out.print("  ");
			if(dt.getBlueChild() != null){
				
				if(dt.getBlueChild().getRedChild()     	!= null) 	System.out.print(dt.getBlueChild().getRedChild().getPlayer().getNom()		+"r");
				if(dt.getBlueChild().getOrangeChild()  	!= null) 	System.out.print(dt.getBlueChild().getOrangeChild().getPlayer().getNom()	+"o");
				if(dt.getBlueChild().getYellowChild()  	!= null) 	System.out.print(dt.getBlueChild().getYellowChild().getPlayer().getNom()	+"y");
				if(dt.getBlueChild().getGreenChild()   	!= null) 	System.out.print(dt.getBlueChild().getGreenChild().getPlayer().getNom()		+"g");
				if(dt.getBlueChild().getBlueChild() 	!= null) 	System.out.print(dt.getBlueChild().getBlueChild().getPlayer().getNom()		+"b");
				if(dt.getBlueChild().getMagentaChild() 	!= null) 	System.out.print(dt.getBlueChild().getMagentaChild().getPlayer().getNom()	+"m");
			}else{	System.out.print("  "); }
					System.out.print("  ");
			if(dt.getMagentaChild() != null){
				
				if(dt.getMagentaChild().getRedChild()     	!= null) 	System.out.print(dt.getMagentaChild().getRedChild().getPlayer().getNom()	+"r");
				if(dt.getMagentaChild().getOrangeChild()  	!= null) 	System.out.print(dt.getMagentaChild().getOrangeChild().getPlayer().getNom()	+"o");
				if(dt.getMagentaChild().getYellowChild()  	!= null) 	System.out.print(dt.getMagentaChild().getYellowChild().getPlayer().getNom()	+"y");
				if(dt.getMagentaChild().getGreenChild()   	!= null) 	System.out.print(dt.getMagentaChild().getGreenChild().getPlayer().getNom()	+"g");
				if(dt.getMagentaChild().getBlueChild() 		!= null) 	System.out.print(dt.getMagentaChild().getBlueChild().getPlayer().getNom()	+"b");
				if(dt.getMagentaChild().getMagentaChild() 	!= null) 	System.out.print(dt.getMagentaChild().getMagentaChild().getPlayer().getNom()+"m");
			}
		}
	}
	
	/*
	 * Création de l'arbre des décisions possibles ...
	 */
	public DecisionTree decisionSimu(DecisionTree dt, int gridSize, int nbJoueurs, int joueurAct, Player[] players, HexaCell[][] gridSimu){

		ArrayList<Color> couleursDispo = getFreeSimuColors(players);
		
		Player player = players[joueurAct];
		
		ArrayList<HexaCell> hexasCtrl = player.getCasesCtrl();
		int prevNbHexasCtrl = 0;
		int actNbHexasCtrl  = 0;
		
		for(Color itemColor : couleursDispo){
			
			for(int i = 0; i < hexasCtrl.size(); i++){
				hexasCtrl.get(i).setColor(itemColor);
			}
			player.setCouleur(itemColor);
			
			prevNbHexasCtrl = hexasCtrl.size();
			hexasCtrl = HexaBoard.getConnectedCellsOfSameColor(hexasCtrl);
			actNbHexasCtrl  = hexasCtrl.size();
	
			if((actNbHexasCtrl > prevNbHexasCtrl)){
	
				player.setCasesCtrl(hexasCtrl);
				for(HexaCell hexa : hexasCtrl){
					hexa.setCtrlBy(player.getNom());
				}
	
				if(hexasCtrl.size() > (gridSize/nbJoueurs)){
					player.setIsWinner(true);
					return dt;
				}
	
				player.setMyTurn(false);
	
				gridSimu = new HexaCell[grille.length][grille.length];
				
				for(int i = 0; i < grille.length; i++){
					for(int j = 0; j < grille.length; j++){
						gridSimu[i][j] = grille[i][j].clone();
					}
				}
				
				gridSimu = defVoisins(gridSimu);
	
				if(itemColor == Color.red) 		{ dt.setRedChild(		new DecisionTree(player.clone(gridSimu)));	}
				if(itemColor == Color.orange) 	{ dt.setOrangeChild(	new DecisionTree(player.clone(gridSimu)));	}
				if(itemColor == Color.yellow) 	{ dt.setYellowChild(	new DecisionTree(player.clone(gridSimu)));	}
				if(itemColor == Color.green) 	{ dt.setGreenChild(		new DecisionTree(player.clone(gridSimu)));	}
				if(itemColor == Color.blue) 	{ dt.setBlueChild(		new DecisionTree(player.clone(gridSimu)));	}
				if(itemColor == Color.magenta) 	{ dt.setMagentaChild(	new DecisionTree(player.clone(gridSimu)));	}
	
				if(joueurAct == 3)				{ joueurAct = 0; }
				if(players[joueurAct+1] == null){ joueurAct = 0; }
				else							{ joueurAct++;   }
	
				players[joueurAct].setMyTurn(true);
				
				Player[] playersBis = new Player[4];
				
				playersBis[0] = players[0].clone(gridSimu);
				playersBis[1] = players[1].clone(gridSimu);
				
				if(players[2] != null){ playersBis[2] = players[2].clone(gridSimu); } else{ playersBis[2] = null; }
				if(players[3] != null){ playersBis[3] = players[3].clone(gridSimu); } else{ playersBis[3] = null; }
				
				if(playersBis[0].getNom().equals(player.getNom())){ playersBis[0] = player.clone(gridSimu); }
				if(playersBis[1].getNom().equals(player.getNom())){ playersBis[1] = player.clone(gridSimu); }
				
				if(playersBis[2] != null){	if(playersBis[2].getNom().equals(player.getNom())){ playersBis[2] = player.clone(gridSimu); }}
				if(playersBis[3] != null){	if(playersBis[3].getNom().equals(player.getNom())){ playersBis[3] = player.clone(gridSimu); }}
	
				if(itemColor == Color.red) 		{ decisionSimu(dt.getRedChild(), 		gridSize, nbJoueurs, joueurAct, playersBis, gridSimu);	}
				if(itemColor == Color.orange) 	{ decisionSimu(dt.getOrangeChild(), 	gridSize, nbJoueurs, joueurAct, playersBis, gridSimu);	}	
				if(itemColor == Color.yellow) 	{ decisionSimu(dt.getYellowChild(), 	gridSize, nbJoueurs, joueurAct, playersBis, gridSimu);	}
				if(itemColor == Color.green) 	{ decisionSimu(dt.getGreenChild(),		gridSize, nbJoueurs, joueurAct, playersBis, gridSimu);	}
				if(itemColor == Color.blue) 	{ decisionSimu(dt.getBlueChild(),		gridSize, nbJoueurs, joueurAct, playersBis, gridSimu);	}
				if(itemColor == Color.magenta)	{ decisionSimu(dt.getMagentaChild(),	gridSize, nbJoueurs, joueurAct, playersBis, gridSimu);	}
				
			}
		}
		
		return dt;
	}
	
	
	public ArrayList<Color> getFreeSimuColors(Player[] players){
		
		ArrayList<Color> couleursNonDispo = new ArrayList<Color>();
		
		Color couleur1 = players[0].getCasesCtrl().get(0).getColor();
		Color couleur2 = players[1].getCasesCtrl().get(0).getColor();
		
		Color couleur3 = null;
		Color couleur4 = null;
		
		if(this.getJoueur3() != null){
			couleur3 = players[2].getCasesCtrl().get(0).getColor();
		}
		if(this.getJoueur4() != null){
			couleur4 = players[3].getCasesCtrl().get(0).getColor();
		}
		
		couleursNonDispo.add(couleur1);
		couleursNonDispo.add(couleur2);
		
		if(players[2] != null){ couleursNonDispo.add(couleur3); }
		if(players[3] != null){ couleursNonDispo.add(couleur4); }
		
		ArrayList<Color> couleursDispo = new ArrayList<Color>();

		if(!couleursNonDispo.contains(Color.red))		couleursDispo.add(Color.red);
		if(!couleursNonDispo.contains(Color.orange))	couleursDispo.add(Color.orange);
		if(!couleursNonDispo.contains(Color.yellow))	couleursDispo.add(Color.yellow);
		if(!couleursNonDispo.contains(Color.green))		couleursDispo.add(Color.green);
		if(!couleursNonDispo.contains(Color.blue))		couleursDispo.add(Color.blue);
		if(!couleursNonDispo.contains(Color.magenta))	couleursDispo.add(Color.magenta);
		
		return couleursDispo;
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
		
		g.setColor(Color.white);
		g.drawString("Joueur1 : \""+joueur1.getNom()+"\"      |      Score : "+joueur1.getCasesCtrl().size(), 10, 10);
		g.drawString("Joueur2 : \""+joueur2.getNom()+"\"      |      Score : "+joueur2.getCasesCtrl().size(), 10, 25);
		
		if(joueur3 != null){
			g.drawString("Joueur3 : \""+joueur3.getNom()+"\"      |      Score : "+joueur3.getCasesCtrl().size(), 10, 40);
		}
		if(joueur4 != null){
			g.drawString("Joueur4 : \""+joueur4.getNom()+"\"      |      Score : "+joueur4.getCasesCtrl().size(), 10, 55);
		}
		
		for(int i = 0; i < grille.length; i++){
			for(int j = 0; j< grille[0].length; j++){
				
				int x[] = {grille[i][j].getCentreX(),		grille[i][j].getCentreX()+10,	grille[i][j].getCentreX()+10,	grille[i][j].getCentreX(), 		grille[i][j].getCentreX()-10, 	grille[i][j].getCentreX()-10};
				int y[] = {grille[i][j].getCentreY()+15,	grille[i][j].getCentreY()+5, 	grille[i][j].getCentreY()-5, 	grille[i][j].getCentreY()-15, 	grille[i][j].getCentreY()-5, 	grille[i][j].getCentreY()+5};
				
				g.setColor(grille[i][j].getColor());
				g.fillPolygon(x, y, 6);
				
				g.setColor(Color.black);
				g.drawPolygon(x, y, 6);
				
				if(joueur1.getCasesCtrl().contains(grille[i][j])){
					g.drawString("1", grille[i][j].getCentreX()-2, grille[i][j].getCentreY()+5);
				}
				if(joueur2.getCasesCtrl().contains(grille[i][j])){
					g.drawString("2", grille[i][j].getCentreX()-2, grille[i][j].getCentreY()+5);
				}
				if(joueur3 != null){
					if(joueur3.getCasesCtrl().contains(grille[i][j])){
						g.drawString("3", grille[i][j].getCentreX()-2, grille[i][j].getCentreY()+5);
					}
				}
				if(joueur4 != null){
					if(joueur4.getCasesCtrl().contains(grille[i][j])){
						g.drawString("4", grille[i][j].getCentreX()-2, grille[i][j].getCentreY()+5);
					}
				}
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
