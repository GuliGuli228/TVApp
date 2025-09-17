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
        textFieldRegexMap.put(TelecastRatingField, "\\d{1}.\\d{2}");
        textFieldRegexMap.put(TelecastMinutePrice, "\\d+.\\d{2}");

        AddDialogButton.setOnAction(event -> {
            validate(textFieldRegexMap);
            if(isValid(textFieldRegexMap)){

                try {
                    JsonObject telecastData = new JsonObject();
                    telecastData.addProperty("telecastName", TelecastNameField.getText());
                    telecastData.addProperty("rating", TelecastRatingField.getText());
                    telecastData.addProperty("minuteCost", TelecastMinutePrice.getText());

                    ApiUtil.bodyRequest(ApiPaths.POST_TELECAST, ApiRequests.POST,telecastData );

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                SceneManager.closeDialog(event);
            }
        });

        TelecastNameField.setOnMouseClicked(event -> resetFromError(TelecastNameField));
        TelecastRatingField.setOnMouseClicked(event -> resetFromError(TelecastRatingField));
        TelecastMinutePrice.setOnMouseClicked(event -> resetFromError(TelecastMinutePrice));
    }
}
