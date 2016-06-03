import java.awt.Color;
import java.util.ArrayList;

import com.tdebroc.filler.connector.PlayerConnector;
import com.tdebroc.filler.game.Game;
import com.tdebroc.filler.game.Grid;

public class IAcontest {

	static final String BASE_URL = "http://62.210.105.118:8081";
	static final String NOM_IA = "Steph_Laeti";
	static String indicePositionIA;

	public static void main(String[] args) {

		PlayerConnector playerConnector = new PlayerConnector(174, BASE_URL);
		playerConnector.registerPlayer(NOM_IA);

		Game game = playerConnector.getGame();
		
		// !!!
		int[] indicesPosi = new int[2];
		
		if( game.getPlayers().get(0).getPlayerName().equals(NOM_IA) )	{ indicesPosi[0] = 1; indicesPosi[1] = 2; }
		else															{ indicesPosi[0] = 2; indicesPosi[1] = 1; }
		// !!!
		// Ici indicesPosi[0] correspond TOUJOURS à l'indice de la position de notre IA
		// 	   indicesPosi[1] correspond TOUJOURS à l'indice de la position de l'IA adverse
		
		indicePositionIA = ""+indicesPosi[0];
		
//		System.out.println("Joueur position IA : "+indicesPosi[0]);
//		System.out.println("Joueur position IA adverse : "+indicesPosi[1]);
		
		do {
			game = playerConnector.waitOppenentsAndGetTheirMoves();
			
			SquareCell[][] grid = extractGridFromGame(game);
			
			char c = nextSquareIAContestMove(grid, indicesPosi);
			
			playerConnector.sendMove(c);
			
			game = playerConnector.getGame();
			
		} while (!game.isFinished());
	}
	
	public static char nextHardIAMove(SquareCell[][] original, int[] indicesPosi){
		
		System.out.println("INDICE POSITION : "+indicesPosi[0]);
		
		ArrayList<Color> couleursDispo = getCouleursDispo(original);
		
		ArrayList<Cell> squaresCtrl = new ArrayList<Cell>();
		ArrayList<Cell> squaresAdvr = new ArrayList<Cell>();
		
		if(indicesPosi[0] == 1){
			squaresCtrl.add(original[0][0]);									squaresAdvr.add(original[original.length-1][original.length-1]);
			squaresCtrl = getConnectedCellsOfSameColor(squaresCtrl);			squaresAdvr = getConnectedCellsOfSameColor(squaresAdvr);
		}else{
			squaresCtrl.add(original[original.length-1][original.length-1]);	squaresAdvr.add(original[0][0]);
			squaresCtrl = getConnectedCellsOfSameColor(squaresCtrl);			squaresAdvr = getConnectedCellsOfSameColor(squaresAdvr);
		}
		int maxCtrl = squaresCtrl.size();
		
		Color colorIniCtrl = squaresCtrl.get(0).getColor();
//		Color colorIniAdvr = squaresAdvr.get(0).getColor();
		Color color = Color.black;
		
		for(int i = 0; i < squaresCtrl.size(); i++)	{ squaresCtrl.get(i).setColor(Color.red); 	}
		int redList = getConnectedCellsOfSameColor(squaresCtrl).size();			System.out.println("Red score : "+redList);
		if( redList >= maxCtrl 		&& couleursDispo.contains(Color.red))		{ color = Color.red; 		maxCtrl = redList;		}
		
		for(int i = 0; i < squaresCtrl.size(); i++)	{ squaresCtrl.get(i).setColor(Color.orange); 	}
		int orangeList = getConnectedCellsOfSameColor(squaresCtrl).size();		System.out.println("Orange score : "+orangeList);
		if( orangeList >= maxCtrl 	&& couleursDispo.contains(Color.orange))	{ color = Color.orange; 	maxCtrl = orangeList;	}
		
		for(int i = 0; i < squaresCtrl.size(); i++)	{ squaresCtrl.get(i).setColor(Color.yellow); 	}
		int yellowList = getConnectedCellsOfSameColor(squaresCtrl).size();		System.out.println("Yellow score : "+yellowList);
		if( yellowList >= maxCtrl 	&& couleursDispo.contains(Color.yellow))	{ color = Color.yellow; 	maxCtrl = yellowList;	}
		
		for(int i = 0; i < squaresCtrl.size(); i++)	{ squaresCtrl.get(i).setColor(Color.green); 	}
		int greenList = getConnectedCellsOfSameColor(squaresCtrl).size();		System.out.println("Green score : "+greenList);
		if( greenList >= maxCtrl 	&& couleursDispo.contains(Color.green))	{ color = Color.green; 			maxCtrl = greenList;	}
		
		for(int i = 0; i < squaresCtrl.size(); i++)	{ squaresCtrl.get(i).setColor(Color.blue); 	}
		int blueList = getConnectedCellsOfSameColor(squaresCtrl).size();		System.out.println("Blue score : "+blueList);
		if( blueList >= maxCtrl 		&& couleursDispo.contains(Color.blue))	{ color = Color.blue; 		maxCtrl = blueList;		}
		
		for(int i = 0; i < squaresCtrl.size(); i++){ squaresCtrl.get(i).setColor(Color.magenta); 	}
		int magentaList = getConnectedCellsOfSameColor(squaresCtrl).size();		System.out.println("Magenta score : "+magentaList);
		if( magentaList >= maxCtrl 	&& couleursDispo.contains(Color.magenta))	{ color = Color.magenta; 	maxCtrl = magentaList;	}
		
		for(int i = 0; i < squaresCtrl.size(); i++){ squaresCtrl.get(i).setColor(colorIniCtrl); }
		
		char c = ' ';
		
		if(color == Color.red) 		c = 'R';
		if(color == Color.orange) 	c = 'O';
		if(color == Color.yellow) 	c = 'J';
		if(color == Color.green) 	c = 'V';
		if(color == Color.blue) 	c = 'B';
		if(color == Color.magenta) 	c = 'I';
		
		return c;
	}

