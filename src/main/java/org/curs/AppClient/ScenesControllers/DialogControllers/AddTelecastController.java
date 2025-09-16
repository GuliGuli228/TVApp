package org.curs.AppClient.ScenesControllers.DialogControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AbstractDialogController;
import org.curs.AppClient.ScenesControllers.SceneManager;

import java.util.HashMap;
import java.util.Map;

public class AddTelecastController extends AbstractDialogController {

    @FXML
    private TextField TelecastNameField;
    @FXML
    private TextField TelecastRatingField;
    @FXML
    private TextField TelecastMinutePrice;

    @FXML
    public void initialize() {
        CancelDialogButton.setOnAction(SceneManager::closeDialog);
        Map<TextInputControl,String> textFieldRegexMap = new HashMap<>();

        textFieldRegexMap.put(TelecastNameField, "[^~\\+\\$@\\*\\(\\)]+");
        textFieldRegexMap.put(TelecastRatingField, "\\d{2}.\\d{2}");
        textFieldRegexMap.put(TelecastMinutePrice, "\\d*.\\d{2}");

        AddDialogButton.setOnAction(event -> {
            validate(textFieldRegexMap);
            if(isValid(textFieldRegexMap)) SceneManager.closeDialog(event);
        });

        TelecastNameField.setOnMouseClicked(event -> resetFromError(TelecastNameField));
        TelecastRatingField.setOnMouseClicked(event -> resetFromError(TelecastRatingField));
        TelecastMinutePrice.setOnMouseClicked(event -> resetFromError(TelecastMinutePrice));
    }
}
