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
	public void nextStep(Color couleur) { }
	
	@Override
	public ArrayList<Color> getColorsFromPlayers() { return null; }

	@Override
	public ArrayList<Color> getFreeColors() { return null; }
	
	@Override
	public void paint(Graphics g){
		
		super.paint(g);
		g.setColor(Color.white);
		g.drawString("Bienvenue dans \" The Filler Game \"", 60, 60);
		
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
