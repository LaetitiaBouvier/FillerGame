import java.awt.Color;
import java.util.ArrayList;

public class IAcontest {

	public static Color nextHexaIAContestMove(HexaBoard original){
		
		//TODO
		
				HexaBoard clone = original.clone();
				DecisionTree dt = new DecisionTree(clone);
				
				//Initialisation :
				dt = oneStepDecisionTreeGeneration(dt);
				
				//________________________________
				if(dt.getRedChild() 	!= null) { dt.setRedChild(oneStepDecisionTreeGeneration		(dt.getRedChild()));	System.out.print("r : "); }
				if(dt.getOrangeChild() 	!= null) { dt.setOrangeChild(oneStepDecisionTreeGeneration	(dt.getOrangeChild()));	System.out.print("o : "); }
				if(dt.getYellowChild() 	!= null) { dt.setYellowChild(oneStepDecisionTreeGeneration	(dt.getYellowChild()));	System.out.print("y : "); }
				if(dt.getGreenChild() 	!= null) { dt.setGreenChild(oneStepDecisionTreeGeneration	(dt.getGreenChild()));	System.out.print("g : "); }
				if(dt.getBlueChild() 	!= null) { dt.setBlueChild(oneStepDecisionTreeGeneration	(dt.getBlueChild()));	System.out.print("b : "); }
				if(dt.getMagentaChild() != null) { dt.setMagentaChild(oneStepDecisionTreeGeneration	(dt.getMagentaChild()));System.out.print("m"); }
				
				System.out.print("\n________________\n");
				
				//________________________________
				if(dt.getRedChild() 	!= null && dt.getRedChild().getRedChild()		!= null){							System.out.print("r_r : ");
					dt.getRedChild().setRedChild( oneStepDecisionTreeGeneration(dt.getRedChild().getRedChild()) ); }
				if(dt.getRedChild() 	!= null && dt.getRedChild().getOrangeChild()	!= null){							System.out.print("r_o : ");
					dt.getRedChild().setOrangeChild( oneStepDecisionTreeGeneration(dt.getRedChild().getOrangeChild()) ); }
				if(dt.getRedChild() 	!= null && dt.getRedChild().getYellowChild()	!= null){							System.out.print("r_y : ");
					dt.getRedChild().setYellowChild( oneStepDecisionTreeGeneration(dt.getRedChild().getYellowChild()) ); }
				if(dt.getRedChild() 	!= null && dt.getRedChild().getGreenChild()		!= null){							System.out.print("r_g : ");
					dt.getRedChild().setGreenChild( oneStepDecisionTreeGeneration(dt.getRedChild().getGreenChild()) ); }
				if(dt.getRedChild() 	!= null && dt.getRedChild().getBlueChild()		!= null){							System.out.print("r_b : ");
					dt.getRedChild().setBlueChild( oneStepDecisionTreeGeneration(dt.getRedChild().getBlueChild()) ); }
				if(dt.getRedChild() 	!= null && dt.getRedChild().getMagentaChild()	!= null){							System.out.print("r_m");
					dt.getRedChild().setMagentaChild( oneStepDecisionTreeGeneration(dt.getRedChild().getMagentaChild()) ); }
				
				System.out.print("\n___\n");
				
				if(dt.getOrangeChild() 	!= null && dt.getOrangeChild().getRedChild()	!= null){							System.out.print("o_r : ");
					dt.getOrangeChild().setRedChild( oneStepDecisionTreeGeneration(dt.getOrangeChild().getRedChild()) ); }
				if(dt.getOrangeChild() 	!= null && dt.getOrangeChild().getOrangeChild()	!= null){							System.out.print("o_o : ");
					dt.getOrangeChild().setOrangeChild( oneStepDecisionTreeGeneration(dt.getOrangeChild().getOrangeChild()) ); }
				if(dt.getOrangeChild() 	!= null && dt.getOrangeChild().getYellowChild()	!= null){							System.out.print("o_y : ");
					dt.getOrangeChild().setYellowChild( oneStepDecisionTreeGeneration(dt.getOrangeChild().getYellowChild()) ); }
				if(dt.getOrangeChild() 	!= null && dt.getOrangeChild().getGreenChild()	!= null){							System.out.print("o_g : ");
					dt.getOrangeChild().setGreenChild( oneStepDecisionTreeGeneration(dt.getOrangeChild().getGreenChild()) ); }
				if(dt.getOrangeChild() 	!= null && dt.getOrangeChild().getBlueChild()	!= null){							System.out.print("o_b : ");
					dt.getOrangeChild().setBlueChild( oneStepDecisionTreeGeneration(dt.getOrangeChild().getBlueChild()) ); }
				if(dt.getOrangeChild() 	!= null && dt.getOrangeChild().getMagentaChild()!= null){							System.out.print("o_m");
					dt.getOrangeChild().setMagentaChild( oneStepDecisionTreeGeneration(dt.getOrangeChild().getMagentaChild()) ); }
				
				System.out.print("\n___\n");
				
				if(dt.getYellowChild() 	!= null && dt.getYellowChild().getRedChild()	!= null){							System.out.print("y_r : ");
					dt.getYellowChild().setRedChild( oneStepDecisionTreeGeneration(dt.getYellowChild().getRedChild()) ); }
				if(dt.getYellowChild() 	!= null && dt.getYellowChild().getOrangeChild()	!= null){							System.out.print("y_o : ");
					dt.getYellowChild().setOrangeChild( oneStepDecisionTreeGeneration(dt.getYellowChild().getOrangeChild()) ); }
				if(dt.getYellowChild() 	!= null && dt.getYellowChild().getYellowChild()	!= null){							System.out.print("y_y : ");
					dt.getYellowChild().setYellowChild( oneStepDecisionTreeGeneration(dt.getYellowChild().getYellowChild()) ); }
				if(dt.getYellowChild() 	!= null && dt.getYellowChild().getGreenChild()	!= null){							System.out.print("y_g : ");
					dt.getYellowChild().setGreenChild( oneStepDecisionTreeGeneration(dt.getYellowChild().getGreenChild()) ); }
				if(dt.getYellowChild() 	!= null && dt.getYellowChild().getBlueChild()	!= null){							System.out.print("y_b : ");
					dt.getYellowChild().setBlueChild( oneStepDecisionTreeGeneration(dt.getYellowChild().getBlueChild()) ); }
				if(dt.getYellowChild() 	!= null && dt.getYellowChild().getMagentaChild()!= null){							System.out.print("y_m");
					dt.getYellowChild().setMagentaChild( oneStepDecisionTreeGeneration(dt.getYellowChild().getMagentaChild()) ); }
				
				System.out.print("\n___\n");
				
				if(dt.getGreenChild() 	!= null && dt.getGreenChild().getRedChild()	!= null){								System.out.print("g_r : ");
					dt.getGreenChild().setRedChild( oneStepDecisionTreeGeneration(dt.getGreenChild().getRedChild()) ); }
				if(dt.getGreenChild() 	!= null && dt.getGreenChild().getOrangeChild()	!= null){							System.out.print("g_o : ");
					dt.getGreenChild().setOrangeChild( oneStepDecisionTreeGeneration(dt.getGreenChild().getOrangeChild()) ); }
				if(dt.getGreenChild() 	!= null && dt.getGreenChild().getYellowChild()	!= null){							System.out.print("g_y : ");
					dt.getGreenChild().setYellowChild( oneStepDecisionTreeGeneration(dt.getGreenChild().getYellowChild()) ); }
				if(dt.getGreenChild() 	!= null && dt.getGreenChild().getGreenChild()	!= null){							System.out.print("g_g : ");
					dt.getGreenChild().setGreenChild( oneStepDecisionTreeGeneration(dt.getGreenChild().getGreenChild()) ); }
				if(dt.getGreenChild() 	!= null && dt.getGreenChild().getBlueChild()	!= null){							System.out.print("g_b : ");
					dt.getGreenChild().setBlueChild( oneStepDecisionTreeGeneration(dt.getGreenChild().getBlueChild()) ); }
				if(dt.getGreenChild() 	!= null && dt.getGreenChild().getMagentaChild()	!= null){							System.out.print("g_m");
					dt.getGreenChild().setMagentaChild( oneStepDecisionTreeGeneration(dt.getGreenChild().getMagentaChild()) ); }
				
				System.out.print("\n___\n");
				
				if(dt.getBlueChild() 	!= null && dt.getBlueChild().getRedChild()		!= null){							System.out.print("b_r : ");
					dt.getBlueChild().setRedChild( oneStepDecisionTreeGeneration(dt.getBlueChild().getRedChild()) ); }
				if(dt.getBlueChild() 	!= null && dt.getBlueChild().getOrangeChild()	!= null){							System.out.print("b_o : ");
					dt.getBlueChild().setOrangeChild( oneStepDecisionTreeGeneration(dt.getBlueChild().getOrangeChild()) ); }
				if(dt.getBlueChild() 	!= null && dt.getBlueChild().getYellowChild()	!= null){							System.out.print("b_y : ");
					dt.getBlueChild().setYellowChild( oneStepDecisionTreeGeneration(dt.getBlueChild().getYellowChild()) ); }
				if(dt.getBlueChild() 	!= null && dt.getBlueChild().getGreenChild()	!= null){							System.out.print("b_g : ");
					dt.getBlueChild().setGreenChild( oneStepDecisionTreeGeneration(dt.getBlueChild().getGreenChild()) ); }
				if(dt.getBlueChild() 	!= null && dt.getBlueChild().getBlueChild()		!= null){							System.out.print("b_b : ");
					dt.getBlueChild().setBlueChild( oneStepDecisionTreeGeneration(dt.getBlueChild().getBlueChild()) ); }
				if(dt.getBlueChild() 	!= null && dt.getBlueChild().getMagentaChild()	!= null){							System.out.print("b_m");
					dt.getBlueChild().setMagentaChild( oneStepDecisionTreeGeneration(dt.getBlueChild().getMagentaChild()) ); }
				
				System.out.print("\n___\n");
				
				if(dt.getMagentaChild() 	!= null && dt.getMagentaChild().getRedChild()		!= null){					System.out.print("m_r : ");
					dt.getMagentaChild().setRedChild( oneStepDecisionTreeGeneration(dt.getMagentaChild().getRedChild()) ); }
				if(dt.getMagentaChild() 	!= null && dt.getMagentaChild().getOrangeChild()	!= null){					System.out.print("m_o : ");
					dt.getMagentaChild().setOrangeChild( oneStepDecisionTreeGeneration(dt.getMagentaChild().getOrangeChild()) ); }
				if(dt.getMagentaChild() 	!= null && dt.getMagentaChild().getYellowChild()	!= null){					System.out.print("m_y : ");
					dt.getMagentaChild().setYellowChild( oneStepDecisionTreeGeneration(dt.getMagentaChild().getYellowChild()) ); }
				if(dt.getMagentaChild() 	!= null && dt.getMagentaChild().getGreenChild()		!= null){					System.out.print("m_g : ");
					dt.getMagentaChild().setGreenChild( oneStepDecisionTreeGeneration(dt.getMagentaChild().getGreenChild()) ); }
				if(dt.getMagentaChild() 	!= null && dt.getMagentaChild().getBlueChild()		!= null){					System.out.print("m_b : ");
					dt.getMagentaChild().setBlueChild( oneStepDecisionTreeGeneration(dt.getMagentaChild().getBlueChild()) ); }
				if(dt.getMagentaChild() 	!= null && dt.getMagentaChild().getMagentaChild()	!= null){					System.out.print("m_m");
					dt.getMagentaChild().setMagentaChild( oneStepDecisionTreeGeneration(dt.getMagentaChild().getMagentaChild()) ); }
				
				System.out.print("\n________________\n");
				
				//________________________________

				if(dt.getRedChild() != null && dt.getRedChild().getRedChild() != null && dt.getRedChild().getRedChild().getRedChild() != null){			System.out.print("r_r_r : ");
					dt.getRedChild().getRedChild().setRedChild(oneStepDecisionTreeGeneration(dt.getRedChild().getRedChild().getRedChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getRedChild() != null && dt.getRedChild().getRedChild().getOrangeChild() != null){		System.out.print("r_r_o : ");
					dt.getRedChild().getRedChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getRedChild().getRedChild().getOrangeChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getRedChild() != null && dt.getRedChild().getRedChild().getYellowChild() != null){		System.out.print("r_r_y : ");
					dt.getRedChild().getRedChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getRedChild().getRedChild().getYellowChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getRedChild() != null && dt.getRedChild().getRedChild().getGreenChild() != null){		System.out.print("r_r_g : ");
					dt.getRedChild().getRedChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getRedChild().getRedChild().getGreenChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getRedChild() != null && dt.getRedChild().getRedChild().getBlueChild() != null){		System.out.print("r_r_b : ");
					dt.getRedChild().getRedChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getRedChild().getRedChild().getBlueChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getRedChild() != null && dt.getRedChild().getRedChild().getMagentaChild() != null){		System.out.print("r_r_m");
					dt.getRedChild().getRedChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getRedChild().getRedChild().getMagentaChild())); }
				
				System.out.print("\n_\n");
				
				if(dt.getRedChild() != null && dt.getRedChild().getOrangeChild() != null && dt.getRedChild().getOrangeChild().getRedChild() != null){		System.out.print("r_o_r : ");
					dt.getRedChild().getOrangeChild().setRedChild(oneStepDecisionTreeGeneration(dt.getRedChild().getOrangeChild().getRedChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getOrangeChild() != null && dt.getRedChild().getOrangeChild().getOrangeChild() != null){	System.out.print("r_o_o : ");
					dt.getRedChild().getOrangeChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getRedChild().getOrangeChild().getOrangeChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getOrangeChild() != null && dt.getRedChild().getOrangeChild().getYellowChild() != null){	System.out.print("r_o_y : ");
					dt.getRedChild().getOrangeChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getRedChild().getOrangeChild().getYellowChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getOrangeChild() != null && dt.getRedChild().getOrangeChild().getGreenChild() != null){		System.out.print("r_o_g : ");
					dt.getRedChild().getOrangeChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getRedChild().getOrangeChild().getGreenChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getOrangeChild() != null && dt.getRedChild().getOrangeChild().getBlueChild() != null){		System.out.print("r_o_b : ");
					dt.getRedChild().getOrangeChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getRedChild().getOrangeChild().getBlueChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getOrangeChild() != null && dt.getRedChild().getOrangeChild().getMagentaChild() != null){	System.out.print("r_o_m");
					dt.getRedChild().getOrangeChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getRedChild().getOrangeChild().getMagentaChild())); }

				System.out.print("\n_\n");
				
				if(dt.getRedChild() != null && dt.getRedChild().getYellowChild() != null && dt.getRedChild().getYellowChild().getRedChild() != null){		System.out.print("r_y_r : ");
					dt.getRedChild().getYellowChild().setRedChild(oneStepDecisionTreeGeneration(dt.getRedChild().getYellowChild().getRedChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getYellowChild() != null && dt.getRedChild().getYellowChild().getOrangeChild() != null){	System.out.print("r_y_o : ");
					dt.getRedChild().getYellowChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getRedChild().getYellowChild().getOrangeChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getYellowChild() != null && dt.getRedChild().getYellowChild().getYellowChild() != null){	System.out.print("r_y_y : ");
					dt.getRedChild().getYellowChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getRedChild().getYellowChild().getYellowChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getYellowChild() != null && dt.getRedChild().getYellowChild().getGreenChild() != null){		System.out.print("r_y_g : ");
					dt.getRedChild().getYellowChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getRedChild().getYellowChild().getGreenChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getYellowChild() != null && dt.getRedChild().getYellowChild().getBlueChild() != null){		System.out.print("r_y_b : ");
					dt.getRedChild().getYellowChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getRedChild().getYellowChild().getBlueChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getYellowChild() != null && dt.getRedChild().getYellowChild().getMagentaChild() != null){	System.out.print("r_y_m");
					dt.getRedChild().getYellowChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getRedChild().getYellowChild().getMagentaChild())); }
				
				System.out.print("\n_\n");
				
				if(dt.getRedChild() != null && dt.getRedChild().getGreenChild() != null && dt.getRedChild().getGreenChild().getRedChild() != null){		System.out.print("r_g_r : ");
					dt.getRedChild().getGreenChild().setRedChild(oneStepDecisionTreeGeneration(dt.getRedChild().getGreenChild().getRedChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getGreenChild() != null && dt.getRedChild().getGreenChild().getOrangeChild() != null){	System.out.print("r_g_o : ");
					dt.getRedChild().getGreenChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getRedChild().getGreenChild().getOrangeChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getGreenChild() != null && dt.getRedChild().getGreenChild().getYellowChild() != null){	System.out.print("r_g_y : ");
					dt.getRedChild().getGreenChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getRedChild().getGreenChild().getYellowChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getGreenChild() != null && dt.getRedChild().getGreenChild().getGreenChild() != null){	System.out.print("r_g_g : ");
					dt.getRedChild().getGreenChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getRedChild().getGreenChild().getGreenChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getGreenChild() != null && dt.getRedChild().getGreenChild().getBlueChild() != null){	System.out.print("r_g_b : ");
					dt.getRedChild().getGreenChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getRedChild().getGreenChild().getBlueChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getGreenChild() != null && dt.getRedChild().getGreenChild().getMagentaChild() != null){	System.out.print("r_g_m");
					dt.getRedChild().getGreenChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getRedChild().getGreenChild().getMagentaChild())); }
				
				System.out.print("\n_\n");
				
				if(dt.getRedChild() != null && dt.getRedChild().getBlueChild() != null && dt.getRedChild().getBlueChild().getRedChild() != null){		System.out.print("r_b_r : ");
					dt.getRedChild().getBlueChild().setRedChild(oneStepDecisionTreeGeneration(dt.getRedChild().getBlueChild().getRedChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getBlueChild() != null && dt.getRedChild().getBlueChild().getOrangeChild() != null){	System.out.print("r_b_o : ");
					dt.getRedChild().getBlueChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getRedChild().getBlueChild().getOrangeChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getBlueChild() != null && dt.getRedChild().getBlueChild().getYellowChild() != null){	System.out.print("r_b_y : ");
					dt.getRedChild().getBlueChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getRedChild().getBlueChild().getYellowChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getBlueChild() != null && dt.getRedChild().getBlueChild().getGreenChild() != null){		System.out.print("r_b_g : ");
					dt.getRedChild().getBlueChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getRedChild().getBlueChild().getGreenChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getBlueChild() != null && dt.getRedChild().getBlueChild().getBlueChild() != null){		System.out.print("r_b_b : ");
					dt.getRedChild().getBlueChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getRedChild().getBlueChild().getBlueChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getBlueChild() != null && dt.getRedChild().getBlueChild().getMagentaChild() != null){	System.out.print("r_b_m");
					dt.getRedChild().getBlueChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getRedChild().getBlueChild().getMagentaChild())); }
			
				System.out.print("\n_\n");
				
				if(dt.getRedChild() != null && dt.getRedChild().getMagentaChild() != null && dt.getRedChild().getMagentaChild().getRedChild() != null){		System.out.print("r_m_r : ");
					dt.getRedChild().getMagentaChild().setRedChild(oneStepDecisionTreeGeneration(dt.getRedChild().getMagentaChild().getRedChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getMagentaChild() != null && dt.getRedChild().getMagentaChild().getOrangeChild() != null){	System.out.print("r_m_o : ");
					dt.getRedChild().getMagentaChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getRedChild().getMagentaChild().getOrangeChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getMagentaChild() != null && dt.getRedChild().getMagentaChild().getYellowChild() != null){	System.out.print("r_m_y : ");
					dt.getRedChild().getMagentaChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getRedChild().getMagentaChild().getYellowChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getMagentaChild() != null && dt.getRedChild().getMagentaChild().getGreenChild() != null){	System.out.print("r_m_g : ");
					dt.getRedChild().getMagentaChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getRedChild().getMagentaChild().getGreenChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getMagentaChild() != null && dt.getRedChild().getMagentaChild().getBlueChild() != null){	System.out.print("r_m_b : ");
					dt.getRedChild().getMagentaChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getRedChild().getMagentaChild().getBlueChild())); }
				if(dt.getRedChild() != null && dt.getRedChild().getMagentaChild() != null && dt.getRedChild().getMagentaChild().getMagentaChild() != null){	System.out.print("r_m_m");
					dt.getRedChild().getMagentaChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getRedChild().getMagentaChild().getMagentaChild())); }
				
				System.out.print("\n___\n");

				if(dt.getOrangeChild() != null && dt.getOrangeChild().getRedChild() != null && dt.getOrangeChild().getRedChild().getRedChild() != null){		System.out.print("o_r_r : ");
					dt.getOrangeChild().getRedChild().setRedChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getRedChild().getRedChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getRedChild() != null && dt.getOrangeChild().getRedChild().getOrangeChild() != null){		System.out.print("o_r_o : ");
					dt.getOrangeChild().getRedChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getRedChild().getOrangeChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getRedChild() != null && dt.getOrangeChild().getRedChild().getYellowChild() != null){		System.out.print("o_r_y : ");
					dt.getOrangeChild().getRedChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getRedChild().getYellowChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getRedChild() != null && dt.getOrangeChild().getRedChild().getGreenChild() != null){		System.out.print("o_r_g : ");
					dt.getOrangeChild().getRedChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getRedChild().getGreenChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getRedChild() != null && dt.getOrangeChild().getRedChild().getBlueChild() != null){		System.out.print("o_r_b : ");
					dt.getOrangeChild().getRedChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getRedChild().getBlueChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getRedChild() != null && dt.getOrangeChild().getRedChild().getMagentaChild() != null){	System.out.print("o_r_m");
					dt.getOrangeChild().getRedChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getRedChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getOrangeChild() != null && dt.getOrangeChild().getOrangeChild() != null && dt.getOrangeChild().getOrangeChild().getRedChild() != null){		System.out.print("o_o_r : ");
					dt.getOrangeChild().getOrangeChild().setRedChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getOrangeChild().getRedChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getOrangeChild() != null && dt.getOrangeChild().getOrangeChild().getOrangeChild() != null){	System.out.print("o_o_o : ");
					dt.getOrangeChild().getOrangeChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getOrangeChild().getOrangeChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getOrangeChild() != null && dt.getOrangeChild().getOrangeChild().getYellowChild() != null){	System.out.print("o_o_y : ");
					dt.getOrangeChild().getOrangeChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getOrangeChild().getYellowChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getOrangeChild() != null && dt.getOrangeChild().getOrangeChild().getGreenChild() != null){	System.out.print("o_o_g : ");
					dt.getOrangeChild().getOrangeChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getOrangeChild().getGreenChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getOrangeChild() != null && dt.getOrangeChild().getOrangeChild().getBlueChild() != null){		System.out.print("o_o_b : ");
					dt.getOrangeChild().getOrangeChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getOrangeChild().getBlueChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getOrangeChild() != null && dt.getOrangeChild().getOrangeChild().getMagentaChild() != null){	System.out.print("o_o_m");
					dt.getOrangeChild().getOrangeChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getOrangeChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getOrangeChild() != null && dt.getOrangeChild().getYellowChild() != null && dt.getOrangeChild().getYellowChild().getRedChild() != null){		System.out.print("o_y_r : ");
					dt.getOrangeChild().getYellowChild().setRedChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getYellowChild().getRedChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getYellowChild() != null && dt.getOrangeChild().getYellowChild().getOrangeChild() != null){	System.out.print("o_y_o : ");
					dt.getOrangeChild().getYellowChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getYellowChild().getOrangeChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getYellowChild() != null && dt.getOrangeChild().getYellowChild().getYellowChild() != null){	System.out.print("o_y_y : ");
					dt.getOrangeChild().getYellowChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getYellowChild().getYellowChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getYellowChild() != null && dt.getOrangeChild().getYellowChild().getGreenChild() != null){	System.out.print("o_y_g : ");
					dt.getOrangeChild().getYellowChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getYellowChild().getGreenChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getYellowChild() != null && dt.getOrangeChild().getYellowChild().getBlueChild() != null){		System.out.print("o_y_b : ");
					dt.getOrangeChild().getYellowChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getYellowChild().getBlueChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getYellowChild() != null && dt.getOrangeChild().getYellowChild().getMagentaChild() != null){	System.out.print("o_y_m");
					dt.getOrangeChild().getYellowChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getYellowChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getOrangeChild() != null && dt.getOrangeChild().getGreenChild() != null && dt.getOrangeChild().getGreenChild().getRedChild() != null){	System.out.print("o_g_r : ");
					dt.getOrangeChild().getGreenChild().setRedChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getGreenChild().getRedChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getGreenChild() != null && dt.getOrangeChild().getGreenChild().getOrangeChild() != null){	System.out.print("o_g_o : ");
					dt.getOrangeChild().getGreenChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getGreenChild().getOrangeChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getGreenChild() != null && dt.getOrangeChild().getGreenChild().getYellowChild() != null){	System.out.print("o_g_y : ");
					dt.getOrangeChild().getGreenChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getGreenChild().getYellowChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getGreenChild() != null && dt.getOrangeChild().getGreenChild().getGreenChild() != null){	System.out.print("o_g_g : ");
					dt.getOrangeChild().getGreenChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getGreenChild().getGreenChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getGreenChild() != null && dt.getOrangeChild().getGreenChild().getBlueChild() != null){	System.out.print("o_g_b : ");
					dt.getOrangeChild().getGreenChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getGreenChild().getBlueChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getGreenChild() != null && dt.getOrangeChild().getGreenChild().getMagentaChild() != null){System.out.print("o_g_m");
					dt.getOrangeChild().getGreenChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getGreenChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getOrangeChild() != null && dt.getOrangeChild().getBlueChild() != null && dt.getOrangeChild().getBlueChild().getRedChild() != null){		System.out.print("o_b_r : ");
					dt.getOrangeChild().getBlueChild().setRedChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getBlueChild().getRedChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getBlueChild() != null && dt.getOrangeChild().getBlueChild().getOrangeChild() != null){	System.out.print("o_b_o : ");
					dt.getOrangeChild().getBlueChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getBlueChild().getOrangeChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getBlueChild() != null && dt.getOrangeChild().getBlueChild().getYellowChild() != null){	System.out.print("o_b_y : ");
					dt.getOrangeChild().getBlueChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getBlueChild().getYellowChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getBlueChild() != null && dt.getOrangeChild().getBlueChild().getGreenChild() != null){	System.out.print("o_b_g : ");
					dt.getOrangeChild().getBlueChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getBlueChild().getGreenChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getBlueChild() != null && dt.getOrangeChild().getBlueChild().getBlueChild() != null){		System.out.print("o_b_b : ");
					dt.getOrangeChild().getBlueChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getBlueChild().getBlueChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getBlueChild() != null && dt.getOrangeChild().getBlueChild().getMagentaChild() != null){	System.out.print("o_b_m");
					dt.getOrangeChild().getBlueChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getBlueChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getOrangeChild() != null && dt.getOrangeChild().getMagentaChild() != null && dt.getOrangeChild().getMagentaChild().getRedChild() != null){	System.out.print("o_m_r : ");
					dt.getOrangeChild().getMagentaChild().setRedChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getMagentaChild().getRedChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getMagentaChild() != null && dt.getOrangeChild().getMagentaChild().getOrangeChild() != null){	System.out.print("o_m_o : ");
					dt.getOrangeChild().getMagentaChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getMagentaChild().getOrangeChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getMagentaChild() != null && dt.getOrangeChild().getMagentaChild().getYellowChild() != null){	System.out.print("o_m_y : ");
					dt.getOrangeChild().getMagentaChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getMagentaChild().getYellowChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getMagentaChild() != null && dt.getOrangeChild().getMagentaChild().getGreenChild() != null){	System.out.print("o_m_g : ");
					dt.getOrangeChild().getMagentaChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getMagentaChild().getGreenChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getMagentaChild() != null && dt.getOrangeChild().getMagentaChild().getBlueChild() != null){	System.out.print("o_m_b : ");
					dt.getOrangeChild().getMagentaChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getMagentaChild().getBlueChild())); }
				if(dt.getOrangeChild() != null && dt.getOrangeChild().getMagentaChild() != null && dt.getOrangeChild().getMagentaChild().getMagentaChild() != null){System.out.print("o_m_m");
					dt.getOrangeChild().getMagentaChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getOrangeChild().getMagentaChild().getMagentaChild())); }

				System.out.print("\n___\n");

				if(dt.getYellowChild() != null && dt.getYellowChild().getRedChild() != null && dt.getYellowChild().getRedChild().getRedChild() != null){		System.out.print("y_r_r : ");
					dt.getYellowChild().getRedChild().setRedChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getRedChild().getRedChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getRedChild() != null && dt.getYellowChild().getRedChild().getOrangeChild() != null){		System.out.print("y_r_o : ");
					dt.getYellowChild().getRedChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getRedChild().getOrangeChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getRedChild() != null && dt.getYellowChild().getRedChild().getYellowChild() != null){		System.out.print("y_r_y : ");
					dt.getYellowChild().getRedChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getRedChild().getYellowChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getRedChild() != null && dt.getYellowChild().getRedChild().getGreenChild() != null){		System.out.print("y_r_g : ");
					dt.getYellowChild().getRedChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getRedChild().getGreenChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getRedChild() != null && dt.getYellowChild().getRedChild().getBlueChild() != null){		System.out.print("y_r_b : ");
					dt.getYellowChild().getRedChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getRedChild().getBlueChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getRedChild() != null && dt.getYellowChild().getRedChild().getMagentaChild() != null){	System.out.print("y_r_m");
					dt.getYellowChild().getRedChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getRedChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getYellowChild() != null && dt.getYellowChild().getOrangeChild() != null && dt.getYellowChild().getOrangeChild().getRedChild() != null){		System.out.print("y_o_r : ");
					dt.getYellowChild().getOrangeChild().setRedChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getOrangeChild().getRedChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getOrangeChild() != null && dt.getYellowChild().getOrangeChild().getOrangeChild() != null){	System.out.print("y_o_o : ");
					dt.getYellowChild().getOrangeChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getOrangeChild().getOrangeChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getOrangeChild() != null && dt.getYellowChild().getOrangeChild().getYellowChild() != null){	System.out.print("y_o_y : ");
					dt.getYellowChild().getOrangeChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getOrangeChild().getYellowChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getOrangeChild() != null && dt.getYellowChild().getOrangeChild().getGreenChild() != null){	System.out.print("y_o_g : ");
					dt.getYellowChild().getOrangeChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getOrangeChild().getGreenChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getOrangeChild() != null && dt.getYellowChild().getOrangeChild().getBlueChild() != null){		System.out.print("y_o_b : ");
					dt.getYellowChild().getOrangeChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getOrangeChild().getBlueChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getOrangeChild() != null && dt.getYellowChild().getOrangeChild().getMagentaChild() != null){	System.out.print("y_o_m");
					dt.getYellowChild().getOrangeChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getOrangeChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getYellowChild() != null && dt.getYellowChild().getYellowChild() != null && dt.getYellowChild().getYellowChild().getRedChild() != null){		System.out.print("y_y_r : ");
					dt.getYellowChild().getYellowChild().setRedChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getYellowChild().getRedChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getYellowChild() != null && dt.getYellowChild().getYellowChild().getOrangeChild() != null){	System.out.print("y_y_o : ");
					dt.getYellowChild().getYellowChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getYellowChild().getOrangeChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getYellowChild() != null && dt.getYellowChild().getYellowChild().getYellowChild() != null){	System.out.print("y_y_y : ");
					dt.getYellowChild().getYellowChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getYellowChild().getYellowChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getYellowChild() != null && dt.getYellowChild().getYellowChild().getGreenChild() != null){	System.out.print("y_y_g : ");
					dt.getYellowChild().getYellowChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getYellowChild().getGreenChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getYellowChild() != null && dt.getYellowChild().getYellowChild().getBlueChild() != null){		System.out.print("y_y_b : ");
					dt.getYellowChild().getYellowChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getYellowChild().getBlueChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getYellowChild() != null && dt.getYellowChild().getYellowChild().getMagentaChild() != null){	System.out.print("y_y_m");
					dt.getYellowChild().getYellowChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getYellowChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getYellowChild() != null && dt.getYellowChild().getGreenChild() != null && dt.getYellowChild().getGreenChild().getRedChild() != null){	System.out.print("y_g_r : ");
					dt.getYellowChild().getGreenChild().setRedChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getGreenChild().getRedChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getGreenChild() != null && dt.getYellowChild().getGreenChild().getOrangeChild() != null){	System.out.print("y_g_o : ");
					dt.getYellowChild().getGreenChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getGreenChild().getOrangeChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getGreenChild() != null && dt.getYellowChild().getGreenChild().getYellowChild() != null){	System.out.print("y_g_y : ");
					dt.getYellowChild().getGreenChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getGreenChild().getYellowChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getGreenChild() != null && dt.getYellowChild().getGreenChild().getGreenChild() != null){	System.out.print("y_g_g : ");
					dt.getYellowChild().getGreenChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getGreenChild().getGreenChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getGreenChild() != null && dt.getYellowChild().getGreenChild().getBlueChild() != null){	System.out.print("y_g_b : ");
					dt.getYellowChild().getGreenChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getGreenChild().getBlueChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getGreenChild() != null && dt.getYellowChild().getGreenChild().getMagentaChild() != null){System.out.print("y_g_m");
					dt.getYellowChild().getGreenChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getGreenChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getYellowChild() != null && dt.getYellowChild().getBlueChild() != null && dt.getYellowChild().getBlueChild().getRedChild() != null){		System.out.print("y_b_r : ");
					dt.getYellowChild().getBlueChild().setRedChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getBlueChild().getRedChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getBlueChild() != null && dt.getYellowChild().getBlueChild().getOrangeChild() != null){	System.out.print("y_b_o : ");
					dt.getYellowChild().getBlueChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getBlueChild().getOrangeChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getBlueChild() != null && dt.getYellowChild().getBlueChild().getYellowChild() != null){	System.out.print("y_b_y : ");
					dt.getYellowChild().getBlueChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getBlueChild().getYellowChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getBlueChild() != null && dt.getYellowChild().getBlueChild().getGreenChild() != null){	System.out.print("y_b_g : ");
					dt.getYellowChild().getBlueChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getBlueChild().getGreenChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getBlueChild() != null && dt.getYellowChild().getBlueChild().getBlueChild() != null){		System.out.print("y_b_b : ");
					dt.getYellowChild().getBlueChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getBlueChild().getBlueChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getBlueChild() != null && dt.getYellowChild().getBlueChild().getMagentaChild() != null){	System.out.print("y_b_m");
					dt.getYellowChild().getBlueChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getBlueChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getYellowChild() != null && dt.getYellowChild().getMagentaChild() != null && dt.getYellowChild().getMagentaChild().getRedChild() != null){	System.out.print("y_m_r : ");
					dt.getYellowChild().getMagentaChild().setRedChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getMagentaChild().getRedChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getMagentaChild() != null && dt.getYellowChild().getMagentaChild().getOrangeChild() != null){	System.out.print("y_m_o : ");
					dt.getYellowChild().getMagentaChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getMagentaChild().getOrangeChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getMagentaChild() != null && dt.getYellowChild().getMagentaChild().getYellowChild() != null){	System.out.print("y_m_y : ");
					dt.getYellowChild().getMagentaChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getMagentaChild().getYellowChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getMagentaChild() != null && dt.getYellowChild().getMagentaChild().getGreenChild() != null){	System.out.print("y_m_g : ");
					dt.getYellowChild().getMagentaChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getMagentaChild().getGreenChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getMagentaChild() != null && dt.getYellowChild().getMagentaChild().getBlueChild() != null){	System.out.print("y_m_b : ");
					dt.getYellowChild().getMagentaChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getMagentaChild().getBlueChild())); }
				if(dt.getYellowChild() != null && dt.getYellowChild().getMagentaChild() != null && dt.getYellowChild().getMagentaChild().getMagentaChild() != null){System.out.print("y_m_m");
					dt.getYellowChild().getMagentaChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getYellowChild().getMagentaChild().getMagentaChild())); }

				System.out.print("\n___\n");

				if(dt.getGreenChild() != null && dt.getGreenChild().getRedChild() != null && dt.getGreenChild().getRedChild().getRedChild() != null){		System.out.print("g_r_r : ");
					dt.getGreenChild().getRedChild().setRedChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getRedChild().getRedChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getRedChild() != null && dt.getGreenChild().getRedChild().getOrangeChild() != null){	System.out.print("g_r_o : ");
					dt.getGreenChild().getRedChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getRedChild().getOrangeChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getRedChild() != null && dt.getGreenChild().getRedChild().getYellowChild() != null){	System.out.print("g_r_y : ");
					dt.getGreenChild().getRedChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getRedChild().getYellowChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getRedChild() != null && dt.getGreenChild().getRedChild().getGreenChild() != null){		System.out.print("g_r_g : ");
					dt.getGreenChild().getRedChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getRedChild().getGreenChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getRedChild() != null && dt.getGreenChild().getRedChild().getBlueChild() != null){		System.out.print("g_r_b : ");
					dt.getGreenChild().getRedChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getRedChild().getBlueChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getRedChild() != null && dt.getGreenChild().getRedChild().getMagentaChild() != null){	System.out.print("g_r_m");
					dt.getGreenChild().getRedChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getRedChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getGreenChild() != null && dt.getGreenChild().getOrangeChild() != null && dt.getGreenChild().getOrangeChild().getRedChild() != null){		System.out.print("g_o_r : ");
					dt.getGreenChild().getOrangeChild().setRedChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getOrangeChild().getRedChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getOrangeChild() != null && dt.getGreenChild().getOrangeChild().getOrangeChild() != null){	System.out.print("g_o_o : ");
					dt.getGreenChild().getOrangeChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getOrangeChild().getOrangeChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getOrangeChild() != null && dt.getGreenChild().getOrangeChild().getYellowChild() != null){	System.out.print("g_o_y : ");
					dt.getGreenChild().getOrangeChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getOrangeChild().getYellowChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getOrangeChild() != null && dt.getGreenChild().getOrangeChild().getGreenChild() != null){	System.out.print("g_o_g : ");
					dt.getGreenChild().getOrangeChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getOrangeChild().getGreenChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getOrangeChild() != null && dt.getGreenChild().getOrangeChild().getBlueChild() != null){	System.out.print("g_o_b : ");
					dt.getGreenChild().getOrangeChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getOrangeChild().getBlueChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getOrangeChild() != null && dt.getGreenChild().getOrangeChild().getMagentaChild() != null){	System.out.print("g_o_m");
					dt.getGreenChild().getOrangeChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getOrangeChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getGreenChild() != null && dt.getGreenChild().getYellowChild() != null && dt.getGreenChild().getYellowChild().getRedChild() != null){		System.out.print("g_y_r : ");
					dt.getGreenChild().getYellowChild().setRedChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getYellowChild().getRedChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getYellowChild() != null && dt.getGreenChild().getYellowChild().getOrangeChild() != null){	System.out.print("g_y_o : ");
					dt.getGreenChild().getYellowChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getYellowChild().getOrangeChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getYellowChild() != null && dt.getGreenChild().getYellowChild().getYellowChild() != null){	System.out.print("g_y_y : ");
					dt.getGreenChild().getYellowChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getYellowChild().getYellowChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getYellowChild() != null && dt.getGreenChild().getYellowChild().getGreenChild() != null){	System.out.print("g_y_g : ");
					dt.getGreenChild().getYellowChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getYellowChild().getGreenChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getYellowChild() != null && dt.getGreenChild().getYellowChild().getBlueChild() != null){	System.out.print("g_y_b : ");
					dt.getGreenChild().getYellowChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getYellowChild().getBlueChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getYellowChild() != null && dt.getGreenChild().getYellowChild().getMagentaChild() != null){	System.out.print("g_y_m");
					dt.getGreenChild().getYellowChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getYellowChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getGreenChild() != null && dt.getGreenChild().getGreenChild() != null && dt.getGreenChild().getGreenChild().getRedChild() != null){	System.out.print("g_g_r : ");
					dt.getGreenChild().getGreenChild().setRedChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getGreenChild().getRedChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getGreenChild() != null && dt.getGreenChild().getGreenChild().getOrangeChild() != null){System.out.print("g_g_o : ");
					dt.getGreenChild().getGreenChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getGreenChild().getOrangeChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getGreenChild() != null && dt.getGreenChild().getGreenChild().getYellowChild() != null){System.out.print("g_g_y : ");
					dt.getGreenChild().getGreenChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getGreenChild().getYellowChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getGreenChild() != null && dt.getGreenChild().getGreenChild().getGreenChild() != null){	System.out.print("g_g_g : ");
					dt.getGreenChild().getGreenChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getGreenChild().getGreenChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getGreenChild() != null && dt.getGreenChild().getGreenChild().getBlueChild() != null){	System.out.print("g_g_b : ");
					dt.getGreenChild().getGreenChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getGreenChild().getBlueChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getGreenChild() != null && dt.getGreenChild().getGreenChild().getMagentaChild() != null){System.out.print("g_g_m");
					dt.getGreenChild().getGreenChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getGreenChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getGreenChild() != null && dt.getGreenChild().getBlueChild() != null && dt.getGreenChild().getBlueChild().getRedChild() != null){		System.out.print("g_b_r : ");
					dt.getGreenChild().getBlueChild().setRedChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getBlueChild().getRedChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getBlueChild() != null && dt.getGreenChild().getBlueChild().getOrangeChild() != null){	System.out.print("g_b_o : ");
					dt.getGreenChild().getBlueChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getBlueChild().getOrangeChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getBlueChild() != null && dt.getGreenChild().getBlueChild().getYellowChild() != null){	System.out.print("g_b_y : ");
					dt.getGreenChild().getBlueChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getBlueChild().getYellowChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getBlueChild() != null && dt.getGreenChild().getBlueChild().getGreenChild() != null){	System.out.print("g_b_g : ");
					dt.getGreenChild().getBlueChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getBlueChild().getGreenChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getBlueChild() != null && dt.getGreenChild().getBlueChild().getBlueChild() != null){	System.out.print("g_b_b : ");
					dt.getGreenChild().getBlueChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getBlueChild().getBlueChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getBlueChild() != null && dt.getGreenChild().getBlueChild().getMagentaChild() != null){	System.out.print("g_b_m");
					dt.getGreenChild().getBlueChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getBlueChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getGreenChild() != null && dt.getGreenChild().getMagentaChild() != null && dt.getGreenChild().getMagentaChild().getRedChild() != null){	System.out.print("g_m_r : ");
					dt.getGreenChild().getMagentaChild().setRedChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getMagentaChild().getRedChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getMagentaChild() != null && dt.getGreenChild().getMagentaChild().getOrangeChild() != null){System.out.print("g_m_o : ");
					dt.getGreenChild().getMagentaChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getMagentaChild().getOrangeChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getMagentaChild() != null && dt.getGreenChild().getMagentaChild().getYellowChild() != null){System.out.print("g_m_y : ");
					dt.getGreenChild().getMagentaChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getMagentaChild().getYellowChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getMagentaChild() != null && dt.getGreenChild().getMagentaChild().getGreenChild() != null){	System.out.print("g_m_g : ");
					dt.getGreenChild().getMagentaChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getMagentaChild().getGreenChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getMagentaChild() != null && dt.getGreenChild().getMagentaChild().getBlueChild() != null){	System.out.print("g_m_b : ");
					dt.getGreenChild().getMagentaChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getMagentaChild().getBlueChild())); }
				if(dt.getGreenChild() != null && dt.getGreenChild().getMagentaChild() != null && dt.getGreenChild().getMagentaChild().getMagentaChild() != null){System.out.print("g_m_m");
					dt.getGreenChild().getMagentaChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getGreenChild().getMagentaChild().getMagentaChild())); }

				System.out.print("\n___\n");

				if(dt.getBlueChild() != null && dt.getBlueChild().getRedChild() != null && dt.getBlueChild().getRedChild().getRedChild() != null){		System.out.print("b_r_r : ");
					dt.getBlueChild().getRedChild().setRedChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getRedChild().getRedChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getRedChild() != null && dt.getBlueChild().getRedChild().getOrangeChild() != null){	System.out.print("b_r_o : ");
					dt.getBlueChild().getRedChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getRedChild().getOrangeChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getRedChild() != null && dt.getBlueChild().getRedChild().getYellowChild() != null){	System.out.print("b_r_y : ");
					dt.getBlueChild().getRedChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getRedChild().getYellowChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getRedChild() != null && dt.getBlueChild().getRedChild().getGreenChild() != null){	System.out.print("b_r_g : ");
					dt.getBlueChild().getRedChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getRedChild().getGreenChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getRedChild() != null && dt.getBlueChild().getRedChild().getBlueChild() != null){		System.out.print("b_r_b : ");
					dt.getBlueChild().getRedChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getRedChild().getBlueChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getRedChild() != null && dt.getBlueChild().getRedChild().getMagentaChild() != null){	System.out.print("b_r_m");
					dt.getBlueChild().getRedChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getRedChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getBlueChild() != null && dt.getBlueChild().getOrangeChild() != null && dt.getBlueChild().getOrangeChild().getRedChild() != null){	System.out.print("b_o_r : ");
					dt.getBlueChild().getOrangeChild().setRedChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getOrangeChild().getRedChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getOrangeChild() != null && dt.getBlueChild().getOrangeChild().getOrangeChild() != null){	System.out.print("b_o_o : ");
					dt.getBlueChild().getOrangeChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getOrangeChild().getOrangeChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getOrangeChild() != null && dt.getBlueChild().getOrangeChild().getYellowChild() != null){	System.out.print("b_o_y : ");
					dt.getBlueChild().getOrangeChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getOrangeChild().getYellowChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getOrangeChild() != null && dt.getBlueChild().getOrangeChild().getGreenChild() != null){	System.out.print("b_o_g : ");
					dt.getBlueChild().getOrangeChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getOrangeChild().getGreenChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getOrangeChild() != null && dt.getBlueChild().getOrangeChild().getBlueChild() != null){	System.out.print("b_o_b : ");
					dt.getBlueChild().getOrangeChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getOrangeChild().getBlueChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getOrangeChild() != null && dt.getBlueChild().getOrangeChild().getMagentaChild() != null){System.out.print("b_o_m");
					dt.getBlueChild().getOrangeChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getOrangeChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getBlueChild() != null && dt.getBlueChild().getYellowChild() != null && dt.getBlueChild().getYellowChild().getRedChild() != null){	System.out.print("b_y_r : ");
					dt.getBlueChild().getYellowChild().setRedChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getYellowChild().getRedChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getYellowChild() != null && dt.getBlueChild().getYellowChild().getOrangeChild() != null){	System.out.print("b_y_o : ");
					dt.getBlueChild().getYellowChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getYellowChild().getOrangeChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getYellowChild() != null && dt.getBlueChild().getYellowChild().getYellowChild() != null){	System.out.print("b_y_y : ");
					dt.getBlueChild().getYellowChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getYellowChild().getYellowChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getYellowChild() != null && dt.getBlueChild().getYellowChild().getGreenChild() != null){	System.out.print("b_y_g : ");
					dt.getBlueChild().getYellowChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getYellowChild().getGreenChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getYellowChild() != null && dt.getBlueChild().getYellowChild().getBlueChild() != null){	System.out.print("b_y_b : ");
					dt.getBlueChild().getYellowChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getYellowChild().getBlueChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getYellowChild() != null && dt.getBlueChild().getYellowChild().getMagentaChild() != null){System.out.print("b_y_m");
					dt.getBlueChild().getYellowChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getYellowChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getBlueChild() != null && dt.getBlueChild().getGreenChild() != null && dt.getBlueChild().getGreenChild().getRedChild() != null){		System.out.print("b_g_r : ");
					dt.getBlueChild().getGreenChild().setRedChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getGreenChild().getRedChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getGreenChild() != null && dt.getBlueChild().getGreenChild().getOrangeChild() != null){	System.out.print("b_g_o : ");
					dt.getBlueChild().getGreenChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getGreenChild().getOrangeChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getGreenChild() != null && dt.getBlueChild().getGreenChild().getYellowChild() != null){	System.out.print("b_g_y : ");
					dt.getBlueChild().getGreenChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getGreenChild().getYellowChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getGreenChild() != null && dt.getBlueChild().getGreenChild().getGreenChild() != null){	System.out.print("b_g_g : ");
					dt.getBlueChild().getGreenChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getGreenChild().getGreenChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getGreenChild() != null && dt.getBlueChild().getGreenChild().getBlueChild() != null){		System.out.print("b_g_b : ");
					dt.getBlueChild().getGreenChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getGreenChild().getBlueChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getGreenChild() != null && dt.getBlueChild().getGreenChild().getMagentaChild() != null){	System.out.print("b_g_m");
					dt.getBlueChild().getGreenChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getGreenChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getBlueChild() != null && dt.getBlueChild().getBlueChild() != null && dt.getBlueChild().getBlueChild().getRedChild() != null){	System.out.println("b_b_r : ");
					dt.getBlueChild().getBlueChild().setRedChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getBlueChild().getRedChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getBlueChild() != null && dt.getBlueChild().getBlueChild().getOrangeChild() != null){	System.out.println("b_b_o : ");
					dt.getBlueChild().getBlueChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getBlueChild().getOrangeChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getBlueChild() != null && dt.getBlueChild().getBlueChild().getYellowChild() != null){	System.out.println("b_b_y : ");
					dt.getBlueChild().getBlueChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getBlueChild().getYellowChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getBlueChild() != null && dt.getBlueChild().getBlueChild().getGreenChild() != null){	System.out.println("b_b_g : ");
					dt.getBlueChild().getBlueChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getBlueChild().getGreenChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getBlueChild() != null && dt.getBlueChild().getBlueChild().getBlueChild() != null){	System.out.println("b_b_b : ");
					dt.getBlueChild().getBlueChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getBlueChild().getBlueChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getBlueChild() != null && dt.getBlueChild().getBlueChild().getMagentaChild() != null){System.out.println("b_b_m");
					dt.getBlueChild().getBlueChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getBlueChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getBlueChild() != null && dt.getBlueChild().getMagentaChild() != null && dt.getBlueChild().getMagentaChild().getRedChild() != null){		System.out.print("b_m_r : ");
					dt.getBlueChild().getMagentaChild().setRedChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getMagentaChild().getRedChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getMagentaChild() != null && dt.getBlueChild().getMagentaChild().getOrangeChild() != null){	System.out.print("b_m_o : ");
					dt.getBlueChild().getMagentaChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getMagentaChild().getOrangeChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getMagentaChild() != null && dt.getBlueChild().getMagentaChild().getYellowChild() != null){	System.out.print("b_m_y : ");
					dt.getBlueChild().getMagentaChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getMagentaChild().getYellowChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getMagentaChild() != null && dt.getBlueChild().getMagentaChild().getGreenChild() != null){	System.out.print("b_m_g : ");
					dt.getBlueChild().getMagentaChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getMagentaChild().getGreenChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getMagentaChild() != null && dt.getBlueChild().getMagentaChild().getBlueChild() != null){		System.out.print("b_m_b : ");
					dt.getBlueChild().getMagentaChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getMagentaChild().getBlueChild())); }
				if(dt.getBlueChild() != null && dt.getBlueChild().getMagentaChild() != null && dt.getBlueChild().getMagentaChild().getMagentaChild() != null){	System.out.print("b_m_m");
					dt.getBlueChild().getMagentaChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getBlueChild().getMagentaChild().getMagentaChild())); }

				System.out.print("\n___\n");

				if(dt.getMagentaChild() != null && dt.getMagentaChild().getRedChild() != null && dt.getMagentaChild().getRedChild().getRedChild() != null){		System.out.print("m_r_r : ");
					dt.getMagentaChild().getRedChild().setRedChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getRedChild().getRedChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getRedChild() != null && dt.getMagentaChild().getRedChild().getOrangeChild() != null){	System.out.print("m_r_o : ");
					dt.getMagentaChild().getRedChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getRedChild().getOrangeChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getRedChild() != null && dt.getMagentaChild().getRedChild().getYellowChild() != null){	System.out.print("m_r_y : ");
					dt.getMagentaChild().getRedChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getRedChild().getYellowChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getRedChild() != null && dt.getMagentaChild().getRedChild().getGreenChild() != null){	System.out.print("m_r_g : ");
					dt.getMagentaChild().getRedChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getRedChild().getGreenChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getRedChild() != null && dt.getMagentaChild().getRedChild().getBlueChild() != null){	System.out.print("m_r_b : ");
					dt.getMagentaChild().getRedChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getRedChild().getBlueChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getRedChild() != null && dt.getMagentaChild().getRedChild().getMagentaChild() != null){	System.out.print("m_r_m");
					dt.getMagentaChild().getRedChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getRedChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getMagentaChild() != null && dt.getMagentaChild().getOrangeChild() != null && dt.getMagentaChild().getOrangeChild().getRedChild() != null){	System.out.print("m_o_r : ");
					dt.getMagentaChild().getOrangeChild().setRedChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getOrangeChild().getRedChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getOrangeChild() != null && dt.getMagentaChild().getOrangeChild().getOrangeChild() != null){System.out.print("m_o_o : ");
					dt.getMagentaChild().getOrangeChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getOrangeChild().getOrangeChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getOrangeChild() != null && dt.getMagentaChild().getOrangeChild().getYellowChild() != null){System.out.print("m_o_y : ");
					dt.getMagentaChild().getOrangeChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getOrangeChild().getYellowChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getOrangeChild() != null && dt.getMagentaChild().getOrangeChild().getGreenChild() != null){	System.out.print("m_o_g : ");
					dt.getMagentaChild().getOrangeChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getOrangeChild().getGreenChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getOrangeChild() != null && dt.getMagentaChild().getOrangeChild().getBlueChild() != null){	System.out.print("m_o_b : ");
					dt.getMagentaChild().getOrangeChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getOrangeChild().getBlueChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getOrangeChild() != null && dt.getMagentaChild().getOrangeChild().getMagentaChild() != null){System.out.print("m_o_m");
					dt.getMagentaChild().getOrangeChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getOrangeChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getMagentaChild() != null && dt.getMagentaChild().getYellowChild() != null && dt.getMagentaChild().getYellowChild().getRedChild() != null){	System.out.print("m_y_r : ");
					dt.getMagentaChild().getYellowChild().setRedChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getYellowChild().getRedChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getYellowChild() != null && dt.getMagentaChild().getYellowChild().getOrangeChild() != null){System.out.print("m_y_o : ");
					dt.getMagentaChild().getYellowChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getYellowChild().getOrangeChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getYellowChild() != null && dt.getMagentaChild().getYellowChild().getYellowChild() != null){System.out.print("m_y_y : ");
					dt.getMagentaChild().getYellowChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getYellowChild().getYellowChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getYellowChild() != null && dt.getMagentaChild().getYellowChild().getGreenChild() != null){	System.out.print("m_y_g : ");
					dt.getMagentaChild().getYellowChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getYellowChild().getGreenChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getYellowChild() != null && dt.getMagentaChild().getYellowChild().getBlueChild() != null){	System.out.print("m_y_b : ");
					dt.getMagentaChild().getYellowChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getYellowChild().getBlueChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getYellowChild() != null && dt.getMagentaChild().getYellowChild().getMagentaChild() != null){System.out.print("m_y_m");
					dt.getMagentaChild().getYellowChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getYellowChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getMagentaChild() != null && dt.getMagentaChild().getGreenChild() != null && dt.getMagentaChild().getGreenChild().getRedChild() != null){		System.out.print("m_g_r : ");
					dt.getMagentaChild().getGreenChild().setRedChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getGreenChild().getRedChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getGreenChild() != null && dt.getMagentaChild().getGreenChild().getOrangeChild() != null){	System.out.print("m_g_o : ");
					dt.getMagentaChild().getGreenChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getGreenChild().getOrangeChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getGreenChild() != null && dt.getMagentaChild().getGreenChild().getYellowChild() != null){	System.out.print("m_g_y : ");
					dt.getMagentaChild().getGreenChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getGreenChild().getYellowChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getGreenChild() != null && dt.getMagentaChild().getGreenChild().getGreenChild() != null){	System.out.print("m_g_g : ");
					dt.getMagentaChild().getGreenChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getGreenChild().getGreenChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getGreenChild() != null && dt.getMagentaChild().getGreenChild().getBlueChild() != null){	System.out.print("m_g_b : ");
					dt.getMagentaChild().getGreenChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getGreenChild().getBlueChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getGreenChild() != null && dt.getMagentaChild().getGreenChild().getMagentaChild() != null){	System.out.print("m_g_m");
					dt.getMagentaChild().getGreenChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getGreenChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getMagentaChild() != null && dt.getMagentaChild().getBlueChild() != null && dt.getMagentaChild().getBlueChild().getRedChild() != null){		System.out.print("m_b_r : ");
					dt.getMagentaChild().getBlueChild().setRedChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getBlueChild().getRedChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getBlueChild() != null && dt.getMagentaChild().getBlueChild().getOrangeChild() != null){	System.out.print("m_b_o : ");
					dt.getMagentaChild().getBlueChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getBlueChild().getOrangeChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getBlueChild() != null && dt.getMagentaChild().getBlueChild().getYellowChild() != null){	System.out.print("m_b_y : ");
					dt.getMagentaChild().getBlueChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getBlueChild().getYellowChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getBlueChild() != null && dt.getMagentaChild().getBlueChild().getGreenChild() != null){		System.out.print("m_b_g : ");
					dt.getMagentaChild().getBlueChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getBlueChild().getGreenChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getBlueChild() != null && dt.getMagentaChild().getBlueChild().getBlueChild() != null){		System.out.print("m_b_b : ");
					dt.getMagentaChild().getBlueChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getBlueChild().getBlueChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getBlueChild() != null && dt.getMagentaChild().getBlueChild().getMagentaChild() != null){	System.out.print("m_b_m");
					dt.getMagentaChild().getBlueChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getBlueChild().getMagentaChild())); }

				System.out.print("\n_\n");

				if(dt.getMagentaChild() != null && dt.getMagentaChild().getMagentaChild() != null && dt.getMagentaChild().getMagentaChild().getRedChild() != null){		System.out.print("m_m_r : ");
					dt.getMagentaChild().getMagentaChild().setRedChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getMagentaChild().getRedChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getMagentaChild() != null && dt.getMagentaChild().getMagentaChild().getOrangeChild() != null){	System.out.print("m_m_o : ");
					dt.getMagentaChild().getMagentaChild().setOrangeChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getMagentaChild().getOrangeChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getMagentaChild() != null && dt.getMagentaChild().getMagentaChild().getYellowChild() != null){	System.out.print("m_m_y : ");
					dt.getMagentaChild().getMagentaChild().setYellowChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getMagentaChild().getYellowChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getMagentaChild() != null && dt.getMagentaChild().getMagentaChild().getGreenChild() != null){	System.out.print("m_m_g : ");
					dt.getMagentaChild().getMagentaChild().setGreenChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getMagentaChild().getGreenChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getMagentaChild() != null && dt.getMagentaChild().getMagentaChild().getBlueChild() != null){	System.out.print("m_m_b : ");
					dt.getMagentaChild().getMagentaChild().setBlueChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getMagentaChild().getBlueChild())); }
				if(dt.getMagentaChild() != null && dt.getMagentaChild().getMagentaChild() != null && dt.getMagentaChild().getMagentaChild().getMagentaChild() != null){	System.out.print("m_m_m");
					dt.getMagentaChild().getMagentaChild().setMagentaChild(oneStepDecisionTreeGeneration(dt.getMagentaChild().getMagentaChild().getMagentaChild())); }

				System.out.print("\n___\n");
		
		return null;
	}
	
	public static DecisionTree oneStepDecisionTreeGeneration(DecisionTree dt){
		
		HexaBoard clone = (HexaBoard) dt.getBoard();
		
		ArrayList<Color> couleursDispo = clone.getFreeColors();
		
		for(Color itemColor : couleursDispo){
			
			HexaBoard cloneBis = clone.clone();
			
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
		}

		return dt;
	}
	
	public static DecisionTree decisionTreeGeneration(DecisionTree dt, int compt){
		
		compt++;	System.out.println("Compt : "+compt);
		
		HexaBoard clone = (HexaBoard) dt.getBoard();
		
		ArrayList<Color> couleursDispo = clone.getFreeColors();
		
		for(Color itemColor : couleursDispo){
			
			HexaBoard cloneBis = clone.clone();
			
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
}
