import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.ImagePattern;
import javafx.geometry.Bounds;


public class Zack
{
	private static final String ZACK_IMAGE_LOC =
	"Resources\\zackRun.png";
	private Image zackImage;
	private Node  zack;
	private double sceneWidth;
	private double sceneHeight;
	private double startXGrid;
	private double startYGrid;
   Rectangle zackRec = new Rectangle();

	private boolean leftStartingPoint = false;

	private ArrayList<Object> levelObjects;

	boolean running, goNorth, goSouth, goEast, goWest;	

	public Zack(Group level, ArrayList<Object> levelObjects) 
	{
		zackImage = new Image("Resources\\zackStill.png");

		//Rectangle zackRec = new Rectangle();
		zackRec.setWidth(23);
		zackRec.setHeight(35);
		zackRec.setFill(new ImagePattern(zackImage));
		zack = zackRec;

		this.levelObjects = levelObjects;

		level.getChildren().add(zack);
	}

	public void addZackToScene(Scene scene, double startX, double startY, int gridWidth, int gridLength) 
	{
		sceneWidth = gridWidth*60;
		sceneHeight = gridLength*60;
		this.startXGrid = startX;
		this.startYGrid = startY;

		// These lines scale the rectangle so that it is centered in the given grid location
		//   For example, if the location is 5 2, it would be (300, 120)
		//   Then, we have to account for the size of the rectangle which is 30x30 by 
		//   adding to the coordinate -> (330, 150)
		startX *= 60;
		startY *= 60;
		startX += 30;
		startY += 30;
		moveZackTo(startX, startY);
	}

