import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class ScrewDriverObject extends Object
{
	boolean isActive = false;
	Image image = new Image("Resources\\spring.png");

	public ScrewDriverObject(int id, String type, String color, double gridX, double gridY) 
	{
		super(id, type, color, gridX, gridY);
		//TODO Auto-generated constructor stub
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
		Image img = new Image("Resources\\screwdriverandwrench.png");
		r.setFill(new ImagePattern(img));
		this.setShape(r);
		level.getChildren().add(r);
	}

	@Override
	public void activateObject()
	{
		if (isActive == false)
		{
			isActive = true;
			this.shape.setFill(Color.TRANSPARENT);
			// this.shape.setStrokeType(StrokeType.INSIDE);
			// this.shape.setStrokeWidth(4);
			// this.shape.setStroke(Color.GRAY);
		} 
	}
}