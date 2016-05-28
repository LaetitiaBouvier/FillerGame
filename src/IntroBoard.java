import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * L'objectif de cette classe est seulement d'offrir un joli petit menu introductif fait maison !
 */
public class IntroBoard extends Canvas implements Board{
	
	private int hauteur;
	private int largeur;
	private boolean isError;
	
	public IntroBoard(int hauteur, int largeur, boolean isError){
		
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.isError = isError;
		
		// Settings :
		setBackground (Color.black);
		setSize(hauteur, largeur);
	}
	
	@Override
	public Player nextMove(Color couleur) { return null; }
	
	@Override
	public Color nextEasyIAMove() { return null; }
	
	@Override
	public Color nextTroubleIAMove(Player joueur) { return null; }
	
	@Override
	public Color nextHardIAMove(Player joueur) { return null; }
	
	@Override
	public ArrayList<Color> getColorsFromPlayers() { return null; }

	@Override
	public ArrayList<Color> getFreeColors() { return null; }
	
	@Override
	public boolean isTheGameOver(){ return true; }
	
	@Override
	public String generateSaveString(){ return null; }
	
	@Override
	public Player getWinner(){ return null; }
	
	@Override
	public void paint(Graphics g){
		
		int mult = 5;
		
		super.paint(g);
		
		if(!this.isError){
			
			g.setColor(Color.white);
			g.drawString("Bienvenue dans \" The Filler Game \"", mult*30, 30);
			
			int[] xH1 = {mult*60, mult*70, mult*70, mult*60, mult*50, mult*50};	int[] xH2 = {mult*70, mult*80, mult*80, mult*70, mult*60, mult*60};	
			int[] yH1 = {mult*90, mult*80, mult*70, mult*60, mult*70, mult*80};	int[] yH2 = {mult*70, mult*60, mult*50, mult*40, mult*50, mult*60};
			
			int[] xH3 = {mult*60, mult*70, mult*70, mult*60, mult*50, mult*50};	int[] xH4 = {mult*40, mult*50, mult*50, mult*40, mult*30, mult*30};
			int[] yH3 = {mult*50, mult*40, mult*30, mult*20, mult*30, mult*40};	int[] yH4 = {mult*50, mult*40, mult*30, mult*20, mult*30, mult*40};
			
			int[] xH5 = {mult*30, mult*40, mult*40, mult*30, mult*20, mult*20};	int[] xH6 = {mult*40, mult*50, mult*50, mult*40, mult*30, mult*30};
			int[] yH5 = {mult*70, mult*60, mult*50, mult*40, mult*50, mult*60};	int[] yH6 = {mult*90, mult*80, mult*70, mult*60, mult*70, mult*80};
			
			int[] xH7 = {mult*50, mult*60, mult*60, mult*50, mult*40, mult*40};
			int[] yH7 = {mult*70, mult*60, mult*50, mult*40, mult*50, mult*60};
			
			//int[] xC1 = {mult*15, mult*15, mult*5 , mult*5 };	int[] xC2 = {mult*95, mult*95, mult*85, mult*85};
			//int[] yC1 = {mult*60, mult*50, mult*50, mult*60};	int[] yC2 = {mult*60, mult*50, mult*50, mult*60};
			
			//int[] xL1 = {mult*50 , mult*60, mult*50, mult*40};	int[] xL2 = {mult*50, mult*60, mult*50, mult*40};
			//int[] yL1 = {mult*105, mult*95, mult*85, mult*95};	int[] yL2 = {mult*25, mult*15, mult*5 , mult*15};
			
			g.setColor(Color.red);		g.fillPolygon(xH1, yH1, 6);
			g.setColor(Color.orange);	g.fillPolygon(xH2, yH2, 6);
			g.setColor(Color.yellow);	g.fillPolygon(xH3, yH3, 6);
			g.setColor(Color.green);	g.fillPolygon(xH4, yH4, 6);
			g.setColor(Color.blue);		g.fillPolygon(xH5, yH5, 6);
			g.setColor(Color.magenta);	g.fillPolygon(xH6, yH6, 6);
			g.setColor(Color.black);	g.fillPolygon(xH7, yH7, 6);
			
			//g.setColor(Color.white);
			//g.fillPolygon(xC1, yC1, 4);
			//g.fillPolygon(xC2, yC2, 4);
			//g.fillPolygon(xL1, yL1, 4);
			//g.fillPolygon(xL2, yL2, 4);
		}else{
			
			g.setColor(Color.red);
			g.drawString("ATTENTION !", mult*30, 30);
			
			g.drawString("Les informations que vous avez entré posent problème !", 10, 60);
			g.drawString("==> La taille d'un côté de la grille doit être un entier positif !", 10, 75);
			g.drawString("==> Vous ne pouvez pas créer une partie composée uniquement d'IA !", 10, 90);
			g.drawString("==> Deux joueurs ne peuvent pas avoir le même nom !", 10, 105);
			g.drawString("Veuillez à nouveau entrer les informations nécessaires !", 10, 120);
			
			g.drawString("_____________________§_§§________§___§_________§§_§", 10, 150);
			g.drawString("______________________§__§§______§§___§§_______§§_§§", 10, 160);
			g.drawString("______________________§§__§§_____§_____§_____§§___§", 10, 170);
			g.drawString("______________________§§____§§___§______§___§_____§", 10, 180);
			g.drawString("__________§§___________§_____§§_§_______§_§§_____§", 10, 190);
			g.drawString("__________§_§§_________§_______§§________§_______§_________§§", 10, 200);
			g.drawString("__________§___§§_______§_________________________§______§§§_§", 10, 210);
			g.drawString("___________§§___§§§____§_________________________§___§§§§__§", 10, 220);
			g.drawString("____________§§_____§§§§§________________________§_§§§§____§", 10, 230);
			g.drawString("_____________§___________________________________§_______§", 10, 240);
			g.drawString("_____________§§_________________________________________§", 10, 250);
			g.drawString("______________§§_______________________________________§______§§§§§§§", 10, 260);
			g.drawString("__§§____§§§§§§§§_________________________________________________§§", 10, 270);
			g.drawString("____§§_________________§§§§§_____________§§§§§§§§§§§§§§§_______§§", 10, 280);
			g.drawString("______§§______§§§§§§§§§§§§§§§§§§§__§§§§§§§§§§___§§§§§§§§§§§cd§§", 10, 290);
			g.drawString("_______§§§cd§§§§§§§§_________§§§§§§§§§§§§§_______§§§§§§_____§", 10, 300);
			g.drawString("_________§§____§§§§__§§§§§§§§§§§§§___§§§__§§§§§§§§§§§§§___§§", 10, 310);
			g.drawString("___________§§__§§§§§§§§§§§§§§§§§§____§§§_§§§§§§§§§§§§§§_____§§§§", 10, 320);
			g.drawString("______§§§§§_____§§§§§§§§§§§§§§§§§____§§§§§§§§§§§§§§§§§§_________§§§§", 10, 330);
			g.drawString("__§§§§§_________§§§§§§§§§§§§§§§§§_____§§§§§§§§§§§§§§§§_____________§§§", 10, 340);
			g.drawString("§§§_____________§§§§§§§§§§§§§§§§_______§§§§§§§§§§§§§§§_______§§§§§§§§", 10, 350);
			g.drawString("______§§§§§§______§§§§§§§§§§§§________§__________________§§", 10, 360);
			g.drawString("____________§§_________________§____§§§____§______________§§§", 10, 370);
			g.drawString("__________§§___________§§______§§§§§_____§§§§§______________§§", 10, 380);
			g.drawString("________§§_______________§§_____________§§§_________§§§§§§§§§§§", 10, 390);
			g.drawString("______§§___________________§§§_________§§§§__________§§", 10, 400);
			g.drawString("____§§______§§§§_____________§§§§§§§§§§§______________§", 10, 410);
			g.drawString("___§§§§§§§§§§__§§________________§§§__________________§§", 10, 420);
			g.drawString("______________§§_______________________________________§§", 10, 430);
			g.drawString("_____________§§________________________________§§§______§", 10, 440);
			g.drawString("____________§§______§§§________________________§§_§§§____§", 10, 450);
			g.drawString("____________§____§§§_§§________________________§§____§§§_§", 10, 460);
			g.drawString("___________§___§§§___§_______§________§§_______§§_______§§§", 10, 470);
			g.drawString("__________§_§§§______§_____§§§§_______§_§§______§", 10, 480);
			g.drawString("_________§§§_________§____§§__§______§§__§§_____§", 10, 490);
			g.drawString("_____________________§___§§___§§_____§_____§§___§", 10, 500);
		}
	}

	@Override
	public int getHauteur() { return this.hauteur; }

	@Override
	public int getLargeur() { return this.largeur; }

	@Override
	public Player getJoueur1() { return null; }

	@Override
	public Player getJoueur2() { return null; }

	@Override
	public Player getJoueur3() { return null; }

	@Override
	public Player getJoueur4() { return null; }
}
