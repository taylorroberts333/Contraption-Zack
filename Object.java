import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;


public class Object 
{
	protected int id;
	protected ObjectType type;
	protected Color color;
	protected double gridX;
	protected double gridY;
	protected Shape shape;
	protected Group level;
	ArrayList<Object> levelObjects;

	public Object(int id, String type, String color, double gridX, double gridY) 
	{
		this.id = id;
		this.setType(type);
		this.setColor(color);
		this.gridX = gridX;
		this.gridY = gridY;
	}

	public void paintObject()
	{
		System.out.println("If you are outputting this text, then you have not overridden the paintObject \n" +
							"method inside the " + this.type + " class.");
	}

	public void activateObject()
	{
		System.out.println("If you are outputting this text, then you have not overridden the activateObject \n" +
							"method inside the " + this.type + " class.");	
	}
	
	public int getId() 
	{
		return this.id;
	}

	public Color getColor() 
	{
		return this.color;
	}

	public double getgridX() 
	{
		return this.gridX;
	}

	public double getgridY() 
	{
		return this.gridY;
	}

	public ObjectType getType()
	{
		return this.type;
	}

	public Shape getShape()
	{
		return this.shape;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public void setType(String type)
	{
		switch (type)
		{
			case "f": this.type = ObjectType.FLOOR; break;
			case "w": this.type = ObjectType.WALL; break;
			case "dU": this.type = ObjectType.DOORUP; break;
			case "dD": this.type = ObjectType.DOORDOWN; break;
			case "dL": this.type = ObjectType.DOORLEFT; break;
			case "dR": this.type = ObjectType.DOORRIGHT; break;
			case "bS": this.type = ObjectType.BUTTONSQUARE; break;
			case "bC": this.type = ObjectType.BUTTONCIRCLE; break;
			case "bST": this.type = ObjectType.BUTTONSQUARETIMER; break;
			case "bCT": this.type = ObjectType.BUTTONCIRCLETIMER; break;
			case "skH": this.type = ObjectType.HSPIKE; break;
			case "skV": this.type = ObjectType.VSPIKE; break;
			case "spL": this.type = ObjectType.SPRINGLEFT; break;
			case "spR": this.type = ObjectType.SPRINGRIGHT; break;
			case "hSL": this.type = ObjectType.HSLIDER; break;
			case "vSL": this.type = ObjectType.VSLIDER; break;
			case "scD": this.type = ObjectType.SCREWDRIVER; break;
		}

	}

	public void setColor(String color) 
	{
		switch (color) 
		{
			case "blu": this.color = Color.DODGERBLUE; break;
			case "org": this.color = Color.ORANGE; break;
			case "gry": this.color = Color.LIGHTGRAY; break;
			case "ylw": this.color = Color.GOLD; break;
			case "pur": this.color = Color.PURPLE; break;
			case "blk": this.color = Color.BLACK; break;
			case "wht": this.color = Color.WHITE; break;
			case "dgry": this.color = Color.GRAY; break;
			case "grn": this.color = Color.GREEN; break;
			case "cob": this.color = Color.BROWN; break; // this is not actually brown, it is just an indication to use the cobblestone image
		   case "flr": this.color = Color.PINK; break; // this is not actually pink, it is just an indication to use the level 4 floor image

      }

	}

	public void setgridX(double gridX) 
	{
		this.gridX = gridX;
	}

	public void setgridY(double gridY) 
	{
		this.gridY = gridY;
	}

	public void setShape(Shape shape)
	{
		this.shape = shape;
	}

	public void setLevel(Group level)
	{
		this.level = level;
	}

	public void setLevelObjects(ArrayList<Object> levelObjects)
	{
		this.levelObjects = levelObjects;
	}
}
