package org.curs.AppClient.ScenesControllers.AbstractControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

import java.util.Map;

public abstract class AbstractDialogController {
    @FXML
    protected Button AddDialogButton;
    @FXML
    protected Button CancelDialogButton;

    protected void validate(Map<TextInputControl, String> tableFormats) {
        if (!isValid(tableFormats)){
            for (Map.Entry<TextInputControl, String> entry : tableFormats.entrySet()){
                validate(entry.getKey(), entry.getValue());
            }
        }
    }

    protected boolean isValid(Map<TextInputControl, String> tableFormats) {
        for (Map.Entry<TextInputControl, String> entry : tableFormats.entrySet()) {
            if(!isValid(entry.getKey(), entry.getValue())) return false;
        }
        return true;
    }

    protected void resetFromError(Map<TextInputControl, String> tableFormats){
        for (Map.Entry<TextInputControl, String> entry : tableFormats.entrySet()){
            resetFromError(entry.getKey());
        }
    }


    protected void validate (TextInputControl field, String format) {
        if(!isValid(field, format)) {
            if(field.getText().isEmpty()) field.setText("Обязательное поле");
            if(!field.getText().matches(format)) field.setText("Неверный формат");
            field.setStyle("-fx-text-fill: red;");
        }
    }
    protected boolean isValid (TextInputControl field, String format) {
        if (field.getText().isEmpty() || !field.getText().matches(format)) return false;
        return true;
    }


    protected void resetFromError(TextInputControl field){
        if(field.getText().equals("Обязательное поле") || field.getText().equals("Неверный формат")){
            field.clear();
            field.setStyle("-fx-text-fill: black");
        }
    }

}
