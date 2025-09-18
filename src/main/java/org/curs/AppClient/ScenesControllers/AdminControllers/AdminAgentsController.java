package org.curs.AppClient.ScenesControllers.AdminControllers;


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


public class AdminAgentsController extends AbstractController {

    @FXML
    private TableView<AppCache.AdminAgentResponse> TableBox;

    @FXML
    public void initialize() {
        super.initialize();
        List<AppCache.AdminAgentResponse> adminAgentResponses = AppCache.getAdminAgentResponses();
        this.addTable(adminAgentResponses, TableBox);
        AdminAddButton.setOnAction(event -> {
            SceneManager.showDialog(Dialogs.ADD_AGENT, JavaFXApp.getPrimaryStage());
        });
        AdminUpdateButton.setOnAction(event -> {});
    }

    private void addTable(List<AppCache.AdminAgentResponse> adminAgentResponse, TableView<AppCache.AdminAgentResponse> TableBox) {
        TableColumn<AppCache.AdminAgentResponse,Integer> tableColumnAgentId = new TableColumn<>("ID Агента");
        TableColumn<AppCache.AdminAgentResponse,String> tableColumnAgentLogin = new TableColumn<>("ФИО агента");
        TableColumn<AppCache.AdminAgentResponse,Double> tableColumnAgentPercent = new TableColumn<>("Ставка");
        TableColumn<AppCache.AdminAgentResponse,Double> tableColumnAgentIncome = new TableColumn<>("Прибыль");
        TableColumn<AppCache.AdminAgentResponse,Integer> tableColumnAgentAmountContracts = new TableColumn<>("Количество договоров");

        tableColumnAgentId.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().agentId()));
        tableColumnAgentLogin.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().agentLogin()));
        tableColumnAgentPercent.setCellValueFactory(cell-> new ReadOnlyObjectWrapper<>(cell.getValue().percent()));
        tableColumnAgentIncome.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().income()));
        tableColumnAgentAmountContracts.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().amountOfContracts()));

        TableBox.getColumns().addAll(tableColumnAgentId,tableColumnAgentLogin,tableColumnAgentPercent,tableColumnAgentIncome,tableColumnAgentAmountContracts);
        ObservableList<AppCache.AdminAgentResponse> list = FXCollections.observableList(adminAgentResponse);
        TableBox.setItems(list);
    }
}
