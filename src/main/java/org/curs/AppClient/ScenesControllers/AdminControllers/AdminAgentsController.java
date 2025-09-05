package org.curs.AppClient.ScenesControllers.AdminControllers;


import javafx.fxml.FXML;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AdminAbstractController;


public class AdminAgentsController extends AdminAbstractController {

    @FXML
    public void initialize() {
        super.initialize();
        TextLable.setText("Agents");
    }
}