	public void addListenerstoScene(Scene scene)
	{
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    goNorth = true; break;
                    case DOWN:  goSouth = true; break;
                    case LEFT:  goWest  = true; break;
                    case RIGHT: goEast  = true; break;
                    case SHIFT: running = true; break;
                }
                zackImage = new Image(ZACK_IMAGE_LOC);
				zackRec.setFill(new ImagePattern(zackImage));
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    goNorth = false; break;
                    case DOWN:  goSouth = false; break;
                    case LEFT:  goWest  = false; break;
                    case RIGHT: goEast  = false; break;
                    case SHIFT: running = false; break;
                }
                zackImage = new Image("Resources\\zackStill.png");
			zackRec.setFill(new ImagePattern(zackImage));
            }
        });

		AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (goNorth) dy -= 3;
                if (goSouth) dy += 3;
                if (goEast)  dx += 3;
                if (goWest)  dx -= 3;
                if (running) { dx *= 3; dy *= 3; }

                moveZackBy(dx, dy);
            }
        };
		timer.start();
	}

	// Takes in the coordinates of a location and relocates character
	private void moveZackTo(double x, double y) 
	{
		// These two lines get the center of zack's dimensions
		//   Since zack is a 64x64 image, cx = cy = 32
		final double cx = zack.getBoundsInLocal().getWidth()  / 2;
		final double cy = zack.getBoundsInLocal().getHeight() / 2;
		
		// String gridLocationX = String.format("%.0f", (x - cx) / 60);
		// String gridLocationY = String.format("%.0f", (y - cy) / 60);
		// System.out.println("[" + (x - cx) / 60+ ", " +(y - cy) / 60 + "]");

		Point2D originalLocation = zack.localToParent(0,0);
		// This if statement is ensuring that the zack does not go off the edge of the screen
		if (x - cx >= 0 &&
			x + cx <= sceneWidth &&
			y - cy >= 0 &&
			y + cy <= sceneHeight) {
			zack.relocate(x - cx, y - cy);
			if (isCollidingWithObject())
			{
				zack.relocate(originalLocation.getX(), originalLocation.getY());
			}

			// leftStartingPoint is used to make sure the door that Zack spawns on doesn't make him 
			//   instantly switch levels; to go back a level, you have to leave the starting point and
			//   walk back onto it
			if (this.leftStartingPoint == false)
			{
				leftStartingPoint = hasLeftStartingPoint(zack);
			}
		}
	}

	private void moveZackBy(int dx, int dy) 
	{
		if (dx == 0 && dy == 0) return;

		final double cx = zack.getBoundsInLocal().getWidth()  / 2;
		final double cy = zack.getBoundsInLocal().getHeight() / 2;

		double x = cx + zack.getLayoutX() + dx;
		double y = cy + zack.getLayoutY() + dy;

		moveZackTo(x, y);
    }

	private boolean isCollidingWithObject() 
	{
		for (Object object : levelObjects)
		{
			if (object.getType() == ObjectType.WALL)
			{

				if (zack.getBoundsInParent().intersects(object.getShape().getBoundsInParent()))
				{
					return true;
				}
			}
			else if (object.getType() == ObjectType.HSPIKE || object.getType() == ObjectType.VSPIKE)
			{
				if (zack.getBoundsInParent().intersects(object.getShape().getBoundsInParent()))
				{
					return true;
				}
			}
			else if (object.getType() == ObjectType.BUTTONSQUARE || object.getType() == ObjectType.BUTTONCIRCLE ||
					object.getType() == ObjectType.BUTTONSQUARETIMER || object.getType() == ObjectType.BUTTONCIRCLETIMER)
			{
         
            Bounds oldBounds = object.getShape().getBoundsInParent();
			Rectangle newRec = new Rectangle(oldBounds.getMinX() + 15, oldBounds.getMinY() + 30, oldBounds.getWidth() - 30, oldBounds.getHeight() - 30);
				
            if (zack.getBoundsInParent().intersects(newRec.getBoundsInParent()))
				{
					// within the button's activateObject method is where we could handle the activation of spikes and stuff
					object.activateObject();
				}
			}
			else if (object.getType() == ObjectType.SPRINGLEFT || object.getType() == ObjectType.SPRINGRIGHT)
			{
            Bounds oldBounds = object.getShape().getBoundsInParent();
			Rectangle newRec = new Rectangle(oldBounds.getMinX() + 15, oldBounds.getMinY() + 30, oldBounds.getWidth() - 30, oldBounds.getHeight() - 30);
				
            if (zack.getBoundsInParent().intersects(newRec.getBoundsInParent()))
				{
					object.activateObject();
					if (object.getType() == ObjectType.SPRINGLEFT)
					{
						moveZackBy(-110, 0);
					}
					else
					{
						moveZackBy(110, 0);
					}
				}
			}
			else if (object.getType() == ObjectType.DOORUP || object.getType() == ObjectType.DOORDOWN || object.getType() == ObjectType.DOORLEFT || object.getType() == ObjectType.DOORRIGHT)
			{
            Bounds oldBounds = object.getShape().getBoundsInParent();
			Rectangle newRec = new Rectangle(oldBounds.getMinX() + 15, oldBounds.getMinY() + 30, oldBounds.getWidth() - 30, oldBounds.getHeight() - 30);
            
				if (zack.getBoundsInParent().intersects(newRec.getBoundsInParent()))
				{
					if (leftStartingPoint)
					{
						object.activateObject();
					}
				}
			}
			else if (object.getType() == ObjectType.HSLIDER || object.getType() == ObjectType.VSLIDER)
			{
				if (zack.getBoundsInParent().intersects(object.getShape().getBoundsInParent()))
				{
					return true;
				}
			}
			else if (object.getType() == ObjectType.SCREWDRIVER)
			{
				if (zack.getBoundsInParent().intersects(object.getShape().getBoundsInParent()))
				{
					object.activateObject();
				}
			}
		}

		return false;
	}

	private boolean hasLeftStartingPoint(Node zack)
	{
		Rectangle startingRectangle = new Rectangle();
		double rectangleX = startXGrid * 60;
		double rectangleY = startYGrid * 60;
		startingRectangle.setWidth(50);
		startingRectangle.setHeight(50);
		startingRectangle.setX(rectangleX + 5);
		startingRectangle.setY(rectangleY + 5);

		if (zack.getBoundsInParent().intersects(startingRectangle.getBoundsInLocal()))
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
}