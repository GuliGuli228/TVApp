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

import java.time.Duration;
import java.util.List;

public class PromoController extends AbstractController {

    @FXML
    private TableView<AppCache.PromoResponse> TableBox;

    @FXML
    public void initialize() {
        super.initialize();
        List<AppCache.PromoResponse> promoResponses =  AppCache.getPromoResponses();
        if(!promoResponses.isEmpty()){
            System.out.println(promoResponses);
        }
        this.addTable(promoResponses, TableBox);
        if(AppCache.getRole().equals("Agent")) {
            AgentAddButton.setDisable(true);
            AgentUpdateButton.setDisable(true);
            AgentDeleteButton.setDisable(true);
        }
        if(AppCache.getRole().equals("Admin")) AdminAddButton.setOnAction(event -> {
            SceneManager.showDialog(Dialogs.ADD_PROMO, JavaFXApp.getPrimaryStage());
        });
    }

    private void addTable(List<AppCache.PromoResponse> promoResponses, TableView<AppCache.PromoResponse> TableBox) {
        TableColumn<AppCache.PromoResponse, Integer> tableColumnPromoId = new TableColumn<>("ID Ролика");
        TableColumn<AppCache.PromoResponse, Integer> tableColumnCustomerId = new TableColumn<>("ID Заказчика");
        TableColumn<AppCache.PromoResponse, String> tableColumnDuration = new TableColumn<>("Продолжительность");
        TableColumn<AppCache.PromoResponse, String> tableColumnUrl= new TableColumn<>("URL Ролика");


        tableColumnPromoId.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().promoId()));
        tableColumnCustomerId.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().customerId()));
        tableColumnDuration.setCellValueFactory(cell -> {
            Duration duration = Duration.parse(cell.getValue().duration());
            String formatted = String.format("%02d:%02d:%02d",
                    duration.toHours(),
                    duration.toMinutesPart(),
                    duration.toSecondsPart());
            return new ReadOnlyObjectWrapper<>(formatted);
        });
        tableColumnUrl.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().promoUrl()));

        tableColumnUrl.setMaxWidth(700);

        TableBox.getColumns().addAll(tableColumnPromoId, tableColumnCustomerId, tableColumnDuration, tableColumnUrl);
        ObservableList<AppCache.PromoResponse> responses = FXCollections.observableList(promoResponses);
        TableBox.setItems(responses);
    }


}
