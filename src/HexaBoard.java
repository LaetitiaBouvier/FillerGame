import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Cette classe permet la gestion de tableaux de cellules hexagonales
 */
public class HexaBoard extends Canvas implements Board, Cloneable {
	
	private static final long serialVersionUID = 1L;
	
	protected HexaCell[][] grille;		// grille[i][j] : j represente les lignes, i les éléments en colonne de chaque ligne
	protected int hauteur;
	protected int largeur;
	protected Player joueur1;
	protected Player joueur2;
	protected Player joueur3;
	protected Player joueur4;
	
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
	public HexaBoard(int nb, String nomJoueur1, String nomJoueur2, String nomJoueur3, String nomJoueur4, String IA1, String IA2, String IA3, String IA4) {
		
		int h = 20*nb +60;
		int l = 20*nb +180;
		
		// Settings :
		setBackground (Color.black);
        setSize(h, l);
        
        this.hauteur = h;
        this.largeur = l;
        
        initialisationGrille(nb);
		
		this.grille = defVoisins(this.grille);
		
		defJoueurs(nb, nomJoueur1, nomJoueur2, nomJoueur3, nomJoueur4, IA1, IA2, IA3, IA4);
		
	}
	
	public HexaBoard(HexaCell[][] grille, Player joueur1, Player joueur2, Player joueur3, Player joueur4){
		
		int h = 20*grille.length +60;
		int l = 20*grille.length +180;
		
		this.hauteur = h;
        this.largeur = l;
        
        this.grille = grille;
        
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueur3 = joueur3;
        this.joueur4 = joueur4;
	}
	
	public HexaBoard(String saveStr){
		
		Scanner scLine = new Scanner(saveStr);
		
		scLine.next();
		int nb = Integer.parseInt(scLine.next());
		scLine.close();
		
		int h = 20*nb +60;
		int l = 20*nb +180;
		
		// Settings :
		setBackground (Color.black);
		setSize(h, l);
		
		this.hauteur = h;
        this.largeur = l;
		
		initialisationGrille(nb, saveStr);
		
		this.grille = defVoisins(this.grille);
		
		defJoueurs(nb, saveStr);
	}
	
