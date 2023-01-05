import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SpikeObject extends Object
{
	private boolean isActive = false;
	private Circle c1 = new Circle();
	private Circle c2 = new Circle();
	private Circle c3 = new Circle();
	private Rectangle spikeShape = new Rectangle();
	private double circleX;
	private double circleY;

	public SpikeObject(int id, String type, String color, double gridx, double gridy, String isActiveStr) 
	{
		super(id, type, color, gridx, gridy);
		this.isActive = isActiveStr.equals("N") ? false : true;
		circleX = this.getgridX() * 60;
		circleY = this.getgridY() * 60;

		c1.setRadius(6.5);
		c2.setRadius(6.5);
		c3.setRadius(6.5);

		if (this.getType() == ObjectType.VSPIKE)
		{
			c1.setCenterX(circleX+10);
			c1.setCenterY(circleY+11);
			c2.setCenterX(circleX+10);
			c2.setCenterY(circleY+30);
			c3.setCenterX(circleX+10);
			c3.setCenterY(circleY+49);
		}
		else if(this.getType() == ObjectType.HSPIKE)
		{
			c1.setCenterX(circleX+11);
			c1.setCenterY(circleY-8);
			c2.setCenterX(circleX+30);
			c2.setCenterY(circleY-8);
			c3.setCenterX(circleX+49);
			c3.setCenterY(circleY-8);
		}
}

	@Override
	public void activateObject()
	{
		if (isActive == false)
		{
			isActive = true;
			lowerSpike();
		}	
		else 
		{
			isActive = false;
			raiseSpike();
		}
	}

	public boolean isActive()
	{
		return isActive;
	}

	@Override
	public Shape getShape()
	{
		return this.shape;
	}

	@Override
	public void paintObject()
	{
		// This rectangle is an invisible barrier that blocks the player from moving through the spikes
		boolean isVSpike = this.getType() == ObjectType.VSPIKE;
		double rectangleX = isVSpike ? this.getgridX() * 60 + 5 : this.getgridX() * 60;
		double rectangleY =  isVSpike ? this.getgridY() * 60 : this.getgridY() * 60 - 13;
		double rectangleWidth = isVSpike ? 10 : 60;
		double rectangleHeight = isVSpike ? 60 : 10;

		spikeShape.setX(rectangleX);
		spikeShape.setY(rectangleY);
		spikeShape.setWidth(rectangleWidth);
		spikeShape.setHeight(rectangleHeight);

		spikeShape.setFill(Color.TRANSPARENT); // this is for debugging
		this.setShape(spikeShape);

		level.getChildren().add(spikeShape);

		level.getChildren().add(c1);
		level.getChildren().add(c2);
		level.getChildren().add(c3);

		if (isActive)
		{
			this.lowerSpike();
		}
		else
		{
			this.raiseSpike();
		}
	}

	private void lowerSpike() 
	{
		c1.setFill(Color.GRAY);
		c2.setFill(Color.GRAY);
		c3.setFill(Color.GRAY);

		this.setShape(new Rectangle());
	}

	private void raiseSpike()
	{
		applyGradient();

		this.setShape(spikeShape);
	}

	private void applyGradient()
	{
		Color darkerColor = Color.BLUE;

		//set gradient
		if(this.color == Color.DODGERBLUE)
			darkerColor = Color.DARKBLUE;

		if(this.color == Color.ORANGE)
			darkerColor = Color.DARKRED;

		if(this.color == Color.GOLD)
			darkerColor = Color.ORANGE;

		if(this.color == Color.PURPLE)
			darkerColor = Color.MIDNIGHTBLUE;

		if(this.color == Color.GREEN)
			darkerColor = Color.DARKSLATEGRAY;
         
      if(this.color == Color.LIGHTGRAY)
			darkerColor = Color.BLACK;

		if (this.getType() == ObjectType.VSPIKE)
		{
			RadialGradient gradient1 = new RadialGradient(70,
			.1,
			circleX+10,
			circleY+11,
			7.5,
			false,
			CycleMethod.NO_CYCLE,
			new Stop(0, this.color),
			new Stop(1, darkerColor));
			RadialGradient gradient2 = new RadialGradient(70,
			.1,
			circleX+10,
			circleY+30,
			7.5,
			false,
			CycleMethod.NO_CYCLE,
			new Stop(0, this.color),
			new Stop(1, darkerColor));
			RadialGradient gradient3 = new RadialGradient(70,
			.1,
			circleX+10,
			circleY+49,
			7.5,
			false,
			CycleMethod.NO_CYCLE,
			new Stop(0, this.color),
			new Stop(1, darkerColor));
			c1.setFill(gradient1);
			c2.setFill(gradient2);
			c3.setFill(gradient3);
		}
		else if(this.getType() == ObjectType.HSPIKE)
		{
			RadialGradient gradient1 = new RadialGradient(70,
			.1,
			circleX+11,
			circleY-8,
			7.5,
			false,
			CycleMethod.NO_CYCLE,
			new Stop(0, this.color),
			new Stop(1, darkerColor));
			RadialGradient gradient2 = new RadialGradient(70,
			.1,
			circleX+30,
			circleY-8,
			7.5,
			false,
			CycleMethod.NO_CYCLE,
			new Stop(0, this.color),
			new Stop(1, darkerColor));
			RadialGradient gradient3 = new RadialGradient(70,
			.1,
			circleX+49,
			circleY-8,
			7.5,
			false,
			CycleMethod.NO_CYCLE,
			new Stop(0, this.color),
			new Stop(1, darkerColor));
			c1.setFill(gradient1);
			c2.setFill(gradient2);
			c3.setFill(gradient3);
		}
	}
}