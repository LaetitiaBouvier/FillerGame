public class DecisionTree {
	
	private SquareCell[][] grid;

	private DecisionTree redChild;
	private DecisionTree orangeChild;
	private DecisionTree yellowChild;
	private DecisionTree greenChild;
	private DecisionTree blueChild;
	private DecisionTree magentaChild;
	
	public DecisionTree(){
		
		this.grid = null;
		
		this.redChild = null;
		this.orangeChild = null;
		this.yellowChild = null;
		this.greenChild = null;
		this.blueChild = null;
		this.magentaChild = null;
	}
	
	public DecisionTree(SquareCell[][] grid){
		
		this.grid = grid;
		
		this.redChild = null;
		this.orangeChild = null;
		this.yellowChild = null;
		this.greenChild = null;
		this.blueChild = null;
		this.magentaChild = null;
	}
	
	public DecisionTree(SquareCell[][] grid, DecisionTree redChild, DecisionTree orangeChild, DecisionTree yellowChild, DecisionTree greenChild, DecisionTree blueChild, DecisionTree magentaChild){
		
		this.grid = grid;
		
		this.redChild = redChild;
		this.orangeChild = orangeChild;
		this.yellowChild = yellowChild;
		this.greenChild = greenChild;
		this.blueChild = blueChild;
		this.magentaChild = magentaChild;
	}
	
	public static int heightTree(DecisionTree dt){
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
	
	public SquareCell[][] getGrid() {
		return this.grid;
	}
	public void setGrid(SquareCell[][] grid) {
		this.grid = grid;
	}
	
	public DecisionTree getRedChild() {
		return redChild;
	}
	public void setRedChild(DecisionTree redChild) {
		this.redChild = redChild;
	}
	
	public DecisionTree getOrangeChild() {
		return orangeChild;
	}
	public void setOrangeChild(DecisionTree orangeChild) {
		this.orangeChild = orangeChild;
	}
	
	public DecisionTree getYellowChild() {
		return yellowChild;
	}
	public void setYellowChild(DecisionTree yellowChild) {
		this.yellowChild = yellowChild;
	}
	
	public DecisionTree getGreenChild() {
		return greenChild;
	}
	public void setGreenChild(DecisionTree greenChild) {
		this.greenChild = greenChild;
	}
	
	public DecisionTree getBlueChild() {
		return blueChild;
	}
	public void setBlueChild(DecisionTree blueChild) {
		this.blueChild = blueChild;
	}
	
	public DecisionTree getMagentaChild() {
		return magentaChild;
	}
	public void setMagentaChild(DecisionTree magentaChild) {
		this.magentaChild = magentaChild;
	}
}
