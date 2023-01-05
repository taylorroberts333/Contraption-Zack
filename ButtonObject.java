import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public class ButtonObject extends Object
{
	private boolean isActive = false;
	private Color timerColor;
	private Shape timerShape;
	public ButtonObject(int id, String type, String color, double gridx, double gridy) 
	{
		super(id, type, color, gridx, gridy);
	}

	@Override
	public void paintObject()
	{
		if(this.getType() == ObjectType.BUTTONSQUARE || this.getType() == ObjectType.BUTTONSQUARETIMER)
		{
			Rectangle r = new Rectangle();
			double rectangleX = this.getgridX() * 60;
			double rectangleY = this.getgridY() * 60;
			r.setX(rectangleX);
			r.setY(rectangleY);
			r.setWidth(60);
			r.setHeight(60);

			r.setFill(this.color);
			r.setStrokeType(StrokeType.INSIDE);
			r.setStrokeWidth(6);
			r.setStroke(Color.DARKGREY);

			this.setShape(r);
			level.getChildren().add(r);

			this.timerColor = this.color;
			this.timerShape = this.shape;
		}
		else if(this.getType() == ObjectType.BUTTONCIRCLE || this.getType() == ObjectType.BUTTONCIRCLETIMER)
		{
			Circle c = new Circle();
			double circleX = this.getgridX() * 60 + 30;
			double circleY = this.getgridY() * 60 + 30;
			c.setCenterX(circleX);
			c.setCenterY(circleY);
			c.setRadius(25);

			c.setFill(this.color);
			c.setStrokeType(StrokeType.INSIDE);
			c.setStrokeWidth(6);
			c.setStroke(Color.DARKGRAY);

			this.setShape(c);
			level.getChildren().add(c);

			this.timerColor = this.color;
			this.timerShape = this.shape;
		}
	}	

	@Override
	public void activateObject()
	{
		if (this.getType() == ObjectType.BUTTONSQUARE || this.getType() == ObjectType.BUTTONCIRCLE)
		{
			if (isActive == false)
			{
				for (Object object : levelObjects)
				{
					if ((object.type == ObjectType.VSPIKE || object.type == ObjectType.HSPIKE) &&
						object.color == this.color)
					{
						object.activateObject();
					}
				}
				
            if(this.color == Color.DODGERBLUE)
               this.shape.setFill(Color.BLUE);
            else if(this.color == Color.PURPLE)
				   this.shape.setFill(Color.INDIGO);
            else if(this.color == Color.ORANGE)
				   this.shape.setFill(Color.CHOCOLATE);
            else if(this.color == Color.GOLD)
				   this.shape.setFill(Color.GOLDENROD);
            else if(this.color == Color.GREEN)
				   this.shape.setFill(Color.DARKGREEN);
            
            this.shape.setStrokeWidth(2);
            this.shape.setStroke(Color.web("#555555"));
            
				isActive = true;
			}
		}
		else if (this.getType() == ObjectType.BUTTONSQUARETIMER || this.getType() == ObjectType.BUTTONCIRCLETIMER)
		{
			if (isActive == false)
			{
				isActive = true;

				for (Object object : levelObjects)
				{
					if ((object.type == ObjectType.VSPIKE || object.type == ObjectType.HSPIKE) &&
						object.color == timerColor)
					{
						object.activateObject();
					}
				}
            
            //for changing color when pressed
            if(this.color == Color.DODGERBLUE)
               this.shape.setFill(Color.DARKBLUE);
            else if(this.color == Color.PURPLE)
				   this.shape.setFill(Color.INDIGO);
            else if(this.color == Color.ORANGE)
				   this.shape.setFill(Color.CHOCOLATE);
            else if(this.color == Color.GOLD)
				   this.shape.setFill(Color.GOLDENROD);
            else if(this.color == Color.GREEN)
				   this.shape.setFill(Color.DARKGREEN);
            
            this.shape.setStrokeWidth(2);
            this.shape.setStroke(Color.web("#555555"));
            
				isActive = true;

				Timer myTimer = new Timer();
				myTimer.schedule(new TimerTask()
				{
					@Override
					public void run() 
					{
						for (Object object : levelObjects)
						{
							if ((object.type == ObjectType.VSPIKE || object.type == ObjectType.HSPIKE) &&
								object.color == timerColor)
							{
								object.activateObject();
							}
						}
						timerShape.setFill(timerColor);
						isActive = false;
					}
				}, 10000);
			}
		}
	}
}