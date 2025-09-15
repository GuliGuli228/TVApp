package org.curs.AppClient.ScenesControllers.DialogControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.curs.AppClient.ScenesControllers.SceneManager;

public class AddAgentController {

    @FXML
    private Button CancelButton;

    @FXML
    public void initialize() {
        CancelButton.setOnAction(SceneManager::closeDialog);
    }
}
