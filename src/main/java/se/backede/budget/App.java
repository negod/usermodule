package se.backede.budget;

import se.backede.budget.dao.CacheInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * JavaFX App
 */
@Slf4j
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        CacheInitializer cacheInitializer = new CacheInitializer();
        scene = new Scene(loadFXML("login/login"), 330, 150);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
