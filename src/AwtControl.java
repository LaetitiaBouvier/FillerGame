import java.awt.Button;
import java.awt.Checkbox;
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
 *	Elle permet la gestion de tous les composants graphiques et de l'utilisation général du programme.
 */
public class AwtControl{

	private Frame mainFrame;
	private Panel controlPanel;
	private Board board;

	/**
	 * Ce constructeur permet de selectionner quel tableau on souhaite générer à l'écran, et prépare les principaux composants graphiques.
	 * 
	 * @param tableau 	 : chaîne de caractères représentant le nom du tableau que l'on souhaite voir
	 */
	public AwtControl(String tableau, int nb, String joueur1, String joueur2, String joueur3, String joueur4, boolean isIA1, boolean isIA2, boolean isIA3, boolean isIA4){
		
		//Sélectionne le tableau à générer
		if(tableau.equals("INTRO") || tableau.contains("PARAM")){
			this.board = new IntroBoard(500, 500);
		}
		if(tableau.equals("HEXA")){
			this.board = new HexaBoard(nb, joueur1, joueur2, joueur3, joueur4, isIA1, isIA2, isIA3, isIA4);
		}
		
		//Prépare le cadre principale
		mainFrame = new Frame("The Filler Game");
		mainFrame.setSize(1440,1440);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});

		//Créé le panneau de contrôle, qui acceuillera les différents boutons, et l'ajoute au cadre principale
		controlPanel = new Panel();
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
		//mainFrame.setResizable(false);
	}

	/**
	 * Cette fonction est la fonction principale/d'entrée ...
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		
		AwtControl awtControl = new AwtControl("INTRO", 0, "", "", "", "", false, false, false, false);
		awtControl.show("INTRO");
	}

	/**
	 * Cette fonction permet l'affichages des différents éléments graphiques : le cadre principale, la barre de menu, le tableau et les boutons 
	 * 
	 * @see setMenu()
	 * @see setBoardAndButtons()
	 */
	private void show(String tableau){

		controlPanel.setBackground(Color.black);
		controlPanel.setSize(board.getHauteur(),board.getLargeur());
		
		mainFrame.setSize(board.getHauteur()+25,board.getLargeur()+450);
		
		setMenu();
		setBoardAndButtons(tableau);

		mainFrame.setVisible(true); 
	}
	
	/**
	 * Cette fonction paramètre et affiche le tableau et les boutons (en créant ces derniers)
	 * 
	 * @see ButtonClickListener : classe interne gérant l'écoute des boutons
	 */
	private void setBoardAndButtons(String tableau){
		
		if(this.board.getJoueur1() != null && this.board.getJoueur2() != null){	// Si on veut afficher un tableau qui nécessite des boutons pour jouer...
			
			//Création des boutons et définition de leur couleur
			Button redButton 		= new Button("ROUGE");		redButton.setBackground(Color.red);
			Button orangeButton 	= new Button("ORANGE");		orangeButton.setBackground(Color.orange);
			Button yellowButton		= new Button("JAUNE");		yellowButton.setBackground(Color.yellow);
			Button greenButton 		= new Button("VERT");		greenButton.setBackground(Color.green);
			Button blueButton 		= new Button("BLEU");		blueButton.setBackground(Color.blue);
			Button magentaButton	= new Button("MAGENTA");	magentaButton.setBackground(Color.magenta);
			
			//Associaion des boutons à une commande propre et à un écouteur particulier (ButtonClickListener)
			redButton.setActionCommand("ROUGE");				redButton.addActionListener		(new ButtonClickListener()); 
			orangeButton.setActionCommand("ORANGE");			orangeButton.addActionListener	(new ButtonClickListener()); 
			yellowButton.setActionCommand("JAUNE");				yellowButton.addActionListener	(new ButtonClickListener());
			greenButton.setActionCommand("VERT");				greenButton.addActionListener	(new ButtonClickListener()); 
			blueButton.setActionCommand("BLEU");				blueButton.addActionListener	(new ButtonClickListener()); 
			magentaButton.setActionCommand("MAGENTA");			magentaButton.addActionListener (new ButtonClickListener()); 
			
			//Création d'un type d'agencement en "sac de grille" (GridBagLayout) et ajout des boutons et du tableau dans cette grille
			GridBagLayout layout = new GridBagLayout();
			
			controlPanel.setLayout(layout);						//Ajoute l'agencement au panneau de contrôle (là où l'on souhaite voir le tableau et les boutons)
			GridBagConstraints gbc = new GridBagConstraints();	//Créé des contraintes sur les éléments de la grille, ici des contraintes de positionnement :
			
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
			
			setAllowedButtons();
			setRestrictedButtons();
		}
		else if (tableau.equals("HEXA_PARAM")){
			
			GridBagLayout layout = new GridBagLayout();
			
			controlPanel.setLayout(layout);						//Ajoute l'agencement au panneau de contrôle (là où l'on souhaite voir le tableau et les boutons)
			GridBagConstraints gbc = new GridBagConstraints();	//Créé des contraintes sur les éléments de la grille, ici des contraintes de positionnement :
			
			Label intro = new Label("Veuillez entrez les paramètres de la prochaine partie que vous souhaitez jouer :", Label.CENTER);
			intro.setForeground(Color.white);
			
			Label nb = new Label("Taille d'un côté :", Label.RIGHT);
			nb.setForeground(Color.white);
			final TextField nbText = new TextField(2);
			
			Label j1 = new Label("Entrez le nom du joueur 1 :");
			j1.setForeground(Color.white);
			final TextField j1Text = new TextField(16);
			
			Label j2 = new Label("Entrez le nom du joueur 2 :");
			j2.setForeground(Color.white);
			final TextField j2Text = new TextField(16);
			
			Label j3 = new Label("Entrez le nom du joueur 3 : (laissez vide si vous ne souhaitez pas en ajouter)");
			j3.setForeground(Color.white);
			final TextField j3Text = new TextField(16);
			
			Label j4 = new Label("Entrez le nom du joueur 4 : (laissez vide si vous ne souhaitez pas en ajouter)");
			j4.setForeground(Color.white);
			final TextField j4Text = new TextField(16);
			
			Label ia1 = new Label("Cochez cette case si vous souhaitez que le joueur 1 soit une IA :");
			ia1.setForeground(Color.white);
			
			Label ia2 = new Label("Cochez cette case si vous souhaitez que le joueur 2 soit une IA :");
			ia2.setForeground(Color.white);
			
			Label ia3 = new Label("Cochez cette case si vous souhaitez que le joueur 3 soit une IA :");
			ia3.setForeground(Color.white);
			
			Label ia4 = new Label("Cochez cette case si vous souhaitez que le joueur 4 soit une IA :");
			ia4.setForeground(Color.white);
			
			Label vide1 = new Label("");
			Label vide2 = new Label("");
			Label vide3 = new Label("");
			Label vide4 = new Label("");

			final Checkbox choixIA1 = new Checkbox("IA1");
			final Checkbox choixIA2 = new Checkbox("IA2");
			final Checkbox choixIA3 = new Checkbox("IA3");
			final Checkbox choixIA4 = new Checkbox("IA4");

			Button play = new Button("Play");
			play.setActionCommand("PLAY");
			play.addActionListener(new ButtonClickListener());
			
			play.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					mainFrame.dispose();
					AwtControl awtControl = new AwtControl("HEXA", Integer.parseInt(nbText.getText()), j1Text.getText(), j2Text.getText(), j3Text.getText(), j4Text.getText(),
															choixIA1.getState(), choixIA2.getState(), choixIA3.getState(), choixIA4.getState());
					awtControl.show("HEXA");
				}
			});
			
			gbc.anchor = GridBagConstraints.CENTER;
			
			gbc.gridx = 0;
			gbc.gridy = 0;
			controlPanel.add((Component) board, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 1;
			controlPanel.add(intro, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 2;
			controlPanel.add(vide1, gbc);
			
			gbc.anchor = GridBagConstraints.LINE_START;
			gbc.weightx = 0.25;
			
			gbc.gridx = 0;
			gbc.gridy = 3;
			controlPanel.add(nb, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 3;
			controlPanel.add(nbText, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 4;
			controlPanel.add(vide2, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 5;
			controlPanel.add(j1, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 5;
			controlPanel.add(j1Text, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 6;
			controlPanel.add(j2, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 6;
			controlPanel.add(j2Text, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 7;
			controlPanel.add(j3, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 7;
			controlPanel.add(j3Text, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 8;
			controlPanel.add(j4, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 8;
			controlPanel.add(j4Text, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 9;
			controlPanel.add(vide3, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 10;
			controlPanel.add(ia1, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 10;
			controlPanel.add(choixIA1, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 11;
			controlPanel.add(ia2, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 11;
			controlPanel.add(choixIA2, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 12;
			controlPanel.add(ia3, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 12;
			controlPanel.add(choixIA3, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 13;
			controlPanel.add(ia4, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 13;
			controlPanel.add(choixIA4, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 14;
			controlPanel.add(vide4, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 15;
			controlPanel.add(play, gbc);
		}
		else{
			FlowLayout layout = new FlowLayout();
			controlPanel.setLayout(layout);
			
			controlPanel.add((Component) board);
		}
	}
	
	/**
	 * Cette fonction paramètre et affiche la barre de menu
	 * 
	 * @see MenuItemListener : classe interne gérant l'écoute des menus
	 */
	private void setMenu(){

		//Création de la barre de menus
		final MenuBar menuBar = new MenuBar();

		//Création des menus
		Menu fileMenu = new Menu("File");
		Menu playMenu = new Menu("Play");
		Menu testMenu = new Menu("Test");

		//Création des items du menu "File"
		MenuItem newMenuItem = new MenuItem("New",new MenuShortcut(KeyEvent.VK_N));
		newMenuItem.setActionCommand("New");

		MenuItem openMenuItem = new MenuItem("Open");
		openMenuItem.setActionCommand("Open");

		MenuItem saveMenuItem = new MenuItem("Save");
		saveMenuItem.setActionCommand("Save");

		MenuItem exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setActionCommand("Exit");
		
		//Création des items du menu "Play"
		MenuItem hexaMenuItem = new MenuItem("Hexa");
		hexaMenuItem.setActionCommand("HEXA_PARAM");

		//Création d'un écouteur d'item, et mise sur écoute des items créés
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

		//Ajout des menus à la barre de menus
		menuBar.add(fileMenu);
		menuBar.add(playMenu);
		menuBar.add(testMenu);

		//Ajout de la barre de menu au cadre
		mainFrame.setMenuBar(menuBar);

		mainFrame.setVisible(true);  
	}
	
	/**
	 * Cette fonction restreint l'utilisation des boutons selon les couleurs des joueurs
	 * 
	 *  @see getColorsFromPlayers()
	 */
	public void setRestrictedButtons(){
		
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
	public void setAllowedButtons(){
		
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

	/**
	 * Cette petite classe interne gère l'écoute des boutons et les actions à prendre selon lesquels sont cliqués
	 */
	private class ButtonClickListener implements ActionListener{
		
		/**
		 * Cette fonction gère l'action "nextMove" (pour faire progresser le jeux) selon le bouton cliqué
		 * et appelle la restriction de l'utilisation des boutons selon les couleurs des joueurs
		 * 
		 * @see nextMove()
		 */
		public void actionPerformed(ActionEvent e) {
			
			String command = e.getActionCommand();
			
			if( command.equals( "ROUGE"	)) {
				Player nextPlayer = board.nextMove(Color.red);
				setAllowedButtons();
				setRestrictedButtons();
				board.nextAdvancedIAMove();
				
				if(nextPlayer != null){
					board.nextMove(board.nextIntermediateIAMove(nextPlayer));
					setAllowedButtons();
					setRestrictedButtons();
				}
			}
			if( command.equals( "ORANGE")) {
				Player nextPlayer = board.nextMove(Color.orange);
				setAllowedButtons();
				setRestrictedButtons();
				
				if(nextPlayer != null){
					board.nextMove(board.nextIntermediateIAMove(nextPlayer));
					setAllowedButtons();
					setRestrictedButtons();
				}
			}
			if( command.equals( "JAUNE"	)) {
				Player nextPlayer = board.nextMove(Color.yellow);
				setAllowedButtons();
				setRestrictedButtons();
				
				if(nextPlayer != null){
					board.nextMove(board.nextIntermediateIAMove(nextPlayer));
					setAllowedButtons();
					setRestrictedButtons();
				}
			}
			if( command.equals( "VERT"  )) {
				Player nextPlayer = board.nextMove(Color.green);
				setAllowedButtons();
				setRestrictedButtons();
				
				if(nextPlayer != null){
					board.nextMove(board.nextIntermediateIAMove(nextPlayer));
					setAllowedButtons();
					setRestrictedButtons();
				}
			}
			if( command.equals( "BLEU"  )) {
				Player nextPlayer = board.nextMove(Color.blue);
				setAllowedButtons();
				setRestrictedButtons();
				
				if(nextPlayer != null){
					board.nextMove(board.nextIntermediateIAMove(nextPlayer));
					setAllowedButtons();
					setRestrictedButtons();
				}
			}
			if( command.equals( "MAGENTA"	)) {
				Player nextPlayer = board.nextMove(Color.magenta);
				setAllowedButtons();
				setRestrictedButtons();
				
				if(nextPlayer != null){
					board.nextMove(board.nextIntermediateIAMove(nextPlayer));
					setAllowedButtons();
					setRestrictedButtons();
				}
			}
		}
	}
	
	/**
	 * Cette petite classe interne gère l'écoute des menus et les actions à prendre selon lesquels sont cliqués
	 */
	class MenuItemListener implements ActionListener {
		
		/**
		 * Cette fonction gère les actions à lancer selon l'item de menu cliqué
		 */
		public void actionPerformed(ActionEvent e) {
			
			String command = e.getActionCommand();
			
			if(command.equals("HEXA_PARAM")){
				
				mainFrame.dispose();
				AwtControl awtControlDemo = new AwtControl(command, 0, "", "", "", "", false, false, false, false);
				awtControlDemo.show(command);
			}
		}    
	}
}