package org.curs.AppClient.ScenesControllers.DialogControllers;

import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
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

public class AddCustomerController extends AbstractDialogController {

    @FXML
    private TextField CustomerPhoneNumber;
    @FXML
    private TextArea CustomerIbanField;
    @FXML
    private TextField CustomerContactPersonField;
    @FXML
    private TextField CustomerName;

    @FXML
    public void initialize() {
        CancelDialogButton.setOnAction(SceneManager::closeDialog);

        Map<TextInputControl, String> textFieldRegexMap = new HashMap<>();
        //FIXME почему то валидацию проходит только 8 916 241 30 81
        /* +712345678 or +7-123-456-78-90 or +7 123 456 78 90 or 8912345678 or 8-123-456-78-90 or 8 123 456 78 90 */
        textFieldRegexMap.put(CustomerPhoneNumber,"(\\+7|8)(\\d{8}|(-\\d{3}){2}(-\\d{2}){2}|(\\s\\d{3}){2}(\\s\\d{2}){2})" );
        textFieldRegexMap.put(CustomerIbanField, "\\d{15,34}");
        /*One, Two or Tree words(Latin and Cyrillic)*/
        textFieldRegexMap.put(CustomerContactPersonField, "[\\p{L}]+(\\s[\\p{L}]+){0,2}");
        textFieldRegexMap.put(CustomerName, "\\w+");

        AddDialogButton.setOnAction(event -> {
            validate(textFieldRegexMap);
            if (isValid(textFieldRegexMap)) {
                try {
                    JsonObject customerData = new JsonObject();
                    customerData.addProperty("phone", CustomerPhoneNumber.getText());
                    customerData.addProperty("contactPerson", CustomerContactPersonField.getText());
                    customerData.addProperty("iban", CustomerIbanField.getText());
                    customerData.addProperty("customerName", CustomerName.getText());

                    ApiUtil.bodyRequest(ApiPaths.POST_CUSTOMER, ApiRequests.POST, customerData);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                SceneManager.closeDialog(event);
            }
        });
        CustomerPhoneNumber.setOnMouseClicked(event -> {resetFromError(CustomerPhoneNumber);});
        CustomerContactPersonField.setOnMouseClicked(event -> {resetFromError(CustomerContactPersonField);});
        CustomerIbanField.setOnMouseClicked(event -> {resetFromError(CustomerIbanField);});
        CustomerName.setOnMouseClicked(event -> {resetFromError(CustomerName);});
    }
}
