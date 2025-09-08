package org.curs.AppClient.ScenesControllers.AdminControllers;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<AppCache.AdminContract> TableBox;

    @FXML
    public void initialize() {
        super.initialize();
        LOGGER.info("Initializing from AdminContract");
        AdminAddButton.setDisable(true);
        List<AppCache.AdminContract> contracts = AppCache.getContracts();

        TableColumn<AppCache.AdminContract,Integer> tableColumnContractID = new TableColumn<>("ID Договора");
        TableColumn<AppCache.AdminContract,Integer> tableColumnAgentID = new TableColumn<>("ID Агента");
        TableColumn<AppCache.AdminContract,Integer> tableColumnCustomerID = new TableColumn<>("ID Заказчика");
        TableColumn<AppCache.AdminContract,Double> tableColumnPrice= new TableColumn<>("Стоимость");


        tableColumnContractID.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().contractId()));
        tableColumnAgentID.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().agentId()));
        tableColumnCustomerID.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().customerId()));
        tableColumnPrice.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().price()));

        TableBox.getColumns().addAll(tableColumnContractID,tableColumnAgentID,tableColumnCustomerID,tableColumnPrice);
        ObservableList<AppCache.AdminContract> observableList = FXCollections.observableList(contracts);
        TableBox.setItems(observableList);
    }

}
