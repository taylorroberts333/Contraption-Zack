import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class DoorObject extends Object
{
	private String nextLevel;
	private Game game;

	private boolean isActive = false;
	public DoorObject(int id, String type, String color, double gridX, double gridY, String nextLevel, Game game) {
		super(id, type, color, gridX, gridY);

		this.nextLevel = nextLevel;
		this.game = game;
	}
	
	@Override
	public void paintObject()
	{
		Rectangle r = new Rectangle();
		double rectangleX = this.getgridX() * 60;
		double rectangleY = this.getgridY() * 60;
		r.setWidth(50);
		r.setHeight(50);
		r.setX(rectangleX + 5);
		r.setY(rectangleY + 5);
		r.setFill(this.color);

		if(this.getType() == ObjectType.DOORUP)
		{
			Image img = new Image("Resources\\arrowUp.png");
			r.setFill(new ImagePattern(img));
		}
		else if(this.getType() == ObjectType.DOORDOWN)
		{
			Image img = new Image("Resources\\arrowDown.png");
			r.setFill(new ImagePattern(img));
		}
		else if(this.getType() == ObjectType.DOORLEFT)
		{
			Image img = new Image("Resources\\arrowLeft.png");
			r.setFill(new ImagePattern(img));
		}
		else if(this.getType() == ObjectType.DOORRIGHT)
		{
			Image img = new Image("Resources\\arrowRight.png");
			r.setFill(new ImagePattern(img));
		}
		
		this.setShape(r);
		level.getChildren().add(r);
	}

	@Override
	public void activateObject()
	{
		if (isActive == false)
		{
			isActive = true;
			switch (nextLevel)
			{
				case "1": nextLevel = "IOFiles\\level1.txt"; break;
				case "2": nextLevel = "IOFiles\\level2.txt"; break;
				case "3": nextLevel = "IOFiles\\level3.txt"; break;
				case "4": nextLevel = "IOFiles\\level4.txt"; break;
				case "9": return;
			}
	
			game.setLevel(nextLevel);
		}
	}
}

