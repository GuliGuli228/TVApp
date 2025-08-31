package org.curs.tvui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.curs.tvapp.TvApp;
import org.curs.tvui.ScenesControllers.SceneManager;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;


public class JavaFXApp extends Application {
    //Контекст для управления жизненным циклом сервера
    private ConfigurableApplicationContext context;

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
        SceneManager.showScene("WelcomeScene", stage);
    }

    // При остановке приложения отключаю сервер
    @Override
    public void stop() throws Exception {
        context.close();
    }
}