	/**
	 * Cette fonction initialise la grille des cellules (ici un pavage d'hexagones) en créant les cellules à leur place et en leur attribuant une couleur aléatoire
	 * 
	 * @param nb	: entier représentant la taille d'un côté du tableau
	 */
	private void initialisationGrille(int nb){
		
		int decalageX = 0;
		int margeY = 120;
		
		this.grille = new HexaCell[nb][nb];
		
		for(int i = 0; i < grille.length; i++){
			for(int j = 0; j < grille[0].length; j++){
				
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
	
	public void initialisationGrille(int nb, String saveStr){
		
		int decalageX = 0;
		int margeY = 120;
		
		this.grille = new HexaCell[nb][nb];
		
		Scanner sc = new Scanner(saveStr);
		
		sc.next();
		sc.next();
		
		String[] str =new String[3];
		String colorStr = "";
		Color color = Color.black;
		
		for(int i = 0; i < grille.length; i++){
			for(int j = 0; j < grille[0].length; j++){
				
				if(i %2 == 0){ decalageX = -10;}
				if(i %2 == 1){ decalageX = 0;}
				
				
				str = sc.next().split("_");
				colorStr = str[2];
				
				if(colorStr.contains("[r=255,g=0,b=0]"))	{ color = Color.red; 		}
				if(colorStr.contains("[r=255,g=200,b=0]"))	{ color = Color.orange; 	}
				if(colorStr.contains("[r=255,g=255,b=0]"))	{ color = Color.yellow; 	}
				if(colorStr.contains("[r=0,g=255,b=0]"))	{ color = Color.green; 		}
				if(colorStr.contains("[r=0,g=0,b=255]"))	{ color = Color.blue; 		}
				if(colorStr.contains("[r=255,g=0,b=255]"))	{ color = Color.magenta;	}
				
				grille[j][i] = new HexaCell(30+j*20+decalageX, 30+i*20+margeY, color, null, null, null, null, null, null);
			}
		}
		
		sc.close();
	}
	
	/**
	 * Cette fonction définie les voisins de chaque cellule héxagonale (liaison des cellules entre elles) en fonction de son positionnement sur la grille
	 * 
	 */
	protected HexaCell[][] defVoisins(HexaCell[][] grille){
		
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
	private void defJoueurs( int nb, String nomJoueur1, String nomJoueur2, String nomJoueur3, String nomJoueur4, String IA1, String IA2, String IA3, String IA4){
		
		ArrayList<Cell> listeInitialeJ1 = new ArrayList<Cell>();
		ArrayList<Cell> listeInitialeJ2 = new ArrayList<Cell>();
		
		listeInitialeJ1.add(grille[0][0]);			grille[0][0].setCtrlBy(nomJoueur1);
		listeInitialeJ1 = getConnectedCellsOfSameColor(listeInitialeJ1);
		
		this.joueur1 = new Player(nomJoueur1, grille[0][0].getColor(), listeInitialeJ1.size(), listeInitialeJ1, IA1);
		this.joueur1.setMyTurn(true);
		
		listeInitialeJ2.add(grille[nb-1][nb-1]);	grille[nb-1][nb-1].setCtrlBy(nomJoueur2);
		listeInitialeJ2 = getConnectedCellsOfSameColor(listeInitialeJ2);
		
		this.joueur2 = new Player(nomJoueur2, grille[nb-1][nb-1].getColor(), listeInitialeJ2.size(), listeInitialeJ2, IA2);
		
		if(!nomJoueur3.isEmpty()){
			
			ArrayList<Cell> listeInitialeJ3 = new ArrayList<Cell>();
			
			listeInitialeJ3.add(grille[nb-1][0]);	grille[nb-1][0].setCtrlBy(nomJoueur3);
			listeInitialeJ3 = getConnectedCellsOfSameColor(listeInitialeJ3);
			
			this.joueur3 = new Player(nomJoueur3, grille[nb-1][0].getColor(), listeInitialeJ3.size(), listeInitialeJ3, IA3);
		}
		else{
			this.joueur3 = null;
		}
		
		if(!nomJoueur4.isEmpty()){
			
			ArrayList<Cell> listeInitialeJ4 = new ArrayList<Cell>();
			
			listeInitialeJ4.add(grille[0][nb-1]);	grille[0][nb-1].setCtrlBy(nomJoueur4);
			listeInitialeJ4 = getConnectedCellsOfSameColor(listeInitialeJ4);
			
			this.joueur4 = new Player(nomJoueur4, grille[0][nb-1].getColor(), listeInitialeJ4.size(), listeInitialeJ4, IA4);
		}
		else{
			this.joueur4 = null;
		}
	}
	
	public void defJoueurs(int nb, String saveStr){
		
		Scanner sc = new Scanner(saveStr);
		
		sc.next(); // Type partie
		sc.next(); // Nb
		for(int i = 0; i < nb; i++){ sc.nextLine(); } // Grille
		
		sc.nextLine();
		sc.nextLine();
		Scanner scLine = new Scanner(sc.nextLine());
		String nomJoueur1 = scLine.next();	System.out.println("NOM JOUEUR 1 : "+nomJoueur1);
		String IA1 = scLine.next(); if(IA1.equals("IA")){ IA1 += " "+scLine.next(); }
		String isJ1TurnStr = scLine.next();
		String colorJ1Str = scLine.next();
		
		Color colorJ1 = null;
		if(colorJ1Str.equals("[r=255,g=0,b=0]"	))	{ colorJ1 = Color.red; 		}
		if(colorJ1Str.equals("[r=255,g=200,b=0]"))	{ colorJ1 = Color.orange; 	}
		if(colorJ1Str.equals("[r=255,g=255,b=0]"))	{ colorJ1 = Color.yellow; 	}
		if(colorJ1Str.equals("[r=0,g=255,b=0]"	))	{ colorJ1 = Color.green; 	}
		if(colorJ1Str.equals("[r=0,g=0,b=255]"	))	{ colorJ1 = Color.blue; 	}
		if(colorJ1Str.equals("[r=255,g=0,b=255]"))	{ colorJ1 = Color.magenta; 	}
		
		ArrayList<Cell> listeJ1 = new ArrayList<Cell>();
		
		String[] ij = new String[2];
		while(scLine.hasNext()){
			ij = scLine.next().split("_");
			
			int i = Integer.parseInt(ij[0]);
			int j = Integer.parseInt(ij[1]);
			
			this.grille[i][j].setCtrlBy(nomJoueur1);
			listeJ1.add(grille[i][j]);
		}
		
		this.joueur1 = new Player(nomJoueur1, colorJ1, listeJ1.size(), listeJ1, IA1);
		if(isJ1TurnStr.equals("true"))	{ this.joueur1.setMyTurn(true); }
		else							{ this.joueur1.setMyTurn(false);}
		
		scLine.close();
		scLine = new Scanner(sc.nextLine());
		String nomJoueur2 = scLine.next();	System.out.println("NOM JOUEUR 2 : "+nomJoueur2);
		String IA2 = scLine.next(); if(IA2.equals("IA")){ IA2 += " "+scLine.next(); }
		String isJ2TurnStr = scLine.next();
		String colorJ2Str = scLine.next();
		
		Color colorJ2 = null;
		if(colorJ2Str.equals("[r=255,g=0,b=0]"	))	{ colorJ2 = Color.red; 		}
		if(colorJ2Str.equals("[r=255,g=200,b=0]"))	{ colorJ2 = Color.orange; 	}
		if(colorJ2Str.equals("[r=255,g=255,b=0]"))	{ colorJ2 = Color.yellow; 	}
		if(colorJ2Str.equals("[r=0,g=255,b=0]"	))	{ colorJ2 = Color.green; 	}
		if(colorJ2Str.equals("[r=0,g=0,b=255]"	))	{ colorJ2 = Color.blue; 	}
		if(colorJ2Str.equals("[r=255,g=0,b=255]"))	{ colorJ2 = Color.magenta; 	}
		
		ArrayList<Cell> listeJ2 = new ArrayList<Cell>();
		
		ij = new String[2];
		while(scLine.hasNext()){
			ij = scLine.next().split("_");
			
			int i = Integer.parseInt(ij[0]);
			int j = Integer.parseInt(ij[1]);
			
			this.grille[i][j].setCtrlBy(nomJoueur2);
			listeJ2.add(grille[i][j]);
		}
		
		this.joueur2 = new Player(nomJoueur2, colorJ2, listeJ2.size(), listeJ2, IA2);
		if(isJ2TurnStr.equals("true"))	{ this.joueur2.setMyTurn(true); }
		else							{ this.joueur2.setMyTurn(false);}
		
		scLine.close();
		if(sc.hasNextLine()){
			
			scLine = new Scanner(sc.nextLine());
			String nomJoueur3 = scLine.next();	System.out.println("NOM JOUEUR 3 : "+nomJoueur3);
			String IA3 = scLine.next(); if(IA3.equals("IA")){ IA3 += " "+scLine.next(); }
			String isJ3TurnStr = scLine.next();
			String colorJ3Str = scLine.next();
			
			Color colorJ3 = null;
			if(colorJ3Str.equals("[r=255,g=0,b=0]"	))	{ colorJ3 = Color.red; 		}
			if(colorJ3Str.equals("[r=255,g=200,b=0]"))	{ colorJ3 = Color.orange; 	}
			if(colorJ3Str.equals("[r=255,g=255,b=0]"))	{ colorJ3 = Color.yellow; 	}
			if(colorJ3Str.equals("[r=0,g=255,b=0]"	))	{ colorJ3 = Color.green; 	}
			if(colorJ3Str.equals("[r=0,g=0,b=255]"	))	{ colorJ3 = Color.blue; 	}
			if(colorJ3Str.equals("[r=255,g=0,b=255]"))	{ colorJ3 = Color.magenta; 	}
			
			ArrayList<Cell> listeJ3 = new ArrayList<Cell>();
			
			ij = new String[2];
			while(scLine.hasNext()){
				ij = scLine.next().split("_");
				
				int i = Integer.parseInt(ij[0]);
				int j = Integer.parseInt(ij[1]);
				
				this.grille[i][j].setCtrlBy(nomJoueur3);
				listeJ3.add(grille[i][j]);
			}
			
			this.joueur3 = new Player(nomJoueur3, colorJ3, listeJ3.size(), listeJ3, IA3);
			if(isJ3TurnStr.equals("true"))	{ this.joueur3.setMyTurn(true); }
			else							{ this.joueur3.setMyTurn(false);}
		}
		scLine.close();
		if(sc.hasNextLine()){
			
			scLine = new Scanner(sc.nextLine());
			String nomJoueur4 = scLine.next();	System.out.println("NOM JOUEUR 4 : "+nomJoueur4);
			String IA4 = scLine.next(); if(IA4.equals("IA")){ IA4 += " "+scLine.next(); }
			String isJ4TurnStr = scLine.next();
			String colorJ4Str = scLine.next();
			
			Color colorJ4 = null;
			if(colorJ4Str.equals("[r=255,g=0,b=0]"	))	{ colorJ4 = Color.red; 		}
			if(colorJ4Str.equals("[r=255,g=200,b=0]"))	{ colorJ4 = Color.orange; 	}
			if(colorJ4Str.equals("[r=255,g=255,b=0]"))	{ colorJ4 = Color.yellow; 	}
			if(colorJ4Str.equals("[r=0,g=255,b=0]"	))	{ colorJ4 = Color.green; 	}
			if(colorJ4Str.equals("[r=0,g=0,b=255]"	))	{ colorJ4 = Color.blue; 	}
			if(colorJ4Str.equals("[r=255,g=0,b=255]"))	{ colorJ4 = Color.magenta; 	}
			
			ArrayList<Cell> listeJ4 = new ArrayList<Cell>();
			
			ij = new String[2];
			while(scLine.hasNext()){
				ij = scLine.next().split("_");
				
				int i = Integer.parseInt(ij[0]);
				int j = Integer.parseInt(ij[1]);
				
				this.grille[i][j].setCtrlBy(nomJoueur4);
				listeJ4.add(grille[i][j]);
			}
			
			this.joueur4 = new Player(nomJoueur4, colorJ4, listeJ4.size(), listeJ4, IA4);
			if(isJ4TurnStr.equals("true"))	{ this.joueur4.setMyTurn(true); }
			else							{ this.joueur4.setMyTurn(false);}
		}
		
		sc.close();
	}
	
	/**
	 * Cette fonction récursive permet d'obtenir, à partir d'une liste initiale de celulles hexagonales, une liste "augmentée" : comprennant en plus leurs voisines de même couleur 
	 * 
	 * @param liste		: liste des cellules hexagonales à partir desquels on souhaite obtenir la liste "augmentée" 
	 * @return liste	: liste des cellules hexagonales comprennant la liste de départ et la liste "augmentée"
	 */
	public static ArrayList<Cell> getConnectedCellsOfSameColor(ArrayList<Cell> listeIni){
		
		@SuppressWarnings("unchecked")
		ArrayList<Cell> liste = (ArrayList<Cell>) listeIni.clone();
		
		boolean add = false;
		
		for(int i = 0; i < liste.size(); i++){
			
			HexaCell hexa = (HexaCell) liste.get(i);
			
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
		
		ArrayList<Cell> hexasCtrl = joueurAct.getCasesCtrl();
		
		for(int i = 0; i < hexasCtrl.size(); i++){
			hexasCtrl.get(i).setColor(couleur);
		}
		
		hexasCtrl = getConnectedCellsOfSameColor(hexasCtrl);
		
		joueurAct.setCasesCtrl(hexasCtrl);
		for(Cell cell : hexasCtrl){
			cell.setCtrlBy(joueurAct.getNom());
		}
		
		repaint();
		
		if(!joueurSui.getIA().equals("Sans")){
			
			return joueurSui;
		}
		else{
			return null;
		}
	}
	
	public Color nextEasyIAMove(){	// IA Aléatoire
		
		ArrayList<Color> freeColors = getFreeColors();
		
		Random r = new Random();
		int alea = r.nextInt(freeColors.size());
		
		Color color = freeColors.get(alea);
		
		return color;
	}
	
	public Color nextTroubleIAMove(Player joueur){
		
		Player nextPlayer = null;
		
		if(joueur == this.joueur1){ nextPlayer = this.joueur2; }
		if(joueur == this.joueur2){
			if		(this.joueur3 != null)	{ nextPlayer = this.joueur3; }
			else if (this.joueur4 != null)	{ nextPlayer = this.joueur4; }
			else							{ nextPlayer = this.joueur1; }
		}
		if(joueur == this.joueur3){
			if(this.joueur4 != null){ nextPlayer = this.joueur4; }
			else					{ nextPlayer = this.joueur1 ;}
		}
		if(joueur == this.joueur4){ nextPlayer = this.joueur1; }
		
		
		ArrayList<Color> freeColors = getFreeColors();
		
		int max  = nextPlayer.getCasesCtrl().size();
		ArrayList<Cell> hexasCtrl = nextPlayer.getCasesCtrl();
		
		Color colorIni = nextPlayer.getCasesCtrl().get(0).getColor();
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
		
		return color;
	}
	
	public Color nextHardIAMove(Player joueur){
		
		ArrayList<Color> freeColors = getFreeColors();
		
		int max  = joueur.getCasesCtrl().size();
		ArrayList<Cell> hexasCtrl = joueur.getCasesCtrl();
		
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
		
		return color;
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
	
	public boolean isTheGameOver(){
		
		boolean isTheGameOver = false;
		
		int nbJoueurs = 2;
		
		if(this.joueur3 != null) nbJoueurs++;
		if(this.joueur4 != null) nbJoueurs++;
		
		if(this.joueur1.getCasesCtrl().size() >= (this.grille.length*this.grille.length / nbJoueurs)){ isTheGameOver = true; }
		if(this.joueur2.getCasesCtrl().size() >= (this.grille.length*this.grille.length / nbJoueurs)){ isTheGameOver = true; }
		
		if(this.joueur3 != null){
			if(this.joueur3.getCasesCtrl().size() >= (this.grille.length*this.grille.length / nbJoueurs)){ isTheGameOver = true; }
		}
		
		if(this.joueur4 != null){
			if(this.joueur4.getCasesCtrl().size() >= (this.grille.length*this.grille.length / nbJoueurs)){ isTheGameOver = true; }
		}
		
		if(this.joueur1 == null){ isTheGameOver = false; } // Car dans ce cas la partie n'a même pas commencé...
		
		return isTheGameOver;
	}
	
	public String generateSaveString(){
		
		String saveStr = new String("");
		
		ArrayList<String> listeJoueur1 = new ArrayList<String>();
		ArrayList<String> listeJoueur2 = new ArrayList<String>();
		ArrayList<String> listeJoueur3 = new ArrayList<String>();
		ArrayList<String> listeJoueur4 = new ArrayList<String>();
		
		Color color = null;
		String colorStr = "";
		
		// Insère le type de partie
		saveStr += "HEXA\r\n";
		
		// Insère la taille d'un côté 
		saveStr += this.grille.length+"\r\n";
		
		// Insère tous les éléments de la grille
		for(int i = 0; i < grille.length; i++){
			for(int j = 0; j < grille[0].length; j++){
				
				color = grille[j][i].getColor();
				if(color == Color.red)		colorStr = "[r=255,g=0,b=0]";
				if(color == Color.orange) 	colorStr = "[r=255,g=200,b=0]";
				if(color == Color.yellow) 	colorStr = "[r=255,g=255,b=0]";
				if(color == Color.green) 	colorStr = "[r=0,g=255,b=0]";
				if(color == Color.blue) 	colorStr = "[r=0,g=0,b=255]";
				if(color == Color.magenta) 	colorStr = "[r=255,g=0,b=255]";
				
				saveStr += i+"_"+j+"_"+colorStr+"\t";
				
				if(!grille[i][j].getCtrlBy().isEmpty()){
					if(grille[i][j].getCtrlBy().equals(this.joueur1.getNom())){listeJoueur1.add(i+"_"+j);}
					if(grille[i][j].getCtrlBy().equals(this.joueur2.getNom())){listeJoueur2.add(i+"_"+j);}
					if(this.joueur3 != null){
						if(grille[i][j].getCtrlBy().equals(this.joueur3.getNom())){listeJoueur3.add(i+"_"+j);}
					}
					if(this.joueur4 != null){
						if(grille[i][j].getCtrlBy().equals(this.joueur4.getNom())){listeJoueur4.add(i+"_"+j);}
					}
				}
			}
			saveStr += "\r\n";
		}
		
		// Insère les caractéristiques des différents joueurs
		
		color = this.joueur1.getCouleur();
		if(color == Color.red)		colorStr = "[r=255,g=0,b=0] ";
		if(color == Color.orange) 	colorStr = "[r=255,g=200,b=0] ";
		if(color == Color.yellow) 	colorStr = "[r=255,g=255,b=0] ";
		if(color == Color.green) 	colorStr = "[r=0,g=255,b=0] ";
		if(color == Color.blue) 	colorStr = "[r=0,g=0,b=255] ";
		if(color == Color.magenta) 	colorStr = "[r=255,g=0,b=255] ";
		
		saveStr += "\r\n"+this.joueur1.getNom()+" "+this.joueur1.getIA()+" "+this.joueur1.isMyTurn()+" "+colorStr;
		for(String str : listeJoueur1){ saveStr += str+" "; }
		
		color = this.joueur2.getCouleur();
		if(color == Color.red)		colorStr = "[r=255,g=0,b=0] ";
		if(color == Color.orange) 	colorStr = "[r=255,g=200,b=0] ";
		if(color == Color.yellow) 	colorStr = "[r=255,g=255,b=0] ";
		if(color == Color.green) 	colorStr = "[r=0,g=255,b=0] ";
		if(color == Color.blue) 	colorStr = "[r=0,g=0,b=255] ";
		if(color == Color.magenta) 	colorStr = "[r=255,g=0,b=255] ";
		
		saveStr += "\r\n"+this.joueur2.getNom()+" "+this.joueur2.getIA()+" "+this.joueur2.isMyTurn()+" "+colorStr;
		for(String str : listeJoueur2){ saveStr += str+" "; }
		
		if(this.joueur3 != null){
			
			color = this.joueur3.getCouleur();
			if(color == Color.red)		colorStr = "[r=255,g=0,b=0] ";
			if(color == Color.orange) 	colorStr = "[r=255,g=200,b=0] ";
			if(color == Color.yellow) 	colorStr = "[r=255,g=255,b=0] ";
			if(color == Color.green) 	colorStr = "[r=0,g=255,b=0] ";
			if(color == Color.blue) 	colorStr = "[r=0,g=0,b=255] ";
			if(color == Color.magenta) 	colorStr = "[r=255,g=0,b=255] ";
			
			saveStr += "\r\n"+this.joueur3.getNom()+" "+this.joueur3.getIA()+" "+this.joueur3.isMyTurn()+" "+colorStr;
			for(String str : listeJoueur3){ saveStr += str+" "; }
		}
		if(this.joueur4 != null){
			
			color = this.joueur4.getCouleur();
			if(color == Color.red)		colorStr = "[r=255,g=0,b=0] ";
			if(color == Color.orange) 	colorStr = "[r=255,g=200,b=0] ";
			if(color == Color.yellow) 	colorStr = "[r=255,g=255,b=0] ";
			if(color == Color.green) 	colorStr = "[r=0,g=255,b=0] ";
			if(color == Color.blue) 	colorStr = "[r=0,g=0,b=255] ";
			if(color == Color.magenta) 	colorStr = "[r=255,g=0,b=255] ";
			
			saveStr += "\r\n"+this.joueur4.getNom()+" "+this.joueur4.getIA()+" "+this.joueur3.isMyTurn()+" "+colorStr;
			for(String str : listeJoueur4){ saveStr += str+" "; }
		}
		
		return saveStr;
	}
	
	public Player getWinner(){
		
		Player winner = this.joueur1;
		
		if(this.joueur2.getCasesCtrl().size() > winner.getCasesCtrl().size()) winner = this.joueur2;
		
		if(this.joueur3 != null){
			if(this.joueur3.getCasesCtrl().size() > winner.getCasesCtrl().size()) winner = this.joueur3;
		}
		
		if(this.joueur4 != null){
			if(this.joueur4.getCasesCtrl().size() > winner.getCasesCtrl().size()) winner = this.joueur4;
		}
		
		return winner;
	}
	
	@Override
	public HexaBoard clone(){
		
		// copie de la grille
		HexaCell[][] grid = new HexaCell[this.grille.length][this.grille[0].length];
		
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				
				//(int centreX, int centreY, Color color, HexaCell voisinDroiteHaut,HexaCell voisinDroite, HexaCell voisinDroiteBas, HexaCell voisinGaucheBas, HexaCell voisinGauche, HexaCell voisinGaucheHaut){
				Color colorGrid = null;
				Color colorGrille = this.grille[i][j].getColor();
				if		(colorGrille == Color.red) 		colorGrid = Color.red;
				else if	(colorGrille == Color.orange) 	colorGrid = Color.orange;
				else if	(colorGrille == Color.yellow) 	colorGrid = Color.yellow;
				else if	(colorGrille == Color.green) 	colorGrid = Color.green;
				else if	(colorGrille == Color.blue) 	colorGrid = Color.blue;
				else if	(colorGrille == Color.magenta) 	colorGrid = Color.magenta;
				
				String ctrlBy = ""+this.grille[i][j].getCtrlBy();
				
				int centreX = this.grille[i][j].getCentreX();
				int centreY = this.grille[i][j].getCentreY();
				
				grid[i][j] = new HexaCell(centreX, centreY, colorGrid, null, null, null, null, null, null);
				
				grid[i][j].setCtrlBy(ctrlBy);
			}
		}
		
		grid = defVoisins(grid);
		
		// copie du joueur 1
		
		String nomJ1 = ""+this.joueur1.getNom();
		
		ArrayList<Cell> casesCtrlJ1 = new ArrayList<Cell>();
		casesCtrlJ1.add(grid[0][0]);
		casesCtrlJ1 = getConnectedCellsOfSameColor(casesCtrlJ1);
		
		int nbCasesJ1 = casesCtrlJ1.size();
		
		Color couleurJ1 = grid[0][0].getColor();
		
		String IAJ1 = ""+this.joueur1.getIA();
		
		Player j1 = new Player(nomJ1, couleurJ1, nbCasesJ1, casesCtrlJ1, IAJ1);
		j1.setMyTurn(this.joueur1.isMyTurn());
		
		// copie du joueur 2
		
		String nomJ2 = ""+this.joueur2.getNom();
		
		ArrayList<Cell> casesCtrlJ2 = new ArrayList<Cell>();
		casesCtrlJ2.add(grid[grid.length-1][grid[0].length-1]);
		casesCtrlJ2 = getConnectedCellsOfSameColor(casesCtrlJ2);
		
		int nbCasesJ2 = casesCtrlJ2.size();
		
		Color couleurJ2 = grid[grid.length-1][grid[0].length-1].getColor();
		
		String IAJ2 = ""+this.joueur2.getIA();
		
		Player j2 = new Player(nomJ2, couleurJ2, nbCasesJ2, casesCtrlJ2, IAJ2);
		j2.setMyTurn(this.joueur2.isMyTurn());
		
		// éventuelle copie du joueur 3
		
		Player j3 = null;
		
		if(this.joueur3 != null){
			
			String nomJ3 = ""+this.joueur3.getNom();
			
			ArrayList<Cell> casesCtrlJ3 = new ArrayList<Cell>();
			casesCtrlJ3.add(grid[grid.length-1][0]);
			casesCtrlJ3 = getConnectedCellsOfSameColor(casesCtrlJ3);
			
			int nbCasesJ3 = casesCtrlJ3.size();
			
			Color couleurJ3 = grid[grid.length-1][0].getColor();
			
			String IAJ3 = ""+this.joueur3.getIA();
			
			j3 = new Player(nomJ3, couleurJ3, nbCasesJ3, casesCtrlJ3, IAJ3);
			j3.setMyTurn(this.joueur3.isMyTurn());
		}
		
		// éventuelle copie du joueur 4
		
		Player j4 = null;
		
		if(this.joueur4 != null){
			
			String nomJ4 = ""+this.joueur4.getNom();
			
			ArrayList<Cell> casesCtrlJ4 = new ArrayList<Cell>();
			casesCtrlJ4.add(grid[0][grid.length-1]);
			casesCtrlJ4 = getConnectedCellsOfSameColor(casesCtrlJ4);
			
			int nbCasesJ4 = casesCtrlJ4.size();
			
			Color couleurJ4 = grid[0][grid.length-1].getColor();
			
			String IAJ4 = ""+this.joueur4.getIA();
			
			j4 = new Player(nomJ4, couleurJ4, nbCasesJ4, casesCtrlJ4, IAJ4);
			j4.setMyTurn(this.joueur4.isMyTurn());
		}
		
		// Creation du nouvel HexaBoard :
		
		HexaBoard clone = new HexaBoard(grid, j1, j2, j3, j4);
		
		return clone;
	}
	
	@Override
	/**
	 * Cette fonction dessine le tableau en tenant compte des coordonées et des couleurs de chaque cellule, ici il s'agit d'un pavage d'hexagones
	 */
	public void paint(Graphics g){
		
		super.paint(g);
		
		g.setColor(Color.white);
		
		if(isTheGameOver() ) g.drawString("FIN DE LA PARTIE ==> GAGNANT : "+getWinner().getNom(), 10, 15);
		if(isTheGameOver() ) g.drawString("Cliquez sur \"Play\" pour une nouvelle partie !", 10, 30);
		
		g.drawString("Joueur1 : \""+joueur1.getNom()+"\"      |      Score : "+joueur1.getCasesCtrl().size(), 10, 60);
		g.drawString("Joueur2 : \""+joueur2.getNom()+"\"      |      Score : "+joueur2.getCasesCtrl().size(), 10, 75);
		
		if(joueur3 != null){
			g.drawString("Joueur3 : \""+joueur3.getNom()+"\"     |      Score : "+joueur3.getCasesCtrl().size(), 10, 90);
		}
		if(joueur4 != null){
			g.drawString("Joueur4 : \""+joueur4.getNom()+"\"     |      Score : "+joueur4.getCasesCtrl().size(), 10, 105);
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
	 * Getter retournant la grille 
	 */
	public HexaCell[][] getGrille(){ return this.grille; }
	
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
