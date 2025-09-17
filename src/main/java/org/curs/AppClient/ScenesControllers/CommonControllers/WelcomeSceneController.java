package org.curs.AppClient.ScenesControllers.CommonControllers;

import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.curs.AppClient.AppCache;
import org.curs.AppClient.Enums.ApiPaths;
import org.curs.AppClient.Enums.ApiRequests;
import org.curs.AppClient.Enums.Scenes;
import org.curs.AppClient.JavaFXApp;
import org.curs.AppClient.ScenesControllers.AdminControllers.AdminContractController;
import org.curs.AppClient.ScenesControllers.AgentContollers.AgentContractController;
import org.curs.AppClient.ScenesControllers.SceneManager;
import org.curs.AppClient.Utils.ApiUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Objects;


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


        //TODO переписать на ParametricRequest и Post(Логи и пароль прередавать в теле а не в URL)
        connection = ApiUtil.fetchApi("/api/v1/user/login?login="+login+"&password="+password, ApiRequests.POST,null);

        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            JsonObject user = Objects.requireNonNull(ApiUtil.parametricRequest(ApiPaths.GET_USER_DATA, ApiRequests.GET, Map.of("login", login))).getAsJsonObject();
            System.out.println(user);

            String role = user.get("role").getAsString();
            AppCache.setRole(role);
            AppCache.setUserId(user.get("id").getAsInt());
            AppCache.setUserName(user.get("name").getAsString());
            AppCache.setUserLogin(login);

            AppCache.loadCache();


            if(role.equals("Admin")){
                SceneManager.showScene("AdminScene", JavaFXApp.getPrimaryStage(),new AdminContractController());
                AppCache.setLastScene(Scenes.ADMIN_CONTRACTS);
            }
            if(role.equals("Agent")){
                SceneManager.showScene("AgentScene", JavaFXApp.getPrimaryStage(),new AgentContractController());
                AppCache.setLastScene(Scenes.AGENT_CONTRACTS);
            }

        }
        else{//FIXME
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
