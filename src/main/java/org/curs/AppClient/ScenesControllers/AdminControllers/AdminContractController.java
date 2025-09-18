package org.curs.AppClient.ScenesControllers.AdminControllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.curs.AppClient.AppCache;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AbstractController;


import java.util.List;
import java.util.logging.Logger;

public class AdminContractController extends AbstractController {

    private final static Logger LOGGER = Logger.getLogger(AdminContractController.class.getName());


    @FXML
    private TableView<AppCache.AdminContract> TableBox;

    @FXML
    public void initialize() {
        super.initialize();
        LOGGER.info("Initializing from AdminContract");
        AdminAddButton.setDisable(true);
        AdminUpdateButton.setDisable(true);
        List<AppCache.AdminContract> contracts = AppCache.getAdminContracts();
        this.addTable(contracts, TableBox);

    }

    private void addTable(List<AppCache.AdminContract> contracts, TableView<AppCache.AdminContract> TableBox) {

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
