package com.rutgers.rucafe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Class that creates a Stage from Application
 * in which the fxml file consisting of the GUI code
 * is set to and shown. The GUI is launched and
 * displayed.
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class ShopMainMenuMain extends Application {

    /**
     * Default constructor for creating the ShopMainMenu
     */
    public ShopMainMenuMain() {
    }

    /**
     * Loads mainmenu fxml file, creates the scene and sets the title, scene, and scale
     *
     * @param stage Stage where everything will be displayed
     *
     * @throws IOException If fxml file is not found or there's an error
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShopMainMenuMain.class.getResource("ShopMainMenuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("RUCafeWest");
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.show();
    }

    /**
     * Launches the window
     *
     * @param args Commandline argument
     */
    public static void main(String[] args) {
        launch();
    }
}

