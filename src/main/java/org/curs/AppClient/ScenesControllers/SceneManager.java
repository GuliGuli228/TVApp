package org.curs.AppClient.ScenesControllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.curs.AppClient.AppCache;
import org.curs.AppClient.JavaFXApp;
import org.curs.AppClient.ScenesControllers.AdminControllers.*;
import org.curs.AppClient.ScenesControllers.AgentContollers.AgentContractController;
import org.curs.AppClient.ScenesControllers.AgentContollers.AgentCustomersController;
import org.curs.AppClient.ScenesControllers.CommonControllers.AccountSceneController;
import org.curs.AppClient.ScenesControllers.CommonControllers.PlaybackController;
import org.curs.AppClient.ScenesControllers.CommonControllers.PromoController;
import org.curs.AppClient.ScenesControllers.CommonControllers.TelecastController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class SceneManager {
    private static final Logger logger = Logger.getLogger(SceneManager.class.getName());

    private final static Map<String, String> scenePaths = new HashMap<>();


    // Добавляю все сцены и их пути, чтобы можно было переключаться между сценами по их названиям
    static {
            scenePaths.put("WelcomeScene", "/FXMLScenes/WelcomeScene.fxml");
            scenePaths.put("AgentScene", "/FXMLScenes/AgentScene.fxml");
            scenePaths.put("AdminScene", "/FXMLScenes/AdminScene.fxml");
            scenePaths.put("AccountScene", "/FXMLScenes/AccountScene.fxml");
    }

    public static void showScene(String sceneName, Stage stage, Object controller) {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(Objects.requireNonNull(SceneManager.class.getResource(scenePaths.get(sceneName))));
            loader.setController(controller);
            logger.info("current controller: " + loader.getController().getClass().getName());
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Ошибка зак грузки сцены");
            e.printStackTrace();
        }
    }
    public static void switchScene(Scenes scene){
        String roleName = AppCache.getRole();
        Object controller = null;
        switch (scene){
            case ADMIN_CONTRACTS -> controller = new AdminContractController();
            case ADMIN_CUSTOMERS -> controller =new AdminCustomersController();
            case AGENTS -> controller =new AdminAgentsController();
            case AGENT_CONTRACTS -> controller =new AgentContractController();
            case AGENT_CUSTOMERS -> controller =new AgentCustomersController();
            case PLAYBACK -> controller =new PlaybackController();
            case PROMO -> controller =new PromoController();
            case TELECAST -> controller =new TelecastController();
            case ACCOUNT -> controller = new AccountSceneController();
        }
        if (scene == Scenes.ACCOUNT) SceneManager.showScene("AccountScene",JavaFXApp.getPrimaryStage(),controller);
        else {
            if (roleName.equals("Admin")) SceneManager.showScene("AdminScene", JavaFXApp.getPrimaryStage(), controller);
            if (roleName.equals("Agent")) SceneManager.showScene("AgentScene", JavaFXApp.getPrimaryStage(), controller);
        }
        AppCache.setLastScene(scene);
    }

    public static void showDialog(String dialogName, Stage ownerStage,Object controller)  {
        Stage stage = new Stage();
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(SceneManager.class.getResource(dialogName)));
            loader.setController(controller);
            root = loader.load();
        } catch (IOException e) {
            System.err.println("Error loading Dialog" + e.getMessage());
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.initOwner(ownerStage);
        stage.showAndWait();
    }
}
