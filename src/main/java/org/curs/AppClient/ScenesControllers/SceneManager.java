package org.curs.tvui.ScenesControllers;

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
    }

    public static void showScene(String sceneName, Stage stage) throws IOException {
        String path = scenePaths.get(sceneName);
        Scene scene = new  Scene(FXMLLoader.load(Objects.requireNonNull(SceneManager.class.getResource(path))));
        stage.setScene(scene);
        stage.show();
    }
}
