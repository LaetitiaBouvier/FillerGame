import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.tdebroc.filler.connector.PlayerConnector;
import com.tdebroc.filler.game.Game;
import com.tdebroc.filler.game.Grid;

public class SquareWebBoard extends Canvas implements Board {
	
	private static final long serialVersionUID = 1L;
	
	private SquareCell[][] grille;
	private int hauteur;
	private int largeur;
	
	private PlayerConnector playerConnector;
	private Game game;
	
	private Player joueur1;
	private Player joueur2;
	
	public SquareWebBoard(PlayerConnector playerConnector, int tailleCote){
		
		int h = 20*tailleCote +60;
		int l = 20*tailleCote +180;
		
		// Settings :
		setBackground (Color.black);
        setSize(h, l);
        
        this.hauteur = h;
        this.largeur = l;
        
        this.playerConnector = playerConnector;
        this.game = playerConnector.getGame();
        
        //playerConnector.getGame().initGame();
        
        initialisationGrille(tailleCote, playerConnector.getGame().getGrid());
        
        this.grille = defVoisins(this.grille);
        
        defJoueurs(tailleCote, playerConnector.getGame().getPlayers().get(0).getPlayerName(), playerConnector.getGame().getPlayers().get(1).getPlayerName());
	}
	
	private void initialisationGrille(int nb, Grid grid){
		
		int margeY = 120;
		
		this.grille = new SquareCell[nb][nb];
		
		for(int i = 0; i < grille.length; i++){
			for(int j = 0; j < grille[0].length; j++){
				
				Color color = Color.black;
				
				char c = grid.getCell(j, i).getColor(); // r : rouge, o : orange, j : jaune, v : vert, b : bleu, i : indogo
				
				if(c == 'R')		{ color = Color.red; 	}
				else if(c == 'O')	{ color = Color.orange; }
				else if(c == 'J')	{ color = Color.yellow; }
				else if(c == 'V')	{ color = Color.green; 	}
				else if(c == 'B')	{ color = Color.blue; 	}
				else if(c == 'I')	{ color = Color.magenta;}
				
				grille[i][j] = new SquareCell(30+i*20, 30+j*20+margeY, color, null, null, null, null);
			}
		}
	}
	
//	private void majGrille(Grid grid){
//		
//		for(int i = 0; i < grille.length; i++){
//			for(int j = 0; j < grille[0].length; j++){
//				
//				char c = grid.getCell(j, i).getColor(); // r : rouge, o : orange, j : jaune, v : vert, b : bleu, i : indogo
//				
//				if(c == 'R')		{ grille[i][j].setColor(Color.red);		}
//				else if(c == 'O')	{ grille[i][j].setColor(Color.orange);	}
//				else if(c == 'J')	{ grille[i][j].setColor(Color.yellow);	}
//				else if(c == 'V')	{ grille[i][j].setColor(Color.green);	}
//				else if(c == 'B')	{ grille[i][j].setColor(Color.blue);	}
//				else if(c == 'I')	{ grille[i][j].setColor(Color.magenta);	}
//			}
//		}
//	}
	
