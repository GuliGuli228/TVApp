package org.curs.AppClient.ScenesControllers.DialogControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AbstractDialogController;
import org.curs.AppClient.ScenesControllers.SceneManager;

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
            if (isValid(textFieldRegexMap)) SceneManager.closeDialog(event);
        });

        NameAgentField.setOnMouseClicked(event -> resetFromError(NameAgentField));
        LoginAgentField.setOnMouseClicked(event -> resetFromError(LoginAgentField));
        PasswordAgentField.setOnMouseClicked(event -> resetFromError(PasswordAgentField));
        PercentAgentField.setOnMouseClicked(event -> resetFromError(PercentAgentField));
    }
}
