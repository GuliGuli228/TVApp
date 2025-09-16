package org.curs.AppClient.ScenesControllers.CommonControllers;

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

public class PlaybackController extends AbstractController {
    @FXML
    private TableView<AppCache.PlaybackResponse> TableBox;

    @FXML
    public void initialize() {
        super.initialize();
        List<AppCache.PlaybackResponse> playbackResponses= AppCache.getPlaybackResponses();
        System.out.println("playbackResponses: " + playbackResponses);
        this.addTable(playbackResponses, TableBox);
        if (AppCache.getRole().equals("Agent")) AgentAddButton.setDisable(true);
        if(AppCache.getRole().equals("Admin")) AdminAddButton.setDisable(true);


    }
    private void addTable(List<AppCache.PlaybackResponse> playbackResponses, TableView<AppCache.PlaybackResponse> TableBox) {
        TableColumn<AppCache.PlaybackResponse, Integer> tableColumnPlaybackId = new TableColumn<>("ID Показа");
        TableColumn<AppCache.PlaybackResponse, Integer> tableColumnContractId = new TableColumn<>("ID Договора");
        TableColumn<AppCache.PlaybackResponse, Integer> tableColumnPromoId = new TableColumn<>("ID Ролика");
        TableColumn<AppCache.PlaybackResponse, String> tableColumTelecastName = new TableColumn<>("Название передачи");
        TableColumn<AppCache.PlaybackResponse, String> tableColumnPlaybackTime = new TableColumn<>("Время показа");
        TableColumn<AppCache.PlaybackResponse, String> tableColumnPlaybackDate = new TableColumn<>("Дата показа");
        TableColumn<AppCache.PlaybackResponse, Double> tableColumnPrice = new TableColumn<>("Стоимость");

        tableColumnPlaybackId.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().playbackId()));
        tableColumnContractId.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().contractId()));
        tableColumnPromoId.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().promoId()));
        tableColumTelecastName.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().telecastName()));
        tableColumnPlaybackTime.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().playbackTime()));
        tableColumnPlaybackDate.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().playbackDate()));
        tableColumnPrice.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().price()));

        TableBox.getColumns().addAll(tableColumnPlaybackId, tableColumnContractId, tableColumnPromoId, tableColumTelecastName, tableColumnPlaybackTime, tableColumnPlaybackDate, tableColumnPrice);
        ObservableList<AppCache.PlaybackResponse> observableList = FXCollections.observableList(playbackResponses);
        TableBox.setItems(observableList);
    }
}