	public static char nextSquareIAContestMove(SquareCell[][] original, int[] indicesPosi){

		ArrayList<Color> couleursNonDispo = new ArrayList<Color>();
		couleursNonDispo.add(original[0][0].getColor());
		couleursNonDispo.add(original[original.length-1][original.length-1].getColor());
		
		
		ArrayList<Integer> scoreRedMove 	= new ArrayList<Integer>();
		ArrayList<Integer> scoreOrangeMove 	= new ArrayList<Integer>();
		ArrayList<Integer> scoreYellowMove 	= new ArrayList<Integer>();
		ArrayList<Integer> scoreGreenMove 	= new ArrayList<Integer>();
		ArrayList<Integer> scoreBlueMove 	= new ArrayList<Integer>();
		ArrayList<Integer> scoreMagentaMove	= new ArrayList<Integer>();

		SquareCell[][] clone = cloneGrid(original);
		DecisionTree dt = new DecisionTree(clone);

		//Initialisation :
		dt = oneStepDecisionTreeGeneration(indicesPosi[0], dt);

		//________________________________
		if(dt.getRedChild() 	!= null) { dt.setRedChild(oneStepDecisionTreeGeneration		(indicesPosi[0], dt.getRedChild()));	System.out.print("r : "); }
		if(dt.getOrangeChild() 	!= null) { dt.setOrangeChild(oneStepDecisionTreeGeneration	(indicesPosi[0], dt.getOrangeChild()));	System.out.print("o : "); }
		if(dt.getYellowChild() 	!= null) { dt.setYellowChild(oneStepDecisionTreeGeneration	(indicesPosi[0], dt.getYellowChild()));	System.out.print("y : "); }
		if(dt.getGreenChild() 	!= null) { dt.setGreenChild(oneStepDecisionTreeGeneration	(indicesPosi[0], dt.getGreenChild()));	System.out.print("g : "); }
		if(dt.getBlueChild() 	!= null) { dt.setBlueChild(oneStepDecisionTreeGeneration	(indicesPosi[0], dt.getBlueChild()));	System.out.print("b : "); }
		if(dt.getMagentaChild() != null) { dt.setMagentaChild(oneStepDecisionTreeGeneration	(indicesPosi[0], dt.getMagentaChild()));System.out.print("m"); }

		System.out.print("\n________________\n");

		//________________________________
		if(dt.getRedChild() 	!= null && dt.getRedChild().getRedChild()		!= null){							System.out.print("r_r : ");
		dt.getRedChild().setRedChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getRedChild().getRedChild()) ); }
		if(dt.getRedChild() 	!= null && dt.getRedChild().getOrangeChild()	!= null){							System.out.print("r_o : ");
		dt.getRedChild().setOrangeChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getRedChild().getOrangeChild()) ); }
		if(dt.getRedChild() 	!= null && dt.getRedChild().getYellowChild()	!= null){							System.out.print("r_y : ");
		dt.getRedChild().setYellowChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getRedChild().getYellowChild()) ); }
		if(dt.getRedChild() 	!= null && dt.getRedChild().getGreenChild()		!= null){							System.out.print("r_g : ");
		dt.getRedChild().setGreenChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getRedChild().getGreenChild()) ); }
		if(dt.getRedChild() 	!= null && dt.getRedChild().getBlueChild()		!= null){							System.out.print("r_b : ");
		dt.getRedChild().setBlueChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getRedChild().getBlueChild()) ); }
		if(dt.getRedChild() 	!= null && dt.getRedChild().getMagentaChild()	!= null){							System.out.print("r_m");
		dt.getRedChild().setMagentaChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getRedChild().getMagentaChild()) ); }

		System.out.print("\n___\n");

		if(dt.getOrangeChild() 	!= null && dt.getOrangeChild().getRedChild()	!= null){							System.out.print("o_r : ");
		dt.getOrangeChild().setRedChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getOrangeChild().getRedChild()) ); }
		if(dt.getOrangeChild() 	!= null && dt.getOrangeChild().getOrangeChild()	!= null){							System.out.print("o_o : ");
		dt.getOrangeChild().setOrangeChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getOrangeChild().getOrangeChild()) ); }
		if(dt.getOrangeChild() 	!= null && dt.getOrangeChild().getYellowChild()	!= null){							System.out.print("o_y : ");
		dt.getOrangeChild().setYellowChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getOrangeChild().getYellowChild()) ); }
		if(dt.getOrangeChild() 	!= null && dt.getOrangeChild().getGreenChild()	!= null){							System.out.print("o_g : ");
		dt.getOrangeChild().setGreenChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getOrangeChild().getGreenChild()) ); }
		if(dt.getOrangeChild() 	!= null && dt.getOrangeChild().getBlueChild()	!= null){							System.out.print("o_b : ");
		dt.getOrangeChild().setBlueChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getOrangeChild().getBlueChild()) ); }
		if(dt.getOrangeChild() 	!= null && dt.getOrangeChild().getMagentaChild()!= null){							System.out.print("o_m");
		dt.getOrangeChild().setMagentaChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getOrangeChild().getMagentaChild()) ); }

		System.out.print("\n___\n");

		if(dt.getYellowChild() 	!= null && dt.getYellowChild().getRedChild()	!= null){							System.out.print("y_r : ");
		dt.getYellowChild().setRedChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getYellowChild().getRedChild()) ); }
		if(dt.getYellowChild() 	!= null && dt.getYellowChild().getOrangeChild()	!= null){							System.out.print("y_o : ");
		dt.getYellowChild().setOrangeChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getYellowChild().getOrangeChild()) ); }
		if(dt.getYellowChild() 	!= null && dt.getYellowChild().getYellowChild()	!= null){							System.out.print("y_y : ");
		dt.getYellowChild().setYellowChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getYellowChild().getYellowChild()) ); }
		if(dt.getYellowChild() 	!= null && dt.getYellowChild().getGreenChild()	!= null){							System.out.print("y_g : ");
		dt.getYellowChild().setGreenChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getYellowChild().getGreenChild()) ); }
		if(dt.getYellowChild() 	!= null && dt.getYellowChild().getBlueChild()	!= null){							System.out.print("y_b : ");
		dt.getYellowChild().setBlueChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getYellowChild().getBlueChild()) ); }
		if(dt.getYellowChild() 	!= null && dt.getYellowChild().getMagentaChild()!= null){							System.out.print("y_m");
		dt.getYellowChild().setMagentaChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getYellowChild().getMagentaChild()) ); }

		System.out.print("\n___\n");

		if(dt.getGreenChild() 	!= null && dt.getGreenChild().getRedChild()	!= null){								System.out.print("g_r : ");
		dt.getGreenChild().setRedChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getGreenChild().getRedChild()) ); }
		if(dt.getGreenChild() 	!= null && dt.getGreenChild().getOrangeChild()	!= null){							System.out.print("g_o : ");
		dt.getGreenChild().setOrangeChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getGreenChild().getOrangeChild()) ); }
		if(dt.getGreenChild() 	!= null && dt.getGreenChild().getYellowChild()	!= null){							System.out.print("g_y : ");
		dt.getGreenChild().setYellowChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getGreenChild().getYellowChild()) ); }
		if(dt.getGreenChild() 	!= null && dt.getGreenChild().getGreenChild()	!= null){							System.out.print("g_g : ");
		dt.getGreenChild().setGreenChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getGreenChild().getGreenChild()) ); }
		if(dt.getGreenChild() 	!= null && dt.getGreenChild().getBlueChild()	!= null){							System.out.print("g_b : ");
		dt.getGreenChild().setBlueChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getGreenChild().getBlueChild()) ); }
		if(dt.getGreenChild() 	!= null && dt.getGreenChild().getMagentaChild()	!= null){							System.out.print("g_m");
		dt.getGreenChild().setMagentaChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getGreenChild().getMagentaChild()) ); }

		System.out.print("\n___\n");

		if(dt.getBlueChild() 	!= null && dt.getBlueChild().getRedChild()		!= null){							System.out.print("b_r : ");
		dt.getBlueChild().setRedChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getBlueChild().getRedChild()) ); }
		if(dt.getBlueChild() 	!= null && dt.getBlueChild().getOrangeChild()	!= null){							System.out.print("b_o : ");
		dt.getBlueChild().setOrangeChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getBlueChild().getOrangeChild()) ); }
		if(dt.getBlueChild() 	!= null && dt.getBlueChild().getYellowChild()	!= null){							System.out.print("b_y : ");
		dt.getBlueChild().setYellowChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getBlueChild().getYellowChild()) ); }
		if(dt.getBlueChild() 	!= null && dt.getBlueChild().getGreenChild()	!= null){							System.out.print("b_g : ");
		dt.getBlueChild().setGreenChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getBlueChild().getGreenChild()) ); }
		if(dt.getBlueChild() 	!= null && dt.getBlueChild().getBlueChild()		!= null){							System.out.print("b_b : ");
		dt.getBlueChild().setBlueChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getBlueChild().getBlueChild()) ); }
		if(dt.getBlueChild() 	!= null && dt.getBlueChild().getMagentaChild()	!= null){							System.out.print("b_m");
		dt.getBlueChild().setMagentaChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getBlueChild().getMagentaChild()) ); }

		System.out.print("\n___\n");

		if(dt.getMagentaChild() 	!= null && dt.getMagentaChild().getRedChild()		!= null){					System.out.print("m_r : ");
		dt.getMagentaChild().setRedChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getMagentaChild().getRedChild()) ); }
		if(dt.getMagentaChild() 	!= null && dt.getMagentaChild().getOrangeChild()	!= null){					System.out.print("m_o : ");
		dt.getMagentaChild().setOrangeChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getMagentaChild().getOrangeChild()) ); }
		if(dt.getMagentaChild() 	!= null && dt.getMagentaChild().getYellowChild()	!= null){					System.out.print("m_y : ");
		dt.getMagentaChild().setYellowChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getMagentaChild().getYellowChild()) ); }
		if(dt.getMagentaChild() 	!= null && dt.getMagentaChild().getGreenChild()		!= null){					System.out.print("m_g : ");
		dt.getMagentaChild().setGreenChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getMagentaChild().getGreenChild()) ); }
		if(dt.getMagentaChild() 	!= null && dt.getMagentaChild().getBlueChild()		!= null){					System.out.print("m_b : ");
		dt.getMagentaChild().setBlueChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getMagentaChild().getBlueChild()) ); }
		if(dt.getMagentaChild() 	!= null && dt.getMagentaChild().getMagentaChild()	!= null){					System.out.print("m_m");
		dt.getMagentaChild().setMagentaChild( oneStepDecisionTreeGeneration(indicesPosi[1], dt.getMagentaChild().getMagentaChild()) ); }

		System.out.print("\n________________\n");

		//________________________________

		if(dt.getRedChild() != null && dt.getRedChild().getRedChild() != null && dt.getRedChild().getRedChild().getRedChild() != null){			System.out.print("r_r_r : ");
		dt.getRedChild().getRedChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getRedChild().getRedChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getRedChild().getRedChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getRedChild() != null && dt.getRedChild().getRedChild().getOrangeChild() != null){		System.out.print("r_r_o : ");
		dt.getRedChild().getRedChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getRedChild().getOrangeChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getRedChild().getOrangeChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getRedChild() != null && dt.getRedChild().getRedChild().getYellowChild() != null){		System.out.print("r_r_y : ");
		dt.getRedChild().getRedChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getRedChild().getYellowChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getRedChild().getYellowChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getRedChild() != null && dt.getRedChild().getRedChild().getGreenChild() != null){		System.out.print("r_r_g : ");
		dt.getRedChild().getRedChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getRedChild().getGreenChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getRedChild().getGreenChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getRedChild() != null && dt.getRedChild().getRedChild().getBlueChild() != null){		System.out.print("r_r_b : ");
		dt.getRedChild().getRedChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getRedChild().getBlueChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getRedChild().getBlueChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getRedChild() != null && dt.getRedChild().getRedChild().getMagentaChild() != null){		System.out.print("r_r_m");
		dt.getRedChild().getRedChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getRedChild().getMagentaChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getRedChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getRedChild() != null && dt.getRedChild().getOrangeChild() != null && dt.getRedChild().getOrangeChild().getRedChild() != null){		System.out.print("r_o_r : ");
		dt.getRedChild().getOrangeChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getOrangeChild().getRedChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getOrangeChild().getRedChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getOrangeChild() != null && dt.getRedChild().getOrangeChild().getOrangeChild() != null){	System.out.print("r_o_o : ");
		dt.getRedChild().getOrangeChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getOrangeChild().getOrangeChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getOrangeChild().getOrangeChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getOrangeChild() != null && dt.getRedChild().getOrangeChild().getYellowChild() != null){	System.out.print("r_o_y : ");
		dt.getRedChild().getOrangeChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getOrangeChild().getYellowChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getOrangeChild().getYellowChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getOrangeChild() != null && dt.getRedChild().getOrangeChild().getGreenChild() != null){		System.out.print("r_o_g : ");
		dt.getRedChild().getOrangeChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getOrangeChild().getGreenChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getOrangeChild().getGreenChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getOrangeChild() != null && dt.getRedChild().getOrangeChild().getBlueChild() != null){		System.out.print("r_o_b : ");
		dt.getRedChild().getOrangeChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getOrangeChild().getBlueChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getOrangeChild().getBlueChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getOrangeChild() != null && dt.getRedChild().getOrangeChild().getMagentaChild() != null){	System.out.print("r_o_m");
		dt.getRedChild().getOrangeChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getOrangeChild().getMagentaChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getOrangeChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getRedChild() != null && dt.getRedChild().getYellowChild() != null && dt.getRedChild().getYellowChild().getRedChild() != null){		System.out.print("r_y_r : ");
		dt.getRedChild().getYellowChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getYellowChild().getRedChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getYellowChild().getRedChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getYellowChild() != null && dt.getRedChild().getYellowChild().getOrangeChild() != null){	System.out.print("r_y_o : ");
		dt.getRedChild().getYellowChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getYellowChild().getOrangeChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getYellowChild().getOrangeChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getYellowChild() != null && dt.getRedChild().getYellowChild().getYellowChild() != null){	System.out.print("r_y_y : ");
		dt.getRedChild().getYellowChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getYellowChild().getYellowChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getYellowChild().getYellowChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getYellowChild() != null && dt.getRedChild().getYellowChild().getGreenChild() != null){		System.out.print("r_y_g : ");
		dt.getRedChild().getYellowChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getYellowChild().getGreenChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getYellowChild().getGreenChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getYellowChild() != null && dt.getRedChild().getYellowChild().getBlueChild() != null){		System.out.print("r_y_b : ");
		dt.getRedChild().getYellowChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getYellowChild().getBlueChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getYellowChild().getBlueChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getYellowChild() != null && dt.getRedChild().getYellowChild().getMagentaChild() != null){	System.out.print("r_y_m");
		dt.getRedChild().getYellowChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getYellowChild().getMagentaChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getYellowChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getRedChild() != null && dt.getRedChild().getGreenChild() != null && dt.getRedChild().getGreenChild().getRedChild() != null){		System.out.print("r_g_r : ");
		dt.getRedChild().getGreenChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getGreenChild().getRedChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getGreenChild().getRedChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getGreenChild() != null && dt.getRedChild().getGreenChild().getOrangeChild() != null){	System.out.print("r_g_o : ");
		dt.getRedChild().getGreenChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getGreenChild().getOrangeChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getGreenChild().getOrangeChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getGreenChild() != null && dt.getRedChild().getGreenChild().getYellowChild() != null){	System.out.print("r_g_y : ");
		dt.getRedChild().getGreenChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getGreenChild().getYellowChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getGreenChild().getYellowChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getGreenChild() != null && dt.getRedChild().getGreenChild().getGreenChild() != null){	System.out.print("r_g_g : ");
		dt.getRedChild().getGreenChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getGreenChild().getGreenChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getGreenChild().getGreenChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getGreenChild() != null && dt.getRedChild().getGreenChild().getBlueChild() != null){	System.out.print("r_g_b : ");
		dt.getRedChild().getGreenChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getGreenChild().getBlueChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getGreenChild().getBlueChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getGreenChild() != null && dt.getRedChild().getGreenChild().getMagentaChild() != null){	System.out.print("r_g_m");
		dt.getRedChild().getGreenChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getGreenChild().getMagentaChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getGreenChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getRedChild() != null && dt.getRedChild().getBlueChild() != null && dt.getRedChild().getBlueChild().getRedChild() != null){		System.out.print("r_b_r : ");
		dt.getRedChild().getBlueChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getBlueChild().getRedChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getBlueChild().getRedChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getBlueChild() != null && dt.getRedChild().getBlueChild().getOrangeChild() != null){	System.out.print("r_b_o : ");
		dt.getRedChild().getBlueChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getBlueChild().getOrangeChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getBlueChild().getOrangeChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getBlueChild() != null && dt.getRedChild().getBlueChild().getYellowChild() != null){	System.out.print("r_b_y : ");
		dt.getRedChild().getBlueChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getBlueChild().getYellowChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getBlueChild().getYellowChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getBlueChild() != null && dt.getRedChild().getBlueChild().getGreenChild() != null){		System.out.print("r_b_g : ");
		dt.getRedChild().getBlueChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getBlueChild().getGreenChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getBlueChild().getGreenChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getBlueChild() != null && dt.getRedChild().getBlueChild().getBlueChild() != null){		System.out.print("r_b_b : ");
		dt.getRedChild().getBlueChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getBlueChild().getBlueChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getBlueChild().getBlueChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getBlueChild() != null && dt.getRedChild().getBlueChild().getMagentaChild() != null){	System.out.print("r_b_m");
		dt.getRedChild().getBlueChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getBlueChild().getMagentaChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getBlueChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getRedChild() != null && dt.getRedChild().getMagentaChild() != null && dt.getRedChild().getMagentaChild().getRedChild() != null){		System.out.print("r_m_r : ");
		dt.getRedChild().getMagentaChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getMagentaChild().getRedChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getMagentaChild().getRedChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getMagentaChild() != null && dt.getRedChild().getMagentaChild().getOrangeChild() != null){	System.out.print("r_m_o : ");
		dt.getRedChild().getMagentaChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getMagentaChild().getOrangeChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getMagentaChild().getOrangeChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getMagentaChild() != null && dt.getRedChild().getMagentaChild().getYellowChild() != null){	System.out.print("r_m_y : ");
		dt.getRedChild().getMagentaChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getMagentaChild().getYellowChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getMagentaChild().getYellowChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getMagentaChild() != null && dt.getRedChild().getMagentaChild().getGreenChild() != null){	System.out.print("r_m_g : ");
		dt.getRedChild().getMagentaChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getMagentaChild().getGreenChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getMagentaChild().getGreenChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getMagentaChild() != null && dt.getRedChild().getMagentaChild().getBlueChild() != null){	System.out.print("r_m_b : ");
		dt.getRedChild().getMagentaChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getMagentaChild().getBlueChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getMagentaChild().getBlueChild().getGrid()));
		}
		if(dt.getRedChild() != null && dt.getRedChild().getMagentaChild() != null && dt.getRedChild().getMagentaChild().getMagentaChild() != null){	System.out.print("r_m_m");
		dt.getRedChild().getMagentaChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getRedChild().getMagentaChild().getMagentaChild()));
		scoreRedMove.add(getScore(indicesPosi, dt.getRedChild().getMagentaChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n___\n");

		if(dt.getOrangeChild() != null && dt.getOrangeChild().getRedChild() != null && dt.getOrangeChild().getRedChild().getRedChild() != null){		System.out.print("o_r_r : ");
		dt.getOrangeChild().getRedChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getRedChild().getRedChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getRedChild().getRedChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getRedChild() != null && dt.getOrangeChild().getRedChild().getOrangeChild() != null){		System.out.print("o_r_o : ");
		dt.getOrangeChild().getRedChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getRedChild().getOrangeChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getRedChild().getOrangeChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getRedChild() != null && dt.getOrangeChild().getRedChild().getYellowChild() != null){		System.out.print("o_r_y : ");
		dt.getOrangeChild().getRedChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getRedChild().getYellowChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getRedChild().getYellowChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getRedChild() != null && dt.getOrangeChild().getRedChild().getGreenChild() != null){		System.out.print("o_r_g : ");
		dt.getOrangeChild().getRedChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getRedChild().getGreenChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getRedChild().getGreenChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getRedChild() != null && dt.getOrangeChild().getRedChild().getBlueChild() != null){		System.out.print("o_r_b : ");
		dt.getOrangeChild().getRedChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getRedChild().getBlueChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getRedChild().getBlueChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getRedChild() != null && dt.getOrangeChild().getRedChild().getMagentaChild() != null){	System.out.print("o_r_m");
		dt.getOrangeChild().getRedChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getRedChild().getMagentaChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getRedChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getOrangeChild() != null && dt.getOrangeChild().getOrangeChild() != null && dt.getOrangeChild().getOrangeChild().getRedChild() != null){		System.out.print("o_o_r : ");
		dt.getOrangeChild().getOrangeChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getOrangeChild().getRedChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getOrangeChild().getRedChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getOrangeChild() != null && dt.getOrangeChild().getOrangeChild().getOrangeChild() != null){	System.out.print("o_o_o : ");
		dt.getOrangeChild().getOrangeChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getOrangeChild().getOrangeChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getOrangeChild().getOrangeChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getOrangeChild() != null && dt.getOrangeChild().getOrangeChild().getYellowChild() != null){	System.out.print("o_o_y : ");
		dt.getOrangeChild().getOrangeChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getOrangeChild().getYellowChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getOrangeChild().getYellowChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getOrangeChild() != null && dt.getOrangeChild().getOrangeChild().getGreenChild() != null){	System.out.print("o_o_g : ");
		dt.getOrangeChild().getOrangeChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getOrangeChild().getGreenChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getOrangeChild().getGreenChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getOrangeChild() != null && dt.getOrangeChild().getOrangeChild().getBlueChild() != null){		System.out.print("o_o_b : ");
		dt.getOrangeChild().getOrangeChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getOrangeChild().getBlueChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getOrangeChild().getBlueChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getOrangeChild() != null && dt.getOrangeChild().getOrangeChild().getMagentaChild() != null){	System.out.print("o_o_m");
		dt.getOrangeChild().getOrangeChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getOrangeChild().getMagentaChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getOrangeChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getOrangeChild() != null && dt.getOrangeChild().getYellowChild() != null && dt.getOrangeChild().getYellowChild().getRedChild() != null){		System.out.print("o_y_r : ");
		dt.getOrangeChild().getYellowChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getYellowChild().getRedChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getYellowChild().getRedChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getYellowChild() != null && dt.getOrangeChild().getYellowChild().getOrangeChild() != null){	System.out.print("o_y_o : ");
		dt.getOrangeChild().getYellowChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getYellowChild().getOrangeChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getYellowChild().getOrangeChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getYellowChild() != null && dt.getOrangeChild().getYellowChild().getYellowChild() != null){	System.out.print("o_y_y : ");
		dt.getOrangeChild().getYellowChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getYellowChild().getYellowChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getYellowChild().getYellowChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getYellowChild() != null && dt.getOrangeChild().getYellowChild().getGreenChild() != null){	System.out.print("o_y_g : ");
		dt.getOrangeChild().getYellowChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getYellowChild().getGreenChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getYellowChild().getGreenChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getYellowChild() != null && dt.getOrangeChild().getYellowChild().getBlueChild() != null){		System.out.print("o_y_b : ");
		dt.getOrangeChild().getYellowChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getYellowChild().getBlueChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getYellowChild().getBlueChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getYellowChild() != null && dt.getOrangeChild().getYellowChild().getMagentaChild() != null){	System.out.print("o_y_m");
		dt.getOrangeChild().getYellowChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getYellowChild().getMagentaChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getYellowChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getOrangeChild() != null && dt.getOrangeChild().getGreenChild() != null && dt.getOrangeChild().getGreenChild().getRedChild() != null){	System.out.print("o_g_r : ");
		dt.getOrangeChild().getGreenChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getGreenChild().getRedChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getGreenChild().getRedChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getGreenChild() != null && dt.getOrangeChild().getGreenChild().getOrangeChild() != null){	System.out.print("o_g_o : ");
		dt.getOrangeChild().getGreenChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getGreenChild().getOrangeChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getGreenChild().getOrangeChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getGreenChild() != null && dt.getOrangeChild().getGreenChild().getYellowChild() != null){	System.out.print("o_g_y : ");
		dt.getOrangeChild().getGreenChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getGreenChild().getYellowChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getGreenChild().getYellowChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getGreenChild() != null && dt.getOrangeChild().getGreenChild().getGreenChild() != null){	System.out.print("o_g_g : ");
		dt.getOrangeChild().getGreenChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getGreenChild().getGreenChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getGreenChild().getGreenChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getGreenChild() != null && dt.getOrangeChild().getGreenChild().getBlueChild() != null){	System.out.print("o_g_b : ");
		dt.getOrangeChild().getGreenChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getGreenChild().getBlueChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getGreenChild().getBlueChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getGreenChild() != null && dt.getOrangeChild().getGreenChild().getMagentaChild() != null){System.out.print("o_g_m");
		dt.getOrangeChild().getGreenChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getGreenChild().getMagentaChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getGreenChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getOrangeChild() != null && dt.getOrangeChild().getBlueChild() != null && dt.getOrangeChild().getBlueChild().getRedChild() != null){		System.out.print("o_b_r : ");
		dt.getOrangeChild().getBlueChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getBlueChild().getRedChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getBlueChild().getRedChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getBlueChild() != null && dt.getOrangeChild().getBlueChild().getOrangeChild() != null){	System.out.print("o_b_o : ");
		dt.getOrangeChild().getBlueChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getBlueChild().getOrangeChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getBlueChild().getOrangeChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getBlueChild() != null && dt.getOrangeChild().getBlueChild().getYellowChild() != null){	System.out.print("o_b_y : ");
		dt.getOrangeChild().getBlueChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getBlueChild().getYellowChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getBlueChild().getYellowChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getBlueChild() != null && dt.getOrangeChild().getBlueChild().getGreenChild() != null){	System.out.print("o_b_g : ");
		dt.getOrangeChild().getBlueChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getBlueChild().getGreenChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getBlueChild().getGreenChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getBlueChild() != null && dt.getOrangeChild().getBlueChild().getBlueChild() != null){		System.out.print("o_b_b : ");
		dt.getOrangeChild().getBlueChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getBlueChild().getBlueChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getBlueChild().getBlueChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getBlueChild() != null && dt.getOrangeChild().getBlueChild().getMagentaChild() != null){	System.out.print("o_b_m");
		dt.getOrangeChild().getBlueChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getBlueChild().getMagentaChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getBlueChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getOrangeChild() != null && dt.getOrangeChild().getMagentaChild() != null && dt.getOrangeChild().getMagentaChild().getRedChild() != null){	System.out.print("o_m_r : ");
		dt.getOrangeChild().getMagentaChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getMagentaChild().getRedChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getMagentaChild().getRedChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getMagentaChild() != null && dt.getOrangeChild().getMagentaChild().getOrangeChild() != null){	System.out.print("o_m_o : ");
		dt.getOrangeChild().getMagentaChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getMagentaChild().getOrangeChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getMagentaChild().getOrangeChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getMagentaChild() != null && dt.getOrangeChild().getMagentaChild().getYellowChild() != null){	System.out.print("o_m_y : ");
		dt.getOrangeChild().getMagentaChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getMagentaChild().getYellowChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getMagentaChild().getYellowChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getMagentaChild() != null && dt.getOrangeChild().getMagentaChild().getGreenChild() != null){	System.out.print("o_m_g : ");
		dt.getOrangeChild().getMagentaChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getMagentaChild().getGreenChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getMagentaChild().getGreenChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getMagentaChild() != null && dt.getOrangeChild().getMagentaChild().getBlueChild() != null){	System.out.print("o_m_b : ");
		dt.getOrangeChild().getMagentaChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getMagentaChild().getBlueChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getMagentaChild().getBlueChild().getGrid()));
		}
		if(dt.getOrangeChild() != null && dt.getOrangeChild().getMagentaChild() != null && dt.getOrangeChild().getMagentaChild().getMagentaChild() != null){System.out.print("o_m_m");
		dt.getOrangeChild().getMagentaChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getOrangeChild().getMagentaChild().getMagentaChild()));
		scoreOrangeMove.add(getScore(indicesPosi, dt.getOrangeChild().getMagentaChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n___\n");

		if(dt.getYellowChild() != null && dt.getYellowChild().getRedChild() != null && dt.getYellowChild().getRedChild().getRedChild() != null){		System.out.print("y_r_r : ");
		dt.getYellowChild().getRedChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getRedChild().getRedChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getRedChild().getRedChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getRedChild() != null && dt.getYellowChild().getRedChild().getOrangeChild() != null){		System.out.print("y_r_o : ");
		dt.getYellowChild().getRedChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getRedChild().getOrangeChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getRedChild().getOrangeChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getRedChild() != null && dt.getYellowChild().getRedChild().getYellowChild() != null){		System.out.print("y_r_y : ");
		dt.getYellowChild().getRedChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getRedChild().getYellowChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getRedChild().getYellowChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getRedChild() != null && dt.getYellowChild().getRedChild().getGreenChild() != null){		System.out.print("y_r_g : ");
		dt.getYellowChild().getRedChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getRedChild().getGreenChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getRedChild().getGreenChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getRedChild() != null && dt.getYellowChild().getRedChild().getBlueChild() != null){		System.out.print("y_r_b : ");
		dt.getYellowChild().getRedChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getRedChild().getBlueChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getRedChild().getBlueChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getRedChild() != null && dt.getYellowChild().getRedChild().getMagentaChild() != null){	System.out.print("y_r_m");
		dt.getYellowChild().getRedChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getRedChild().getMagentaChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getRedChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getYellowChild() != null && dt.getYellowChild().getOrangeChild() != null && dt.getYellowChild().getOrangeChild().getRedChild() != null){		System.out.print("y_o_r : ");
		dt.getYellowChild().getOrangeChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getOrangeChild().getRedChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getOrangeChild().getRedChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getOrangeChild() != null && dt.getYellowChild().getOrangeChild().getOrangeChild() != null){	System.out.print("y_o_o : ");
		dt.getYellowChild().getOrangeChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getOrangeChild().getOrangeChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getOrangeChild().getOrangeChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getOrangeChild() != null && dt.getYellowChild().getOrangeChild().getYellowChild() != null){	System.out.print("y_o_y : ");
		dt.getYellowChild().getOrangeChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getOrangeChild().getYellowChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getOrangeChild().getYellowChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getOrangeChild() != null && dt.getYellowChild().getOrangeChild().getGreenChild() != null){	System.out.print("y_o_g : ");
		dt.getYellowChild().getOrangeChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getOrangeChild().getGreenChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getOrangeChild().getGreenChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getOrangeChild() != null && dt.getYellowChild().getOrangeChild().getBlueChild() != null){		System.out.print("y_o_b : ");
		dt.getYellowChild().getOrangeChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getOrangeChild().getBlueChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getOrangeChild().getBlueChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getOrangeChild() != null && dt.getYellowChild().getOrangeChild().getMagentaChild() != null){	System.out.print("y_o_m");
		dt.getYellowChild().getOrangeChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getOrangeChild().getMagentaChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getOrangeChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getYellowChild() != null && dt.getYellowChild().getYellowChild() != null && dt.getYellowChild().getYellowChild().getRedChild() != null){		System.out.print("y_y_r : ");
		dt.getYellowChild().getYellowChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getYellowChild().getRedChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getYellowChild().getRedChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getYellowChild() != null && dt.getYellowChild().getYellowChild().getOrangeChild() != null){	System.out.print("y_y_o : ");
		dt.getYellowChild().getYellowChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getYellowChild().getOrangeChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getYellowChild().getOrangeChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getYellowChild() != null && dt.getYellowChild().getYellowChild().getYellowChild() != null){	System.out.print("y_y_y : ");
		dt.getYellowChild().getYellowChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getYellowChild().getYellowChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getYellowChild().getYellowChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getYellowChild() != null && dt.getYellowChild().getYellowChild().getGreenChild() != null){	System.out.print("y_y_g : ");
		dt.getYellowChild().getYellowChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getYellowChild().getGreenChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getYellowChild().getGreenChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getYellowChild() != null && dt.getYellowChild().getYellowChild().getBlueChild() != null){		System.out.print("y_y_b : ");
		dt.getYellowChild().getYellowChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getYellowChild().getBlueChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getYellowChild().getBlueChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getYellowChild() != null && dt.getYellowChild().getYellowChild().getMagentaChild() != null){	System.out.print("y_y_m");
		dt.getYellowChild().getYellowChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getYellowChild().getMagentaChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getYellowChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getYellowChild() != null && dt.getYellowChild().getGreenChild() != null && dt.getYellowChild().getGreenChild().getRedChild() != null){	System.out.print("y_g_r : ");
		dt.getYellowChild().getGreenChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getGreenChild().getRedChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getGreenChild().getRedChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getGreenChild() != null && dt.getYellowChild().getGreenChild().getOrangeChild() != null){	System.out.print("y_g_o : ");
		dt.getYellowChild().getGreenChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getGreenChild().getOrangeChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getGreenChild().getOrangeChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getGreenChild() != null && dt.getYellowChild().getGreenChild().getYellowChild() != null){	System.out.print("y_g_y : ");
		dt.getYellowChild().getGreenChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getGreenChild().getYellowChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getGreenChild().getYellowChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getGreenChild() != null && dt.getYellowChild().getGreenChild().getGreenChild() != null){	System.out.print("y_g_g : ");
		dt.getYellowChild().getGreenChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getGreenChild().getGreenChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getGreenChild().getGreenChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getGreenChild() != null && dt.getYellowChild().getGreenChild().getBlueChild() != null){	System.out.print("y_g_b : ");
		dt.getYellowChild().getGreenChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getGreenChild().getBlueChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getGreenChild().getBlueChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getGreenChild() != null && dt.getYellowChild().getGreenChild().getMagentaChild() != null){System.out.print("y_g_m");
		dt.getYellowChild().getGreenChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getGreenChild().getMagentaChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getGreenChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getYellowChild() != null && dt.getYellowChild().getBlueChild() != null && dt.getYellowChild().getBlueChild().getRedChild() != null){		System.out.print("y_b_r : ");
		dt.getYellowChild().getBlueChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getBlueChild().getRedChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getBlueChild().getRedChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getBlueChild() != null && dt.getYellowChild().getBlueChild().getOrangeChild() != null){	System.out.print("y_b_o : ");
		dt.getYellowChild().getBlueChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getBlueChild().getOrangeChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getBlueChild().getOrangeChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getBlueChild() != null && dt.getYellowChild().getBlueChild().getYellowChild() != null){	System.out.print("y_b_y : ");
		dt.getYellowChild().getBlueChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getBlueChild().getYellowChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getBlueChild().getYellowChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getBlueChild() != null && dt.getYellowChild().getBlueChild().getGreenChild() != null){	System.out.print("y_b_g : ");
		dt.getYellowChild().getBlueChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getBlueChild().getGreenChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getBlueChild().getGreenChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getBlueChild() != null && dt.getYellowChild().getBlueChild().getBlueChild() != null){		System.out.print("y_b_b : ");
		dt.getYellowChild().getBlueChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getBlueChild().getBlueChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getBlueChild().getBlueChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getBlueChild() != null && dt.getYellowChild().getBlueChild().getMagentaChild() != null){	System.out.print("y_b_m");
		dt.getYellowChild().getBlueChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getBlueChild().getMagentaChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getBlueChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getYellowChild() != null && dt.getYellowChild().getMagentaChild() != null && dt.getYellowChild().getMagentaChild().getRedChild() != null){	System.out.print("y_m_r : ");
		dt.getYellowChild().getMagentaChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getMagentaChild().getRedChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getMagentaChild().getRedChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getMagentaChild() != null && dt.getYellowChild().getMagentaChild().getOrangeChild() != null){	System.out.print("y_m_o : ");
		dt.getYellowChild().getMagentaChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getMagentaChild().getOrangeChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getMagentaChild().getOrangeChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getMagentaChild() != null && dt.getYellowChild().getMagentaChild().getYellowChild() != null){	System.out.print("y_m_y : ");
		dt.getYellowChild().getMagentaChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getMagentaChild().getYellowChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getMagentaChild().getYellowChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getMagentaChild() != null && dt.getYellowChild().getMagentaChild().getGreenChild() != null){	System.out.print("y_m_g : ");
		dt.getYellowChild().getMagentaChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getMagentaChild().getGreenChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getMagentaChild().getGreenChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getMagentaChild() != null && dt.getYellowChild().getMagentaChild().getBlueChild() != null){	System.out.print("y_m_b : ");
		dt.getYellowChild().getMagentaChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getMagentaChild().getBlueChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getMagentaChild().getBlueChild().getGrid()));
		}
		if(dt.getYellowChild() != null && dt.getYellowChild().getMagentaChild() != null && dt.getYellowChild().getMagentaChild().getMagentaChild() != null){System.out.print("y_m_m");
		dt.getYellowChild().getMagentaChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getYellowChild().getMagentaChild().getMagentaChild()));
		scoreYellowMove.add(getScore(indicesPosi, dt.getYellowChild().getMagentaChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n___\n");

		if(dt.getGreenChild() != null && dt.getGreenChild().getRedChild() != null && dt.getGreenChild().getRedChild().getRedChild() != null){		System.out.print("g_r_r : ");
		dt.getGreenChild().getRedChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getRedChild().getRedChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getRedChild().getRedChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getRedChild() != null && dt.getGreenChild().getRedChild().getOrangeChild() != null){	System.out.print("g_r_o : ");
		dt.getGreenChild().getRedChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getRedChild().getOrangeChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getRedChild().getOrangeChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getRedChild() != null && dt.getGreenChild().getRedChild().getYellowChild() != null){	System.out.print("g_r_y : ");
		dt.getGreenChild().getRedChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getRedChild().getYellowChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getRedChild().getYellowChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getRedChild() != null && dt.getGreenChild().getRedChild().getGreenChild() != null){		System.out.print("g_r_g : ");
		dt.getGreenChild().getRedChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getRedChild().getGreenChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getRedChild().getGreenChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getRedChild() != null && dt.getGreenChild().getRedChild().getBlueChild() != null){		System.out.print("g_r_b : ");
		dt.getGreenChild().getRedChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getRedChild().getBlueChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getRedChild().getBlueChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getRedChild() != null && dt.getGreenChild().getRedChild().getMagentaChild() != null){	System.out.print("g_r_m");
		dt.getGreenChild().getRedChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getRedChild().getMagentaChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getRedChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getGreenChild() != null && dt.getGreenChild().getOrangeChild() != null && dt.getGreenChild().getOrangeChild().getRedChild() != null){		System.out.print("g_o_r : ");
		dt.getGreenChild().getOrangeChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getOrangeChild().getRedChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getOrangeChild().getRedChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getOrangeChild() != null && dt.getGreenChild().getOrangeChild().getOrangeChild() != null){	System.out.print("g_o_o : ");
		dt.getGreenChild().getOrangeChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getOrangeChild().getOrangeChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getOrangeChild().getOrangeChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getOrangeChild() != null && dt.getGreenChild().getOrangeChild().getYellowChild() != null){	System.out.print("g_o_y : ");
		dt.getGreenChild().getOrangeChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getOrangeChild().getYellowChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getOrangeChild().getYellowChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getOrangeChild() != null && dt.getGreenChild().getOrangeChild().getGreenChild() != null){	System.out.print("g_o_g : ");
		dt.getGreenChild().getOrangeChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getOrangeChild().getGreenChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getOrangeChild().getGreenChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getOrangeChild() != null && dt.getGreenChild().getOrangeChild().getBlueChild() != null){	System.out.print("g_o_b : ");
		dt.getGreenChild().getOrangeChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getOrangeChild().getBlueChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getOrangeChild().getBlueChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getOrangeChild() != null && dt.getGreenChild().getOrangeChild().getMagentaChild() != null){	System.out.print("g_o_m");
		dt.getGreenChild().getOrangeChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getOrangeChild().getMagentaChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getOrangeChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getGreenChild() != null && dt.getGreenChild().getYellowChild() != null && dt.getGreenChild().getYellowChild().getRedChild() != null){		System.out.print("g_y_r : ");
		dt.getGreenChild().getYellowChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getYellowChild().getRedChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getYellowChild().getRedChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getYellowChild() != null && dt.getGreenChild().getYellowChild().getOrangeChild() != null){	System.out.print("g_y_o : ");
		dt.getGreenChild().getYellowChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getYellowChild().getOrangeChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getYellowChild().getOrangeChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getYellowChild() != null && dt.getGreenChild().getYellowChild().getYellowChild() != null){	System.out.print("g_y_y : ");
		dt.getGreenChild().getYellowChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getYellowChild().getYellowChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getYellowChild().getYellowChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getYellowChild() != null && dt.getGreenChild().getYellowChild().getGreenChild() != null){	System.out.print("g_y_g : ");
		dt.getGreenChild().getYellowChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getYellowChild().getGreenChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getYellowChild().getGreenChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getYellowChild() != null && dt.getGreenChild().getYellowChild().getBlueChild() != null){	System.out.print("g_y_b : ");
		dt.getGreenChild().getYellowChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getYellowChild().getBlueChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getYellowChild().getBlueChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getYellowChild() != null && dt.getGreenChild().getYellowChild().getMagentaChild() != null){	System.out.print("g_y_m");
		dt.getGreenChild().getYellowChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getYellowChild().getMagentaChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getYellowChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getGreenChild() != null && dt.getGreenChild().getGreenChild() != null && dt.getGreenChild().getGreenChild().getRedChild() != null){	System.out.print("g_g_r : ");
		dt.getGreenChild().getGreenChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getGreenChild().getRedChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getGreenChild().getRedChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getGreenChild() != null && dt.getGreenChild().getGreenChild().getOrangeChild() != null){System.out.print("g_g_o : ");
		dt.getGreenChild().getGreenChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getGreenChild().getOrangeChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getGreenChild().getOrangeChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getGreenChild() != null && dt.getGreenChild().getGreenChild().getYellowChild() != null){System.out.print("g_g_y : ");
		dt.getGreenChild().getGreenChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getGreenChild().getYellowChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getGreenChild().getYellowChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getGreenChild() != null && dt.getGreenChild().getGreenChild().getGreenChild() != null){	System.out.print("g_g_g : ");
		dt.getGreenChild().getGreenChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getGreenChild().getGreenChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getGreenChild().getGreenChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getGreenChild() != null && dt.getGreenChild().getGreenChild().getBlueChild() != null){	System.out.print("g_g_b : ");
		dt.getGreenChild().getGreenChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getGreenChild().getBlueChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getGreenChild().getBlueChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getGreenChild() != null && dt.getGreenChild().getGreenChild().getMagentaChild() != null){System.out.print("g_g_m");
		dt.getGreenChild().getGreenChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getGreenChild().getMagentaChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getGreenChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getGreenChild() != null && dt.getGreenChild().getBlueChild() != null && dt.getGreenChild().getBlueChild().getRedChild() != null){		System.out.print("g_b_r : ");
		dt.getGreenChild().getBlueChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getBlueChild().getRedChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getBlueChild().getRedChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getBlueChild() != null && dt.getGreenChild().getBlueChild().getOrangeChild() != null){	System.out.print("g_b_o : ");
		dt.getGreenChild().getBlueChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getBlueChild().getOrangeChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getBlueChild().getOrangeChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getBlueChild() != null && dt.getGreenChild().getBlueChild().getYellowChild() != null){	System.out.print("g_b_y : ");
		dt.getGreenChild().getBlueChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getBlueChild().getYellowChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getBlueChild().getYellowChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getBlueChild() != null && dt.getGreenChild().getBlueChild().getGreenChild() != null){	System.out.print("g_b_g : ");
		dt.getGreenChild().getBlueChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getBlueChild().getGreenChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getBlueChild().getGreenChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getBlueChild() != null && dt.getGreenChild().getBlueChild().getBlueChild() != null){	System.out.print("g_b_b : ");
		dt.getGreenChild().getBlueChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getBlueChild().getBlueChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getBlueChild().getBlueChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getBlueChild() != null && dt.getGreenChild().getBlueChild().getMagentaChild() != null){	System.out.print("g_b_m");
		dt.getGreenChild().getBlueChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getBlueChild().getMagentaChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getBlueChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getGreenChild() != null && dt.getGreenChild().getMagentaChild() != null && dt.getGreenChild().getMagentaChild().getRedChild() != null){	System.out.print("g_m_r : ");
		dt.getGreenChild().getMagentaChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getMagentaChild().getRedChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getMagentaChild().getRedChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getMagentaChild() != null && dt.getGreenChild().getMagentaChild().getOrangeChild() != null){System.out.print("g_m_o : ");
		dt.getGreenChild().getMagentaChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getMagentaChild().getOrangeChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getMagentaChild().getOrangeChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getMagentaChild() != null && dt.getGreenChild().getMagentaChild().getYellowChild() != null){System.out.print("g_m_y : ");
		dt.getGreenChild().getMagentaChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getMagentaChild().getYellowChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getMagentaChild().getYellowChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getMagentaChild() != null && dt.getGreenChild().getMagentaChild().getGreenChild() != null){	System.out.print("g_m_g : ");
		dt.getGreenChild().getMagentaChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getMagentaChild().getGreenChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getMagentaChild().getGreenChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getMagentaChild() != null && dt.getGreenChild().getMagentaChild().getBlueChild() != null){	System.out.print("g_m_b : ");
		dt.getGreenChild().getMagentaChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getMagentaChild().getBlueChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getMagentaChild().getBlueChild().getGrid()));
		}
		if(dt.getGreenChild() != null && dt.getGreenChild().getMagentaChild() != null && dt.getGreenChild().getMagentaChild().getMagentaChild() != null){System.out.print("g_m_m");
		dt.getGreenChild().getMagentaChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getGreenChild().getMagentaChild().getMagentaChild()));
		scoreGreenMove.add(getScore(indicesPosi, dt.getGreenChild().getMagentaChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n___\n");

		if(dt.getBlueChild() != null && dt.getBlueChild().getRedChild() != null && dt.getBlueChild().getRedChild().getRedChild() != null){		System.out.print("b_r_r : ");
		dt.getBlueChild().getRedChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getRedChild().getRedChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getRedChild().getRedChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getRedChild() != null && dt.getBlueChild().getRedChild().getOrangeChild() != null){	System.out.print("b_r_o : ");
		dt.getBlueChild().getRedChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getRedChild().getOrangeChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getRedChild().getOrangeChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getRedChild() != null && dt.getBlueChild().getRedChild().getYellowChild() != null){	System.out.print("b_r_y : ");
		dt.getBlueChild().getRedChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getRedChild().getYellowChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getRedChild().getYellowChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getRedChild() != null && dt.getBlueChild().getRedChild().getGreenChild() != null){	System.out.print("b_r_g : ");
		dt.getBlueChild().getRedChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getRedChild().getGreenChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getRedChild().getGreenChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getRedChild() != null && dt.getBlueChild().getRedChild().getBlueChild() != null){		System.out.print("b_r_b : ");
		dt.getBlueChild().getRedChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getRedChild().getBlueChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getRedChild().getBlueChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getRedChild() != null && dt.getBlueChild().getRedChild().getMagentaChild() != null){	System.out.print("b_r_m");
		dt.getBlueChild().getRedChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getRedChild().getMagentaChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getRedChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getBlueChild() != null && dt.getBlueChild().getOrangeChild() != null && dt.getBlueChild().getOrangeChild().getRedChild() != null){	System.out.print("b_o_r : ");
		dt.getBlueChild().getOrangeChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getOrangeChild().getRedChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getOrangeChild().getRedChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getOrangeChild() != null && dt.getBlueChild().getOrangeChild().getOrangeChild() != null){	System.out.print("b_o_o : ");
		dt.getBlueChild().getOrangeChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getOrangeChild().getOrangeChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getOrangeChild().getOrangeChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getOrangeChild() != null && dt.getBlueChild().getOrangeChild().getYellowChild() != null){	System.out.print("b_o_y : ");
		dt.getBlueChild().getOrangeChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getOrangeChild().getYellowChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getOrangeChild().getYellowChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getOrangeChild() != null && dt.getBlueChild().getOrangeChild().getGreenChild() != null){	System.out.print("b_o_g : ");
		dt.getBlueChild().getOrangeChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getOrangeChild().getGreenChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getOrangeChild().getGreenChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getOrangeChild() != null && dt.getBlueChild().getOrangeChild().getBlueChild() != null){	System.out.print("b_o_b : ");
		dt.getBlueChild().getOrangeChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getOrangeChild().getBlueChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getOrangeChild().getBlueChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getOrangeChild() != null && dt.getBlueChild().getOrangeChild().getMagentaChild() != null){System.out.print("b_o_m");
		dt.getBlueChild().getOrangeChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getOrangeChild().getMagentaChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getOrangeChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getBlueChild() != null && dt.getBlueChild().getYellowChild() != null && dt.getBlueChild().getYellowChild().getRedChild() != null){	System.out.print("b_y_r : ");
		dt.getBlueChild().getYellowChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getYellowChild().getRedChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getYellowChild().getRedChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getYellowChild() != null && dt.getBlueChild().getYellowChild().getOrangeChild() != null){	System.out.print("b_y_o : ");
		dt.getBlueChild().getYellowChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getYellowChild().getOrangeChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getYellowChild().getOrangeChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getYellowChild() != null && dt.getBlueChild().getYellowChild().getYellowChild() != null){	System.out.print("b_y_y : ");
		dt.getBlueChild().getYellowChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getYellowChild().getYellowChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getYellowChild().getYellowChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getYellowChild() != null && dt.getBlueChild().getYellowChild().getGreenChild() != null){	System.out.print("b_y_g : ");
		dt.getBlueChild().getYellowChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getYellowChild().getGreenChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getYellowChild().getGreenChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getYellowChild() != null && dt.getBlueChild().getYellowChild().getBlueChild() != null){	System.out.print("b_y_b : ");
		dt.getBlueChild().getYellowChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getYellowChild().getBlueChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getYellowChild().getBlueChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getYellowChild() != null && dt.getBlueChild().getYellowChild().getMagentaChild() != null){System.out.print("b_y_m");
		dt.getBlueChild().getYellowChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getYellowChild().getMagentaChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getYellowChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getBlueChild() != null && dt.getBlueChild().getGreenChild() != null && dt.getBlueChild().getGreenChild().getRedChild() != null){		System.out.print("b_g_r : ");
		dt.getBlueChild().getGreenChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getGreenChild().getRedChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getGreenChild().getRedChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getGreenChild() != null && dt.getBlueChild().getGreenChild().getOrangeChild() != null){	System.out.print("b_g_o : ");
		dt.getBlueChild().getGreenChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getGreenChild().getOrangeChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getGreenChild().getOrangeChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getGreenChild() != null && dt.getBlueChild().getGreenChild().getYellowChild() != null){	System.out.print("b_g_y : ");
		dt.getBlueChild().getGreenChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getGreenChild().getYellowChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getGreenChild().getYellowChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getGreenChild() != null && dt.getBlueChild().getGreenChild().getGreenChild() != null){	System.out.print("b_g_g : ");
		dt.getBlueChild().getGreenChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getGreenChild().getGreenChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getGreenChild().getGreenChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getGreenChild() != null && dt.getBlueChild().getGreenChild().getBlueChild() != null){		System.out.print("b_g_b : ");
		dt.getBlueChild().getGreenChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getGreenChild().getBlueChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getGreenChild().getBlueChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getGreenChild() != null && dt.getBlueChild().getGreenChild().getMagentaChild() != null){	System.out.print("b_g_m");
		dt.getBlueChild().getGreenChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getGreenChild().getMagentaChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getGreenChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getBlueChild() != null && dt.getBlueChild().getBlueChild() != null && dt.getBlueChild().getBlueChild().getRedChild() != null){	System.out.println("b_b_r : ");
		dt.getBlueChild().getBlueChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getBlueChild().getRedChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getBlueChild().getRedChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getBlueChild() != null && dt.getBlueChild().getBlueChild().getOrangeChild() != null){	System.out.println("b_b_o : ");
		dt.getBlueChild().getBlueChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getBlueChild().getOrangeChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getBlueChild().getOrangeChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getBlueChild() != null && dt.getBlueChild().getBlueChild().getYellowChild() != null){	System.out.println("b_b_y : ");
		dt.getBlueChild().getBlueChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getBlueChild().getYellowChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getBlueChild().getYellowChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getBlueChild() != null && dt.getBlueChild().getBlueChild().getGreenChild() != null){	System.out.println("b_b_g : ");
		dt.getBlueChild().getBlueChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getBlueChild().getGreenChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getBlueChild().getGreenChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getBlueChild() != null && dt.getBlueChild().getBlueChild().getBlueChild() != null){	System.out.println("b_b_b : ");
		dt.getBlueChild().getBlueChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getBlueChild().getBlueChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getBlueChild().getBlueChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getBlueChild() != null && dt.getBlueChild().getBlueChild().getMagentaChild() != null){System.out.println("b_b_m");
		dt.getBlueChild().getBlueChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getBlueChild().getMagentaChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getBlueChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getBlueChild() != null && dt.getBlueChild().getMagentaChild() != null && dt.getBlueChild().getMagentaChild().getRedChild() != null){		System.out.print("b_m_r : ");
		dt.getBlueChild().getMagentaChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getMagentaChild().getRedChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getMagentaChild().getRedChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getMagentaChild() != null && dt.getBlueChild().getMagentaChild().getOrangeChild() != null){	System.out.print("b_m_o : ");
		dt.getBlueChild().getMagentaChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getMagentaChild().getOrangeChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getMagentaChild().getOrangeChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getMagentaChild() != null && dt.getBlueChild().getMagentaChild().getYellowChild() != null){	System.out.print("b_m_y : ");
		dt.getBlueChild().getMagentaChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getMagentaChild().getYellowChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getMagentaChild().getYellowChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getMagentaChild() != null && dt.getBlueChild().getMagentaChild().getGreenChild() != null){	System.out.print("b_m_g : ");
		dt.getBlueChild().getMagentaChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getMagentaChild().getGreenChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getMagentaChild().getGreenChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getMagentaChild() != null && dt.getBlueChild().getMagentaChild().getBlueChild() != null){		System.out.print("b_m_b : ");
		dt.getBlueChild().getMagentaChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getMagentaChild().getBlueChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getMagentaChild().getBlueChild().getGrid()));
		}
		if(dt.getBlueChild() != null && dt.getBlueChild().getMagentaChild() != null && dt.getBlueChild().getMagentaChild().getMagentaChild() != null){	System.out.print("b_m_m");
		dt.getBlueChild().getMagentaChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getBlueChild().getMagentaChild().getMagentaChild()));
		scoreBlueMove.add(getScore(indicesPosi, dt.getBlueChild().getMagentaChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n___\n");

		if(dt.getMagentaChild() != null && dt.getMagentaChild().getRedChild() != null && dt.getMagentaChild().getRedChild().getRedChild() != null){		System.out.print("m_r_r : ");
		dt.getMagentaChild().getRedChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getRedChild().getRedChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getRedChild().getRedChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getRedChild() != null && dt.getMagentaChild().getRedChild().getOrangeChild() != null){	System.out.print("m_r_o : ");
		dt.getMagentaChild().getRedChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getRedChild().getOrangeChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getRedChild().getOrangeChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getRedChild() != null && dt.getMagentaChild().getRedChild().getYellowChild() != null){	System.out.print("m_r_y : ");
		dt.getMagentaChild().getRedChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getRedChild().getYellowChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getRedChild().getYellowChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getRedChild() != null && dt.getMagentaChild().getRedChild().getGreenChild() != null){	System.out.print("m_r_g : ");
		dt.getMagentaChild().getRedChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getRedChild().getGreenChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getRedChild().getGreenChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getRedChild() != null && dt.getMagentaChild().getRedChild().getBlueChild() != null){	System.out.print("m_r_b : ");
		dt.getMagentaChild().getRedChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getRedChild().getBlueChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getRedChild().getBlueChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getRedChild() != null && dt.getMagentaChild().getRedChild().getMagentaChild() != null){	System.out.print("m_r_m");
		dt.getMagentaChild().getRedChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getRedChild().getMagentaChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getRedChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getMagentaChild() != null && dt.getMagentaChild().getOrangeChild() != null && dt.getMagentaChild().getOrangeChild().getRedChild() != null){	System.out.print("m_o_r : ");
		dt.getMagentaChild().getOrangeChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getOrangeChild().getRedChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getOrangeChild().getRedChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getOrangeChild() != null && dt.getMagentaChild().getOrangeChild().getOrangeChild() != null){System.out.print("m_o_o : ");
		dt.getMagentaChild().getOrangeChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getOrangeChild().getOrangeChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getOrangeChild().getOrangeChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getOrangeChild() != null && dt.getMagentaChild().getOrangeChild().getYellowChild() != null){System.out.print("m_o_y : ");
		dt.getMagentaChild().getOrangeChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getOrangeChild().getYellowChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getOrangeChild().getYellowChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getOrangeChild() != null && dt.getMagentaChild().getOrangeChild().getGreenChild() != null){	System.out.print("m_o_g : ");
		dt.getMagentaChild().getOrangeChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getOrangeChild().getGreenChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getOrangeChild().getGreenChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getOrangeChild() != null && dt.getMagentaChild().getOrangeChild().getBlueChild() != null){	System.out.print("m_o_b : ");
		dt.getMagentaChild().getOrangeChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getOrangeChild().getBlueChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getOrangeChild().getBlueChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getOrangeChild() != null && dt.getMagentaChild().getOrangeChild().getMagentaChild() != null){System.out.print("m_o_m");
		dt.getMagentaChild().getOrangeChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getOrangeChild().getMagentaChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getOrangeChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getMagentaChild() != null && dt.getMagentaChild().getYellowChild() != null && dt.getMagentaChild().getYellowChild().getRedChild() != null){	System.out.print("m_y_r : ");
		dt.getMagentaChild().getYellowChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getYellowChild().getRedChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getYellowChild().getRedChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getYellowChild() != null && dt.getMagentaChild().getYellowChild().getOrangeChild() != null){System.out.print("m_y_o : ");
		dt.getMagentaChild().getYellowChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getYellowChild().getOrangeChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getYellowChild().getOrangeChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getYellowChild() != null && dt.getMagentaChild().getYellowChild().getYellowChild() != null){System.out.print("m_y_y : ");
		dt.getMagentaChild().getYellowChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getYellowChild().getYellowChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getYellowChild().getYellowChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getYellowChild() != null && dt.getMagentaChild().getYellowChild().getGreenChild() != null){	System.out.print("m_y_g : ");
		dt.getMagentaChild().getYellowChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getYellowChild().getGreenChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getYellowChild().getGreenChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getYellowChild() != null && dt.getMagentaChild().getYellowChild().getBlueChild() != null){	System.out.print("m_y_b : ");
		dt.getMagentaChild().getYellowChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getYellowChild().getBlueChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getYellowChild().getBlueChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getYellowChild() != null && dt.getMagentaChild().getYellowChild().getMagentaChild() != null){System.out.print("m_y_m");
		dt.getMagentaChild().getYellowChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getYellowChild().getMagentaChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getYellowChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getMagentaChild() != null && dt.getMagentaChild().getGreenChild() != null && dt.getMagentaChild().getGreenChild().getRedChild() != null){		System.out.print("m_g_r : ");
		dt.getMagentaChild().getGreenChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getGreenChild().getRedChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getGreenChild().getRedChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getGreenChild() != null && dt.getMagentaChild().getGreenChild().getOrangeChild() != null){	System.out.print("m_g_o : ");
		dt.getMagentaChild().getGreenChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getGreenChild().getOrangeChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getGreenChild().getOrangeChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getGreenChild() != null && dt.getMagentaChild().getGreenChild().getYellowChild() != null){	System.out.print("m_g_y : ");
		dt.getMagentaChild().getGreenChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getGreenChild().getYellowChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getGreenChild().getYellowChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getGreenChild() != null && dt.getMagentaChild().getGreenChild().getGreenChild() != null){	System.out.print("m_g_g : ");
		dt.getMagentaChild().getGreenChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getGreenChild().getGreenChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getGreenChild().getGreenChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getGreenChild() != null && dt.getMagentaChild().getGreenChild().getBlueChild() != null){	System.out.print("m_g_b : ");
		dt.getMagentaChild().getGreenChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getGreenChild().getBlueChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getGreenChild().getBlueChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getGreenChild() != null && dt.getMagentaChild().getGreenChild().getMagentaChild() != null){	System.out.print("m_g_m");
		dt.getMagentaChild().getGreenChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getGreenChild().getMagentaChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getGreenChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getMagentaChild() != null && dt.getMagentaChild().getBlueChild() != null && dt.getMagentaChild().getBlueChild().getRedChild() != null){		System.out.print("m_b_r : ");
		dt.getMagentaChild().getBlueChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getBlueChild().getRedChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getBlueChild().getRedChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getBlueChild() != null && dt.getMagentaChild().getBlueChild().getOrangeChild() != null){	System.out.print("m_b_o : ");
		dt.getMagentaChild().getBlueChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getBlueChild().getOrangeChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getBlueChild().getOrangeChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getBlueChild() != null && dt.getMagentaChild().getBlueChild().getYellowChild() != null){	System.out.print("m_b_y : ");
		dt.getMagentaChild().getBlueChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getBlueChild().getYellowChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getBlueChild().getYellowChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getBlueChild() != null && dt.getMagentaChild().getBlueChild().getGreenChild() != null){		System.out.print("m_b_g : ");
		dt.getMagentaChild().getBlueChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getBlueChild().getGreenChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getBlueChild().getGreenChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getBlueChild() != null && dt.getMagentaChild().getBlueChild().getBlueChild() != null){		System.out.print("m_b_b : ");
		dt.getMagentaChild().getBlueChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getBlueChild().getBlueChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getBlueChild().getBlueChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getBlueChild() != null && dt.getMagentaChild().getBlueChild().getMagentaChild() != null){	System.out.print("m_b_m");
		dt.getMagentaChild().getBlueChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getBlueChild().getMagentaChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getBlueChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n_\n");

		if(dt.getMagentaChild() != null && dt.getMagentaChild().getMagentaChild() != null && dt.getMagentaChild().getMagentaChild().getRedChild() != null){		System.out.print("m_m_r : ");
		dt.getMagentaChild().getMagentaChild().setRedChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getMagentaChild().getRedChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getMagentaChild().getRedChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getMagentaChild() != null && dt.getMagentaChild().getMagentaChild().getOrangeChild() != null){	System.out.print("m_m_o : ");
		dt.getMagentaChild().getMagentaChild().setOrangeChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getMagentaChild().getOrangeChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getMagentaChild().getOrangeChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getMagentaChild() != null && dt.getMagentaChild().getMagentaChild().getYellowChild() != null){	System.out.print("m_m_y : ");
		dt.getMagentaChild().getMagentaChild().setYellowChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getMagentaChild().getYellowChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getMagentaChild().getYellowChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getMagentaChild() != null && dt.getMagentaChild().getMagentaChild().getGreenChild() != null){	System.out.print("m_m_g : ");
		dt.getMagentaChild().getMagentaChild().setGreenChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getMagentaChild().getGreenChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getMagentaChild().getGreenChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getMagentaChild() != null && dt.getMagentaChild().getMagentaChild().getBlueChild() != null){	System.out.print("m_m_b : ");
		dt.getMagentaChild().getMagentaChild().setBlueChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getMagentaChild().getBlueChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getMagentaChild().getBlueChild().getGrid()));
		}
		if(dt.getMagentaChild() != null && dt.getMagentaChild().getMagentaChild() != null && dt.getMagentaChild().getMagentaChild().getMagentaChild() != null){	System.out.print("m_m_m");
		dt.getMagentaChild().getMagentaChild().setMagentaChild(oneStepDecisionTreeGeneration(indicesPosi[0], dt.getMagentaChild().getMagentaChild().getMagentaChild()));
		scoreMagentaMove.add(getScore(indicesPosi, dt.getMagentaChild().getMagentaChild().getMagentaChild().getGrid()));
		}

		System.out.print("\n___\n");
		
		int redScore = 0;
		if(!couleursNonDispo.contains(Color.red)) {
			
			for(Integer score : scoreRedMove){ redScore += score; }
			if(scoreRedMove.size() > 0) redScore /= scoreRedMove.size();
		}else{
			redScore = -(original.length * original.length);
		}																				System.out.println("Score redMove : "+redScore);
		
		int orangeScore = 0;
		if(!couleursNonDispo.contains(Color.orange)) {
			
			for(Integer score : scoreOrangeMove){ orangeScore += score; }
			if(scoreOrangeMove.size() > 0) orangeScore /= scoreOrangeMove.size();
		}else{
			orangeScore = -(original.length * original.length);
		}																				System.out.println("Score orangeMove : "+orangeScore);
		
		int yellowScore = 0;
		if(!couleursNonDispo.contains(Color.yellow)) {
			
			for(Integer score : scoreYellowMove){ yellowScore += score; }
			if(scoreYellowMove.size() > 0) yellowScore /= scoreYellowMove.size();				
		}else{
			yellowScore = -(original.length * original.length);
		}																				System.out.println("Score yellowMove : "+yellowScore);
		
		int greenScore = 0;
		if(!couleursNonDispo.contains(Color.green)) {
			
			for(Integer score : scoreGreenMove){ greenScore += score; }
			if(scoreGreenMove.size() > 0) greenScore /= scoreGreenMove.size();					
		}else{
			greenScore = -(original.length * original.length);
		}																				System.out.println("Score greenMove : "+greenScore);
		
		int blueScore = 0;
		if(!couleursNonDispo.contains(Color.blue)) {
			
			for(Integer score : scoreBlueMove){ blueScore += score; }
			if(scoreBlueMove.size() > 0) blueScore /= scoreBlueMove.size();						
		}else{
			blueScore = -(original.length * original.length);
		}																				System.out.println("Score blueMove : "+blueScore);
		
		int magentaScore = 0;
		if(!couleursNonDispo.contains(Color.magenta)) {
			
			for(Integer score : scoreMagentaMove){ magentaScore += score; }
			if(scoreMagentaMove.size() > 0) magentaScore /= scoreMagentaMove.size();			
		}else{
			magentaScore = -(original.length * original.length);
		}																				System.out.println("Score magentaMove : "+magentaScore);
		
		char bestMove = ' ';
		
		if(redScore >= orangeScore && redScore >= yellowScore && redScore >= greenScore && redScore >= blueScore && redScore >= magentaScore){
			bestMove = 'R';
		}
		if(orangeScore >= redScore && orangeScore >= yellowScore && orangeScore >= greenScore && orangeScore >= blueScore && orangeScore >= magentaScore){
			bestMove = 'O';
		}
		if(yellowScore >= redScore && yellowScore >= orangeScore && yellowScore >= greenScore && yellowScore >= blueScore && yellowScore >= magentaScore){
			bestMove = 'J';
		}
		if(greenScore >= redScore && greenScore >= orangeScore && greenScore >= yellowScore && greenScore >= blueScore && greenScore >= magentaScore){
			bestMove = 'V';
		}
		if(blueScore >= redScore && blueScore >= orangeScore && blueScore >= yellowScore && blueScore >= greenScore && blueScore >= magentaScore){
			bestMove = 'B';
		}
		if(magentaScore >= redScore && magentaScore >= orangeScore && magentaScore >= yellowScore && magentaScore >= greenScore && magentaScore >= blueScore){
			bestMove = 'I';
		}

		return bestMove;
	}

	public static DecisionTree oneStepDecisionTreeGeneration(int indiceJoueurAct, DecisionTree dt){

		SquareCell[][] clone = dt.getGrid();

		ArrayList<Color> couleursDispo = getCouleursDispo(clone);

		for(Color itemColor : couleursDispo){

			SquareCell[][] cloneBis = cloneGrid(clone);

			ArrayList<Cell> squaresCtrlJ1 = new ArrayList<Cell>();	squaresCtrlJ1.add(cloneBis[0][0]);
			ArrayList<Cell> squaresCtrlJ2 = new ArrayList<Cell>();	squaresCtrlJ2.add(cloneBis[cloneBis.length-1][cloneBis.length-1]);
			
			squaresCtrlJ1 = getConnectedCellsOfSameColor(squaresCtrlJ1);
			squaresCtrlJ2 = getConnectedCellsOfSameColor(squaresCtrlJ2);
			
			ArrayList<Cell> squareCtrl = null;
			
			if(indiceJoueurAct == 1){ squareCtrl = squaresCtrlJ1; }
			if(indiceJoueurAct == 2){ squareCtrl = squaresCtrlJ2; }

			for(int i = 0; i < squareCtrl.size(); i++){
				squareCtrl.get(i).setColor(itemColor);
			}

			squareCtrl = getConnectedCellsOfSameColor(squareCtrl);

			for(Cell square : squareCtrl){
				if(indiceJoueurAct == 1) { square.setCtrlBy("1"); }
				if(indiceJoueurAct == 2) { square.setCtrlBy("2"); }
			}

			if		(itemColor == Color.red) 	{ dt.setRedChild(		new DecisionTree(cloneGrid(cloneBis)));	}
			else if	(itemColor == Color.orange) { dt.setOrangeChild(	new DecisionTree(cloneGrid(cloneBis)));	}
			else if	(itemColor == Color.yellow) { dt.setYellowChild(	new DecisionTree(cloneGrid(cloneBis)));	}
			else if	(itemColor == Color.green) 	{ dt.setGreenChild(		new DecisionTree(cloneGrid(cloneBis)));	}
			else if	(itemColor == Color.blue) 	{ dt.setBlueChild(		new DecisionTree(cloneGrid(cloneBis)));	}
			else if	(itemColor == Color.magenta){ dt.setMagentaChild(	new DecisionTree(cloneGrid(cloneBis)));	}
		}

		return dt;
	}

	/*
	public static DecisionTree decisionTreeGeneration(DecisionTree dt, int compt){

		compt++;	System.out.println("Compt : "+compt);

		Cell[][] clone = dt.getGrid();

		ArrayList<Color> couleursDispo = clone.getFreeColors();

		for(Color itemColor : couleursDispo){

			Cell[][] cloneBis = clone.clone();

			int nbJoueurs = 2;

			Player joueurAct = null;
			if		(cloneBis.getJoueur1().isMyTurn())									{ joueurAct = cloneBis.getJoueur1(); }
			else if	(cloneBis.getJoueur2().isMyTurn())									{ joueurAct = cloneBis.getJoueur2(); }
			else if	(cloneBis.getJoueur3() != null && cloneBis.getJoueur3().isMyTurn())	{ joueurAct = cloneBis.getJoueur3(); nbJoueurs++; }
			else if	(cloneBis.getJoueur4() != null && cloneBis.getJoueur4().isMyTurn())	{ joueurAct = cloneBis.getJoueur4(); nbJoueurs++; }

			ArrayList<Cell> hexasCtrl = joueurAct.getCasesCtrl();

			for(int i = 0; i < hexasCtrl.size(); i++){
				hexasCtrl.get(i).setColor(itemColor);
			}
			joueurAct.setCouleur(itemColor);

			hexasCtrl = HexaBoard.getConnectedCellsOfSameColor(hexasCtrl);


			joueurAct.setCasesCtrl(hexasCtrl);
			for(Cell hexa : hexasCtrl){
				hexa.setCtrlBy(joueurAct.getNom());
			}

			if(hexasCtrl.size() > (cloneBis.getGrille().length/nbJoueurs)){
				joueurAct.setIsWinner(true);
				return dt;
			}

			joueurAct.setMyTurn(false);

			Player joueurSui = null;

			if(joueurAct == cloneBis.getJoueur1())	{ joueurSui = cloneBis.getJoueur2(); }
			else if (joueurAct == cloneBis.getJoueur2()){
				if(cloneBis.getJoueur3() != null)	{ joueurSui = cloneBis.getJoueur3(); }
				else							{ joueurSui = cloneBis.getJoueur1(); }
			}
			else if(cloneBis.getJoueur3() != null && joueurAct == cloneBis.getJoueur3()){
				if(cloneBis.getJoueur4() != null)	{ joueurSui = cloneBis.getJoueur4(); }
				else							{ joueurSui = cloneBis.getJoueur1(); }
			}
			else if(cloneBis.getJoueur4() != null && joueurAct == cloneBis.getJoueur4()){

				joueurSui = cloneBis.getJoueur1();
			}

			joueurSui.setMyTurn(true);

			if(itemColor == Color.red) 			{ dt.setRedChild(		new DecisionTree(cloneBis));	}
			else if(itemColor == Color.orange) 	{ dt.setOrangeChild(	new DecisionTree(cloneBis));	}
			else if(itemColor == Color.yellow) 	{ dt.setYellowChild(	new DecisionTree(cloneBis));	}
			else if(itemColor == Color.green) 	{ dt.setGreenChild(		new DecisionTree(cloneBis));	}
			else if(itemColor == Color.blue) 	{ dt.setBlueChild(		new DecisionTree(cloneBis));	}
			else if(itemColor == Color.magenta) { dt.setMagentaChild(	new DecisionTree(cloneBis));	}

			if(itemColor == Color.red) 			{ decisionTreeGeneration(dt.getRedChild(), compt);		}
			else if(itemColor == Color.orange) 	{ decisionTreeGeneration(dt.getOrangeChild(), compt);	}	
			else if(itemColor == Color.yellow) 	{ decisionTreeGeneration(dt.getYellowChild(), compt);	}
			else if(itemColor == Color.green) 	{ decisionTreeGeneration(dt.getGreenChild(), compt);	}
			else if(itemColor == Color.blue) 	{ decisionTreeGeneration(dt.getBlueChild(), compt);		}
			else if(itemColor == Color.magenta)	{ decisionTreeGeneration(dt.getMagentaChild(), compt);	}
		}

		return dt;
	}
	*/
	
	public static SquareCell[][] cloneGrid(SquareCell[][] original){

		SquareCell[][] clone = new SquareCell[original.length][original[0].length];

		for(int i = 0; i < clone.length; i++){
			for(int j = 0; j < clone[0].length; j++){

				Color colorGrid = null;
				Color colorGrille = original[i][j].getColor();
				if		(colorGrille == Color.red) 		colorGrid = Color.red;
				else if	(colorGrille == Color.orange) 	colorGrid = Color.orange;
				else if	(colorGrille == Color.yellow) 	colorGrid = Color.yellow;
				else if	(colorGrille == Color.green) 	colorGrid = Color.green;
				else if	(colorGrille == Color.blue) 	colorGrid = Color.blue;
				else if	(colorGrille == Color.magenta) 	colorGrid = Color.magenta;

				String ctrlBy = ""+original[i][j].getCtrlBy();

				int centreX = original[i][j].getCentreX();
				int centreY = original[i][j].getCentreY();

				clone[i][j] = new SquareCell(centreX, centreY, colorGrid, null, null, null, null);

				clone[i][j].setCtrlBy(ctrlBy);
			}
		}

		clone = defVoisins(clone);

		return clone;
	}
	
	private static SquareCell[][] extractGridFromGame(Game game){
		
		Grid gridToExtract = game.getGrid();
		
		SquareCell[][] grid = new SquareCell[gridToExtract.getGrid().length][gridToExtract.getGrid().length];
		
		int decalageX = 0;
		int margeY = 120;
		
		Color color = null;
		char c = ' ';
		
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				//(int centreX, int centreY, Color color, SquareCell voisinHaut, SquareCell voisinDroite, SquareCell voisinBas, SquareCell voisinGauche){
				
				c = gridToExtract.getCell(i, j).getColor();
				
				if		(c == 'R'){ color = Color.red; 		}
				else if	(c == 'O'){ color = Color.orange; 	}
				else if	(c == 'J'){ color = Color.yellow; 	}
				else if	(c == 'V'){ color = Color.green; 	}
				else if	(c == 'B'){ color = Color.blue; 	}
				else if	(c == 'I'){ color = Color.magenta;	}
				
				grid[i][j] = new SquareCell(30+i*20+decalageX, 30+j*20+margeY, color, null, null, null, null);
				
				System.out.print(c+" ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
		
		grid = defVoisins(grid);
		
		ArrayList<Cell> squares1 = new ArrayList<Cell>();
		ArrayList<Cell> squares2 = new ArrayList<Cell>();
		
		squares1.add(grid[0][0]);
		squares2.add(grid[grid.length-1][grid.length-1]);
		
		squares1 = getConnectedCellsOfSameColor(squares1);
		squares2 = getConnectedCellsOfSameColor(squares2);
		
		for(Cell cell : squares1){ cell.setCtrlBy("1"); }
		for(Cell cell : squares2){ cell.setCtrlBy("2"); }
		
		return grid;
	}
	
	private static SquareCell[][] defVoisins(SquareCell[][] grille){
		
		for(int i = 0; i < grille.length; i++){
			for(int j = 0; j < grille.length; j++){
				
					try{ grille[i][j].setVoisinHaut(grille[i][j-1]);	}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinHaut(null); 	}
					try{ grille[i][j].setVoisinBas(grille[i][j+1]); 	}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinBas(null); 		}
					try{ grille[i][j].setVoisinDroite(grille[i+1][j]);	}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinDroite(null); 	}
					try{ grille[i][j].setVoisinGauche(grille[i-1][j]);	}catch(ArrayIndexOutOfBoundsException e){ grille[i][j].setVoisinGauche(null); 	}
			}
		}
		return grille;
	}
	
	public static ArrayList<Cell> getConnectedCellsOfSameColor(ArrayList<Cell> listeIni){
		
		@SuppressWarnings("unchecked")
		ArrayList<Cell> liste = (ArrayList<Cell>) listeIni.clone();
		
		boolean add = false;
		
		for(int i = 0; i < liste.size(); i++){
			
			SquareCell square = (SquareCell) liste.get(i);
			
			if( square.getVoisinDroite() != null && !liste.contains(square.getVoisinDroite()) ){
				if( square.getVoisinDroite().getColor().getRGB() == square.getColor().getRGB()){
					
					liste.add(square.getVoisinDroite());	add = true;
				}
			}
			if( square.getVoisinHaut() != null && !liste.contains(square.getVoisinHaut()) ){
				if( square.getVoisinHaut().getColor().getRGB() == square.getColor().getRGB()){
					
					liste.add(square.getVoisinHaut());		add = true;
				}
			}
			if( square.getVoisinGauche() != null && !liste.contains(square.getVoisinGauche()) ){
				if( square.getVoisinGauche().getColor().getRGB() == square.getColor().getRGB()){
					
					liste.add(square.getVoisinGauche());	add = true;
				}
			}
			if(square.getVoisinBas() != null && !liste.contains(square.getVoisinBas()) ){
				if( square.getVoisinBas().getColor().getRGB() == square.getColor().getRGB()){
					
					liste.add(square.getVoisinBas());	add = true;
				}
			}
		}
		if(add == false){	return liste;	}	// Condition de sortie de la récursion
		
		return getConnectedCellsOfSameColor(liste);
	}
	
	private static int getScore(int[] indicesPosi, SquareCell[][] grid){ // score = scoreIA - scoreIAadvr
		
		int score = 0;
		
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				
				if		(grid[i][j].getCtrlBy().equals(""+indicesPosi[0])){ score++; }
				else if	(grid[i][j].getCtrlBy().equals(""+indicesPosi[1])){ score--; }
			}
		}
		
		return score;
	}
	
	private static ArrayList<Color> getCouleursDispo(SquareCell[][] grid){
		
		ArrayList<Color> couleursDispo = new ArrayList<Color>();
		
		Color colorJ1 = grid[0][0].getColor();
		Color colorJ2 = grid[grid.length-1][grid.length-1].getColor();
		
		if(colorJ1 != Color.red 	&& colorJ2 != Color.red)		couleursDispo.add(Color.red);
		if(colorJ1 != Color.orange 	&& colorJ2 != Color.orange)		couleursDispo.add(Color.orange);
		if(colorJ1 != Color.yellow 	&& colorJ2 != Color.yellow)		couleursDispo.add(Color.yellow);
		if(colorJ1 != Color.green 	&& colorJ2 != Color.green)		couleursDispo.add(Color.green);
		if(colorJ1 != Color.blue 	&& colorJ2 != Color.blue)		couleursDispo.add(Color.blue);
		if(colorJ1 != Color.magenta && colorJ2 != Color.magenta)	couleursDispo.add(Color.magenta);
		
		return couleursDispo;
	}
}
