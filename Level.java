import javafx.scene.Scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.*; 
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;


public class Level 
{
	private Game game;

	private int gridLength = 0;
	private int gridWidth = 0;
	private double zackStartX = 0;
	private double zackStartY = 0;

	private ArrayList<String> levelObjectStrings = new ArrayList<String>();
	private ArrayList<Object> levelObjects = new ArrayList<Object>();
	private Group level = new Group();
    private HBox centered = new HBox();

	public Level(Game game)
	{
		this.game = game;
	}

	public Scene buildLevel(String levelFile)
	{
		// Parses the file and gets necessary data
		this.parseLevel(levelFile);
		// This loop takes each tile string from the file and makes tile objects
		for (String item : levelObjectStrings) 
		{
			String[] itemArr = item.split("-");
			int id = Integer.valueOf(itemArr[0]);
			String type = itemArr[1];
			String color = itemArr[2];
			Double gridX = Double.valueOf(itemArr[3]);
			Double gridY = Double.valueOf(itemArr[4]);

			switch (type)
			{
				case "f":   levelObjects.add(new FloorObject(id, type, color, gridX, gridY)); break;
				case "w":  
				case "hWH":
				case "vWH":
				case "wB":
				case "wF":
				case "wC": levelObjects.add(new WallObject(id, type, color, gridX, gridY, Double.valueOf(itemArr[5]), Double.valueOf(itemArr[6]))); break;
				case "dU":  
				case "dD":  
				case "dL":  
				case "dR":  levelObjects.add(new DoorObject(id, type, color, gridX, gridY, itemArr[5], this.game)); break;
				case "bS":  
				case "bST":  
				case "bC":  levelObjects.add(new ButtonObject(id, type, color, gridX, gridY)); break;
				case "bCT":  levelObjects.add(new ButtonObject(id, type, color, gridX, gridY)); break;
				case "skH": 
				case "skV": levelObjects.add(new SpikeObject(id, type, color, gridX, gridY, itemArr[5])); break;
				case "spL": 
				case "spR": levelObjects.add(new SpringObject(id, type, color, gridX, gridY)); break;
				case "hSL":
				case "vSL": levelObjects.add(new SliderObject(id, type, color, gridX, gridY, Long.valueOf(itemArr[5]))); break;
				case "scD": levelObjects.add(new ScrewDriverObject(id, type, color, gridX, gridY));
			}
		}

		paintLevel();

		// This method adds a new Zack Node to the floor group
		Zack zack = new Zack(level, levelObjects);

		centered.getChildren().add(level);
		centered.setAlignment(Pos.CENTER);
		centered.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));

		Scene scene = new Scene(centered, 600, 600);

		
		// Once the scene is created, we can add zack to it
		// NOTE* we should change the text file to include the starting point for zack
		zack.addZackToScene(scene, zackStartX, zackStartY, gridWidth, gridLength);

		// adds key event listeners as well as an animation timer
		zack.addListenerstoScene(scene);

		// scene.setFill(Color.BLUE);

		return scene;
	}

	private void parseLevel(String levelFile)
	{
		//create floor group to add to the scene
		try {
			//creating File instance to reference text file in Java 
			File text = new File(levelFile);
			Scanner scnr = new Scanner(text);

			if (scnr.next().equals("grid-size"))
			{
				gridWidth = scnr.nextInt();
				gridLength = scnr.nextInt();
			}
			if (scnr.next().equals("zack-start"))
			{
				zackStartX = scnr.nextDouble();
				zackStartY = scnr.nextDouble();
			}
			if (scnr.next().equals("level-objects"))
			{
				while (scnr.hasNext())
				{
					levelObjectStrings.add(scnr.next());
					scnr.next(); // reads in pipe between each string
				}
			}

			scnr.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void paintLevel() 
	{
		for (Object object : levelObjects)
		{
			object.setLevel(level);
			object.paintObject();
			object.setLevelObjects(levelObjects);
		}
	}

	public Group getGroup()
	{
		return this.level;
	}
}