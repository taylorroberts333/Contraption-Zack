import javafx.event.ActionEvent;
import javafx.event.EventHandler; 
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundPosition;
import javafx.geometry.Pos;

public class Game 
{
	private Stage stage;
	private Level currentLevel;

	private MenuBar mb;
	
	private String levelString = "IOFiles\\level1.txt";

	private int saveGameCount = 0;
	
	public Game(Stage stage)
	{
		this.stage = stage;
		// this.stage.setHeight(600);
		// this.stage.setWidth(1000);

		this.stage.sizeToScene();
		this.stage.setTitle("Contraption Zack");
		this.buildMenu();
		createTitleScene();
	}

	public void startGame()
	{
		this.currentLevel = new Level(this);
		stage.setScene(currentLevel.buildLevel("IOFiles\\level1.txt"));
		currentLevel.getGroup().getChildren().addAll(mb);
		stage.show();


		//setting stage
		stage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) ->
		{
			if(event.getCode() == KeyCode.ESCAPE)
			{
				this.mb.getMenus().get(0).show();
			}
		});
	}

	public void setLevel(String levelString)
	{
		this.levelString = levelString;
		this.currentLevel = new Level(this);
		stage.setScene(currentLevel.buildLevel(levelString));
		currentLevel.getGroup().getChildren().addAll(mb);
	}

	private void buildMenu()
	{
		Menu menu = new Menu();
		//create menu items
		MenuItem item1 = new MenuItem("Restart Area");
		MenuItem item2 = new MenuItem("Restart Level");
		MenuItem item3 = new MenuItem("Save Game");
		Menu item4 = new Menu("Load Game");
		MenuItem item5 = new MenuItem("Quit Game");

		item1.setOnAction(e -> {
			startGame();
		});
		item2.setOnAction(e -> {
			setLevel(this.levelString);
		});
		item3.setOnAction(e -> {
			if (saveGameCount < 11)
			{
				// saveGames.getItems();
				item4.getItems().addAll(new MenuItem("Save Game #" + ++saveGameCount));
			}
			else
			{
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Save level error!");
				errorAlert.setContentText("You are limited to 10 save files");
				errorAlert.showAndWait();
			}
		});
		item4.setOnAction(e -> {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Load level error!");
			errorAlert.setContentText("The load level feature is not a part of Contraption Zack v.0.0.1");
			errorAlert.showAndWait();
		});
		item5.setOnAction(e -> {
			createTitleScene();
		});
		
		
		//add items to menu with separators
		menu.getItems().addAll(item1, new SeparatorMenuItem(), 
								item2, new SeparatorMenuItem(),
								item3, new SeparatorMenuItem(),
								item4, new SeparatorMenuItem(),
								item5, new SeparatorMenuItem());

		
		
		//create menubar, add menu, and make it invisible 
		this.mb = new MenuBar(menu);
		mb.setVisible(false);
	}

	private void createTitleScene()
	{
		BorderPane bPane = new BorderPane();
		Button startButton = new Button("NEW GAME");
		startButton.setStyle("-fx-font-size: 3em; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-color:blue;-fx-text-fill: yellow; -fx-background-radius: 15px;");
		bPane.setCenter(startButton);
		
		//load button no functionality
		Button loadButton = new Button("LOAD GAME");
		loadButton.setStyle("-fx-font-size: 2em; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-color:blue;-fx-text-fill: yellow; -fx-background-radius: 15px;");
		bPane.setTop(loadButton);
		bPane.setAlignment(loadButton, Pos.TOP_RIGHT);
		
		Image image1 = new Image("Resources\\zackStart.png");

		bPane.setBackground(new Background(new BackgroundImage(image1,
			null,
			null,
			BackgroundPosition.DEFAULT,
			BackgroundSize.DEFAULT)));

		// action event 
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{ 
				startGame();
			} 
		}; 


      // action event for load game
		EventHandler<ActionEvent> loadGame = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{ 
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Load level error!");
				errorAlert.setContentText("The load level feature is not a part of Contraption Zack v.0.0.1");
				errorAlert.showAndWait();
			} 
		}; 
	
		// when button is pressed 
		startButton.setOnAction(event);
		loadButton.setOnAction(loadGame);

		Scene scene = new Scene(bPane, 600, 600);
		
		stage.setScene(scene);
		stage.show();
	}
}
