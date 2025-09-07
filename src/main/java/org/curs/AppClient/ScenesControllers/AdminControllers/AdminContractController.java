package org.curs.AppClient.ScenesControllers.AdminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.SneakyThrows;
import org.curs.AppClient.AppCache;
import org.curs.AppClient.ScenesControllers.*;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AdminAbstractController;
import org.curs.AppClient.ScenesControllers.ComponentsControllers.AdminContractElementController;


import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static org.curs.AppClient.AppCache.getContracts;

public class AdminContractController extends AdminAbstractController {

    private final static Logger LOGGER = Logger.getLogger(AdminContractController.class.getName());

    @FXML
    private VBox ContentVbox;
    @FXML
    public void initialize() {
        super.initialize();
        LOGGER.info("Initializing from AdminContract");
        AdminAddButton.setDisable(true);
        List<AppCache.AdminContract> contracts = AppCache.getContracts();
        if (contracts == null) {
            System.out.println("no contracts");
        }
        for (AppCache.AdminContract contract : contracts) {
            try {
                String contractId = contract.contractId().toString();
                String agentId = contract.agentId().toString();
                String customerId = contract.customerId().toString();
                String price = contract.price().toString();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLScenes/AdminContractElement.fxml"));
                HBox row = loader.load();
                Node separator = new Separator(Orientation.HORIZONTAL);
                AdminContractElementController controller = loader.getController();
                controller.setData(contractId, agentId, customerId, price);
                ContentVbox.getChildren().add(row);
                ContentVbox.getChildren().add(separator);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
