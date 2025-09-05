package org.curs.AppClient.ScenesControllers.AdminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import lombok.SneakyThrows;
import org.curs.AppClient.ScenesControllers.*;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AdminAbstractController;


import java.io.IOException;
import java.util.logging.Logger;

public class AdminContractController extends AdminAbstractController {

    private final static Logger LOGGER = Logger.getLogger(AdminContractController.class.getName());
    @FXML
    public void initialize() {
        super.initialize();
        LOGGER.info("Initializing from AdminContract");
        AdminAddButton.setDisable(true);
        TextLable.setText("Contracts");
    }

}
