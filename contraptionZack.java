import javafx.application.Application;
import javafx.stage.Stage;


public class ContraptionZack extends Application
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	//stage is the main screen that holds the game
	public void start(Stage stage)
	{
		Game game = new Game(stage);
		// game.startGame();
	}
}