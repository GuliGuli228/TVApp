package org.curs.AppClient.ScenesControllers.DialogControllers;

import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import org.curs.AppClient.Enums.ApiPaths;
import org.curs.AppClient.Enums.ApiRequests;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AbstractDialogController;
import org.curs.AppClient.ScenesControllers.SceneManager;
import org.curs.AppClient.Utils.ApiUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddAgentController extends AbstractDialogController {

    @FXML
    private TextField NameAgentField;
    @FXML
    private TextField LoginAgentField;
    @FXML
    private TextField PasswordAgentField;
    @FXML
    private TextField PercentAgentField;


    @FXML
    public void initialize() {
        CancelDialogButton.setOnAction(SceneManager::closeDialog);

        Map<TextInputControl, String> textFieldRegexMap = new HashMap<>();

        textFieldRegexMap.put(NameAgentField,"([\\p{L}]+\\s){2}[\\p{L}]+");
        textFieldRegexMap.put(LoginAgentField,"[A-Za-z]+");
        textFieldRegexMap.put(PasswordAgentField,"\\w+");
        textFieldRegexMap.put(PercentAgentField,"\\d{2}.\\d{2}");

        AddDialogButton.setOnAction(event -> {
            validate(textFieldRegexMap);
            if (isValid(textFieldRegexMap)){
                //TODO Прописать Exception
                try {
                    JsonObject agentData = new JsonObject();
                    agentData.addProperty("agentName",NameAgentField.getText());
                    agentData.addProperty("login",LoginAgentField.getText());
                    agentData.addProperty("password",PasswordAgentField.getText());
                    agentData.addProperty("percent",PercentAgentField.getText());
                    agentData.addProperty("customerName",CustomerName.getText());

                    ApiUtil.bodyRequest(ApiPaths.POST_AGENT, ApiRequests.POST, agentData);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                SceneManager.closeDialog(event);
            }
        });

        NameAgentField.setOnMouseClicked(event -> resetFromError(NameAgentField));
        LoginAgentField.setOnMouseClicked(event -> resetFromError(LoginAgentField));
        PasswordAgentField.setOnMouseClicked(event -> resetFromError(PasswordAgentField));
        PercentAgentField.setOnMouseClicked(event -> resetFromError(PercentAgentField));
    }
}
