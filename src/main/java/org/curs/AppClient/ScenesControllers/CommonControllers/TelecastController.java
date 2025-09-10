package org.curs.AppClient.ScenesControllers.CommonControllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.curs.AppClient.AppCache;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AbstractController;

import java.util.List;

public class TelecastController extends AbstractController {

    @FXML
    private TableView<AppCache.TelecastResponse> TableBox;

    @FXML
    public void initialize() {
        super.initialize();
        List<AppCache.TelecastResponse> telecastResponses = AppCache.getTelecastResponses();

        System.out.println("telecastResponses: " + telecastResponses);
        TableColumn<AppCache.TelecastResponse, Integer> tableColumnTelecastId = new TableColumn<>("ID Передачи");
        TableColumn<AppCache.TelecastResponse, String> tableColumnTelecastName = new TableColumn<>("Название передачи");
        TableColumn<AppCache.TelecastResponse, Double> tableColumnTelecastRating = new TableColumn<>("Рэйтинг передачи");
        TableColumn<AppCache.TelecastResponse, Double> tableColumnTelecastMinuteCost = new TableColumn<>("Стоимость минуты");

        tableColumnTelecastId.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().id()));
        tableColumnTelecastName.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().telecastName()));
        tableColumnTelecastRating.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().rating()));
        tableColumnTelecastMinuteCost.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().minuteCost()));

        TableBox.getColumns().addAll(tableColumnTelecastId,tableColumnTelecastName,tableColumnTelecastRating,tableColumnTelecastMinuteCost);
        ObservableList<AppCache.TelecastResponse> responses = FXCollections.observableList(telecastResponses);
        TableBox.setItems(responses);

    }
}
