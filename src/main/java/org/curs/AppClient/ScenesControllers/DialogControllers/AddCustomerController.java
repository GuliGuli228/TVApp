package org.curs.AppClient.ScenesControllers.DialogControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AbstractDialogController;
import org.curs.AppClient.ScenesControllers.SceneManager;

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
    public void initialize() {
        CancelDialogButton.setOnAction(SceneManager::closeDialog);

        Map<TextInputControl, String> textFieldRegexMap = new HashMap<>();
        /* +712345678 or +7-123-456-78-90 or +7 123 456 78 90 or 8912345678 or 8-123-456-78-90 or 8 123 456 78 90 */
        textFieldRegexMap.put(CustomerPhoneNumber,"(\\+7|8)(\\d{8}|(-\\d{3}){2}(-\\d{2}){2}|(\\s\\d{3}){2}(\\s\\d{2}){2})" );
        textFieldRegexMap.put(CustomerIbanField, "\\d{15,34}");
        /*One, Two or Tree words(Latin and Cyrillic)*/
        textFieldRegexMap.put(CustomerContactPersonField, "[\\p{L}]+(\\s[\\p{L}]+){0,2}");

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
        resetFromError(CustomerPhoneNumber);
        resetFromError(CustomerIbanField);
        resetFromError(CustomerContactPersonField);

    }
}
