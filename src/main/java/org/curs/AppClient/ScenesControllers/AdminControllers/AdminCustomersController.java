package org.curs.AppClient.ScenesControllers.AdminControllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.curs.AppClient.AppCache;
import org.curs.AppClient.JavaFXApp;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AbstractController;
import org.curs.AppClient.ScenesControllers.DialogControllers.AddCustomerController;
import org.curs.AppClient.ScenesControllers.SceneManager;

import java.util.List;
import java.util.logging.Logger;

public class AdminCustomersController extends AbstractController {
    private final static Logger LOGGER = Logger.getLogger(AdminContractController.class.getName());

    @FXML
    private TableView<AppCache.CustomersResponse> TableBox;

    @FXML
    public void initialize() {
        super.initialize();
        List<AppCache.CustomersResponse> customersResponses = AppCache.getCustomersResponses();
        this.addTable(customersResponses, TableBox);
        AdminAddButton.setOnAction(event -> {
            SceneManager.showDialog("AddCustomer", JavaFXApp.getPrimaryStage(), new AddCustomerController());
        });

    }

    private void addTable(List<AppCache.CustomersResponse> customers, TableView<AppCache.CustomersResponse> TableBox) {
        TableColumn<AppCache.CustomersResponse, Integer> tableColumnCustomerId = new TableColumn<>("ID Заказчика");
        TableColumn<AppCache.CustomersResponse, String> tableColumnCustomerIban = new TableColumn<>("Банковские реквизиты");
        TableColumn<AppCache.CustomersResponse, String> tableColumnCustomerPhone = new TableColumn<>("Номер телефона");
        TableColumn<AppCache.CustomersResponse, String> tableColumnCustomerContactPerson = new TableColumn<>("Контактное лицо");
        TableColumn<AppCache.CustomersResponse, Integer> tableColumnCustomerAmountOfContracts = new TableColumn<>("Количество контрактов");
        TableColumn<AppCache.CustomersResponse, Double> tableColumnCustomerPriceOfContracts = new TableColumn<>("Стоимость всех контрактов");

        tableColumnCustomerId.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().customerId()));
        tableColumnCustomerIban.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().iban()));
        tableColumnCustomerPhone.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().phone()));
        tableColumnCustomerContactPerson.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().contactPerson()));
        tableColumnCustomerAmountOfContracts.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().amountOfContracts()));
        tableColumnCustomerPriceOfContracts.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().priceOfContracts()));

        TableBox.getColumns().addAll(tableColumnCustomerId, tableColumnCustomerIban, tableColumnCustomerPhone, tableColumnCustomerContactPerson, tableColumnCustomerAmountOfContracts, tableColumnCustomerPriceOfContracts);
        ObservableList<AppCache.CustomersResponse> list = FXCollections.observableArrayList(customers);
        TableBox.setItems(list);
    }
}