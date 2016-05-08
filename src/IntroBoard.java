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
	
	public IntroBoard(int hauteur, int largeur){
		
		this.hauteur = hauteur;
		this.largeur = largeur;
		
		// Settings :
		setBackground (Color.black);
		setSize(hauteur, largeur);
	}
	
	@Override
	public Player nextMove(Color couleur) { return null; }
	
	@Override
	public Color nextIntermediateIAMove(Player joueur) { return null; }
	
	@Override
	public ArrayList<Color> getColorsFromPlayers() { return null; }

	@Override
	public ArrayList<Color> getFreeColors() { return null; }
	
	@Override
	public void paint(Graphics g){
		
		int mult = 5;
		
		super.paint(g);
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
