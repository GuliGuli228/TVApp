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
    public static void switchScene(Scenes scene){
        String roleName = AppContext.getRole();
        Object controller = null;
        switch (scene){
            case ADMIN_CONTRACTS -> controller = new AdminContractController();
            case ADMIN_CUSTOMERS -> controller =new AdminCustomersController();
            case ADMIN_PLAYBACK -> controller =new AdminPlaybackController();
            case AGENTS -> controller =new AdminAgentsController();
            case FINANCES -> controller =new FinancesController();
            case AGENT_CONTRACTS -> controller =new AgentContractController();
            case AGENT_PLAYBACK -> controller =new AgentPlaybackController();
            case AGENT_CUSTOMERS -> controller =new AgentCustomersController();
            case PROMO -> controller =new PromoController();
            case TELECAST -> controller =new TelecastController();
        }
        if (roleName.equals("Admin")) SceneManager.showScene("AdminScene", JavaFXApp.getPrimaryStage(), controller);
        if (roleName.equals("Agent")) SceneManager.showScene("AgentScene", JavaFXApp.getPrimaryStage(), controller);
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
