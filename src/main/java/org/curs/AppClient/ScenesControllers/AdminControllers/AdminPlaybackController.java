package org.curs.AppClient.ScenesControllers.AdminControllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.curs.AppClient.AppCache;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AdminAbstractController;

import java.util.List;

public class AdminPlaybackController extends AdminAbstractController {

    @FXML
    private TableView<AppCache.AdminPlaybackResponse> TableBox;

    @FXML
    public void initialize() {
        super.initialize();
        List<AppCache.AdminPlaybackResponse> playbackResponses= AppCache.getAdminPlaybackResponses();

        TableColumn<AppCache.AdminPlaybackResponse,Integer> tableColumnPlaybackId = new TableColumn<>("ID Показа");
        TableColumn<AppCache.AdminPlaybackResponse,Integer> tableColumnContractId = new TableColumn<>("ID Договора");
        TableColumn<AppCache.AdminPlaybackResponse,Integer> tableColumnPromoId = new TableColumn<>("ID Ролика");
        TableColumn<AppCache.AdminPlaybackResponse,String> tableColumTelecastName = new TableColumn<>("Название передачи");
        TableColumn<AppCache.AdminPlaybackResponse,String> tableColumnPlaybackTime = new TableColumn<>("Время показа");
        TableColumn<AppCache.AdminPlaybackResponse,String> tableColumnPlaybackDate = new TableColumn<>("Дата показа");
        TableColumn<AppCache.AdminPlaybackResponse,Double> tableColumnPrice = new TableColumn<>("Стоимость");

        tableColumnPlaybackId.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().playbackId()));
        tableColumnContractId.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().contractId()));
        tableColumnPromoId.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().promoId()));
        tableColumTelecastName.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().telecastName()));
        tableColumnPlaybackTime.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().playbackTime()));
        tableColumnPlaybackDate.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().playbackDate()));
        tableColumnPrice.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().price()));

        TableBox.getColumns().addAll(tableColumnPlaybackId,tableColumnContractId,tableColumnPromoId,tableColumTelecastName,tableColumnPlaybackTime,tableColumnPlaybackDate,tableColumnPrice);
        ObservableList<AppCache.AdminPlaybackResponse> observableList = FXCollections.observableList(playbackResponses);
        TableBox.setItems(observableList);
    }
}
