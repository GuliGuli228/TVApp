package org.curs.tvui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavaFXApp extends Application {
    private ConfigurableApplicationContext context;

    @Override
    public void init(){
        context = SpringApplication.run(JavaFXApp.class);
    }
    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void stop() throws Exception {
        context.close();
    }
}
