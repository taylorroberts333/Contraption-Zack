import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.io.File;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.Timeline;

public class WallObject extends Object
{
	double height, width;
	public WallObject(int id, String type, String color, double gridX, double gridY, double width, double height) {
		super(id, type, color, gridX, gridY);
		//TODO Auto-generated constructor stub

		this.width = width;
		this.height = height;
	}
	
	@Override
	public void paintObject()
	{
		Rectangle r = new Rectangle();
		double rectangleX = this.getgridX() * 60;
		double rectangleY = this.getgridY() * 60;
		r.setX(rectangleX);
		r.setY(rectangleY);
		
		if(this.color == Color.DODGERBLUE)
		{
			Image i = new Image(new File("Resources\\jukebox1.gif").toURI().toString());
			r.setFill(new ImagePattern(i));
		}
		else if(this.color == Color.ORANGE)
		{
			r.setFill(Color.BLUE);

			FadeTransition ft = new FadeTransition(Duration.millis(2000), r);
			ft.setFromValue(1.0);
			ft.setToValue(0.5);
			ft.setCycleCount(-1);
			ft.setAutoReverse(true);

			ft.play();
		}
		else if(this.color == Color.PINK)
		{
			Image i = new Image(new File("Resources\\rope.png").toURI().toString());
			r.setFill(new ImagePattern(i));
		}
		else{
			r.setFill(this.color);
		}
		// if (this.type == ObjectType.WALL)
		// {
		// 	r.setWidth(60);
		// 	r.setHeight(60);
		// }
		// else if (this.type == ObjectType.HWALLHALF)
		// {
		// 	r.setHeight(30);
		// 	r.setWidth(60);
		// }
		// else if (this.type == ObjectType.VWALLHALF)
		// {
		// 	r.setHeight(60);
		// 	r.setWidth(30);
		// }
		// else if (this.type == ObjectType.WALLBOX)
		// {
		// 	r.setHeight(15);
		// 	r.setWidth(60);
		// }
		// else if (this.type == ObjectType.WALLFOURTH)
		// {
		// 	r.setHeight(30);
		// 	r.setWidth(30);
		// }
		// else if (this.type == ObjectType.WALLCUSTOM)
		// {
		// 	r.setHeight(this.height);
		// 	r.setWidth(this.width);
		// }
		
		r.setWidth(this.width);
		r.setHeight(this.height);

		this.setShape(r);
		level.getChildren().add(r);
	}
}
