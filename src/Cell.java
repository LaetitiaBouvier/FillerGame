import java.awt.Color;


public interface Cell {

	public int getCentreX();
	public void setCentreX(int centreX);

	public int getCentreY();
	public void setCentreY(int centreY);

	public Color getColor();
	public void setColor(Color color);

	public String getCtrlBy();
	public void setCtrlBy(String ctrlBy);
}
