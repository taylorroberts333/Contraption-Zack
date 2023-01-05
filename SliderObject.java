import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SliderObject extends Object {
	private boolean isActive = false;
	
	private Rectangle sliderShape = new Rectangle();
	private long startTime;
	private long openTime = 15000;
	
	public SliderObject(int id, String type, String color, double gridx, double gridy, long startTime) 
	{
		super(id, type, color, gridx, gridy);
		this.startTime = startTime * 1000;
	}

	@Override
	public void paintObject()
	{
		double rectangleX = this.getgridX() * 60;
		double rectangleY = this.getgridY() * 60;
		sliderShape.setX(rectangleX);
		sliderShape.setY(rectangleY);
		sliderShape.setFill(this.color);
		this.setShape(sliderShape);
		level.getChildren().add(sliderShape);



		if(this.getType() == ObjectType.HSLIDER)
		{
			sliderShape.setHeight(15);
			sliderShape.setWidth(60);
		}
		else if(this.getType() == ObjectType.VSLIDER)
		{
			sliderShape.setHeight(60);
			sliderShape.setWidth(15);
		}

		Timer myTimer = new Timer();
		myTimer.schedule(new TimerTask()
		{
			@Override
			public void run() 
			{
				activateObject();
				isActive = false;
			}
		}, this.startTime);
	}

	@Override
	public void activateObject()
	{
		
		// if the slider is not activated, we need to open it
		if (isActive == false)
		{
			this.setShape(new Rectangle());
			this.isActive = true;
			sliderShape.setFill(Color.TRANSPARENT);
			Timer myTimer = new Timer();
			myTimer.schedule(new TimerTask()
			{
				@Override
				public void run() 
				{
					activateObject();
				}
			}, 2500);
			openTime = 15000;
		}
		// If it is activated, we need to close it
		else if (isActive == true)
		{
			this.setShape(sliderShape);
			sliderShape.setFill(this.color);
			this.isActive = false;
			Timer myTimer = new Timer();
			myTimer.schedule(new TimerTask()
			{
				@Override
				public void run() 
				{
					activateObject();
				}
			}, openTime);
		}
	}
}
