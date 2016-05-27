import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.tdebroc.filler.game.Game;

/* REMARQUES IMPORTANTES :
 * 
 * - Il faudrait une fonction pour vérifier le format d'un fichier texte avant de créer une partie avec.
 */

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
	public AwtControl(String tableau, int nb, String joueur1, String joueur2, String joueur3, String joueur4, String IA1, String IA2, String IA3, String IA4){

		//Sélectionne le tableau à générer
		if(tableau.equals("INTRO") || tableau.contains("PARAM") || tableau.equals("WEB")){
			this.board = new IntroBoard(500, 500, false);
		}
		if(tableau.contains("ERROR")){
			this.board = new IntroBoard(500, 500, true);
		}
		if(tableau.equals("HEXA")){
			this.board = new HexaBoard(nb, joueur1, joueur2, joueur3, joueur4, IA1, IA2, IA3, IA4);
		}
		if(tableau.length() > 10){
			Scanner sc = new Scanner (tableau);
			if(sc.next().equals("HEXA")){
				this.board = new HexaBoard(tableau);
			}else{}
			
			sc.close();
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
	
	public AwtControl(Game game, int tailleCote){
		
		this.board = new SquareWebBoard(game, tailleCote);

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

		AwtControl awtControl = new AwtControl("INTRO", 0, "", "", "", "", "", "", "", "");
		awtControl.show("INTRO", "", false);
	}

	/**
	 * Cette fonction permet l'affichages des différents éléments graphiques : le cadre principale, la barre de menu, le tableau et les boutons 
	 * 
	 * @see setMenu()
	 * @see setBoardAndButtons()
	 */
	private void show(String tableau, String choixIAJoueur1, boolean isWebGame){
		
		if(tableau.isEmpty() && choixIAJoueur1.isEmpty() && !isWebGame){
			
			choixIAJoueur1 = board.getJoueur1().getIA();
			
			System.out.println("Hauteur : "+board.getHauteur());
			System.out.println("Largeur : "+board.getLargeur());
			
		}

		controlPanel.setBackground(Color.black);
		controlPanel.setSize(board.getHauteur(),board.getLargeur());

		mainFrame.setSize(board.getHauteur()+25,board.getLargeur()+450);

		setMenu();
		setBoardAndButtons(tableau);

		mainFrame.setVisible(true);

		if(!choixIAJoueur1.equals("Sans") && !choixIAJoueur1.isEmpty()){

			Player nextPlayer = board.getJoueur1();

			do{
				if(nextPlayer.getIA().equals("IA Simple")){			nextPlayer = board.nextMove(board.nextEasyIAMove());				}
				else if(nextPlayer.getIA().equals("IA Penible")){	nextPlayer = board.nextMove(board.nextTroubleIAMove(nextPlayer));	}
				else if(nextPlayer.getIA().equals("IA Difficile")){	nextPlayer = board.nextMove(board.nextHardIAMove(nextPlayer));		}
				setAllowedButtons();
				setRestrictedButtons();
			}while(nextPlayer != null);
		}
	}

	/**
	 * Cette fonction paramètre et affiche le tableau et les boutons (en créant ces derniers)
	 * 
	 * @see ButtonClickListener : classe interne gérant l'écoute des boutons
	 */
	private void setBoardAndButtons(String tableau){

		if(tableau.equals("INTRO")){
			FlowLayout layout = new FlowLayout();
			controlPanel.setLayout(layout);

			controlPanel.add((Component) board);
		}
		else if(tableau.equals("WEB") || tableau.equals("WEB_ERROR")){
			
			GridBagLayout layout = new GridBagLayout();

			controlPanel.setLayout(layout);						//Ajoute l'agencement au panneau de contrôle (là où l'on souhaite voir le tableau et les boutons)
			GridBagConstraints gbc = new GridBagConstraints();	//Créé des contraintes sur les éléments de la grille, ici des contraintes de positionnement :

			Label intro = new Label("Veuillez paramètrer votre prochaine partie en ligne :", Label.CENTER);
			intro.setForeground(Color.white);

			Label nb = new Label("Taille d'un côté :", Label.RIGHT);
			nb.setForeground(Color.white);
			final TextField nbText = new TextField(2);

			Label joueur = new Label("Entrez votre pseudo :");
			joueur.setForeground(Color.white);
			final TextField joueurText = new TextField(16);
			
			Label vide1 = new Label("");
			
			Button play = new Button("Play");
			play.setActionCommand("PLAYWEB");
			play.addActionListener(new ButtonClickListener());
			
			play.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					boolean cond = true;
					int tailleCote = 8;
					
					if(joueurText.getText().isEmpty()){ cond = false; }

					try{   tailleCote = Integer.parseInt(nbText.getText());   }catch (NumberFormatException ex){ cond = false; }

					if(tailleCote < 8){ cond = false; }
					
					if(cond){
						Game game = Web.createGame(joueurText.getText(), tailleCote);
						
						JOptionPane.showMessageDialog(null, "Vous devez patienter jusqu'à l'arrivée de votre adversaire,"
							+ "l'ID de votre partie est : "+game.getIdGame(), "Céation partie", JOptionPane.INFORMATION_MESSAGE);
						
						mainFrame.dispose();
						AwtControl awtControl = new AwtControl(game, tailleCote);
						awtControl.show("", "", true);
					}else{
						mainFrame.dispose();
						AwtControl awtControl = new AwtControl("WEB_ERROR", 0, "", "", "", "", "", "", "", "");
						awtControl.show("WEB_ERROR", "", false);
					}

					/*
					if(cond){
						mainFrame.dispose();
						AwtControl awtControl = new AwtControl("HEXA", Integer.parseInt(nbText.getText()), j1Text.getText(), j2Text.getText(), j3Text.getText(), j4Text.getText(),
								IAList1.getItem(IAList1.getSelectedIndex()), IAList2.getItem(IAList2.getSelectedIndex()), IAList3.getItem(IAList3.getSelectedIndex()), IAList4.getItem(IAList4.getSelectedIndex()));
						awtControl.show("HEXA", IAList1.getItem(IAList1.getSelectedIndex()));
					}else{
						mainFrame.dispose();
						AwtControl awtControlDemo = new AwtControl("HEXA_ERROR", 0, "", "", "", "", "", "", "", "");
						awtControlDemo.show("HEXA_ERROR", "");
					}
					*/
				}
			} );
			
			gbc.anchor = GridBagConstraints.CENTER;

			gbc.gridx = 0; gbc.gridy = 0; 		controlPanel.add((Component) board, gbc);

			gbc.gridx = 0; gbc.gridy = 1; 		controlPanel.add(intro, gbc);

			gbc.gridx = 0; gbc.gridy = 2; 		controlPanel.add(vide1, gbc);

			gbc.anchor = GridBagConstraints.LINE_START;
			gbc.weightx = 0.25;

			gbc.gridx = 0; gbc.gridy = 3; 		controlPanel.add(nb, gbc);

			gbc.gridx = 1; gbc.gridy = 3; 		controlPanel.add(nbText, gbc);

			gbc.gridx = 0; gbc.gridy = 4; 		controlPanel.add(vide1, gbc);

			gbc.gridx = 0; gbc.gridy = 5; 		controlPanel.add(joueur, gbc);
			gbc.gridx = 1; gbc.gridy = 5; 		controlPanel.add(joueurText, gbc);
			
			gbc.gridx = 0; gbc.gridy = 6; 		controlPanel.add(vide1, gbc);
			
			gbc.gridx = 0; gbc.gridy = 7; 		controlPanel.add(play, gbc);
			
		}
		else if(this.board.getJoueur1() != null && this.board.getJoueur2() != null){	// Si on veut afficher un tableau qui nécessite des boutons pour jouer...

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

			gbc.gridx = 0; gbc.gridy = 0; 		controlPanel.add((Component) board,gbc);

			gbc.fill = GridBagConstraints.HORIZONTAL;

			gbc.gridx = 0; gbc.gridy = 1; 		controlPanel.add(redButton,gbc); 

			gbc.gridx = 0; gbc.gridy = 2;		controlPanel.add(orangeButton,gbc); 

			gbc.gridx = 0; gbc.gridy = 3; 		controlPanel.add(yellowButton,gbc);  

			gbc.gridx = 0; gbc.gridy = 4; 		controlPanel.add(greenButton,gbc);

			gbc.gridx = 0; gbc.gridy = 5; 		controlPanel.add(blueButton,gbc);

			gbc.gridx = 0; gbc.gridy = 6; 		controlPanel.add(magentaButton,gbc);

			setAllowedButtons();
			setRestrictedButtons();
		}
		else {

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

			Label ia1 = new Label("IA du Joueur 1 :");
			ia1.setForeground(Color.white);

			Label ia2 = new Label("IA du Joueur 2 :");
			ia2.setForeground(Color.white);

			Label ia3 = new Label("IA du Joueur 3 :");
			ia3.setForeground(Color.white);

			Label ia4 = new Label("IA du Joueur 4 :");
			ia4.setForeground(Color.white);

			Label vide1 = new Label("");
			Label vide2 = new Label("");
			Label vide3 = new Label("");
			Label vide4 = new Label("");

			final Choice IAList1 = new Choice();
			final Choice IAList2 = new Choice();
			final Choice IAList3 = new Choice();
			final Choice IAList4 = new Choice();

			IAList1.add("Sans");		IAList2.add("Sans");		IAList3.add("Sans");		IAList4.add("Sans");
			IAList1.add("IA Simple");	IAList2.add("IA Simple");	IAList3.add("IA Simple");	IAList4.add("IA Simple");
			IAList1.add("IA Penible");	IAList2.add("IA Penible");	IAList3.add("IA Penible");	IAList4.add("IA Penible");
			IAList1.add("IA Difficile");IAList2.add("IA Difficile");IAList3.add("IA Difficile");IAList4.add("IA Difficile");

			Button play = new Button("Play");
			play.setActionCommand("PLAY");
			play.addActionListener(new ButtonClickListener());

			if (tableau.equals("HEXA_PARAM") || tableau.equals("HEXA_ERROR")){

				play.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						boolean cond = true;

						if(!IAList1.getItem(IAList1.getSelectedIndex()).equals("Sans") && !IAList2.getItem(IAList2.getSelectedIndex()).equals("Sans")){

							if(j3Text.getText().isEmpty() && j4Text.getText().isEmpty()){ cond = false; }

							if(!j3Text.getText().isEmpty() && j4Text.getText().isEmpty()){

								if(!IAList3.getItem(IAList3.getSelectedIndex()).equals("Sans")){ cond = false; }
							}

							if(j3Text.getText().isEmpty() && !j4Text.getText().isEmpty()){

								if(!IAList4.getItem(IAList4.getSelectedIndex()).equals("Sans")){ cond = false; }
							}

							if(!j3Text.getText().isEmpty() && !j4Text.getText().isEmpty()){

								if(!IAList3.getItem(IAList3.getSelectedIndex()).equals("Sans") && !IAList4.getItem(IAList4.getSelectedIndex()).equals("Sans")){ cond = false; }
							}
						}
						
						if(j1Text.getText().equals(j2Text.getText()) || j1Text.getText().equals(j3Text.getText()) || j1Text.getText().equals(j4Text.getText())
						|| j2Text.getText().equals(j3Text.getText()) || j2Text.getText().equals(j4Text.getText()) ){ cond = false; }
						
						if(!j3Text.getText().isEmpty() && !j4Text.getText().isEmpty()){
							if(j3Text.getText().equals(j4Text.getText())){
								cond = false;
							}
						}

						int tailleCote = 8;

						try{   tailleCote = Integer.parseInt(nbText.getText());   }catch (NumberFormatException ex){ cond = false; }

						if(tailleCote < 8){ cond = false; }

						if(cond){
							mainFrame.dispose();
							AwtControl awtControl = new AwtControl("HEXA", Integer.parseInt(nbText.getText()), j1Text.getText(), j2Text.getText(), j3Text.getText(), j4Text.getText(),
									IAList1.getItem(IAList1.getSelectedIndex()), IAList2.getItem(IAList2.getSelectedIndex()), IAList3.getItem(IAList3.getSelectedIndex()), IAList4.getItem(IAList4.getSelectedIndex()));
							awtControl.show("HEXA", IAList1.getItem(IAList1.getSelectedIndex()), false);
						}else{
							mainFrame.dispose();
							AwtControl awtControlDemo = new AwtControl("HEXA_ERROR", 0, "", "", "", "", "", "", "", "");
							awtControlDemo.show("HEXA_ERROR", "", false);
						}
					}
				} );
			}

			gbc.anchor = GridBagConstraints.CENTER;

			gbc.gridx = 0; gbc.gridy = 0; 		controlPanel.add((Component) board, gbc);

			gbc.gridx = 0; gbc.gridy = 1; 		controlPanel.add(intro, gbc);

			gbc.gridx = 0; gbc.gridy = 2; 		controlPanel.add(vide1, gbc);

			gbc.anchor = GridBagConstraints.LINE_START;
			gbc.weightx = 0.25;

			gbc.gridx = 0; gbc.gridy = 3; 		controlPanel.add(nb, gbc);

			gbc.gridx = 1; gbc.gridy = 3; 		controlPanel.add(nbText, gbc);

			gbc.gridx = 0; gbc.gridy = 4; 		controlPanel.add(vide2, gbc);

			gbc.gridx = 0; gbc.gridy = 5; 		controlPanel.add(j1, gbc);
			gbc.gridx = 1; gbc.gridy = 5; 		controlPanel.add(j1Text, gbc);

			gbc.gridx = 0; gbc.gridy = 6; 		controlPanel.add(j2, gbc);
			gbc.gridx = 1; gbc.gridy = 6; 		controlPanel.add(j2Text, gbc);

			gbc.gridx = 0; gbc.gridy = 7; 		controlPanel.add(j3, gbc);
			gbc.gridx = 1; gbc.gridy = 7; 		controlPanel.add(j3Text, gbc);

			gbc.gridx = 0; gbc.gridy = 8; 		controlPanel.add(j4, gbc);
			gbc.gridx = 1; gbc.gridy = 8; 		controlPanel.add(j4Text, gbc);

			gbc.gridx = 0; gbc.gridy = 9; 		controlPanel.add(vide3, gbc);

			gbc.gridx = 0; gbc.gridy = 10; 		controlPanel.add(ia1, gbc);
			gbc.gridx = 1; gbc.gridy = 10; 		controlPanel.add(IAList1, gbc);

			gbc.gridx = 0; gbc.gridy = 11; 		controlPanel.add(ia2, gbc);
			gbc.gridx = 1; gbc.gridy = 11; 		controlPanel.add(IAList2, gbc);

			gbc.gridx = 0; gbc.gridy = 12; 		controlPanel.add(ia3, gbc);
			gbc.gridx = 1; gbc.gridy = 12; 		controlPanel.add(IAList3, gbc);

			gbc.gridx = 0; gbc.gridy = 13; 		controlPanel.add(ia4, gbc);
			gbc.gridx = 1; gbc.gridy = 13; 		controlPanel.add(IAList4, gbc);

			gbc.gridx = 0; gbc.gridy = 14; 		controlPanel.add(vide4, gbc);

			gbc.gridx = 0; gbc.gridy = 15; 		controlPanel.add(play, gbc);
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
		Menu homeMenu = new Menu("Home");
		Menu playMenu = new Menu("Play");
		Menu webMenu  = new Menu("Web");

		//Création des items du menu "File"
		MenuItem newMenuItem = new MenuItem("Create",new MenuShortcut(KeyEvent.VK_C));
		newMenuItem.setActionCommand("CREATE");

		MenuItem openMenuItem = new MenuItem("Open", new MenuShortcut(KeyEvent.VK_O));
		openMenuItem.setActionCommand("OPEN");

		MenuItem saveMenuItem = new MenuItem("Save", new MenuShortcut(KeyEvent.VK_S));
		saveMenuItem.setActionCommand("SAVE");

		MenuItem exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setActionCommand("EXIT");

		//Création de l'item du menu "Home"
		MenuItem homeMenuItem = new MenuItem("Retour à l'acceuil");
		homeMenuItem.setActionCommand("INTRO");

		//Création des items du menu "Play"
		MenuItem hexaMenuItem = new MenuItem("Hexa");
		hexaMenuItem.setActionCommand("HEXA_PARAM");
		
		//Création des items du menu "Web"
		MenuItem createGameMenuItem = new MenuItem("Créer partie");
		createGameMenuItem.setActionCommand("CREATEGAME");
		
		MenuItem joinGameMenuItem = new MenuItem("Rejoindre partie");
		joinGameMenuItem.setActionCommand("JOINGAME");

		//Création d'un écouteur d'item, et mise sur écoute des items créés
		MenuItemListener menuItemListener = new MenuItemListener();

		newMenuItem.addActionListener(menuItemListener);
		openMenuItem.addActionListener(menuItemListener);
		saveMenuItem.addActionListener(menuItemListener);
		exitMenuItem.addActionListener(menuItemListener);
		homeMenuItem.addActionListener(menuItemListener);
		hexaMenuItem.addActionListener(menuItemListener);
		createGameMenuItem.addActionListener(menuItemListener);
		joinGameMenuItem.addActionListener(menuItemListener);

		//Ajout des items au menu "File"
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);

		//Ajout de l'item au menu "Home"
		homeMenu.add(homeMenuItem);

		//AJout des items au menu "Play"
		playMenu.add(hexaMenuItem);
		
		//Ajout des item au menu "Web"
		webMenu.add(createGameMenuItem);
		webMenu.add(joinGameMenuItem);

		//Ajout des menus à la barre de menus
		menuBar.add(fileMenu);
		menuBar.add(homeMenu);
		menuBar.add(playMenu);
		menuBar.add(webMenu);

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
	 * Cette fonction met fin à la partie
	 */
	public void setAllButtonsToBlack(){

		controlPanel.getComponent(1).setBackground(Color.black);
		controlPanel.getComponent(1).setEnabled(false);

		controlPanel.getComponent(2).setBackground(Color.black);
		controlPanel.getComponent(2).setEnabled(false);

		controlPanel.getComponent(3).setBackground(Color.black);
		controlPanel.getComponent(3).setEnabled(false);

		controlPanel.getComponent(4).setBackground(Color.black);
		controlPanel.getComponent(4).setEnabled(false);

		controlPanel.getComponent(5).setBackground(Color.black);
		controlPanel.getComponent(5).setEnabled(false);

		controlPanel.getComponent(6).setBackground(Color.black);
		controlPanel.getComponent(6).setEnabled(false);
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

			if(board.isTheGameOver() && !command.equals("PLAY")){

				setAllButtonsToBlack();

			}else{

				if( command.equals( "ROUGE"	)) {
					Player nextPlayer = board.nextMove(Color.red);
					setAllowedButtons();
					setRestrictedButtons();

					while(nextPlayer != null){
						if(nextPlayer.getIA().equals("IA Simple")){		nextPlayer = board.nextMove(board.nextEasyIAMove());				}
						else if(nextPlayer.getIA().equals("IA Penible")){	nextPlayer = board.nextMove(board.nextTroubleIAMove(nextPlayer));	}
						else if(nextPlayer.getIA().equals("IA Difficile")){	nextPlayer = board.nextMove(board.nextHardIAMove(nextPlayer));		}
						setAllowedButtons();
						setRestrictedButtons();
					}
				}
				else if( command.equals( "ORANGE")) {
					Player nextPlayer = board.nextMove(Color.orange);
					setAllowedButtons();
					setRestrictedButtons();

					while(nextPlayer != null){
						if(nextPlayer.getIA().equals("IA Simple")){		nextPlayer = board.nextMove(board.nextEasyIAMove());				}
						else if(nextPlayer.getIA().equals("IA Penible")){	nextPlayer = board.nextMove(board.nextTroubleIAMove(nextPlayer));	}
						else if(nextPlayer.getIA().equals("IA Difficile")){	nextPlayer = board.nextMove(board.nextHardIAMove(nextPlayer));		}
						setAllowedButtons();
						setRestrictedButtons();
					}
				}
				else if( command.equals( "JAUNE"	)) {
					Player nextPlayer = board.nextMove(Color.yellow);
					setAllowedButtons();
					setRestrictedButtons();

					while(nextPlayer != null){
						if(nextPlayer.getIA().equals("IA Simple")){		nextPlayer = board.nextMove(board.nextEasyIAMove());				}
						else if(nextPlayer.getIA().equals("IA Penible")){	nextPlayer = board.nextMove(board.nextTroubleIAMove(nextPlayer));	}
						else if(nextPlayer.getIA().equals("IA Difficile")){	nextPlayer = board.nextMove(board.nextHardIAMove(nextPlayer));		}
						setAllowedButtons();
						setRestrictedButtons();
					}
				}
				else if( command.equals( "VERT"  )) {
					Player nextPlayer = board.nextMove(Color.green);
					setAllowedButtons();
					setRestrictedButtons();

					while(nextPlayer != null){
						if(nextPlayer.getIA().equals("IA Simple")){		nextPlayer = board.nextMove(board.nextEasyIAMove());				}
						else if(nextPlayer.getIA().equals("IA Penible")){	nextPlayer = board.nextMove(board.nextTroubleIAMove(nextPlayer));	}
						else if(nextPlayer.getIA().equals("IA Difficile")){	nextPlayer = board.nextMove(board.nextHardIAMove(nextPlayer));		}
						setAllowedButtons();
						setRestrictedButtons();
					}
				}
				else if( command.equals( "BLEU"  )) {
					Player nextPlayer = board.nextMove(Color.blue);
					setAllowedButtons();
					setRestrictedButtons();

					while(nextPlayer != null){
						if(nextPlayer.getIA().equals("IA Simple")){		nextPlayer = board.nextMove(board.nextEasyIAMove());				}
						else if(nextPlayer.getIA().equals("IA Penible")){	nextPlayer = board.nextMove(board.nextTroubleIAMove(nextPlayer));	}
						else if(nextPlayer.getIA().equals("IA Difficile")){	nextPlayer = board.nextMove(board.nextHardIAMove(nextPlayer));		}
						setAllowedButtons();
						setRestrictedButtons();
					}
				}
				else if( command.equals( "MAGENTA"	)) {
					Player nextPlayer = board.nextMove(Color.magenta);
					setAllowedButtons();
					setRestrictedButtons();

					while(nextPlayer != null){
						if(nextPlayer.getIA().equals("IA Simple")){		nextPlayer = board.nextMove(board.nextEasyIAMove());				}
						else if(nextPlayer.getIA().equals("IA Penible")){	nextPlayer = board.nextMove(board.nextTroubleIAMove(nextPlayer));	}
						else if(nextPlayer.getIA().equals("IA Difficile")){	nextPlayer = board.nextMove(board.nextHardIAMove(nextPlayer));		}
						setAllowedButtons();
						setRestrictedButtons();
					}
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
				AwtControl awtControlDemo = new AwtControl(command, 0, "", "", "", "", "", "", "", "");
				awtControlDemo.show(command, "", false);
			}
			if(command.equals("INTRO")){
				mainFrame.dispose();
				AwtControl awtControlDemo = new AwtControl(command, 0, "", "", "", "", "", "", "", "");
				awtControlDemo.show(command, "", false);
			}
			if(command.equals("SAVE")){
				
				if(board.getJoueur1() == null){
					JOptionPane.showMessageDialog(null, "Vous ne pouvez pas sauvegarder de partie avant que "
							+ "celle-ci ne commence !");
				}
				else{
					Frame frame = new Frame("The Filler Game");
					frame.setSize(1440,1440);

					FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.SAVE);
					fd.setDirectory("C:\\");
					fd.setFile("*.txt");
					fd.setVisible(true);

					String directoryName = fd.getDirectory();
					String fileName = fd.getFile();
					if (directoryName == null)	{   System.out.println("You cancelled your choice");   }
					else						{
						System.out.println("You chose "+directoryName+fileName);
					
						String saveStr = board.generateSaveString();
						
						try {
							File f = new File(directoryName+fileName);
							f.createNewFile();
							
							FileWriter fw = new FileWriter(f);
							
							BufferedWriter bw = new BufferedWriter(fw);
							
							bw.write(saveStr);
							
							bw.close();
							fw.close();
							
						} catch (IOException e1) {  e1.printStackTrace(); }
					}
				}
			}
			if(command.equals("OPEN")){
				
				Frame frame = new Frame("The Filler Game");
				frame.setSize(1440,1440);

				FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
				fd.setDirectory("C:\\");
				fd.setFile("*.txt");
				fd.setVisible(true);

				String directoryName = fd.getDirectory();
				String fileName = fd.getFile();
				
				if (directoryName == null)	{   System.out.println("You cancelled your choice");   }
				else						{
					System.out.println("You chose "+directoryName+fileName);
					String saveStr = "";
					
					try {
						BufferedReader buffer = new BufferedReader(new FileReader(directoryName+fileName));
						
						String ligne;  
						while ((ligne = buffer.readLine()) != null) {  
							
							saveStr += ligne+"\r\n";
						}
						buffer.close();
						
					} catch (IOException e1) { e1.printStackTrace(); }
					
					mainFrame.dispose();
					AwtControl awtControl = new AwtControl(saveStr, 0, "", "", "", "", "", "", "", "");
					awtControl.show("", "", false);
				}
			}
			
			if(command.equals("CREATEGAME")){
				
				mainFrame.dispose();
				AwtControl awtControl = new AwtControl("WEB", 0, "", "", "", "", "", "", "", "");
				awtControl.show("WEB", "", false);
			}
		}    
	}
}