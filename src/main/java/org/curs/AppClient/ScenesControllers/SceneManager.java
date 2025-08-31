package org.curs.AppClient.ScenesControllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SceneManager {

    private final static Map<String, String> scenePaths = new HashMap<>();

    // Добавляю все сцены и их пути, чтобы можно было переключаться между сценами по их названиям
    static {
            scenePaths.put("WelcomeScene", "/FXMLScenes/WelcomeScene.fxml");
            scenePaths.put("AgentScene", "/FXMLScenes/AgentScene.fxml");
    }

    public static void showScene(String sceneName, Stage stage, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(SceneManager.class.getResource(scenePaths.get(sceneName))));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new  Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
