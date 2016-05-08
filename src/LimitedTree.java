import java.awt.Color;

public class LimitedTree {

	private String playerName; // celui qui a joué ce coup ..
	
	private Color color;
	
	private LimitedTree redChild;
	private LimitedTree orangeChild;
	private LimitedTree yellowChild;
	private LimitedTree greenChild;
	private LimitedTree blueChild;
	private LimitedTree magentaChild;
	
	public LimitedTree(){
		
		this.playerName = "";
		
		this.color = null;
		
		this.redChild = null;
		this.orangeChild = null;
		this.yellowChild = null;
		this.greenChild = null;
		this.blueChild = null;
		this.magentaChild = null;
	}
	
	public LimitedTree(String playerName){
		
		this.playerName = playerName;
		
		this.color = null;
		
		this.redChild = null;
		this.orangeChild = null;
		this.yellowChild = null;
		this.greenChild = null;
		this.blueChild = null;
		this.magentaChild = null;
	}
	
	public LimitedTree(String playerName, Color color, LimitedTree redChild, LimitedTree orangeChild, LimitedTree yellowChild, LimitedTree greenChild, LimitedTree blueChild, LimitedTree magentaChild){
		
		this.playerName = playerName;
		
		this.color = color;
		
		this.redChild = redChild;
		this.orangeChild = orangeChild;
		this.yellowChild = yellowChild;
		this.greenChild = greenChild;
		this.blueChild = blueChild;
		this.magentaChild = magentaChild;
	}
	
	public static int heightTree(LimitedTree dt){
		if(dt == null){
			return 0;
		}
		else{
			return 1 + max(heightTree(dt.redChild), heightTree(dt.orangeChild), heightTree(dt.yellowChild), heightTree(dt.greenChild), heightTree(dt.blueChild), heightTree(dt.magentaChild));
		}
	}
	
	private static int max(int r, int o, int y, int g, int b, int m){
		
		int max = 0;
		
		if(r >= max) max = r;
		if(o >= max) max = o;
		if(y >= max) max = y;
		if(g >= max) max = g;
		if(b >= max) max = b;
		if(m >= max) max = m;
		
		return max;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	public void setPlayer(String playerName) {
		this.playerName = playerName;
	}
	
	public LimitedTree getRedChild() {
		return redChild;
	}
	public void setRedChild(LimitedTree redChild) {
		this.redChild = redChild;
	}
	
	public LimitedTree getOrangeChild() {
		return orangeChild;
	}
	public void setOrangeChild(LimitedTree orangeChild) {
		this.orangeChild = orangeChild;
	}
	
	public LimitedTree getYellowChild() {
		return yellowChild;
	}
	public void setYellowChild(LimitedTree yellowChild) {
		this.yellowChild = yellowChild;
	}
	
	public LimitedTree getGreenChild() {
		return greenChild;
	}
	public void setGreenChild(LimitedTree greenChild) {
		this.greenChild = greenChild;
	}
	
	public LimitedTree getBlueChild() {
		return blueChild;
	}
	public void setBlueChild(LimitedTree blueChild) {
		this.blueChild = blueChild;
	}
	
	public LimitedTree getMagentaChild() {
		return magentaChild;
	}
	public void setMagentaChild(LimitedTree magentaChild) {
		this.magentaChild = magentaChild;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
