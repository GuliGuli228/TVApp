package org.curs.AppClient.ScenesControllers.CommonControllers;

import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.curs.AppClient.AppContext;
import org.curs.AppClient.JavaFXApp;
import org.curs.AppClient.ScenesControllers.AdminControllers.AdminContractController;
import org.curs.AppClient.ScenesControllers.AgentContollers.AgentContractController;
import org.curs.AppClient.ScenesControllers.SceneManager;
import org.curs.AppClient.Utils.ApiUtil;

import java.io.IOException;
import java.net.HttpURLConnection;

public class WelcomeSceneController {

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @FXML
    public void getData(ActionEvent actionEvent) throws IOException {

        if (loginField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            WelcomeSceneController.showAlert("Please enter your login or password");
            return;
        }

        String login = loginField.getText();
        String password = passwordField.getText();
        HttpURLConnection connection;


        connection = ApiUtil.fetchApi("/api/v1/user/login?login="+login+"&password="+password, ApiUtil.RequestMethod.POST,null);

        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            JsonObject user = ApiUtil.getUserData("/api/v1/user/login", login,password);
            String role = user.get("role").getAsString();

            System.out.println(user.toString());
            AppContext.setUserId(user.get("id").getAsInt());
            AppContext.setUserName(user.get("login").getAsString());
            AppContext.setRole(role);

            if(role.equals("Admin")) SceneManager.showScene("AdminScene", JavaFXApp.getPrimaryStage(),new AdminContractController());
            if(role.equals("Agent"))SceneManager.showScene("AgentScene", JavaFXApp.getPrimaryStage(),new AgentContractController());
        }
        else{
            if (connection.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                System.out.println(connection.getResponseCode());
                WelcomeSceneController.showAlert("You are not authorized");
            }
            if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println(connection.getResponseCode());
                WelcomeSceneController.showAlert("User not found");
            }
        }

    }

    private static void showAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(text);
    }
}