	private SquareCell[][] defVoisins(SquareCell[][] grille){
		
		for(int i = 0; i < grille.length; i++){
			for(int j = 0; j < grille.length; j++){

				try{ grille[i][j].setVoisinHaut(grille[i][j-1]);		}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinHaut(null); 	}
				try{ grille[i][j].setVoisinDroite(grille[i+1][j]); 		}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinDroite(null); 	}
				try{ grille[i][j].setVoisinBas(grille[i][j+1]);			}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinBas(null);		}
				try{ grille[i][j].setVoisinGauche(grille[i-1][j+1]);	}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinGauche(null); 	}
			}
		}
		return grille;
	}
	
	private void defJoueurs( int nb, String nomJoueur1, String nomJoueur2){
		
		ArrayList<Cell> listeInitialeJ1 = new ArrayList<Cell>();
		ArrayList<Cell> listeInitialeJ2 = new ArrayList<Cell>();
		
		listeInitialeJ1.add(grille[0][0]);			grille[0][0].setCtrlBy(nomJoueur1);
		listeInitialeJ1 = getConnectedCellsOfSameColor(listeInitialeJ1);
		
		this.joueur1 = new Player(nomJoueur1, grille[0][0].getColor(), listeInitialeJ1.size(), listeInitialeJ1, "Sans");
		this.joueur1.setMyTurn(true);
		
		listeInitialeJ2.add(grille[nb-1][nb-1]);	grille[nb-1][nb-1].setCtrlBy(nomJoueur2);
		listeInitialeJ2 = getConnectedCellsOfSameColor(listeInitialeJ2);
		
		this.joueur2 = new Player(nomJoueur2, grille[nb-1][nb-1].getColor(), listeInitialeJ2.size(), listeInitialeJ2, "Sans");
		
	}
	
	public static ArrayList<Cell> getConnectedCellsOfSameColor(ArrayList<Cell> listeIni){
		
		@SuppressWarnings("unchecked")
		ArrayList<Cell> liste = (ArrayList<Cell>) listeIni.clone();
		
		boolean add = false;
		
		for(int i = 0; i < liste.size(); i++){
			
			SquareCell square = (SquareCell) liste.get(i);
			
			if( square.getVoisinHaut() != null && !liste.contains(square.getVoisinHaut()) ){
				if( square.getVoisinHaut().getColor().getRGB() == square.getColor().getRGB() && square.getVoisinHaut().getCtrlBy().isEmpty()){
					
					liste.add(square.getVoisinHaut());	add = true;
				}
			}
			if( square.getVoisinDroite() != null && !liste.contains(square.getVoisinDroite()) ){
				if( square.getVoisinDroite().getColor().getRGB() == square.getColor().getRGB() && square.getVoisinDroite().getCtrlBy().isEmpty()){
					
					liste.add(square.getVoisinDroite());		add = true;
				}
			}
			if( square.getVoisinBas() != null && !liste.contains(square.getVoisinBas()) ){
				if( square.getVoisinBas().getColor().getRGB() == square.getColor().getRGB() && square.getVoisinBas().getCtrlBy().isEmpty()){
					
					liste.add(square.getVoisinBas());	add = true;
				}
			}
			if(square.getVoisinGauche() != null && !liste.contains(square.getVoisinGauche()) ){
				if( square.getVoisinGauche().getColor().getRGB() == square.getColor().getRGB() && square.getVoisinGauche().getCtrlBy().isEmpty()){
					
					liste.add(square.getVoisinGauche());	add = true;
				}
			}
		}
		if(add == false){	return liste;	}	// Condition de sortie de la récursion
		
		return getConnectedCellsOfSameColor(liste);
	}

	@Override
	public Player nextMove(Color couleur) {
		
		Player joueurAct = null;
		Player joueurSui = null;
		
		if(this.joueur1.isMyTurn())	{ joueurAct = this.joueur1; joueurSui = this.joueur2; }
		else						{ joueurAct = this.joueur2; joueurSui = this.joueur1; }
		
		ArrayList<Cell> squareCtrl = joueurAct.getCasesCtrl();
		
		for(int i = 0; i < squareCtrl.size(); i++){
			squareCtrl.get(i).setColor(couleur);
		}
		
		squareCtrl = getConnectedCellsOfSameColor(squareCtrl);
		
		joueurAct.setCasesCtrl(squareCtrl);
		for(Cell cell : squareCtrl){
			cell.setCtrlBy(joueurAct.getNom());
		}
		
		repaint();

		char c = ' ';
		
		if(couleur == Color.red)	{ c = 'R'; }
		if(couleur == Color.orange)	{ c = 'O'; }
		if(couleur == Color.yellow)	{ c = 'J'; }
		if(couleur == Color.green)	{ c = 'V'; }
		if(couleur == Color.blue)	{ c = 'B'; }
		if(couleur == Color.magenta){ c = 'I'; }
		
		this.playerConnector.sendMove(c);
		
		this.game = playerConnector.waitOppenentsAndGetTheirMoves();
		
		System.out.println(game.currentPlayerPlaying().getPlayerName());
		int tour = game.getCurrentIdPlayerTurn();	if(tour == 1){ tour = 0; }else{ tour = 1; }
		char cJnext = game.getPlayers().get(tour).getPlayerColor();
		
		Color couleurJnext = null;
		
		if(cJnext == 'R')	{ couleurJnext = Color.red; 	}
		if(cJnext == 'O')	{ couleurJnext = Color.orange; }
		if(cJnext == 'J')	{ couleurJnext = Color.yellow; }
		if(cJnext == 'V')	{ couleurJnext = Color.green; 	}
		if(cJnext == 'B')	{ couleurJnext = Color.blue; 	}
		if(cJnext == 'I')	{ couleurJnext = Color.magenta;}
		
		squareCtrl = joueurSui.getCasesCtrl();
		
		for(int i = 0; i < squareCtrl.size(); i++){
			squareCtrl.get(i).setColor(couleurJnext);
		}
		
		squareCtrl = getConnectedCellsOfSameColor(squareCtrl);
		
		joueurSui.setCasesCtrl(squareCtrl);
		for(Cell cell : squareCtrl){
			cell.setCtrlBy(joueurSui.getNom());
		}
		
        repaint();
		
		return null;
	}

	@Override
	public Color nextEasyIAMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color nextTroubleIAMove(Player joueur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color nextHardIAMove(Player joueur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Color> getColorsFromPlayers() {
		
		ArrayList<Color> couleurs = new ArrayList<Color>();
		
		Color couleur1 = this.getJoueur1().getCasesCtrl().get(0).getColor();
		Color couleur2 = this.getJoueur2().getCasesCtrl().get(0).getColor();
		
		couleurs.add(couleur1);
		couleurs.add(couleur2);
		
		return couleurs;
	}

	@Override
	public ArrayList<Color> getFreeColors() {
		
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
	public boolean isTheGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String generateSaveString() { return null; }

	@Override
	public Player getWinner() {
		// TODO Auto-generated method stub
		return null;
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
		
		for(int i = 0; i < grille.length; i++){
			for(int j = 0; j< grille[0].length; j++){
				
				int x[] = {grille[i][j].getCentreX()+10,	grille[i][j].getCentreX()+10,	grille[i][j].getCentreX()-10,	grille[i][j].getCentreX()-10};
				int y[] = {grille[i][j].getCentreY()+15,	grille[i][j].getCentreY()-5, 	grille[i][j].getCentreY()-5, 	grille[i][j].getCentreY()+15};
				
				g.setColor(grille[i][j].getColor());
				g.fillPolygon(x, y, 4);
				
				g.setColor(Color.black);
				g.drawPolygon(x, y, 4);
				
				if(joueur1.getCasesCtrl().contains(grille[i][j])){
					g.drawString("1", grille[i][j].getCentreX()-2, grille[i][j].getCentreY()+8);
				}
				if(joueur2.getCasesCtrl().contains(grille[i][j])){
					g.drawString("2", grille[i][j].getCentreX()-2, grille[i][j].getCentreY()+8);
				}
			}
		}
	}
	
	@Override
	/**
	 * Cette fonction permet de raffraichir le dessin du tableau
	 */
	public void repaint(){ super.repaint(); }

	@Override
	public int getHauteur() { return this.hauteur; }

	@Override
	public int getLargeur() { return this.largeur; }

	@Override
	public Player getJoueur1() { return this.joueur1; }
	public void setJoueur1(Player joueur1){ this.joueur1 = joueur1; }

	@Override
	public Player getJoueur2() { return this.joueur2; }
	public void setJoueur2(Player joueur2){ this.joueur2 = joueur2; }

	@Override
	public Player getJoueur3() { return null; }

	@Override
	public Player getJoueur4() { return null; }

}
