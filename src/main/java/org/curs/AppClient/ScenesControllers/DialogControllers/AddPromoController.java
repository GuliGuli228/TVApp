package org.curs.AppClient.ScenesControllers.DialogControllers;

import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.curs.AppClient.Enums.ApiPaths;
import org.curs.AppClient.Enums.ApiRequests;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AbstractDialogController;
import org.curs.AppClient.ScenesControllers.SceneManager;
import org.curs.AppClient.Utils.ApiUtil;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class AddPromoController extends AbstractDialogController {

    @FXML
    private TextArea URLPromoField;
    @FXML
    private TextField CustomerIdField;
    @FXML
    private ComboBox<String> Hours;
    @FXML
    private ComboBox<String> Minutes;
    @FXML
    private ComboBox<String> Seconds;

    @FXML
    public void initialize(){
        CancelDialogButton.setOnAction(SceneManager::closeDialog);

        Map<TextInputControl,String> textFieldRegexMap  = new HashMap<>();

        textFieldRegexMap.put(URLPromoField, "[A-Za-z0-9\\-_.~]+");
        textFieldRegexMap.put(CustomerIdField, "\\d+");

        int[] numbers = IntStream.rangeClosed(0, 60).toArray();

        Hours.getItems().addAll(Arrays.stream(numbers).mapToObj(String::valueOf).toArray(String[]::new));
        Minutes.getItems().addAll(Arrays.stream(numbers).mapToObj(String::valueOf).toArray(String[]::new));
        Seconds.getItems().addAll(Arrays.stream(numbers).mapToObj(String::valueOf).toArray(String[]::new));

        AddDialogButton.setOnAction(event -> {
            validate(textFieldRegexMap);

            int  hours = Integer.parseInt(Hours.getSelectionModel().getSelectedItem());
            int  minute = Integer.parseInt(Minutes.getSelectionModel().getSelectedItem());
            int  second = Integer.parseInt(Seconds.getSelectionModel().getSelectedItem());

            if (isValid(textFieldRegexMap)) {
                try {
                    Duration duration = Duration.ofHours(hours).plusMinutes(minute).plusSeconds(second);
                    JsonObject promoData = new  JsonObject();
                    promoData.addProperty("promoUrl", URLPromoField.getText());
                    promoData.addProperty("customer_id", CustomerIdField.getText());
                    promoData.addProperty("duration", duration.toString());

                    ApiUtil.bodyRequest(ApiPaths.POST_PROMO, ApiRequests.POST, promoData);

                }catch (IOException e){
                    e.printStackTrace();
                }
                SceneManager.closeDialog(event);
            }
        });

        URLPromoField.setOnMouseClicked(event -> resetFromError(URLPromoField));
        CustomerIdField.setOnMouseClicked(event -> resetFromError(CustomerIdField));
    }
}
