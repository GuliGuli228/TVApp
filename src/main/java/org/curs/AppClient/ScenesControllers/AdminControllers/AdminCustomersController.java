package org.curs.AppClient.ScenesControllers.AdminControllers;

import javafx.fxml.FXML;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AdminAbstractController;

import java.util.logging.Logger;

public class AdminCustomersController extends AdminAbstractController {
    private final static Logger LOGGER = Logger.getLogger(AdminContractController.class.getName());
    @FXML
    public void initialize() {
        super.initialize();
        LOGGER.info("Initializing from AdminCustomersController");
        TextLable.setText("Customers");
    }
}
