import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class FloorObject extends Object
{
	public FloorObject(int id, String type, String color, double gridX, double gridY) {
		super(id, type, color, gridX, gridY);
	}

	@Override
	public void paintObject()
	{
			Rectangle r = new Rectangle();
			double rectangleX = this.getgridX() * 60;
			double rectangleY = this.getgridY() * 60;
			r.setWidth(60);
			r.setHeight(60);
			r.setX(rectangleX);
			r.setY(rectangleY);
			r.setFill(this.color);
			r.setStroke(Color.DARKGREY);

			if(this.color == Color.BROWN)
			{
				Image img = new Image("Resources\\cobblestone.jpeg");
				r.setFill(new ImagePattern(img));
			}
			else if(this.color == Color.PINK)
			{
				Image img = new Image("Resources\\floor4.png");
				r.setFill(new ImagePattern(img));
			}
			else
			{
				r.setFill(this.color);
			}
			
			this.setShape(r);
			level.getChildren().add(r);
	}
}