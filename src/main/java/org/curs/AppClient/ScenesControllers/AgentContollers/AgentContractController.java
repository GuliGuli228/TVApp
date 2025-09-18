package org.curs.AppClient.ScenesControllers.AgentContollers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.curs.AppClient.AppCache;
import org.curs.AppClient.Enums.Dialogs;
import org.curs.AppClient.JavaFXApp;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AbstractController;
import org.curs.AppClient.ScenesControllers.SceneManager;

import java.util.List;

public class AgentContractController extends AbstractController {

    @FXML
    private TableView<AppCache.AgentContractResponse> TableBox;

    @FXML
    public void initialize() {
        super.initialize();
        List<AppCache.AgentContractResponse> agentContractResponses = AppCache.getAgentContractResponses();
        System.out.println("agentContractResponses: " + agentContractResponses);
        this.addTable(agentContractResponses, TableBox);
        AgentUpdateButton.setDisable(true);
        AgentDeleteButton.setDisable(true);
        AgentAddButton.setOnAction(event -> {
            SceneManager.showDialog(Dialogs.ADD_CONTRACT, JavaFXApp.getPrimaryStage());
        });
    }
    private void addTable(List<AppCache.AgentContractResponse> agentContractResponses, TableView<AppCache.AgentContractResponse> TableBox) {
        TableColumn<AppCache.AgentContractResponse, Integer> tableColumnContractId = new TableColumn<>("ID Контракта");
        TableColumn<AppCache.AgentContractResponse, Integer> tableColumnCustomerId = new TableColumn<>("ID Заказчика");
        TableColumn<AppCache.AgentContractResponse, Double> tableColumnPrice = new TableColumn<>("Стоимость");

        tableColumnContractId.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().contractId()));
        tableColumnCustomerId.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().customerId()));
        tableColumnPrice.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().price()));

        TableBox.getColumns().addAll(tableColumnContractId, tableColumnCustomerId, tableColumnPrice);
        ObservableList<AppCache.AgentContractResponse> responses = FXCollections.observableList(agentContractResponses);
        TableBox.setItems(responses);
    }

}
