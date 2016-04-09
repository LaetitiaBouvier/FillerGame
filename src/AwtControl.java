import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 *	Cette classe est la classe principale du logiciel "The Filler Game".
 *	Elle permet la gestion de tous les composants graphiques et de l'utilisation g�n�ral du programme.
 */
public class AwtControl{

	private Frame mainFrame;
	private Panel controlPanel;
	private Board board;

	/**
	 * Ce constructeur permet de selectionner quel tableau on souhaite g�n�rer � l'�cran, et pr�pare les principaux composants graphiques.
	 * 
	 * @param tableau 	 : cha�ne de caract�res repr�sentant le nom du tableau que l'on souhaite voir
	 */
	public AwtControl(String tableau, int nb){
		
		//S�lectionne le tableau � g�n�rer
		if(tableau.equals("INTRO") || tableau.contains("PARAM")){
			this.board = new IntroBoard(500, 500);
		}
		if(tableau.equals("HEXA")){
			this.board = new HexaBoard(nb, "Joueur1", "Joueur2", "", "");
		}
		
		//Pr�pare le cadre principale
		mainFrame = new Frame("The Filler Game");
		mainFrame.setSize(1440,1440);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});

		//Cr�� le panneau de contr�le, qui acceuillera les diff�rents boutons, et l'ajoute au cadre principale
		controlPanel = new Panel();
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}

	/**
	 * Cette fonction est la fonction principale/d'entr�e ...
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		
		AwtControl awtControl = new AwtControl("INTRO", 0);
		awtControl.show("INTRO");
	}

	/**
	 * Cette fonction permet l'affichages des diff�rents �l�ments graphiques : le cadre principale, la barre de menu, le tableau et les boutons 
	 * 
	 * @see setMenu()
	 * @see setBoardAndButtons()
	 */
	private void show(String tableau){

		controlPanel.setBackground(Color.black);
		controlPanel.setSize(board.getHauteur(),board.getLargeur());
		
		mainFrame.setSize(board.getHauteur()+30,board.getLargeur()+270);
		
		setMenu();
		setBoardAndButtons(tableau);

		mainFrame.setVisible(true); 
	}
	
	/**
	 * Cette fonction param�tre et affiche le tableau et les boutons (en cr�ant ces derniers)
	 * 
	 * @see ButtonClickListener : classe interne g�rant l'�coute des boutons
	 */
	private void setBoardAndButtons(String tableau){
		
		if(this.board.getJoueur1() != null && this.board.getJoueur2() != null){	// Si on veut afficher un tableau qui n�cessite des boutons pour jouer...
			
			//Cr�ation des boutons et d�finition de leur couleur
			Button redButton 		= new Button("ROUGE");		redButton.setBackground(Color.red);
			Button orangeButton 	= new Button("ORANGE");		orangeButton.setBackground(Color.orange);
			Button yellowButton		= new Button("JAUNE");		yellowButton.setBackground(Color.yellow);
			Button greenButton 		= new Button("VERT");		greenButton.setBackground(Color.green);
			Button blueButton 		= new Button("BLEU");		blueButton.setBackground(Color.blue);
			Button magentaButton	= new Button("MAGENTA");	magentaButton.setBackground(Color.magenta);
			
			//Associaion des boutons � une commande propre et � un �couteur particulier (ButtonClickListener)
			redButton.setActionCommand("ROUGE");				redButton.addActionListener		(new ButtonClickListener()); 
			orangeButton.setActionCommand("ORANGE");			orangeButton.addActionListener	(new ButtonClickListener()); 
			yellowButton.setActionCommand("JAUNE");				yellowButton.addActionListener	(new ButtonClickListener());
			greenButton.setActionCommand("VERT");				greenButton.addActionListener	(new ButtonClickListener()); 
			blueButton.setActionCommand("BLEU");				blueButton.addActionListener	(new ButtonClickListener()); 
			magentaButton.setActionCommand("MAGENTA");			magentaButton.addActionListener (new ButtonClickListener()); 
			
			//Cr�ation d'un type d'agencement en "sac de grille" (GridBagLayout) et ajout des boutons et du tableau dans cette grille
			GridBagLayout layout = new GridBagLayout();
			
			controlPanel.setLayout(layout);						//Ajoute l'agencement au panneau de contr�le (l� o� l'on souhaite voir le tableau et les boutons)
			GridBagConstraints gbc = new GridBagConstraints();	//Cr�� des contraintes sur les �l�ments de la grille, ici des contraintes de positionnement :
			
			gbc.gridx = 0;
			gbc.gridy = 0;
			controlPanel.add((Component) board,gbc);
			
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 0;
			gbc.gridy = 1;
			controlPanel.add(redButton,gbc); 
			
			gbc.gridx = 0;
			gbc.gridy = 2;
			controlPanel.add(orangeButton,gbc); 
			
			gbc.gridx = 0;
			gbc.gridy = 3;       
			controlPanel.add(yellowButton,gbc);  
			
			gbc.gridx = 0;
			gbc.gridy = 4;      
			controlPanel.add(greenButton,gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 5;      
			controlPanel.add(blueButton,gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 6;      
			controlPanel.add(magentaButton,gbc);
		}
		else if (tableau.equals("HEXA_PARAM")){
			
			FlowLayout layout = new FlowLayout();
			controlPanel.setLayout(layout);
			
			controlPanel.add((Component) board);
			
			Label intro = new Label("Veuillez entrez les param�tres de la prochaine partie que vous souhaitez jouer :", Label.CENTER);
			intro.setForeground(Color.white);
			
			Label nb = new Label("Taille d'un c�t� (min: 12, max: 100", Label.RIGHT);
			nb.setForeground(Color.white);
			final TextField nbText = new TextField(2);

			final Choice choice = new Choice();

			choice.add("Hexa");
			choice.add("Square");
			choice.add("Diamond");
			choice.add("Mixte");
			
			Button play = new Button("Play");
			play.setActionCommand("PLAY");
			play.addActionListener(new ButtonClickListener());
			
			play.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 
		            mainFrame.dispose();
					AwtControl awtControlDemo = new AwtControl("HEXA", Integer.parseInt(nbText.getText()));
					awtControlDemo.show("HEXA");
		         }
		      });
			
			controlPanel.add(intro);
			controlPanel.add(nb);
			controlPanel.add(nbText);
			controlPanel.add(play);
		}
		else{
			FlowLayout layout = new FlowLayout();
			controlPanel.setLayout(layout);
			
			controlPanel.add((Component) board);
		}
	}
	
	/**
	 * Cette fonction param�tre et affiche la barre de menu
	 * 
	 * @see MenuItemListener : classe interne g�rant l'�coute des menus
	 */
	private void setMenu(){

		//Cr�ation de la barre de menus
		final MenuBar menuBar = new MenuBar();

		//Cr�ation des menus
		Menu fileMenu = new Menu("File");
		Menu playMenu = new Menu("Play");
		Menu testMenu = new Menu("Test");

		//Cr�ation des items du menu "File"
		MenuItem newMenuItem = new MenuItem("New",new MenuShortcut(KeyEvent.VK_N));
		newMenuItem.setActionCommand("New");

		MenuItem openMenuItem = new MenuItem("Open");
		openMenuItem.setActionCommand("Open");

		MenuItem saveMenuItem = new MenuItem("Save");
		saveMenuItem.setActionCommand("Save");

		MenuItem exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setActionCommand("Exit");
		
		//Cr�ation des items du menu "Play"
		MenuItem hexaMenuItem = new MenuItem("Hexa");
		hexaMenuItem.setActionCommand("HEXA_PARAM");

		//Cr�ation d'un �couteur d'item, et mise sur �coute des items cr��s
		MenuItemListener menuItemListener = new MenuItemListener();

		newMenuItem.addActionListener(menuItemListener);
		openMenuItem.addActionListener(menuItemListener);
		saveMenuItem.addActionListener(menuItemListener);
		exitMenuItem.addActionListener(menuItemListener);
		hexaMenuItem.addActionListener(menuItemListener);

		//Ajout des items au menu "File"
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		
		//AJout des items au menu "Play"
		playMenu.add(hexaMenuItem);

		//Ajout des menus � la barre de menus
		menuBar.add(fileMenu);
		menuBar.add(playMenu);
		menuBar.add(testMenu);

		//Ajout de la barre de menu au cadre
		mainFrame.setMenuBar(menuBar);

		mainFrame.setVisible(true);  
	}

	/**
	 * Cette petite classe interne g�re l'�coute des boutons et les actions � prendre selon lesquels sont cliqu�s
	 */
	private class ButtonClickListener implements ActionListener{
		
		/**
		 * Cette fonction g�re l'action "nextStep" (pour faire progresser le jeux) selon le bouton cliqu�
		 * et appelle la restriction de l'utilisation des boutons selon les couleurs des joueurs
		 * 
		 * @see nextStep()
		 */
		public void actionPerformed(ActionEvent e) {
			
			String command = e.getActionCommand();
			
			if( command.equals( "ROUGE"	)) {
				board.nextStep(Color.red);
				setAllowedButtons();
				setRestrictedButtons();
			}
			if( command.equals( "ORANGE")) {
				board.nextStep(Color.orange);
				setAllowedButtons();
				setRestrictedButtons();
			}
			if( command.equals( "JAUNE"	)) {
				board.nextStep(Color.yellow);
				setAllowedButtons();
				setRestrictedButtons();
			}
			if( command.equals( "VERT"  )) {
				board.nextStep(Color.green);
				setAllowedButtons();
				setRestrictedButtons();
			}
			if( command.equals( "BLEU"  )) {
				board.nextStep(Color.blue);
				setAllowedButtons();
				setRestrictedButtons();
			}
			if( command.equals( "MAGENTA"	)) {
				board.nextStep(Color.magenta);
				setAllowedButtons();
				setRestrictedButtons();
			}
			
			if( command.equals( "PLAY" )){
				
			}
		}
		
		/**
		 * Cette fonction restreint l'utilisation des boutons selon les couleurs des joueurs
		 * 
		 *  @see getColorsFromPlayers()
		 */
		private void setRestrictedButtons(){
			
			ArrayList<Color> couleursOccupees = board.getColorsFromPlayers();
			
			for(int i = 0; i < couleursOccupees.size(); i++){
				
				if(couleursOccupees.get(i) == Color.red){
					controlPanel.getComponent(1).setBackground(Color.black);
					controlPanel.getComponent(1).setEnabled(false);
				}
				if(couleursOccupees.get(i) == Color.orange){
					controlPanel.getComponent(2).setBackground(Color.black);
					controlPanel.getComponent(2).setEnabled(false);
				}
				if(couleursOccupees.get(i) == Color.yellow){
					controlPanel.getComponent(3).setBackground(Color.black);
					controlPanel.getComponent(3).setEnabled(false);
				}
				if(couleursOccupees.get(i) == Color.green){
					controlPanel.getComponent(4).setBackground(Color.black);
					controlPanel.getComponent(4).setEnabled(false);
				}
				if(couleursOccupees.get(i) == Color.blue){
					controlPanel.getComponent(5).setBackground(Color.black);
					controlPanel.getComponent(5).setEnabled(false);
				}
				if(couleursOccupees.get(i) == Color.magenta){
					controlPanel.getComponent(6).setBackground(Color.black);
					controlPanel.getComponent(6).setEnabled(false);
				}
			}
		}
		
		/**
		 * Cette fonction permet d'autoriser l'utilisation des boutons selon les couleurs des joueurs 
		 * 
		 * @see setAllowedButtons()
		 */
		private void setAllowedButtons(){
			
			ArrayList<Color> couleursLibres = board.getFreeColors();
			
			for(int i = 0; i < couleursLibres.size(); i++){
				
				if(couleursLibres.get(i) == Color.red){
					controlPanel.getComponent(1).setBackground(Color.red);
					controlPanel.getComponent(1).setEnabled(true);
				}
				if(couleursLibres.get(i) == Color.orange){
					controlPanel.getComponent(2).setBackground(Color.orange);
					controlPanel.getComponent(2).setEnabled(true);
				}
				if(couleursLibres.get(i) == Color.yellow){
					controlPanel.getComponent(3).setBackground(Color.yellow);
					controlPanel.getComponent(3).setEnabled(true);
				}
				if(couleursLibres.get(i) == Color.green){
					controlPanel.getComponent(4).setBackground(Color.green);
					controlPanel.getComponent(4).setEnabled(true);
				}
				if(couleursLibres.get(i) == Color.blue){
					controlPanel.getComponent(5).setBackground(Color.blue);
					controlPanel.getComponent(5).setEnabled(true);
				}
				if(couleursLibres.get(i) == Color.magenta){
					controlPanel.getComponent(6).setBackground(Color.magenta);
					controlPanel.getComponent(6).setEnabled(true);
				}
			}
		}
	}
	
	/**
	 * Cette petite classe interne g�re l'�coute des menus et les actions � prendre selon lesquels sont cliqu�s
	 */
	class MenuItemListener implements ActionListener {
		
		/**
		 * Cette fonction g�re les actions � lancer selon l'item de menu cliqu�
		 */
		public void actionPerformed(ActionEvent e) {
			
			String command = e.getActionCommand();
			
			if(command.equals("HEXA_PARAM")){
				
				mainFrame.dispose();
				AwtControl awtControlDemo = new AwtControl(command, 0);
				awtControlDemo.show(command);
			}
		}    
	}
}