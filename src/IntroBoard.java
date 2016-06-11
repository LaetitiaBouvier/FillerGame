import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * <b>Cette classe permet seulement d'offrir un petit tableau introductif, ou un petit tableau d'erreur. </b>
 */
public class IntroBoard extends Canvas implements Board{
	
	private static final long serialVersionUID = 1L;
	
	private int hauteur;
	private int largeur;
	private boolean isError;
	
	/**
	 * Ce constructeur permet de créer un tableau en définissant sa hauteur, sa largeur et si il s'agit d'un tableau d'intro ou d'erreur
	 * 
	 * @param hauteur	( int )		: <br> hauteur du tableau à créer 													</br><br>
	 * @param largeur	( int )		: <br> largeur du tableau à créer 													</br><br>
	 * @param isError	( boolean )	: <br> est-ce que ce tableau est un tableau d'erreur, sinon c'est un tableau d'intro</br><br>
	 */
	public IntroBoard(int hauteur, int largeur, boolean isError){
		
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.isError = isError;
		
		// Settings :
		setBackground (Color.black);
		setSize(hauteur, largeur);
	}
	
	/**
	 * Cette fonction n'a pas d'utilité ici puisqu'il ne s'agit que d'afficher un petit tableau introductif ou un petit tableau d'erreur
	 */
	@Override
	public Player nextMove(Color couleur) { return null; }
	
	/**
	 * Cette fonction n'a pas d'utilité ici puisqu'il ne s'agit que d'afficher un petit tableau introductif ou un petit tableau d'erreur
	 */
	@Override
	public Color nextEasyIAMove() { return null; }
	
	/**
	 * Cette fonction n'a pas d'utilité ici puisqu'il ne s'agit que d'afficher un petit tableau introductif ou un petit tableau d'erreur
	 */
	@Override
	public Color nextTroubleIAMove(Player joueur) { return null; }
	
	/**
	 * Cette fonction n'a pas d'utilité ici puisqu'il ne s'agit que d'afficher un petit tableau introductif ou un petit tableau d'erreur
	 */
	@Override
	public Color nextHardIAMove(Player joueur) { return null; }
	
	/**
	 * Cette fonction n'a pas d'utilité ici puisqu'il ne s'agit que d'afficher un petit tableau introductif ou un petit tableau d'erreur
	 */
	@Override
	public ArrayList<Color> getColorsFromPlayers() { return null; }

	/**
	 * Cette fonction n'a pas d'utilité ici puisqu'il ne s'agit que d'afficher un petit tableau introductif ou un petit tableau d'erreur
	 */
	@Override
	public ArrayList<Color> getFreeColors() { return null; }
	
	/**
	 * Cette fonction n'a pas d'utilité ici puisqu'il ne s'agit que d'afficher un petit tableau introductif ou un petit tableau d'erreur
	 */
	@Override
	public boolean isTheGameOver(){ return true; }
	
	/**
	 * Cette fonction n'a pas d'utilité ici puisqu'il ne s'agit que d'afficher un petit tableau introductif ou un petit tableau d'erreur
	 */
	@Override
	public String generateSaveString(){ return null; }
	
	/**
	 * Cette fonction n'a pas d'utilité ici puisqu'il ne s'agit que d'afficher un petit tableau introductif ou un petit tableau d'erreur
	 */
	@Override
	public Player getWinner(){ return null; }
	
