package org.curs.AppClient.ScenesControllers.DialogControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AbstractDialogController;
import org.curs.AppClient.ScenesControllers.SceneManager;

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
    private ComboBox<Integer> Hours;
    @FXML
    private ComboBox<Integer> Minutes;
    @FXML
    private ComboBox<Integer> Seconds;

    @FXML
    public void initialize(){
        CancelDialogButton.setOnAction(SceneManager::closeDialog);

        Map<TextInputControl,String> textFieldRegexMap  = new HashMap<>();

        textFieldRegexMap.put(URLPromoField, "[A-Za-z0-9\\-_.~]+");
        textFieldRegexMap.put(CustomerIdField, "\\d+");

        int[] numbers = IntStream.rangeClosed(0, 60).toArray();

        Hours.getItems().addAll(Arrays.stream(numbers).boxed().toArray(Integer[]::new));
        Minutes.getItems().addAll(Arrays.stream(numbers).boxed().toArray(Integer[]::new));
        Seconds.getItems().addAll(Arrays.stream(numbers).boxed().toArray(Integer[]::new));

        AddDialogButton.setOnAction(event -> {
            validate(textFieldRegexMap);

            int  hours = Hours.getSelectionModel().getSelectedItem();
            int  minute = Minutes.getSelectionModel().getSelectedItem();
            int  second = Seconds.getSelectionModel().getSelectedItem();

            if (isValid(textFieldRegexMap)) SceneManager.closeDialog(event);
        });

        URLPromoField.setOnMouseClicked(event -> resetFromError(URLPromoField));
        CustomerIdField.setOnMouseClicked(event -> resetFromError(CustomerIdField));
    }
}
