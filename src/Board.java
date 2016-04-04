import java.awt.Color;


public interface Board {
	
	public void nextStep(Color couleur);
	
	public int getHauteur();
	public int getLargeur();
	public Player getJoueur1();
	public Player getJoueur2();
	public Player getJoueur3();
	public Player getJoueur4();
}