	/**
	 * Cette fonction permet d'afficher le tableau à proprement parlé
	 */
	@Override
	public void paint(Graphics g){
		
		int mult = 3;
		
		super.paint(g);
		
		if(!this.isError){
			
			g.setColor(Color.white);
			g.drawString("Bienvenue dans \" The Filler Game \"", 110 + mult*30, 30);
			
			int[] xH1 = {150 + mult*60, 150 + mult*70, 150 + mult*70, 150 + mult*60, 150 + mult*50, 150 + mult*50};	int[] xH2 = {150 + mult*70, 150 + mult*80, 150 + mult*80, 150 + mult*70, 150 + mult*60, 150 + mult*60};	
			int[] yH1 = {mult*90, mult*80, mult*70, mult*60, mult*70, mult*80};	int[] yH2 = {mult*70, mult*60, mult*50, mult*40, mult*50, mult*60};
			
			int[] xH3 = {150 + mult*60, 150 + mult*70, 150 + mult*70, 150 + mult*60, 150 + mult*50, 150 + mult*50};	int[] xH4 = {150 + mult*40, 150 + mult*50, 150 + mult*50, 150 + mult*40, 150 + mult*30, 150 + mult*30};
			int[] yH3 = {mult*50, mult*40, mult*30, mult*20, mult*30, mult*40};	int[] yH4 = {mult*50, mult*40, mult*30, mult*20, mult*30, mult*40};
			
			int[] xH5 = {150 + mult*30, 150 + mult*40, 150 + mult*40, 150 + mult*30, 150 + mult*20, 150 + mult*20};	int[] xH6 = {150 + mult*40, 150 + mult*50, 150 + mult*50, 150 + mult*40, 150 + mult*30, 150 + mult*30};
			int[] yH5 = {mult*70, mult*60, mult*50, mult*40, mult*50, mult*60};	int[] yH6 = {mult*90, mult*80, mult*70, mult*60, mult*70, mult*80};
			
			int[] xH7 = {150 + mult*50, 150 + mult*60, 150 + mult*60, 150 + mult*50, 150 + mult*40, 150 + mult*40};
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
			
			g.drawString("________________§§§§§_____________§§§§§§§§§§§§§§§________", 10, 150);
			g.drawString("_______§§§§§§§§§§§§§§§§§§§__§§§§§§§§§§___§§§§§§§§§§§_____", 10, 160);
			g.drawString("§§§§§§§§§§§§§_________§§§§§§§§§§§§§_______§§§§§§§§§§§§§__", 10, 170);
			g.drawString("________§§§§__§§§§§§§§§§§§§___§§§__§§§§§§§§§§§§§_________", 10, 180);
			g.drawString("________§§§§§§§§§§§§§§§§§§____§§§_§§§§§§§§§§§§§§_________", 10, 190);
			g.drawString("_________§§§§§§§§§§§§§§§§§____§§§§§§§§§§§§§§§§§§_________", 10, 200);
			g.drawString("_________§§§§§§§§§§§§§§§§§_____§§§§§§§§§§§§§§§§__________", 10, 210);
			g.drawString("_________§§§§§§§§§§§§§§§§_______§§§§§§§§§§§§§§§__________", 10, 220);
			g.drawString("___________§§§§§§§§§§§§________§_________________________", 10, 230);
			g.drawString("________________________§____§§§____§____________________", 10, 240);
			g.drawString("________________§§______§§§§§_____§§§§§__________________", 10, 250);
			g.drawString("__________________§§_____________§§§_____________________", 10, 260);
			g.drawString("____________________§§§_________§§§§_____________________", 10, 270);
			g.drawString("______________________§§§§§§§§§§§________________________", 10, 280);
			g.drawString("__________________________§§§____________________________", 10, 290);
			g.drawString("_________________________________________________________", 10, 300);
		}
	}

	/**
	 * Cette fonction est le getter permettant d'accéder à la hauteur du tableau
	 */
	@Override
	public int getHauteur() { return this.hauteur; }

	/**
	 * Cette fonction est le getter permettant d'accéder à la largeur du tableau
	 */
	@Override
	public int getLargeur() { return this.largeur; }

	/**
	 * Cette fonction n'a pas d'utilité ici puisqu'il ne s'agit que d'afficher un petit tableau introductif ou un petit tableau d'erreur
	 */
	@Override
	public Player getJoueur1() { return null; }

	/**
	 * Cette fonction n'a pas d'utilité ici puisqu'il ne s'agit que d'afficher un petit tableau introductif ou un petit tableau d'erreur
	 */
	@Override
	public Player getJoueur2() { return null; }

	/**
	 * Cette fonction n'a pas d'utilité ici puisqu'il ne s'agit que d'afficher un petit tableau introductif ou un petit tableau d'erreur
	 */
	@Override
	public Player getJoueur3() { return null; }

	/**
	 * Cette fonction n'a pas d'utilité ici puisqu'il ne s'agit que d'afficher un petit tableau introductif ou un petit tableau d'erreur
	 */
	@Override
	public Player getJoueur4() { return null; }
}
