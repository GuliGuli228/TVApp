package org.curs.AppClient.ScenesControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    public void getData(ActionEvent actionEvent) {

        String login ="";
        String password ="";

        if (!loginField.getText().equals("") && !passwordField.getText().equals("")) {
            login = loginField.getText();
            password = passwordField.getText();
        }
        else{
            WelcomeSceneController.showAlert("Please enter your login or password");
            return;
        }

        HttpURLConnection connection;
        try {
            connection = ApiUtil.fetchApi("/login/login?login="+login+"&password=" + password, ApiUtil.RequestMethod.POST,null);
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println(connection.getResponseMessage());
            }
            else{
                if (connection.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                   WelcomeSceneController.showAlert("You are not authorized");
                }
                if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                    WelcomeSceneController.showAlert("User not found");
                }
            }

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private static void showAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(text);
    }
}
