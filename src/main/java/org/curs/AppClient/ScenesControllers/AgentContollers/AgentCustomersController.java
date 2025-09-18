package org.curs.AppClient.ScenesControllers.AgentContollers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.curs.AppClient.AppCache;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AbstractController;

import java.util.List;

public class AgentCustomersController extends AbstractController {

    @FXML
    private TableView<AppCache.CustomersResponse> TableBox;

    @FXML
    public void initialize() {
        super.initialize();

        List<AppCache.CustomersResponse> customersResponses = AppCache.getCustomersResponses();
        System.out.println("customersResponses: " + customersResponses);
        this.addTable(customersResponses, TableBox);
        AgentAddButton.setDisable(true);


    }
    private void addTable(List<AppCache.CustomersResponse> customersResponses, TableView<AppCache.CustomersResponse> TableBox) {
        TableColumn<AppCache.CustomersResponse, Integer> tableColumnCustomerId = new TableColumn<>("ID Заказчика");
        TableColumn<AppCache.CustomersResponse, String> tableColumnCustomerName = new TableColumn<>("Название");
        TableColumn<AppCache.CustomersResponse, String> tableColumnCustomerIban = new TableColumn<>("Банковские реквизиты");
        TableColumn<AppCache.CustomersResponse, String> tableColumnCustomerPhone = new TableColumn<>("Номер телефона");
        TableColumn<AppCache.CustomersResponse, String> tableColumnCustomerContactPerson = new TableColumn<>("Контактное лицо");
        TableColumn<AppCache.CustomersResponse, Integer> tableColumnCustomerAmountOfContracts = new TableColumn<>("Количество контрактов");
        TableColumn<AppCache.CustomersResponse, Double> tableColumnCustomerPriceOfContracts = new TableColumn<>("Стоимость всех контрактов");

        tableColumnCustomerId.setCellValueFactory(cell-> new ReadOnlyObjectWrapper<>(cell.getValue().customerId()));
        tableColumnCustomerName.setCellValueFactory(cell-> new ReadOnlyObjectWrapper<>(cell.getValue().customerName()));
        tableColumnCustomerIban.setCellValueFactory(cell-> new ReadOnlyObjectWrapper<>(cell.getValue().iban()));
        tableColumnCustomerPhone.setCellValueFactory(cell-> new ReadOnlyObjectWrapper<>(cell.getValue().phone()));
        tableColumnCustomerContactPerson.setCellValueFactory(cell-> new ReadOnlyObjectWrapper<>(cell.getValue().contactPerson()));
        tableColumnCustomerAmountOfContracts.setCellValueFactory(cell-> new ReadOnlyObjectWrapper<>(cell.getValue().amountOfContracts()));
        tableColumnCustomerPriceOfContracts.setCellValueFactory(cell-> new ReadOnlyObjectWrapper<>(cell.getValue().priceOfContracts()));

        TableBox.getColumns().addAll(tableColumnCustomerId,tableColumnCustomerName, tableColumnCustomerIban, tableColumnCustomerPhone,tableColumnCustomerContactPerson,tableColumnCustomerAmountOfContracts,tableColumnCustomerPriceOfContracts );
        ObservableList<AppCache.CustomersResponse> list = FXCollections.observableArrayList(customersResponses);
        TableBox.setItems(list);
    }

}
