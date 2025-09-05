package org.curs.AppClient;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.Getter;
import org.curs.AppClient.ScenesControllers.CommonControllers.WelcomeSceneController;
import org.curs.AppServer.TvApp;
import org.curs.AppClient.ScenesControllers.SceneManager;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;


public class JavaFXApp extends Application {
    //Контекст для управления жизненным циклом сервера
    private ConfigurableApplicationContext context;
    //Сцена для отрисовки в других классах
    @Getter
    private static Stage primaryStage;

    //Запускаю сервер перед javaFx
    @Override
    public void init(){
        context = SpringApplication.run(TvApp.class);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setResizable(false);
        stage.setTitle("TV Application");
        primaryStage = stage;
        SceneManager.showScene("WelcomeScene", stage, new WelcomeSceneController());
    }

    // При остановке приложения отключаю сервер
    @Override
    public void stop() throws Exception {
        context.close();
    }
}
