package org.curs.AppClient.ScenesControllers.AdminControllers;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.curs.AppClient.AppCache;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AdminAbstractController;

import java.util.List;
import java.util.logging.Logger;

public class AdminCustomersController extends AdminAbstractController {
    private final static Logger LOGGER = Logger.getLogger(AdminContractController.class.getName());

    @FXML
    private TableView<AppCache.AdminCustomersResponse> TableBox;

    @FXML
    public void initialize() {
        super.initialize();
        List<AppCache.AdminCustomersResponse> customersResponses = AppCache.getAdminCustomersResponses();

        TableColumn<AppCache.AdminCustomersResponse, Integer> tableColumnCustomerId = new TableColumn<>("ID Заказчика");
        TableColumn<AppCache.AdminCustomersResponse, String> tableColumnCustomerIban = new TableColumn<>("Банковские реквизиты");
        TableColumn<AppCache.AdminCustomersResponse, String> tableColumnCustomerPhone = new TableColumn<>("Номер телефона");
        TableColumn<AppCache.AdminCustomersResponse, String> tableColumnCustomerContactPerson = new TableColumn<>("Контактное лицо");
        TableColumn<AppCache.AdminCustomersResponse, Integer> tableColumnCustomerAmountOfContracts = new TableColumn<>("Количество контрактов");
        TableColumn<AppCache.AdminCustomersResponse, Double> tableColumnCustomerPriceOfContracts = new TableColumn<>("Стоимость всех контрактов");

        tableColumnCustomerId.setCellValueFactory(cell-> new ReadOnlyObjectWrapper<>(cell.getValue().customerId()));
        tableColumnCustomerIban.setCellValueFactory(cell-> new ReadOnlyObjectWrapper<>(cell.getValue().iban()));
        tableColumnCustomerPhone.setCellValueFactory(cell-> new ReadOnlyObjectWrapper<>(cell.getValue().phone()));
        tableColumnCustomerContactPerson.setCellValueFactory(cell-> new ReadOnlyObjectWrapper<>(cell.getValue().contactPerson()));
        tableColumnCustomerAmountOfContracts.setCellValueFactory(cell-> new ReadOnlyObjectWrapper<>(cell.getValue().amountOfContracts()));
        tableColumnCustomerPriceOfContracts.setCellValueFactory(cell-> new ReadOnlyObjectWrapper<>(cell.getValue().priceOfContracts()));

        TableBox.getColumns().addAll(tableColumnCustomerId,tableColumnCustomerIban, tableColumnCustomerPhone,tableColumnCustomerContactPerson,tableColumnCustomerAmountOfContracts,tableColumnCustomerPriceOfContracts );
        ObservableList<AppCache.AdminCustomersResponse> list = FXCollections.observableArrayList(customersResponses);
        TableBox.setItems(list);
    }
}
