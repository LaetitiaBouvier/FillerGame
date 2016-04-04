import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AwtControl{

	private Frame mainFrame;
	private Panel controlPanel;
	private Board board;

	public AwtControl(String tableau){
		
		if(tableau.equals("INTRO")){
			this.board = new IntroBoard(300, 300);
		}
		if(tableau.equals("HEXA")){
			this.board = new HexaBoard(13, "Joueur1", "Joueur2", "", "");
		}
		
		prepareGUI();
	}

	public static void main(String[] args){
		
		AwtControl awtControlDemo = new AwtControl("INTRO");
		awtControlDemo.showEventDemo();
	}

	private void prepareGUI(){
		
		mainFrame = new Frame("The Filler Game");
		mainFrame.setSize(1440,1440);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});

		controlPanel = new Panel();

		mainFrame.add(controlPanel);
		
		mainFrame.setVisible(true);  
	}

	private void showEventDemo(){

		controlPanel.setBackground(Color.black);
		controlPanel.setSize(board.getHauteur(),board.getLargeur());
		
		mainFrame.setSize(board.getHauteur()+10,board.getLargeur()+270);
		
		setButtons();
		
		setMenu();

		mainFrame.setVisible(true); 
	}
	
	private void setButtons(){
		
		if(this.board.getJoueur1() != null && this.board.getJoueur2() != null){
			
			Button redButton 		= new Button("ROUGE");		redButton.setBackground(Color.red);
			Button orangeButton 	= new Button("ORANGE");		orangeButton.setBackground(Color.orange);
			Button yellowButton		= new Button("JAUNE");		yellowButton.setBackground(Color.yellow);
			Button greenButton 		= new Button("VERT");		greenButton.setBackground(Color.green);
			Button blueButton 		= new Button("BLEU");		blueButton.setBackground(Color.blue);
			Button magentaButton	= new Button("MAGENTA");	magentaButton.setBackground(Color.magenta);
			
			redButton.setActionCommand("ROUGE");				redButton.addActionListener		(new ButtonClickListener()); 
			orangeButton.setActionCommand("ORANGE");			orangeButton.addActionListener	(new ButtonClickListener()); 
			yellowButton.setActionCommand("JAUNE");				yellowButton.addActionListener	(new ButtonClickListener());
			greenButton.setActionCommand("VERT");				greenButton.addActionListener	(new ButtonClickListener()); 
			blueButton.setActionCommand("BLEU");				blueButton.addActionListener	(new ButtonClickListener()); 
			magentaButton.setActionCommand("MAGENTA");			magentaButton.addActionListener (new ButtonClickListener()); 
			
			GridBagLayout layout = new GridBagLayout();
			
			controlPanel.setLayout(layout);        
			GridBagConstraints gbc = new GridBagConstraints();
			
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
		else{
			FlowLayout layout = new FlowLayout();
			controlPanel.setLayout(layout);
			
			controlPanel.add((Component) board);
		}
	}
	
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
		hexaMenuItem.setActionCommand("HEXA");

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

	private class ButtonClickListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
			String command = e.getActionCommand();
			
			if( command.equals( "ROUGE"	)) {
				board.nextStep(Color.red);
			}
			if( command.equals( "ORANGE")) {
				board.nextStep(Color.orange);
			}
			if( command.equals( "JAUNE"	)) {
				board.nextStep(Color.yellow);
			}
			if( command.equals( "VERT"  )) {
				board.nextStep(Color.green);
			}
			if( command.equals( "BLEU"  )) {
				board.nextStep(Color.blue);
			}
			if( command.equals( "MAGENTA"	)) {
				board.nextStep(Color.magenta);
			}
			
			setRestrictedButtons();
			setAllowedButtons();
		}
		
		private ArrayList<Color> getColorsFromPlayers(){
			
			ArrayList<Color> couleurs = new ArrayList<Color>();
			
			Color couleur1 = board.getJoueur1().getCasesCtrl().get(0).getColor();
			Color couleur2 = board.getJoueur2().getCasesCtrl().get(0).getColor();
			
			Color couleur3 = null;
			Color couleur4 = null;
			
			if(board.getJoueur3() != null){
				couleur3 = board.getJoueur3().getCasesCtrl().get(0).getColor();
			}
			if(board.getJoueur4() != null){
				couleur4 = board.getJoueur4().getCasesCtrl().get(0).getColor();
			}
			
			couleurs.add(couleur1);
			couleurs.add(couleur2);
			
			if(couleur3 != null){ couleurs.add(couleur3); }
			if(couleur4 != null){ couleurs.add(couleur4); }
			
			return couleurs;
		}
		
		private ArrayList<Color> getFreeColors(){
			
			ArrayList<Color> couleursLibres = new ArrayList<Color>();
			ArrayList<Color> couleursOccupees = getColorsFromPlayers();
			
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
		
		private void setRestrictedButtons(){
			
			ArrayList<Color> couleursOccupees = getColorsFromPlayers();
			
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
		
		private void setAllowedButtons(){
			
			ArrayList<Color> couleursLibres = getFreeColors();
			
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
	
	class MenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			System.out.println(e.getActionCommand() + " MenuItem clicked.");
			
			String command = e.getActionCommand();
			
			if(command.equals("HEXA")){
				
				mainFrame.dispose();
				AwtControl awtControlDemo = new AwtControl(command);
				awtControlDemo.showEventDemo();
			}
		}    
	}
}