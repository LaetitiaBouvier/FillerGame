import java.awt.Button;
import java.awt.Color;
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

public class AwtControl{

	private Frame mainFrame;
	private Panel controlPanel;
	private Board board;

	public AwtControl(){
		
		this.board = new Board(13, "Joueur1", "Joueur2", "", "");
		prepareGUI();
	}

	public static void main(String[] args){
		
		AwtControl awtControlDemo = new AwtControl();
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
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(controlPanel);
		
		mainFrame.setVisible(true);  
	}

	private void showEventDemo(){

		Panel panel = new Panel();
		panel.setBackground(Color.black);
		panel.setSize(board.getHeight(),board.getWidth());
		
		mainFrame.setSize(board.getHeight()+10,board.getWidth()+270);
		
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

		panel.setLayout(layout);        
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(board,gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(redButton,gbc); 

		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(orangeButton,gbc); 

		gbc.gridx = 0;
		gbc.gridy = 3;       
		panel.add(yellowButton,gbc);  

		gbc.gridx = 0;
		gbc.gridy = 4;      
		panel.add(greenButton,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;      
		panel.add(blueButton,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;      
		panel.add(magentaButton,gbc);

		controlPanel.add(panel);

		
		Button okButton = new Button("OK");
		Button submitButton = new Button("Submit");
		Button cancelButton = new Button("Cancel");

		okButton.setActionCommand("OK");
		submitButton.setActionCommand("Submit");
		cancelButton.setActionCommand("Cancel");

		okButton.addActionListener(new ButtonClickListener()); 
		submitButton.addActionListener(new ButtonClickListener()); 
		cancelButton.addActionListener(new ButtonClickListener()); 
		
		controlPanel.add(okButton);
		controlPanel.add(submitButton);
		controlPanel.add(cancelButton);
		
		//create a menu bar
		final MenuBar menuBar = new MenuBar();

		//create menus
		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Jouer");
		Menu aboutMenu = new Menu("Test");
		
		//create menu items
		MenuItem newMenuItem = 
				new MenuItem("New",new MenuShortcut(KeyEvent.VK_N));
		newMenuItem.setActionCommand("New");

		MenuItem openMenuItem = new MenuItem("Open");
		openMenuItem.setActionCommand("Open");

		MenuItem saveMenuItem = new MenuItem("Save");
		saveMenuItem.setActionCommand("Save");

		MenuItem exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setActionCommand("Exit");


		MenuItemListener menuItemListener = new MenuItemListener();

		newMenuItem.addActionListener(menuItemListener);
		openMenuItem.addActionListener(menuItemListener);
		saveMenuItem.addActionListener(menuItemListener);
		exitMenuItem.addActionListener(menuItemListener);
		
		//add menu items to menus
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);

		//add menu to menubar
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(aboutMenu);

		//add menubar to the frame
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
		}		
	}
	
	class MenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {            
			System.out.println(e.getActionCommand() 
					+ " MenuItem clicked.");
		}    
	}
}